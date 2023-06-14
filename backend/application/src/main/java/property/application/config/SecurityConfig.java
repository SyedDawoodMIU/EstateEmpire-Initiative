package property.application.config;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import property.application.filter.JwtFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

=======
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
<<<<<<< HEAD
    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }
    String[] roles = { "ADMIN","CUSTOMER","AGENT","USER"};
=======
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().cors().and()
                .authorizeHttpRequests()
<<<<<<< HEAD
                .requestMatchers("/api/v1/authenticate/**").permitAll()
                .requestMatchers("/api/v1/users/**").hasAnyAuthority("USER")
                .requestMatchers("/api/v1/users**").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/v1/users/**").hasAnyAuthority(roles)
=======
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
<<<<<<< HEAD
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }
=======
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3

}
