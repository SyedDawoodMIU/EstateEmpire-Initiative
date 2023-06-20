package property.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import property.application.model.User;

@Data
@AllArgsConstructor
public class MessageDto {

    @NotNull(message = "Message cannot be null")
    private String message;

    private User receiver;

    private User sender;


}
