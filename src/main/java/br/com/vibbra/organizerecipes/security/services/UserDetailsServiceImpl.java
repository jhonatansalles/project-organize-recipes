package br.com.vibbra.organizerecipes.security.services;

import br.com.vibbra.organizerecipes.model.entity.users.Users;
import br.com.vibbra.organizerecipes.repository.users.UsersRepository;
import br.com.vibbra.organizerecipes.security.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Users not found with -> EMAIL: " + email));

        return UserPrincipal.build(user);
    }
}