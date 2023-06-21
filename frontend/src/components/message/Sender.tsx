import React from "react";
import { iSenderProps } from "../../types/MessageTypes";
import { RootState } from "../../store/Store";
import { useSelector } from "react-redux";

export const Sender: React.FC<iSenderProps> = ({ id, name, onClick }) => {
  const { loggedInUserSenderId } = useSelector(
    (state: RootState) => state.message
  );

  const isCurrentUser = id === loggedInUserSenderId;

  return (
    <div
      className={`sender ${isCurrentUser ? "current-user" : ""}`}
      onClick={onClick}
    >
      <p>{name}</p>
    </div>
  );
};
