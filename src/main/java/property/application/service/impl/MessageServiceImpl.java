package property.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.MessageDto;
import property.application.dto.request.MessageDtoRequest;
import property.application.exception.BadRequestException;
import property.application.model.Message;
import property.application.model.Property;
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

    public MessageServiceImpl(MessageRepo messageRepo, ModelMapper modelMapper, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public List<MessageDto> getMessagesByReceiverId(long receiverId) {
        Optional<User> receiver = userRepo.findById(receiverId);
        List<MessageDto> messages1 = null;
        if (receiver.isEmpty()) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Receiver Id not found");
        } else {
            List<Message> messages2 = messageRepo.findAllByReceiver(receiver.get());
            List<Message> messages3 = messageRepo.findAllBySender(receiver.get());
            messages2.addAll(messages3);
            messages1 = messages2.stream()
                    .distinct() // Remove duplicate messages based on equals() and hashCode() methods
                    .map(message -> modelMapper.map(message, MessageDto.class))
                    .sorted((m1, m2) -> m1.getCreatedAt().compareTo(m2.getCreatedAt()))
                    .collect(Collectors.toList());
            // System.out.println(messages);
        }
        return messages1;
    }

    @Override
    public void sendMessage(MessageDtoRequest messageDtoRequest) {
        Optional<User> sender = userRepo.findById(messageDtoRequest.getSenderId());
        Optional<User> receiver = userRepo.findById(messageDtoRequest.getReceiverId());
        if (receiver.isEmpty() || sender.isEmpty()) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Incorrect/Missing receiverId or senderId!");
        } else {
            // Message message = modelMapper.map(messageDtoRequest, Message.class);
            Message newMessage = new Message(sender.get(), receiver.get(), messageDtoRequest.getMessage());
            messageRepo.save(newMessage);
        }

    }
}
