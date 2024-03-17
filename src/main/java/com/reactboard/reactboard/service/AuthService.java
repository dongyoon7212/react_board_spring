package com.reactboard.reactboard.service;

import com.reactboard.reactboard.dto.SigninReqDto;
import com.reactboard.reactboard.dto.SignupReqDto;
import com.reactboard.reactboard.entity.User;
import com.reactboard.reactboard.exception.SaveException;
import com.reactboard.reactboard.jwt.JwtProvider;
import com.reactboard.reactboard.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public void signup(SignupReqDto signupReqDto) {
        int successCount = 0;
        User user = signupReqDto.toEntity(passwordEncoder);

        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId());

        if(successCount < 2) {
            throw new SaveException();
        }
    }

    public String signin(SigninReqDto signinReqDto) {
        User user = userMapper.findUserByUsername(signinReqDto.getUsername());

        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }

        if(!passwordEncoder.matches(signinReqDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }

        return jwtProvider.generateToken(user);
    }
}
