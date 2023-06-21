export interface Sender {
  id: number;
  name: string;
}

export interface iMessage {
  id: number;
  senderId: number;
  text: string;
  time: string;
}

export interface iMessageState {
  senders: Sender[];
  messages: iMessage[];
  currentSenderId: number;
  loggedInUserSenderId: number;
  loggedInUserName: string;
}

export interface iSenderProps {
  id: number;
  name: string;
  onClick: () => void;
}
