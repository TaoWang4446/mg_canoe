package com.hworld.canoe.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class MybatisConfiguration {
//    private RelaxedPropertyResolver propertyResolver;


    private String driveClassName;
    private String url;
    private String userName;
    private String password;
    private String xmlLocation;
    private String typeAliasesPackage;
    /////////////////////druid参数///////////////////////////////////////////////////
    private String filters;
    private String maxActive;
    private String initialSize;
    private String maxWait;
    private String minIdle;
    private String timeBetweenEvictionRunsMillis;
    private String minEvictableIdleTimeMillis;
    private String validationQuery;
    private String testWhileIdle;
    private String testOnBorrow;
    private String testOnReturn;
    private String poolPreparedStatements;
    private String maxOpenPreparedStatements;
    //////////////////////////////////////////////////////////////////////////

    @Bean
    @Primary
    public DataSource dataSource(ConnectionPropertyConfig connectionPropertyConfig, Environment env) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(connectionPropertyConfig.getUrl());
        druidDataSource.setUsername(connectionPropertyConfig.getUserName());
        druidDataSource.setPassword(connectionPropertyConfig.getPassword());
        druidDataSource.setDriverClassName(StringUtils.isNotBlank(connectionPropertyConfig.getDriverClassName()) ? connectionPropertyConfig.getDriverClassName() : "com.mysql.jdbc.Driver");
        druidDataSource.setMaxActive(StringUtils.isNotBlank(connectionPropertyConfig.getMaxActive()) ? Integer.parseInt(connectionPropertyConfig.getMaxActive()) : 10);
        druidDataSource.setInitialSize(StringUtils.isNotBlank(connectionPropertyConfig.getInitialSize()) ? Integer.parseInt(connectionPropertyConfig.getInitialSize()) : 1);
        druidDataSource.setMaxWait(StringUtils.isNotBlank(connectionPropertyConfig.getMaxActive()) ? Integer.parseInt(connectionPropertyConfig.getMaxActive()) : 60000);
        druidDataSource.setMinIdle(StringUtils.isNotBlank(connectionPropertyConfig.getMinIdle()) ? Integer.parseInt(connectionPropertyConfig.getMinIdle()) : 3);
        druidDataSource.setTimeBetweenEvictionRunsMillis(StringUtils.isNotBlank(connectionPropertyConfig.getTimeBetweenEvictionRunsMillis()) ?
                Integer.parseInt(connectionPropertyConfig.getTimeBetweenEvictionRunsMillis()) : 60000);
        druidDataSource.setMinEvictableIdleTimeMillis(StringUtils.isNotBlank(connectionPropertyConfig.getMinEvictableIdleTimeMillis()) ?
                Integer.parseInt(connectionPropertyConfig.getMinEvictableIdleTimeMillis()) : 300000);
        druidDataSource.setValidationQuery(StringUtils.isNotBlank(connectionPropertyConfig.getValidationQuery()) ? connectionPropertyConfig.getValidationQuery() : "select 'x'");
        druidDataSource.setTestWhileIdle(StringUtils.isNotBlank(connectionPropertyConfig.getTestWhileIdle()) ? Boolean.parseBoolean(connectionPropertyConfig.getTestWhileIdle()) : true);
        druidDataSource.setTestOnBorrow(StringUtils.isNotBlank(connectionPropertyConfig.getTestOnBorrow()) ? Boolean.parseBoolean(connectionPropertyConfig.getTestOnBorrow()) : false);
        druidDataSource.setTestOnReturn(StringUtils.isNotBlank(connectionPropertyConfig.getTestOnReturn()) ? Boolean.parseBoolean(connectionPropertyConfig.getTestOnReturn()) : false);
        druidDataSource.setPoolPreparedStatements(StringUtils.isNotBlank(connectionPropertyConfig.getPoolPreparedStatements()) ? Boolean.parseBoolean(connectionPropertyConfig.getPoolPreparedStatements()) : true);
        druidDataSource.setMaxOpenPreparedStatements(StringUtils.isNotBlank(connectionPropertyConfig.getMaxOpenPreparedStatements()) ? Integer.parseInt(connectionPropertyConfig.getMaxOpenPreparedStatements()) : 20);

        try {
            druidDataSource.setFilters(StringUtils.isNotBlank(connectionPropertyConfig.getFilters()) ? connectionPropertyConfig.getFilters() : "stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        bean.setDataSource(dataSource);
//        if(StringUtils.isNotBlank(typeAliasesPackage)){
        bean.setTypeAliasesPackage("com.hworld.canoe.framework.db.canoedb.entity");
//        }
        //分页插件
      /*  PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);*/
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      /*  Interceptor[] plugins =  new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);*/
        try {
            bean.setMapperLocations(resolver.getResources("classpath:/mybatis-mappers/canoedb/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hworld.canoe.framework.db.canoedb.mapper");
        mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        return mapperScannerConfigurer;
    }


    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
