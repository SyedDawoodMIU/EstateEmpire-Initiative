import React, { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import AuthContext from '../auth/AuthContext';
import oauthService from '../auth/oauthService';

const LoginPage = () => {
  const history = useHistory();
  const { login } = useContext(AuthContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const accessToken = await oauthService.login(username, password);
      login(accessToken);
      history.push('/dashboard'); // Redirect to the dashboard or any desired page upon successful login
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div>
      <h2>Login Page</h2>
      {error && <p>{error}</p>}
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
