package property.application.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import property.application.model.User;
import property.application.repo.UserRepo;

@Component
@RequiredArgsConstructor
public class LoggedinUserUtil {

    private final UserRepo userRepo;

    public String getUserEmail(){
        return getAuthentication().getName();
    }

    public User getCurrentUser(){
        return userRepo.findByEmail(getUserEmail()).orElse(null);
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
