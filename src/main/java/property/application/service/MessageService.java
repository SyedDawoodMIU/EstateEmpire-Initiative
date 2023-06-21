package property.application.service;

import org.springframework.stereotype.Service;
import property.application.dto.MessageDto;
import property.application.dto.request.MessageDtoRequest;
import property.application.model.Message;

import java.util.List;

public interface MessageService {


    List<MessageDto> getMessagesByReceiverId(long receiverId);

    public void sendMessage(MessageDtoRequest messageDtoRequest);

}
