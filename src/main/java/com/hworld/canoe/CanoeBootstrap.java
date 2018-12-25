package com.hworld.canoe;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication(scanBasePackages = {"com.alicp.jetcache.autoconfigure"})
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = "com.hworld.canoe")
//@MapperScan("com.hworld.canoe.framework.db.canoedb.mapper")
@EnableMethodCache(basePackages = "com.hworld.canoe")
@EnableCreateCacheAnnotation
@EnableSwagger2Doc
public class CanoeBootstrap extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CanoeBootstrap.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CanoeBootstrap.class);
    }
    
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("UTF-8");
	    resolver.setResolveLazily(true);
	    resolver.setMaxInMemorySize(40960);
	    resolver.setMaxUploadSize(50*1024*1024);
	    return resolver;
    }
}
