import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:9990/api/v1",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

const AuthService = {
  // Function to initiate the OAuth2 login process
  login: async (email, password) => {
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
  logout: async (accessToken) => {
    try {
      await apiClient.post("/authenticate/revoke", {
        token: accessToken,
      });

      // Perform any additional cleanup or redirect
    } catch (error) {
      throw new Error("Failed to logout. Please try again.");
    }
  },
};

export default AuthService;
