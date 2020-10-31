package com.soumya.springsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.soumya.springsecurity.security.ApplicationUserRole.*;

@Repository("fake")
public class ApplicationUserDAOImpl implements ApplicationUserDAO {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDAOImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(user -> username.equals(user.getUsername())).findFirst();
    }

    public List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> users = Lists.newArrayList(
                new ApplicationUser("lakshmiDBUser", passwordEncoder.encode("password"), STUDENT.getGrantedAuthorities(), true, true, true, true),
                new ApplicationUser("admin", passwordEncoder.encode("password123"), ADMIN.getGrantedAuthorities(), true, true, true, true),
                new ApplicationUser("adminTrainee", passwordEncoder.encode("password123"), ADMINTRAINEE.getGrantedAuthorities(), true, true, true, true)
        );
        return users;
    }
}
