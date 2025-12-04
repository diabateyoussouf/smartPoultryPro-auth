package com.youssouf.net.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthentificationApplication {
    //  MYSQL_ROOT_PASSWORD=rootpassword
    //   -e MYSQL_DATABASE=authdb \

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationApplication.class, args);
    }

}
