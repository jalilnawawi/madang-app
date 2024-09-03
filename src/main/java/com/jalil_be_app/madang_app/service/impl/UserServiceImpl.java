package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.userDto.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.User;
import com.jalil_be_app.madang_app.model.enums.Gender;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

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
        user.setPassword(registerUserRequestDto.getPassword());

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
}
