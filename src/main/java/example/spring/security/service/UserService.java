package example.spring.security.service;


import example.spring.security.model.User;
import example.spring.security.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User register(User user) {

        return userRepo.save(user);

    }
}
