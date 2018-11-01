package se.typedef.frameworks.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan( basePackages = {"se.typedef.frameworks.springmvc.infrastructure"} )
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
