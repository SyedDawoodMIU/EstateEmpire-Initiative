// Message.tsx
import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  selectSenders,
  selectMessages,
  sendMessage,
  messageSlice,
} from "../../store/MessageSlice";
import "./Message.css";
import { RootState } from "../../store/Store";
import { Sender } from "./Sender";
import { Message } from "./Message";

export const MessageContainer: React.FC = () => {
  const [inputText, setInputText] = useState("");
  const senders = useSelector(selectSenders);
  const messages = useSelector(selectMessages);
  const currentSender = useSelector(
    (state: RootState) => state.message.currentSenderId
  );
  const { loggedInUserSenderId } = useSelector(
    (state: RootState) => state.message
  );
  const dispatch = useDispatch();

  const handleSenderClick = (id: number) => {
    dispatch(messageSlice.actions.setCurrentSender(id));
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputText(e.target.value);
  };

  const handleSendClick = () => {
    if (inputText.trim()) {
      dispatch(sendMessage(inputText)); // Pass inputText directly as the payload
      setInputText("");
    }
  };

  return (
    <div className="message-container">
      <div className="sender-list">
        {senders.map((sender) => (
          <Sender
            key={sender.id}
            id={sender.id}
            name={sender.name}
            onClick={() => handleSenderClick(sender.id)}
          />
        ))}
      </div>
      <div className="conversation">
        <div className="message-list">
          {messages
            .filter(
              (message) =>
                message.senderId === currentSender ||
                message.senderId === loggedInUserSenderId
            ) // Include loggedInUserSenderId in filter
            .map((message) => (
              <Message
                key={message.id}
                id={message.id}
                senderId={message.senderId}
                text={message.text}
                time={message.time}
              />
            ))}
        </div>
        <div className="input-area">
          <input
            type="text"
            value={inputText}
            onChange={handleInputChange}
            placeholder="Type a message"
          />
          <button onClick={handleSendClick}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default MessageContainer;
