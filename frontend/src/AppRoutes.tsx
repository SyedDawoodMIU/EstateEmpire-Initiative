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
import UserGrid from "./pages/admin/Users";
import MessageContainer from "./components/message/Messages";
import PropertyDetails from "./components/owner/property/PropertyDetails";
import PropertyList from "./components/owner/property/PropertyList";
import { Container } from "react-bootstrap";

export const AppRoutes = () => {
  return (
    <Container>
      <Router>
        <Routes>
          <Route path="/dashboard" element={<OwnerDashboard />} />
          <Route path="/buy" element={<BuyPage />} />
          <Route path="/sell" element={<SellPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/admin" element={<UserGrid />} />
          <Route path="/messages" element={<MessageContainer />} />
          <Route path="/register" element={<RegistrationPage />} />
          <Route path="/owner-dashboard" element={<OwnerDashboard />} />
          <Route path="/properties/:id" element={<PropertyDetails />} />
          <Route path="/properties" element={<PropertyList />} />
          {/* <Route path="/add-properties" element={<PropertyAddForm title={""} location={""} price={0} imageUrl={""} description={""}/>} /> */}
          {/* <PrivateRoute exact path="/dashboard" component={DashboardPage} /> */}
        </Routes>
      </Router>
    </Container>
  );
};
