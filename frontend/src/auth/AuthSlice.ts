import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store/store";
import { iAuthState } from "./AuthTypes";

const initialState: iAuthState = {
  authenticated: false,
  accessToken: "",
  refreshToken: "",
};

export const authSlice = createSlice({
  name: "counter",
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    loginAction: (state, action: PayloadAction<iAuthState>) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
      state.authenticated = true;
    },
    logoutAction: (state) => {
      state.accessToken = "";
      state.authenticated = false;
      state.refreshToken = "";
    },
    registerAction: (state, action: PayloadAction<iAuthState>) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
      state.authenticated = true;
    },
  },
});

export const { loginAction, logoutAction, registerAction } = authSlice.actions;
export const authState = (state: RootState) => state.auth;
export default authSlice.reducer;
