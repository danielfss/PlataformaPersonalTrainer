package com.compasso.academia;

import com.compasso.academia.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AcademiaApplication {public static void main(String[] args) {SpringApplication.run(AcademiaApplication.class, args);
    }
}
