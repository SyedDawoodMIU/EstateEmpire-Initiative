import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../../store/Store";
import { iMessage } from "../../types/MessageTypes";
export const Message: React.FC<iMessage> = ({ id, senderId, text, time }) => {
  const { loggedInUserSenderId } = useSelector(
    (state: RootState) => state.message
  );

  const isMine = senderId === loggedInUserSenderId;

  return (
    <div className={`message ${isMine ? "mine" : "other"}`}>
      <p>{text}</p>
      <span>
        <small>{time}</small>
      </span>
    </div>
  );
};
