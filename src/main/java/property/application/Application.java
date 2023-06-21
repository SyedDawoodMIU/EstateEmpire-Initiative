package property.application;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import property.application.model.Role;
import property.application.model.User;
import property.application.repo.RoleRepo;
import property.application.repo.UserRepo;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements CommandLineRunner {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) {
        var admin = roleRepo.findByRoleName("ADMIN");
        var customer = roleRepo.findByRoleName("CUSTOMER");
        var owner = roleRepo.findByRoleName("OWNER");

        if (admin.isEmpty()) {
            roleRepo.save(new Role("ADMIN"));
        }
        if (customer.isEmpty()) {
            roleRepo.save(new Role("CUSTOMER"));
        }
        if (owner.isEmpty()) {
            roleRepo.save(new Role("OWNER"));
        }

        var adminUser = userRepo.findByEmail("admin@admin.com");
        if (adminUser.isEmpty()){
            var user = new User();
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder().encode("admin123"));
            var adminRole = roleRepo.findByRoleName("ADMIN").get();
            user.setRoles(Collections.singletonList(adminRole));
            userRepo.save(user);
        }

    }
}
