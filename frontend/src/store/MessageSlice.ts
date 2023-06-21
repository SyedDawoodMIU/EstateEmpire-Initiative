// messageSlice.ts
import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "./Store";
import { iMessage, iMessageState } from "../types/MessageTypes";

const initialState: iMessageState = {
  senders: [
    { id: 1, name: "Alice" },
    { id: 2, name: "Bob" },
    { id: 3, name: "Charlie" },
  ],
  messages: [
    {
      id: 1,
      senderId: 1,
      text: "Hi Bob",
      time: "10:00 AM",
    },
    {
      id: 2,
      senderId: 2,
      text: "Hi Alice",
      time: "10:01 AM",
    },
    {
      id: 3,
      senderId: 1,
      text: "How are you?",
      time: "10:02 AM",
    },
    {
      id: 4,
      senderId: 2,
      text: "I'm good, thanks. And you?",
      time: "10:03 AM",
    },
    {
      id: 5,
      senderId: 3,
      text: "Hello Alice and Bob",
      time: "10:04 AM",
    },
    {
      id: 6,
      senderId: 1,
      text: "Hello Charlie",
      time: "10:05 AM",
    },
    {
      id: 7,
      senderId: 2,
      text: "Hello Charlie",
      time: "10:06 AM",
    },
    {
      id: 8,
      senderId: 3,
      text: "What are you up to?",
      time: "10:07 AM",
    },
  ],
  currentSenderId: -1,
  loggedInUserSenderId: 1,
  loggedInUserName: "Alice",
};

export const messageSlice = createSlice({
  name: "message",
  initialState,
  reducers: {
    setCurrentSender(state, action) {
      state.currentSenderId = action.payload;
    },
    sendMessage(state, action) {
      const newMessage: iMessage = {
        id: Math.max(...state.messages.map((message) => message.id)) + 1,
        senderId: state.currentSenderId,
        text: action.payload,
        time:
          new Date().toLocaleTimeString([], {
            hour12: true,
            hour: "numeric",
            minute: "numeric",
          }) || "",
      };
      state.messages = [...state.messages, newMessage];
    },
  },
});

export const selectSenders = (state: RootState) => state.message.senders;
export const selectMessages = (state: RootState) => state.message.messages;

export const { setCurrentSender, sendMessage } = messageSlice.actions;

export default messageSlice.reducer;
