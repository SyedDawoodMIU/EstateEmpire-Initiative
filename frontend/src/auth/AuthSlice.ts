import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../store/store";
import { iAuthState } from "./AuthTypes";

const initialState: iAuthState = {
  authenticated: false,
  accessToken: "",
};

export const authSlice = createSlice({
  name: "counter",
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    login: (state, action: PayloadAction<string>) => {
      state.accessToken = action.payload;
      state.authenticated = true;
    },
    logout: (state) => {
      state.accessToken = "";
      state.authenticated = false;
    },
  },
});

export const { login, logout } = authSlice.actions;
export const authState = (state: RootState) => state.auth;
export default authSlice.reducer;
