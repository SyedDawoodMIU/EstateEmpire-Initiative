export interface iUserData {
  userId?: number;
  name: string;
  email: string;
  password: string;
  role: "OWNER" | "CUSTOMER";
  isDisabled?: boolean;
}
