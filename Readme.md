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
#### 相关文件
[DecimalSerializer](./bookstore-common/src/main/java/com/moon/bookstore/common/serialize/DecimalSerializer.java)

[DecimalSerializeOpt](./bookstore-common/src/main/java/com/moon/bookstore/common/annotation/DecimalSerializeOpt.java)

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

### excel、word、txt、pdf 文件预览

#### 安装
> cd Apache_OpenOffice_4.1.11_Linux_x86-64_install-deb_zh-CN/zh-CN/DEBS
> sudo dpkg -i *.deb

#### 启动命令
> cd /opt/openoffice4/program

> ./soffice "-accept=socket,host=localhost,port=8100;urp;StarOffice.ServiceManager" -nologo -headless -nofirststartwizard &


#### 参考链接
[手把手教你用 Java 实现word、excel、ppt、txt等办公文件在线预览功能！](https://mp.weixin.qq.com/s/nxrvFyUhQGPPr9EZ42p7hw)

[openoffice 官网](https://www.openoffice.org/)

[PDF.js访问远程服务器报file origin does not match viewer's](https://blogls.com/view/main/article_details?id=5ea81da53bbf27777a7cfae2)

[vue项目中使用pdf.js预览pdf文件](https://blog.csdn.net/shentibeitaokong/article/details/80011900)

[pdf.js实现图片在线预览](https://www.cnblogs.com/love-daodao/p/11072282.html)

### List 中属性校验和对象中 List 中属性校验

#### 参考链接
[Validating Lists in a Spring Controller](https://www.baeldung.com/spring-validate-list-controller)

### springboot 启动使用 banner.txt 显示项目名称

#### 参考链接
[SpringBoot-12-banner自定义](https://baijiahao.baidu.com/s?id=1725819127500897322&wfr=spider&for=pc)

[Spring Boot自定义启动Banner在线生成工具](https://www.bootschool.net/ascii-art)

[IMG2TXT: ASCII Art Made Easy!](https://www.degraeve.com/img2txt.php)

[ASCII Generator](http://www.network-science.de/ascii/)

[Online Spring Boot Banner Generator (with FIGlet Fonts)](https://devops.datenkollektiv.de/banner.txt/index.html)

[SpringBoot源码剖析之自定义Banner](https://www.jianshu.com/p/aad49c1abb43)

### mybatis 拦截器处理公共字段插入
[FieldInjectInterceptor](./bookstore-manager/src/main/java/com/moon/bookstore/config/FieldInjectInterceptor.java)

### 自动生成md的目录
[markdown文件生成目录的方式](https://www.cnblogs.com/ExMan/p/14986547.html)

### dubbo 接口测试方式
#### 编写单元测试类
```java
import com.alibaba.fastjson.JSON;
import com.enn.carbon.trade.client.req.MyCarbonAssetReq;
import com.enn.carbon.trade.client.res.CarbonAssetRes;
import com.enn.carbon.trade.client.service.CarbonAssetDubboService;
import com.enn.carbon.trade.starter.run.ApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.rdfa.framework.biz.ro.RdfaResult;

/**
 * @author yujiangtaoa
 * @date 2022/4/12 上午11:05
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRunner.class)
public class DubboTest {

  @DubboReference
  private CarbonAssetDubboService carbonAssetDubboService;

  @Test
  public void testUserAsset() {
    MyCarbonAssetReq myCarbonAssetReq = new MyCarbonAssetReq();
    myCarbonAssetReq.setTenantId("1468033520249413633");
    myCarbonAssetReq.setUserId("8199956771276783616");
    RdfaResult<CarbonAssetRes> myCarbonAsset = carbonAssetDubboService.getMyCarbonAsset(myCarbonAssetReq);
    log.info(JSON.toJSONString(myCarbonAsset));
  }
}
```

#### 使用 telnet 请求
1. 安装 telnet
> sudo apt-get install telnet

2. 在配置文件中加上允许执行的命令
```yaml
dubbo.provider.telnet = ls,ps,cd,pwd,trace,count,invoke,select,status,log,help,clear,exit,shutdown
```
3. 进入 telnet 中的 dubbo 命令行
> telnet 127.0.0.1 20880

4. 回车进入 dubbo 命令行，[命令参考](https://dubbo.apache.org/zh/docs/references/telnet/)

5. 接口调用参考
```
invoke com.enn.carbon.trade.client.service.CarbonAssetDubboService.getMyCarbonAsset({"tenantId":"1457645554732371970", "userId":"1454435733737545730", "class":"com.enn.carbon.trade.client.req.MyCarbonAssetReq"})
```