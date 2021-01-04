package com.my.projmanager;

import com.my.projmanager.config.AppBean;
import com.my.projmanager.config.PersistentContextBean;
import com.my.projmanager.config.SwaggerConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.my"})
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableTransactionManagement
@Import({
        AppBean.class,
        SwaggerConfigBean.class,
        PersistentContextBean.class
})
public class ProjtimingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjtimingApplication.class, args);
    }

}
