package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.userDto.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.LoginUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.model.entity.User;
import com.jalil_be_app.madang_app.model.enums.Gender;
import com.jalil_be_app.madang_app.model.enums.UserStatus;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.UserService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Override
    public RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        User user = new User();
        user.setFullname(registerUserRequestDto.getFullname());

        if (registerUserRequestDto.getGender().equalsIgnoreCase("laki-laki")
                || registerUserRequestDto.getGender().equalsIgnoreCase("laki laki")){
            user.setGender(Gender.LAKI_LAKI);
        } else if (registerUserRequestDto.getGender().equalsIgnoreCase("perempuan")){
            user.setGender(Gender.PEREMPUAN);
        }

        user.setUsername(registerUserRequestDto.getUsername());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        user.setStatus(UserStatus.INACTIVE);

//        Image image = new Image();
//        image.setImageLink(registerUserRequestDto.getImageLink());
//        image.setCategory(ImageCategory.USER_IMAGE);
//        image.setSize(ImageSize.SMALL);
//        imageRepository.save(image);
//
//        user.setImage(image);
        userRepository.save(user);

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setFullname(registerUserRequestDto.getFullname());
        responseDto.setGender(registerUserRequestDto.getGender());
        responseDto.setUsername(registerUserRequestDto.getUsername());
        responseDto.setEmail(registerUserRequestDto.getEmail());
//        responseDto.setImageLink(registerUserRequestDto.getImageLink());
        return responseDto;
    }

    @Override
    public LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(loginUserRequestDto.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        ));

        User user = userOptional.get();

        if (loginUserRequestDto.getEmail() == null || loginUserRequestDto.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password can't null");
        }
        if (!passwordEncoder.matches(loginUserRequestDto.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your email or password is invalid");
        } else if (user.getStatus() == UserStatus.INACTIVE) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your account is inactive");
        } else {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequestDto.getEmail(),
                        loginUserRequestDto.getPassword()
                ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(authentication);
            User userDetails = (User) authentication.getPrincipal();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);

            LoginUserResponseDto responseDto = new LoginUserResponseDto();
            responseDto.setAccessToken(token);
            responseDto.setUserId(userDetails.getId());
            responseDto.setUserStatus(user.getStatus());
            return responseDto;
        }

    }
}
