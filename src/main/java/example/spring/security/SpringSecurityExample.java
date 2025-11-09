package example.spring.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityExample {
    public static void main(String[] args) {
        // main class
        SpringApplication.run(SpringSecurityExample.class,args);
    }
}
