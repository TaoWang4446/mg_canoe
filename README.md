### 项目结构
```
├─com.hworld.canoe
│                ├─cache----------------缓存 
│                ├─controller----------------MVC Controller 
│                ├─dao----------------数据库操作逻辑
│                ├─domain----------------实体类 
│                ├─framework----------------框架(配置，初始化代码都在此)
│                ├─rest----------------restful api接口
│                ├─service----------------业务层逻辑
│                ├─validation----------------验证

```


### 前端目录结构
```shell
├── java                     // java代码
├── resource                 // 资源文件
│   ├── i18n                 // 国际化资源
│   ├── web                  // 主题 字体等静态资源
│    ├── page                // 页面路径
│  	  ├── error    			 // 异常页面
│	  ├── include    		 // 共通页面
│	  ├── ....*.html    	 // 画面页面
│	 ├── static    			 // 静态资源文件
│	  ├── css    			 // 样式文件
│	  ├── fonts    			 // 字库
│	  ├── img    			 // 图片资源
│	  ├── js    			 // js脚本
│   ├── ...*.yml             // spring boot配置
├── webapp                   // 第三方不打包资源
│    └── WEB-INF              // 富文本
│	 ├── mg-sso-setting.xml  // sso配置
│	 ├── web.xml             // web.xml

```

### 技术选型

#### 后端技术:
技术 | 名称 | 官网
----|------|----
Spring Framework 5 | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
Spring MVC 5 | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Spring Boot 2 | 架构框架  | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
Spring Session | 分布式Session管理  | [http://projects.spring.io/spring-session/](http://projects.spring.io/spring-session/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatis Generator | 代码生成  | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html)
PageHelper | MyBatis物理分页插件  | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
Thymeleaf | 模板引擎  | [http://www.thymeleaf.org/](http://www.thymeleaf.org/)
Redis | 分布式缓存数据库  | [https://redis.io/](https://redis.io/)
Log4J | 日志组件  | [http://logging.apache.org/log4j/1.2/](http://logging.apache.org/log4j/1.2/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
Jenkins | 持续集成工具  | [https://jenkins.io/index.html](https://jenkins.io/index.html)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

#### 前端技术:
技术 | 名称 | 官网
----|------|----
jQuery | 函式库  | [http://jquery.com/](http://jquery.com/)
Layui | 前端框架(大杂烩)  | [http://www.layui.com/](http://www.layui.com/)


## 后端工程启动
### 环境须知
#### 开发工具:
- MariaDB: 数据库
- Tomcat: 应用服务器
- Git: 版本管理
- Nginx: 反向代理服务器
- IntelliJ IDEA: 开发IDE
- PowerDesigner: 建模工具
- Navicat for Premium: 数据库客户端

#### 开发环境：
- Jdk8+
- MariaDB10.*+
- Redis
- Spring Cloud
- RabbitMQ
- Spring Cloud-monitor

#### 生产环境：
- Centos 6.5+


### 框架规范约定

约定优于配置(convention over configuration)，此框架约定了很多编程规范，下面一一列举：

### - dao层不准做check,只做数据库操作
### - check只做在 *Validation  例
```java
public class LevelRuleValidation {
    public static SurveillanceFeatureList check(LevelRuleRequest entity) {
        ParameterRuleValidator prv = new ParameterRuleValidator();
        Object[][] checkArraySingle = new Object[][]{
                //等级名称
                new Object[]{entity.getName(), ParameterRuleValidator.NN, null,
                        "C05025", new Object[]{}, "name", 0},
                //改变类型
                new Object[]{entity.getChangeType(), ParameterRuleValidator.NN, null,
                        "C05026", new Object[]{}, "changeType", 0},
                //原始等级
                new Object[]{entity.getSourceLevelId(), ParameterRuleValidator.NN, null,
                        "C05027", new Object[]{}, "sourceLevelId", 0},
                //目标等级
                new Object[]{entity.getTargetLevelId(), ParameterRuleValidator.NN, null,
                        "C05028", new Object[]{}, "targetLevelId", 0},
                //改变方式
                new Object[]{entity.getLevelMethodId(), ParameterRuleValidator.NN, null,
                        "C05029", new Object[]{}, "levelMethodId", 0},
                //满足条件
                new Object[]{entity.getConditionOperator(), ParameterRuleValidator.NN, null,
                        "C05030", new Object[]{}, "operand", 0},
                new Object[]{entity.getVariableName(), ParameterRuleValidator.NN, null,
                        "C05031", new Object[]{}, "variableName", 0},
                new Object[]{entity.getOperator(), ParameterRuleValidator.NN, null,
                        "C05032", new Object[]{}, "operator", 0},
                new Object[]{entity.getComparisonValue(), ParameterRuleValidator.NN, null,
                        "C05033", new Object[]{}, "comparisonValue", 0},
        };
        SurveillanceFeatureList msg = prv.checkByArray(
                checkArraySingle, true);
        if (msg.size() > 0) {
            return msg;
        }
        return new SurveillanceFeatureList();
    }
}

```

```

- service类，需要在叫名`service`的包下，并以`Service`结尾，继承BaseServiceImpl，如`public class CampaignMethodExecuteMonitorService extends BaseServiceImpl<CampaignMethodExecuteMonitorMapper, CampaignMethodExecuteMonitor>`

- controller类，需要在以`controller`结尾的包下，类名以Controller结尾，如`ActivityCodeController.java`，并继承`BaseMVCController`

- spring task类，需要在叫名`task`的包下，并以`Task`结尾，如`TestTask.java`

- mapper.xml，需要在名叫`mapper`的包下，并以`Mapper.xml`结尾，如`ActOrderArrivalMemberMapper.xml`

- mapper接口，需要在名叫`mapper`的包下，并以`Mapper`结尾，如`public interface ActOrderArrivalMemberMapper extends Mapper<ActOrderArrivalMember>`

- model实体类，需要在名叫`model`的包下，命名规则为数据表转驼峰规则，如`CmsArticle.java`

- spring配置文件，基于Java配置

- 类名：首字母大写驼峰规则；方法名：首字母小写驼峰规则；常量：全大写；变量：首字母小写驼峰规则，尽量非缩写

- 配置文件放到`src/main/resources`目录下

- 静态资源文件放到`src/main/resources/web/static`目录下

- html文件，需要在`/src/main/resources/web/page/`目录下

```

## 演示地址

演示地址： 未部署

## 功能截图
![img](http://dev2git/other/mg_canoe/blob/master/src/test/java/1530770695.png)
![img](http://dev2git/other/mg_canoe/blob/master/src/test/java/1530767585.jpg)

### 基本功能
