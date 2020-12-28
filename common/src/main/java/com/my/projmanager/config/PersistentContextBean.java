package com.my.projmanager.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class PersistentContextBean {
    @Bean(name = "entityManagerFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.my");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getAdditionalProperties());
        factoryBean.setAnnotatedPackages("com.my");
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.connection.characterEncoding", "utf8");
        properties.put("hibernate.connection.CharSet", "utf8");
        properties.put("hibernate.connection.useUnicode", "true");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}
