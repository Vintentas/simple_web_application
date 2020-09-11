package org.example.springtest.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {


//    @Bean(initMethod = "migrate")
//    public Flyway flyway() {
//        Flyway flyway = new Flyway(Flyway.configure());
//        flyway.getConfiguration().setBaselineOnMigrate(true);
//        flyway.setValidateOnMigrate(false);
//        flyway.setDataSource(dataSource());
//        return flyway;
//    }
}
