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
import PropertyList from "./components/owner/property/PropertyList";
import PropertyDetails from "./components/owner/property/PropertyDetails";
import { iProperty } from "./types/PropertyTypes";
import OwnerDashboard from "./components/owner/dashboard/OwnerDashboard";
import PropertyAddForm from "./components/owner/property/PropertyAddForm";

export const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/dashboard" element={<OwnerDashboard />} />
        <Route path="/buy" element={<BuyPage />} />
        <Route path="/sell" element={<SellPage />} />
        <Route path="/owner-dashboard" element={<OwnerDashboard />} />
        <Route path="/properties/:id" element={<PropertyDetails title={""} location={""} price={0} imageUrl={""} description={""}/>} />
        <Route path="/properties" element={<PropertyList />} />
        <Route path="/add-properties" element={<PropertyAddForm title={""} location={""} price={0} imageUrl={""} description={""}/>} />
        
        
        {/* <PrivateRoute exact path="/dashboard" component={DashboardPage} /> */}
      </Routes>
    </Router>
  );
};
