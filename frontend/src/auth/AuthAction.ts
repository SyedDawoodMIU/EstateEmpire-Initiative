import { useNavigate } from "react-router-dom";
import AuthService from "./AuthService";
import { login } from "./AuthSlice";

export const handleLogin =
  (username: string, password: string) => async (dispatch: any) => {
    try {
      const accessToken = await AuthService.login(username, password);
      dispatch(login(accessToken));
      const navigate = useNavigate();
      navigate("/dashboard"); // Redirect to the dashboard or any desired page upon successful login
    } catch (error) {
      // Handle login error
    }
  };
