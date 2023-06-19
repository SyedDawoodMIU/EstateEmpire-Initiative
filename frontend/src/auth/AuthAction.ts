import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";
import { login } from "./AuthSlice";

export const handleLogin =
  (email: string, password: string) => async (dispatch: any) => {
    try {
      const accessToken = await AuthService.login(email, password);
      dispatch(login(accessToken));
    } catch (error) {
      // Handle login error
    }
  };
