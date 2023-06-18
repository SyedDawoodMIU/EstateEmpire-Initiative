import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage/LoginPage';
import PrivateRoute from './auth/PrivateRoute';
import NavBar from './BaseComponents/Navigation';

const App = () => {
  return (
    <Router>
      <div>
        <NavBar/>
      <Routes>
      
      <Route path="/" element={<LoginPage />} />
      <Route path="/login" element={<LoginPage />} />
      {/* <PrivateRoute exact path="/dashboard" component={DashboardPage} /> */}
    </Routes>
      </div>
    </Router>
  );
};

export default App;
