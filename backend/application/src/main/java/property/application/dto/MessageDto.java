package property.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import property.application.model.User;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageDto {

    public MessageDto(){}

    @NotNull(message = "Message cannot be null")
    private String content;

//    private UserDto receiver;

    private UserDto sender;

    private Date createdAt;


}
