package com.giggle;

import com.giggle.repositories.user.CustomUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.io.File;

@SpringBootApplication
public class Application {

    public static String IMAGES_DIRECTORY = "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "images";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) ->
                new File(IMAGES_DIRECTORY).mkdir();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:9000")
                        .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS")
                        .allowedHeaders("Origin", "Content-Type", "Authorization", "Accept", "X-Requested-With", "X-XSRF-TOKEN")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Inject
        private CustomUserDetailsService customUserDetailsService;

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests().antMatchers("/**")
                    .permitAll().anyRequest()
                    .authenticated().and()
                    .csrf().disable();
//                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//                    .csrf().csrfTokenRepository(csrfTokenRepository());
        }


        @Bean
        protected CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

        @Override
        protected UserDetailsService userDetailsService() {
            return customUserDetailsService;
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    }

}

