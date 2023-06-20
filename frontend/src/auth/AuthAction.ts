import { get } from "./../services/baseService";
import { useNavigate } from "react-router-dom";
import { iUserData } from "../types/UserTypes";
import { iAuthState } from "./AuthTypes";
import { loginAction, logoutAction, registerAction } from "./AuthSlice";
import AuthService from "./AuthService";
import {
  clearToken,
  decodeToken,
  getRefreshToken,
  getToken,
} from "../utils/tokenUtils";
import { clear } from "console";

export const handleLogin =
  (email: string, password: string) => async (dispatch: any) => {
    try {
      let tokens: iAuthState = {} as iAuthState;
      tokens = await AuthService.login(email, password);
      dispatch(loginAction(tokens));
      initializeTokenRefresh(tokens.accessToken);
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
      initializeTokenRefresh(tokens.accessToken);
    } catch (error) {
      // Handle login error
    }
  };

export const handleLogout = () => async (dispatch: any) => {
  try {
    cleanupTokenRefresh();
    dispatch(logoutAction());
  } catch (error) {
    // Handle logout error
  }
};

export const getAccessTokenWithRefreshToken = () => async (dispatch: any) => {
  try {
    const refreshToken = getRefreshToken();
    const { access_token, refresh_token } =
      await AuthService.getAccessTokenWithRefreshToken(refreshToken ?? "");
    let tokens: iAuthState = {
      accessToken: access_token,
      refreshToken: refresh_token,
    };
    dispatch(loginAction(tokens));
    return access_token;
  } catch (error) {
    // Handle logout error
  }
};

const TOKEN_REFRESH_THRESHOLD = 300; // Number of seconds before token expiry to trigger refresh (e.g., 5 minutes)
let tokenRefreshTimeout: any = null;

export const isTokenExpired = () => {
  const token = getToken();
  if (token) {
    const decodedToken = decodeToken(token);
    if (decodedToken && decodedToken.exp) {
      const currentTime = Math.floor(Date.now() / 1000);
      const expiresIn = decodedToken.exp - currentTime;
      if (expiresIn <= TOKEN_REFRESH_THRESHOLD) {
        clearToken();
        (() => (dispatch:any) => {
          dispatch(handleLogout());
        })();
        return true;
      }
      return false;
    }

    clearToken();
    return true;
  }

  clearToken();
  return true;
};

const initializeTokenRefresh = (accessToken: string) => {
  if (accessToken) {
    scheduleTokenRefresh(accessToken);
  }
};

const cleanupTokenRefresh = () => {
  clearTimeout(tokenRefreshTimeout);
};

const scheduleTokenRefresh = (accessToken: string) => {
  const decodedToken = decodeToken(accessToken);
  const currentTime = Math.floor(Date.now() / 1000);
  const expiresIn = decodedToken.exp - currentTime;
  const refreshTime = expiresIn * 1000 - TOKEN_REFRESH_THRESHOLD;

  if (refreshTime > 0) {
    tokenRefreshTimeout = setTimeout(() => {
      if (expiresIn <= TOKEN_REFRESH_THRESHOLD) {
        try {
          getAccessTokenWithRefreshToken();
        } catch (error) {
          console.error("Token refresh failed:", error);
        }
      }
    }, refreshTime);
  }
};

window.addEventListener("beforeunload", cleanupTokenRefresh);
