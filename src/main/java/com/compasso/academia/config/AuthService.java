package com.compasso.academia.config;

import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByEmail(s);
        if (user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("Dados Invalidos!");
    }
}
