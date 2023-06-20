package property.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import property.application.dto.MessageDto;
import property.application.exception.BadRequestException;
import property.application.model.Message;
import property.application.model.User;
import property.application.repo.MessageRepo;
import property.application.repo.UserRepo;
import property.application.service.MessageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepo messageRepo;
    private ModelMapper modelMapper;

    private UserRepo userRepo;

    public MessageServiceImpl(MessageRepo messageRepo, ModelMapper modelMapper, UserRepo userRepo){
        this.messageRepo = messageRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }


    @Override
    public List<MessageDto> getMessagesByReceiverId(long receiverId) {
        Optional<User> receiver = userRepo.findById(receiverId);
        List<MessageDto> messages = null;
        if (receiver.isEmpty()) {
            System.out.println("Error");
        } else {
            List<Message> messages1 = messageRepo.findAllByReceiver(receiver.get());
            messages = messages1.stream().map((message) -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
        }
        return messages;
    }

    @Override
    public void sendMessage(Long senderId, Long receiverId, String content) {
        Optional<User> receiver = userRepo.findById(receiverId);
        Optional<User> sender = userRepo.findById(senderId);
        if(receiver.isEmpty() || sender.isEmpty()){
            System.out.println("Bad Request");
//            Exception exception1 = new Exception("Bad Request");
//            throw exception1;
        }else{
            Message newMessage = new Message(sender, receiver, content);
            messageRepo.save(newMessage);
        }

    }
}
