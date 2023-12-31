package property.application.dto.response;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserMessageDto {

    public UserMessageDto(){}

    @NotNull(message = "Message cannot be null")
    private String message;

//    private UserDto receiver;

    private UserDtoResponse sender;


}
