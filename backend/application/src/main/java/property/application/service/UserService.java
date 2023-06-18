package property.application.service;

import property.application.dto.UserDto;

import java.util.List;

public interface UserService {

    void save(UserDto userDto);

    List<UserDto> findAll();

}
