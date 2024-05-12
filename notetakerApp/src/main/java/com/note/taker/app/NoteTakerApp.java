package com.note.taker.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@ComponentScan("com.note.taker")
public class NoteTakerApp
{
    public static void main( String[] args )
    {
       SpringApplication.run(NoteTakerApp.class, args);
    }
}
