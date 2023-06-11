package property.application.mapper;

import org.mapstruct.Mapper;
import property.application.dto.UserDto;
import property.application.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<User> toEntityList(List<UserDto> userDtos);

    List<UserDto> toDtoList(List<User> users);

}
