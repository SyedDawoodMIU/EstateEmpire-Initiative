package property.application.dto.response;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import property.application.dto.UserDto;
import property.application.model.User;

@Data
@AllArgsConstructor
public class UserMessageDto {

    public UserMessageDto(){}

    @NotNull(message = "Message cannot be null")
    private String message;

//    private UserDto receiver;

    private UserDto sender;


}
