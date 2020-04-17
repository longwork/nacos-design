package org.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RefreshScope
public class NacosFeignUserAndRoleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosFeignUserAndRoleManagementApplication.class, args);
    }

}
