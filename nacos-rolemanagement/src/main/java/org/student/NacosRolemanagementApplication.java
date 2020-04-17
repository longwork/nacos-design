package org.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@MapperScan("org.student.mapper")
public class NacosRolemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosRolemanagementApplication.class, args);
    }

}
