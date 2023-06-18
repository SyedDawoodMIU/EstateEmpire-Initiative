import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";
import { login } from "./AuthSlice";

export const handleLogin =
  (username: string, password: string) => async (dispatch: any) => {
    try {
      const accessToken = await AuthService.login(username, password);
      dispatch(login(accessToken));
    } catch (error) {
      // Handle login error
    }
  };
