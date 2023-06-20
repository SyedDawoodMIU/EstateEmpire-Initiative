import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store/store";
import { iAuthState } from "./AuthTypes";
import {
  storeToken,
  clearToken,
  getToken,
  storeRefreshToken,
  clearRefreshToken,
} from "../utils/tokenUtils";
import { clear } from "console";

const initialState: iAuthState = {
  isAuthenticated: false,
  accessToken: "",
  refreshToken: "",
};

export const authSlice = createSlice({
  name: "authSlice",
  initialState,
  reducers: {
    loginAction: (state, action: PayloadAction<iAuthState>) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
      state.isAuthenticated = true;
      storeToken(action.payload.accessToken);
      storeRefreshToken(action.payload.refreshToken);
    },
    logoutAction: (state) => {
      state.accessToken = "";
      state.isAuthenticated = false;
      state.refreshToken = "";
      clearToken();
      clearRefreshToken();
    },
    registerAction: (state, action: PayloadAction<iAuthState>) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
      state.isAuthenticated = true;
      storeToken(action.payload.accessToken);
      storeRefreshToken(action.payload.refreshToken);
    }
  },
});

export const { loginAction, logoutAction, registerAction } = authSlice.actions;
export const authState = (state: RootState) => state.auth;
export default authSlice.reducer;
