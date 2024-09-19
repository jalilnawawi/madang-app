package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.userDto.login.request.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.response.LoginUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.login.request.RefreshTokenRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.response.RefreshTokenResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.register.request.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.register.response.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.request.UpdateImageRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.response.UpdateImageResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.request.UpdatePasswordRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.response.UpdatePasswordResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.Gender;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.model.enums.UserStatus;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.RoleRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.UserService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import com.jalil_be_app.madang_app.service.jwt.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Override
    @Transactional
    public RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        Optional<User> existingUserCheck = userRepository.findByUsername(registerUserRequestDto.getUsername());
        if (existingUserCheck.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your email has registered");
        } else {
            User user = new User();
            user.setFullname(registerUserRequestDto.getFullname());

            if (registerUserRequestDto.getGender().equalsIgnoreCase("laki-laki")
                    || registerUserRequestDto.getGender().equalsIgnoreCase("laki laki")) {
                user.setGender(Gender.LAKI_LAKI);
            } else if (registerUserRequestDto.getGender().equalsIgnoreCase("perempuan")) {
                user.setGender(Gender.PEREMPUAN);
            }

            user.setUsername(registerUserRequestDto.getUsername());
            user.setEmail(registerUserRequestDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
            user.setStatus(UserStatus.INACTIVE);

            Set<String> strRoles = registerUserRequestDto.getRole();
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(UserRole.ROLE_USER);
            Role merchantRole = roleRepository.findByName(UserRole.ROLE_MERCHANT);
            //TODO for admin role perlu ditaruh di API ?
            if (strRoles == null || strRoles.isEmpty()){
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    if ("merchant".equals(role)){
                        roles.add(merchantRole);
                    } else {
                        roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);

            Image image = new Image();
            image.setImageLink(registerUserRequestDto.getImageLink());
            image.setCategory(ImageCategory.USER);
            image.setSize(ImageSize.S);
            imageRepository.save(image);

            user.setImage(image);
            userRepository.save(user);

            RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
            responseDto.setFullname(registerUserRequestDto.getFullname());
            responseDto.setGender(registerUserRequestDto.getGender());
            responseDto.setUsername(registerUserRequestDto.getUsername());
            responseDto.setEmail(registerUserRequestDto.getEmail());
            responseDto.setRole(roles);
            responseDto.setImageLink(registerUserRequestDto.getImageLink());
            return responseDto;
        }
    }

    @Override
    public LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto) {
        if (loginUserRequestDto.getUsername() == null || loginUserRequestDto.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password can't null");
        }
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(loginUserRequestDto.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        ));
        User user = userOptional.get();

        if (!passwordEncoder.matches(loginUserRequestDto.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your username or password is invalid");
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserRequestDto.getUsername(),
                            loginUserRequestDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(authentication);
            String refreshToken = jwtService.generateRefreshToken(authentication);
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);

            LoginUserResponseDto responseDto = new LoginUserResponseDto();
            responseDto.setAccessToken(token);
            responseDto.setRefreshToken(refreshToken);
            responseDto.setUserId(userDetails.getId());
            responseDto.setUserStatus(user.getStatus());
            responseDto.setRoles(roles);
            return responseDto;
        }

    }

    @Override
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String username = jwtService.getUsername(refreshTokenRequestDto.getRefreshToken());
        String token = jwtService.generateTokenFromUsername(username);

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        } else {
            RefreshTokenResponseDto responseDto = new RefreshTokenResponseDto();
            responseDto.setAccessToken(token);
            return responseDto;
        }
    }

    @Override
    public UpdatePasswordResponseDto updatePassword(String token, UpdatePasswordRequestDto updatePasswordRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        User existingUser = userRepository.findById(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        existingUser.setPassword(passwordEncoder.encode(updatePasswordRequestDto.getPassword()));
        userRepository.save(existingUser);

        UpdatePasswordResponseDto responseDto = new UpdatePasswordResponseDto();
        responseDto.setUsername(existingUser.getUsername());
        return responseDto;
    }

    @Override
    public UpdateImageResponseDto updateImage(String token, UpdateImageRequestDto updateImageRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);
        User existingUser = userRepository.findById(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        Image existingImage = imageRepository.findById(existingUser.getImage().getId()).get();
        existingImage.setImageLink(updateImageRequestDto.getImageLink());
        imageRepository.save(existingImage);

        userRepository.save(existingUser);

        UpdateImageResponseDto responseDto = new UpdateImageResponseDto();
        responseDto.setUsername(existingUser.getUsername());
        responseDto.setImageLink(existingImage.getImageLink());
        return responseDto;
    }
}
