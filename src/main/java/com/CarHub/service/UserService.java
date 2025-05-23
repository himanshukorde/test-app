package com.CarHub.service;

import com.CarHub.entity.User;
import com.CarHub.payload.LoginDto;
import com.CarHub.payload.SignupDto;
import com.CarHub.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository regRepo;
    private ModelMapper mapper;
    private JWTService jwtService;

    public UserService(UserRepository regRepo, ModelMapper mapper, JWTService jwtService) {
        this.regRepo = regRepo;
        this.mapper = mapper;
        this.jwtService = jwtService;
    }

    public String signup(SignupDto dto, String role) {
        if (regRepo.findByUsername(dto.getUsername()).isPresent()) {
            return "User Already Exists";
        }
        if (regRepo.findByEmailId(dto.getEmailId()).isPresent()) {
            return "Email Already Exists";

        }

        User user = mapper.map(dto, User.class);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole(role);

        regRepo.save(user);
        return "SignUp Successful";
    }

    public String adminSignup(SignupDto dto){
        return signup(dto, "ROLE_ADMIN");

    }
    public String userSignup(SignupDto dto){
        return signup(dto, "ROLE_USER");
    }

    public String contentManager(SignupDto dto){
        return signup(dto, "ROLE_MANAGER");
    }

    public String verifyLogin(LoginDto dto) {
        Optional<User> opUser = regRepo.findByUsername(dto.getUsername());
        if (opUser.isPresent()) {
            User user = opUser.get();
            if (BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
                return jwtService.generateToken(user.getUsername());
            }

        }
             return null;
    }

}
