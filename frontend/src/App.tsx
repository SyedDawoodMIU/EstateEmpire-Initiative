import React from "react";
import Navigation from "./baseComponents/Navigation";
import { AppRoutes } from "./AppRoutes";

const App = () => {
  return (
    <div className="container-fluid">
      <Navigation />
      <AppRoutes />
    </div>
  );
};

export default App;
