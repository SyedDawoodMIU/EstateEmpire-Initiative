import { createSlice } from "@reduxjs/toolkit";
import { iUserData } from "../types/UserTypes";

const userState: iUserData[] = [];

const initialState = {
  users: userState,
  isLoading: false,
  error: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    getUsersStart(state) {
      state.isLoading = true;
      state.error = null;
    },
    getUsersSuccess(state, action) {
      state.isLoading = false;
      state.users = action.payload;
    },
    getUsersFailure(state, action) {
      state.isLoading = false;
      state.error = action.payload;
    },
  },
});

export const { getUsersStart, getUsersSuccess, getUsersFailure } =
  userSlice.actions;
export default userSlice.reducer;
