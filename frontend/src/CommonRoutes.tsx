import React, { FC, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import LoginPage from "./pages/login/LoginPage";
import RegistrationPage from "./pages/login/RegistrationPage";
import BuyPage from "./pages/buy/BuyPage";
import SellPage from "./pages/sell/SellPage";
import OwnerDashboard from "./pages/owner/OwnerDashboard";
import PrivateRoute from "./PrivateRoute";

export const CommonRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
      </Routes>
    </Router>
  );
};
