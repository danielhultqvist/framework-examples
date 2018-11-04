package se.typedef.frameworks.springmvn.programatically;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import se.typedef.frameworks.springmvn.programatically.controllers.UserController;
import se.typedef.frameworks.springmvn.programatically.infrastructure.SqlUserRepository;

import javax.sql.DataSource;

@SpringBootConfiguration
@EnableAutoConfiguration
public class MvnBeansApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MvnBeansApplication.class);
        springApplication.addInitializers(initialize());
        springApplication.run(args);
    }

    public static ApplicationContextInitializer<GenericApplicationContext> initialize() {
        return applicationContext -> {
            final Environment environment = applicationContext.getEnvironment();
            final DataSource dataSource = database(environment);

            final SqlUserRepository userRepository = new SqlUserRepository(dataSource);
            final UserController userController = new UserController(userRepository);

            applicationContext.registerBean(UserController.class, () -> userController);
        };
    }

    private static DataSource database(final Environment environment) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
        hikariConfig.setMaximumPoolSize(25);
        return new HikariDataSource(hikariConfig);
    }
}
