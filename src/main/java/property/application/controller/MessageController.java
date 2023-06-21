package property.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import property.application.dto.MessageDto;
import property.application.dto.request.MessageDtoRequest;
import property.application.model.Message;
import property.application.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{receiver_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> receiveAllMessages(@PathVariable("receiver_id") Long receiver_id){
        return messageService.getMessagesByReceiverId(receiver_id);
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody MessageDtoRequest messageDtoRequest){
        messageService.sendMessage(messageDtoRequest);
    }




}
