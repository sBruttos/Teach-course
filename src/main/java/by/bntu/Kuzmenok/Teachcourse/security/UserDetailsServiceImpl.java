package by.bntu.Kuzmenok.Teachcourse.security;

import by.bntu.Kuzmenok.Teachcourse.entity.User;
import by.bntu.Kuzmenok.Teachcourse.repository.RoleRepository;
import by.bntu.Kuzmenok.Teachcourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public static final String ROLE_USER = "ROLE_USER";

    private MyUserDetails userDetails;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) user;
    }

    @Transactional
    public boolean saveNewUser(User user){
        User userDB = userRepository.findByEmail(userDetails.getUsername());

        if (userDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(roleRepository.findByRole(ROLE_USER)));
        userRepository.save(user);
        return true;

    }
}
