import React from "react";
import { AppRoutes } from "./AppRoutes";
import Navigation from "./BaseComponents/Navigation";

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
