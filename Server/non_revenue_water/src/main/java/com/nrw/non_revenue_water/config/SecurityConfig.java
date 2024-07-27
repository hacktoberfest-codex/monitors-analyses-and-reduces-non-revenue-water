package com.nrw.non_revenue_water.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nrw.non_revenue_water.filter.JWTFilter;
import com.nrw.non_revenue_water.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Configuration // indicates that the class has @Bean definition methods.
@RequiredArgsConstructor
public class SecurityConfig {
    private final AccountRepository accountRepository;
    private final JWTFilter jwtFilter;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            var account = accountRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return User.builder()
                    .username(account.getCredential().getAccountEmail())
                    .password(account.getCredential().getAccountPassword())
                    .roles("")
                    .build();
        };
    }

    @Bean // It indicate that a method instantiates, configures, and initializes a new
          // object to be managed by the Spring IoC container.
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(request -> {
            request
                    .requestMatchers("/api/v1/accounts/register", "/api/v1/accounts/create",
                            "/api/v1/accounts/adminregister").permitAll()
                    .requestMatchers("/api/v1/accounts/login", "/api/v1/accounts/adminlogin").permitAll()
                    .requestMatchers("/api/v1/accounts/*/image").permitAll()
                    .requestMatchers("/api/v1/accounts/registercomplaint").permitAll()
                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated();
        });

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    AuthenticationManager authenticationManager() {
        var dao = new DaoAuthenticationProvider();// DaoAuthenticationProvider supports username/password-based
                                                  // authentication
                                                  // DaoAuthenticationProvider is an AuthenticationProvider
                                                  // implementation that
                                                  // uses a UserDetailsService and PasswordEncoder to authenticate a
                                                  // username and
                                                  // password.
                                                  // Refer this
                                                  // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html
        dao.setUserDetailsService(userDetailsService());
        dao.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(dao);

    }

}
