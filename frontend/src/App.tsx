import React from "react";
import Navigation from "./baseComponents/Navigation";
import { AppRoutes } from "./AppRoutes";
import { useAppSelector } from "./store/StoreHooks";
import { RootState } from "./store/Store";
import { CommonRoutes } from "./CommonRoutes";

const App = () => {
  const isAuthenticated = useAppSelector(
    (state: RootState) => state.auth.isAuthenticated
  );

  return (
    <div className="container-fluid">
      {isAuthenticated ? (
        <div>
          <Navigation />
          <AppRoutes />
        </div>
      ) : (
        <CommonRoutes />
      )}
    </div>
  );
};

export default App;
