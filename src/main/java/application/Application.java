package application;


import application.service.FileStorageService;
//import application.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "application")
@EnableScheduling
public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Resource
    FileStorageService storageService;

//    @Resource
//    UserService userService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
