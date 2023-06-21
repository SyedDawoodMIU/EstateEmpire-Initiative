package property.application.mapper;

import org.mapstruct.Mapper;
import property.application.dto.request.UserDto;
import property.application.dto.response.UserDtoResponse;
import property.application.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDtoResponse toDto(User user);

    List<User> toEntityList(List<UserDto> userDtos);

    List<UserDtoResponse> toDtoList(List<User> users);

}
