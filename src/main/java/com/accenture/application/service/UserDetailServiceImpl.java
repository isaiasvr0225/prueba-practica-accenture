package com.accenture.application.service;

import com.accenture.infrastructure.persistence.repository.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public @Service class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@Email String email) throws UsernameNotFoundException {

        var clientEntity = userRepository.findClientEntityByEmail(email)
                                                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_".concat(clientEntity.getRole())));

        return new User(
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.isEnabled(),
                clientEntity.isAccountNoExpired(),
                clientEntity.isCredentialsNoExpired(),
                clientEntity.isAccountNoLocked(),
                grantedAuthorityList
        );
    }
}
