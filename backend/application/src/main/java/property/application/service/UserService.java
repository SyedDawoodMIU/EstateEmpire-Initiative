package property.application.service;

import property.application.dto.UserDto;
import property.application.dto.response.LoginResponse;

import java.util.List;

public interface UserService {

    LoginResponse save(UserDto userDto);

    List<UserDto> findAll();

}
