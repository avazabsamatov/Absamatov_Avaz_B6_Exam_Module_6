package uz.pdp.absamatov_avaz_b6_exam_1_variant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.User;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            User user = byUsername.get();
            return new org.springframework
                    .security
                    .core
                    .userdetails
                    .User(user
                    .getUsername(),
                    user
                    .getPassword(),
                    Collections.emptyList());
        }
        throw  new UsernameNotFoundException(username);
    }
}
