package property.application.service;

import property.application.dto.request.UserDto;
import property.application.dto.response.LoginResponse;
import property.application.dto.response.UserDtoResponse;

import java.util.List;

public interface UserService {

    LoginResponse save(UserDto userDto);

    List<UserDtoResponse> findAll();

    void deleteUser(Long id);

    UserDtoResponse getUserById(Long id);

    UserDtoResponse update(UserDto userDto, Long id);

    UserDtoResponse getUserByEmail(String email);

}
