# 书屋应用

## 介绍
该应用主要是通过爬虫爬取书籍信息


## 技术
### ELK
[ELK入门使用-与springboot集成](https://www.cnblogs.com/woshimrf/p/elk-springboot.html)

[SpringBoot继承LogStash实现日志收集](https://juejin.cn/post/6844903929755467783)

[ELK 处理 Spring Boot 日志，妙！](https://mp.weixin.qq.com/s/i1qgONtrtaqHo0o0TcRFGA)

[Linux搭建 ELK 日志收集系统: filebeat + redis + logstash +elasticsearch + kibana](https://juejin.cn/post/6844904111490465799)

[Kibana使用教程](https://blog.csdn.net/xiaozm1223/article/details/89475003)

[Metricbeat quick start: installation and configuration](https://www.elastic.co/guide/en/beats/metricbeat/7.14/metricbeat-installation-configuration.html)

### druid 监控配置
[SpringBoot配置Druid监控](https://www.cnblogs.com/DFX339/p/12751584.html)

### 统一异常处理
> GlobalExceptionHandler
#### 参考链接
[SpringBoot优雅的全局异常处理](https://www.cnblogs.com/xuwujing/p/10933082.html)

[SpringBoot处理全局统一异常](https://www.cnblogs.com/lgjlife/p/10988439.html)

### 日志相关
> logback-spring.xml
```xml
<logger name="com.moon.bookstore.service.mapper" level="DEBUG"/>
```

> application.yml
```yaml
mybatis-plus:
  # 打印 sql
  configuration:
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
```

[springboot中使用logback打印mybatis的sql日志](https://blog.csdn.net/u012489412/article/details/86691611)

### opencv
#### 基本准备
> 安装 ant，并配置 ANT_HOME

> mvn install:install-file -Dfile=/home/yujt/software/opencv-4.5.3/build/bin/opencv-453.jar -DgroupId=org.opencv -DartifactId=opencv -Dversion=4.5.3 -Dpackaging=jar


#### 参考链接
[Java调用opencv实现图片去水印](https://blog.csdn.net/anhuiwangjj/article/details/114381836)


### 自定义序列化
#### 参考类名
com.moon.bookstore.common.serialize.DecimalSerializer
com.moon.bookstore.common.annotation.DecimalSerializeOpt

#### 参考链接
[SpringBoot接口自定义序列化](https://www.jianshu.com/p/7ad5b5182aca)

[Jackson自定义注解里面如何获取到注解信息](https://www.oschina.net/question/3960451_2288679?sort=time)

### 统一操作日志

#### 参考链接
[Springboot 实现操作日志统一处理](https://blog.csdn.net/wang_jing_jing/article/details/118197625)

[Springboot 实现操作日志统一处理](https://blog.csdn.net/wang_jing_jing/article/details/118197625)

[AOP统一日志打印处理(系统操作日志通用设计)](https://www.cnblogs.com/linjiqin/p/12218751.html)

[统一接口设计及日志管理](https://cloud.tencent.com/developer/article/1394264)

[Spring Expression Language(SpEL)](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions)

[玩转Spring中强大的spel表达式！](https://zhuanlan.zhihu.com/p/174786047)

[由浅入深SpEL表达式注入漏洞](http://rui0.cn/archives/1043)