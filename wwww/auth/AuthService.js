import axios from 'axios';

const oauthService = {
  // Function to initiate the OAuth2 login process
  login: async (username, password) => {
    try {
      const response = await axios.post('/oauth/token', {
        grant_type: 'password',
        username,
        password,
      });

      // Assuming the server returns an access token
      const { access_token } = response.data;
      return access_token;
    } catch (error) {
      throw new Error('Failed to login. Please try again.');
    }
  },

  // Function to logout and revoke the access token
  logout: async (accessToken) => {
    try {
      await axios.post('/oauth/revoke', {
        token: accessToken,
      });

      // Perform any additional cleanup or redirect
    } catch (error) {
      throw new Error('Failed to logout. Please try again.');
    }
  },
};

export default oauthService;
