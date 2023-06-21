package property.application.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import property.application.config.securityUser.EmpireUserDetails;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.request.UserDto;
import property.application.dto.response.LoginResponse;
import property.application.dto.response.UserDtoResponse;
import property.application.exception.BadRequestException;
import property.application.mapper.UserMapper;
import property.application.repo.RoleRepo;
import property.application.repo.UserRepo;
import property.application.service.UserService;
import property.application.util.JwtUtil;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse save(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsDisabled(Boolean.FALSE);
        if (userDto.getRole().equals("ADMIN")){
        if (userDto.getRole().equals("ADMIN")) {
            new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Role cannot be set to ADMIN");
        }
        var role = roleRepo.findByRoleName(userDto.getRole())
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Role not found"));
        user.setRoles(Collections.singletonList(role));
        userRepo.save(user);
        return bindResponse(new EmpireUserDetails(user));
    }

    LoginResponse bindResponse(EmpireUserDetails userDetails) {
        var response = new LoginResponse();
        response.setAccessToken(jwtUtil.generateToken(userDetails));
        response.setRefreshToken(jwtUtil.generateRefreshToken(userDetails.getUsername()));
        return response;
    }

    @Override
    public List<UserDtoResponse> findAll() {
        return userMapper.toDtoList(userRepo.findAll());
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDtoResponse getUserById(Long id) {
        return userMapper.toDto(
                userRepo.findById(id)
                        .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "User not found")));
    }

    @Override
    public UserDtoResponse update(UserDto userDto, Long id) {
        var user = userRepo.findById(id)
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "User not found"));
        user.setEmail(userDto.getEmail());
        user.setIsDisabled(userDto.getIsDisabled());
        user.setName(user.getName());
        userRepo.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDtoResponse getUserByEmail(String email) {
        return userMapper.toDto(
                userRepo.findByEmail(email)
                        .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "User not found")));
    }

}
