package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.UserRepository;
import by.bsu.famcs.trucking.front.entity.UserAuthResponseFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.security.auth.login.FailedLoginException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserAuthResponseFront authenticate(String username, String password) throws FailedLoginException {
        if (!userRepository.existsByUsername(username)) {
            throw new FailedLoginException("User is not authorized");
        }
        UserBack userInDB = userRepository.findByUsername(username);
        if (!bCryptPasswordEncoder.matches(password, userInDB.getPassword())) {
            throw new FailedLoginException("Incorrect pass");
        }
        if (userInDB.isBanned()) {
            throw new FailedLoginException("Sorry, you have been banned by admin");
        }
        UserAuthResponseFront ret = new UserAuthResponseFront();
        ret.setUsername(userInDB.getUsername());
        ret.setFirstName(userInDB.getFirstName());
        ret.setId(userInDB.getId());
        ret.setLastName(userInDB.getLastName());
        ret.setRole(userInDB.getRole());
        ret.setToken(jwtTokenUtil.generateToken(userInDB.getUsername()));
        return ret;
    }

    public UserBack authorization(UserBack user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
