import React, { useState } from 'react';
import AuthContext from './AuthContext';

const AuthProvider = ({ children }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [accessToken, setAccessToken] = useState('');

  const login = (token) => {
    setAccessToken(token);
    setAuthenticated(true);
  };

  const logout = () => {
    setAccessToken('');
    setAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ authenticated, accessToken, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
