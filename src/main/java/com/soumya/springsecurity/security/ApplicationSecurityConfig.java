package com.soumya.springsecurity.security;

import com.soumya.springsecurity.auth.ApplicationUserService;
import com.soumya.springsecurity.jwt.JWTUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.soumya.springsecurity.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")/*for this no need to specify username password*/.permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                /* .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(STUDENT_WRITE.name())
                 .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(STUDENT_WRITE.name())
                 .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(STUDENT_WRITE.name())
                 .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name()) */
                /*.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())*/
                .anyRequest()
                .authenticated();

    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails lakshmi = User.builder()
//                .username("lakshmiDBUser")
//                .password(passwordEncoder.encode("password"))
//                //               .roles(STUDENT.name())//ROLE_STUDENT
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password123"))
//                //               .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//        UserDetails admintrainee = User.builder()
//                .username("adminTrainee")
//                .password(passwordEncoder.encode("password123"))
//                //               .roles(ADMINTRAINEE.name())
//                .authorities(ADMINTRAINEE.getGrantedAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(lakshmi, admin, admintrainee);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
