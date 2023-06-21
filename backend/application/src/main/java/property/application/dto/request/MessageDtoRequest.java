package property.application.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MessageDtoRequest {

    long receiverId;
    long senderId;
    String message;

}
