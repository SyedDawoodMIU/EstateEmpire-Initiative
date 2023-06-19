import { useNavigate } from "react-router-dom";
import { iUserData } from "../types/UserTypes";
import { iAuthState } from "./AuthTypes";
import { loginAction, registerAction } from "./AuthSlice";
import AuthService from "./AuthService";

export const handleLogin =
  (email: string, password: string) => async (dispatch: any) => {
    try {
      const { access_token, refresh_token } = await AuthService.login(
        email,
        password
      );

      let tokens: iAuthState = {
        accessToken: access_token,
        refreshToken: refresh_token,
      };
      dispatch(loginAction(tokens));
    } catch (error) {
      // Handle login error
    }
  };

export const handleRegistration =
  (userData: iUserData) => async (dispatch: any) => {
    try {
      const { access_token, refresh_token } = await AuthService.register(
        userData
      );

      let tokens: iAuthState = {
        accessToken: access_token,
        refreshToken: refresh_token,
      };
      dispatch(registerAction(tokens));
    } catch (error) {
      // Handle login error
    }
  };
