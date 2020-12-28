package com.my.projmanager.security.service;

import com.my.projmanager.model.impl.Employee;
import com.my.projmanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceProvider implements UserDetailsService {
    private final EmployeeRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Optional<Employee> searchResult = userRepository.findByMail(email);
            if (searchResult.isPresent()) {
                Employee user = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getMail(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                user.getRole().getName().name())
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", email));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
