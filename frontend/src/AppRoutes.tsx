import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage/LoginPage";
import PrivateRoute from "./auth/PrivateRoute";
import BuyPage from "./pages/buy/BuyPage";
import SellPage from "./pages/sell/SellPage";

export const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<LoginPage />} />
        <Route path="/buy" element={<BuyPage />} />
        <Route path="/sell" element={<SellPage />} />

        {/* <PrivateRoute exact path="/dashboard" component={DashboardPage} /> */}
      </Routes>
    </Router>
  );
};
