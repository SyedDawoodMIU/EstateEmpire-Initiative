import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import PrivateRoute from './auth/PrivateRoute';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        {/* <PrivateRoute exact path="/dashboard" component={DashboardPage} /> */}
      </Routes>
    </Router>
  );
};

export default App;
