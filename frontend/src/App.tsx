import React, { useEffect, useState } from "react";
import { AppRoutes } from "./AppRoutes";
import Navigation from "./BaseComponents/Navigation";
import { CommonRoutes } from "./CommonRoutes";
import { RootState } from "./store/store";
import { useAppSelector } from "./store/storeHooks";

const App = () => {
  
  

  return (
    <div className="container-fluid">
      
        <div>
          <Navigation />
          <AppRoutes />
        </div>
     
      
    </div>
  );
};

export default App;

