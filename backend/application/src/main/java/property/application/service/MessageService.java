package property.application.service;

import org.springframework.stereotype.Service;
import property.application.dto.MessageDto;
import property.application.model.Message;

import java.util.List;

public interface MessageService {


    List<MessageDto> getMessagesByReceiverId(long receiverId);

    public void sendMessage(Long senderId, Long receiverId, String message) throws Exception;

}
