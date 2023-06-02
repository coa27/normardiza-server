package xyz.normadiza.normadiza.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import xyz.normadiza.normadiza.security.filter.CustomAuthenticationFilter;
import xyz.normadiza.normadiza.security.provider.AutenticacionProvider;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    @Lazy
    private AutenticacionProvider autenticacionProvider;

    @Autowired
    private CustomAuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(autenticacionProvider)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req
                            .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                            .anyRequest().authenticated();
                });

        httpSecurity.addFilterBefore(authenticationFilter, SecurityContextHolderFilter.class);

        return httpSecurity.build();
    }


}
