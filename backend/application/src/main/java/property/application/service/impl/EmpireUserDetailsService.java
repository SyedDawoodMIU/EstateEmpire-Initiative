package property.application.service.impl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import property.application.repo.UserRepo;

@Service("userDetailsService")
@Transactional
public class EmpireUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public EmpireUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmail(username);
        var userDetails = new EmpireUserDetails(user);
        return userDetails;
    }

}
