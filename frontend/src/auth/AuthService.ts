import { getRefreshToken } from "./../utils/tokenUtils";
import axios from "axios";
import { iUserData } from "../types/UserTypes";
const apiClient = axios.create({
  baseURL: "http://localhost:9990/api/v1",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

const AuthService = {
  login: async (email: string, password: string) => {
    try {
      const response = await apiClient.post("/authenticate", {
        email,
        password,
      });

      // Assuming the server returns an access token
      const { access_token } = response.data;
      return access_token;
    } catch (error) {
      throw new Error("Failed to login. Please try again.");
    }
  },

  // Function to logout and revoke the access token
  logout: async (accessToken: string) => {
    try {
      await apiClient.post("/authenticate/revoke", {
        token: accessToken,
      });

      // Perform any additional cleanup or redirect
    } catch (error) {
      throw new Error("Failed to logout. Please try again.");
    }
  },

  //Function to register a new user
  register: async (userData: iUserData) => {
    try {
      const response = await apiClient.post("/users/register", userData);

      // Assuming the server returns an access token
      const { access_token } = response.data;
      return access_token;
    } catch (error) {
      throw new Error("Failed to register. Please try again.");
    }
  },

  // Function to get a new access token with a refresh token
  getAccessTokenWithRefreshToken: async (refreshToken: string) => {
    try {
      const response = await apiClient.post("/authenticate/refresh", {
        refreshToken,
      });
      const { access_token } = response.data;
      return access_token;
    } catch (error) {
      throw new Error("Failed to get access token. Please try again.");
    }
  },
};




export default AuthService;
