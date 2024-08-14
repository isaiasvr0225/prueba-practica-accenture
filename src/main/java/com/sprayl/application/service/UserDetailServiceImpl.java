package com.sprayl.application.service;

import com.sprayl.infrastructure.persistence.repository.ClientRepository;
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

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var clientEntity = clientRepository.findClientEntityByUsername(username)
                                                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();

        clientEntity.getRoles()
                    .forEach(userRole -> grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userRole.getRoleEnum().name()))));

        clientEntity.getRoles().stream()
                                .flatMap(role -> role.getPermissionSet().stream())
                                .forEach(permission -> grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(
                clientEntity.getUsername(),
                clientEntity.getPassword(),
                clientEntity.isEnabled(),
                clientEntity.isAccountNoExpired(),
                clientEntity.isCredentialsNoExpired(),
                clientEntity.isAccountNoLocked(),
                grantedAuthorityList
        );
    }
}
