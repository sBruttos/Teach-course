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
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new MyUserDetails(userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user")));
    }

    @Transactional
    public boolean saveNewUser(User user){

        if (user == null) {
            return false;
        }

        if (userRepository.findByEmail(user.getEmail()).isEmpty()){
            user = User.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(Collections.singleton(roleRepository.findByRole("ROLE_USER")))
                    .build();

            userRepository.save(user);
            return true;
        }

        return false;
    }
    @Transactional
    public Optional<User> findUserById(int id) {
        return userRepository.findById((long) id);
    }
    @Transactional
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}

