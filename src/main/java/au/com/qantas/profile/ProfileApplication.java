package au.com.qantas.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableCaching
@ComponentScan(basePackages = {"au.com.qantas.profile"})
public class ProfileApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProfileApplication.class, args);
  }
}
