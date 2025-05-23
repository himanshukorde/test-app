package com.CarHub.controller;

import com.CarHub.entity.User;
import com.CarHub.payload.JWTTokenDto;
import com.CarHub.payload.LoginDto;
import com.CarHub.payload.SignupDto;
import com.CarHub.repository.UserRepository;
import com.CarHub.service.JWTService;
import com.CarHub.service.OTPService;
import com.CarHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserService userService;
    private OTPService otpService;
    private UserRepository rr;
    private JWTService jwtService;

    public AuthController(UserService userService, OTPService otpService, UserRepository rr, JWTService jwtService) {
        this.userService = userService;
        this.otpService = otpService;
        this.rr = rr;
        this.jwtService = jwtService;
    }



    public ResponseEntity<?> signup(SignupDto dto, String result){
        if(result.equals("username Already Exists")||result.equals("Email Already Exists")){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/signUp/admin")
    public ResponseEntity<?> adminSignup(@RequestBody SignupDto dto){
        String result = userService.adminSignup(dto);
        return signup(dto, result);
    }
    @PostMapping("/signUp/user")
    public ResponseEntity<?> userSignup(@RequestBody SignupDto dto){
            String result = userService.userSignup(dto);
            return signup(dto, result);
    }
    @PostMapping("/signUp/manager")
    public ResponseEntity<?> managerSignup(@RequestBody SignupDto dto){
            String result = userService.contentManager(dto);
            return signup(dto, result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto dto){
        String token = userService.verifyLogin(dto);
        if(token!=null) {
            JWTTokenDto tokenDto =new JWTTokenDto();
            tokenDto.setToken(token);
            tokenDto.setTokenType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Token", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/generateOTP/{mobile}")
    public String generateOtp(@PathVariable String mobile){
        Optional<User> opUser = rr.findByMobile(mobile);
        if(opUser.isPresent()) {
            String otp = otpService.generateOTP(mobile);
            return mobile + " : " + otp;
        }
        return "user not found";
    }

    @PostMapping("/validateOtp/{mobile}/{otp}")
    public ResponseEntity<?> validateOTP(@PathVariable String mobile, @PathVariable String otp){
        Optional<User> opUser = rr.findByMobile(mobile);
        if(opUser.isPresent()){
            boolean status = otpService.validateOtp(mobile, otp);
            if(status){
                String token = jwtService.generateToken(opUser.get().getUsername());
                JWTTokenDto jwtToken = new JWTTokenDto();
                jwtToken.setToken(token);
                jwtToken.setTokenType("JWT");
                return  new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }


}
