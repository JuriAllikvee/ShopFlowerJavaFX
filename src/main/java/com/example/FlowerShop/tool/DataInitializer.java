package com.example.FlowerShop.tool;

import com.example.FlowerShop.model.AppUser;
import com.example.FlowerShop.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(AppUserService appUserService) {
        return args -> {
            if (appUserService.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setFirstname("Admin");
                admin.setLastname("Admin");
                admin.setUsername("admin");
                admin.setPassword("12345");
                admin.getRoles().add(AppUserService.ROLES.ADMINISTRATOR.toString());
                appUserService.add(admin);
                System.out.println("Администратор успешно зашёл.");
            }
        };
    }
}
