import React, { FC, useEffect } from "react";
import { Navigate } from "react-router-dom";
import { RootState } from "./store/store";
import { useAppSelector } from "./store/storeHooks";

const PrivateRoute: FC<any> = ({ element }) => {
  const [isAuthenticated, setIsAuthenticated] = React.useState(false);
  const isAuthenticatedState = useAppSelector(
    (state: RootState) => state.auth.isAuthenticated
  );

  useEffect(() => {
    setIsAuthenticated(isAuthenticatedState ? true : false);
  }, [isAuthenticatedState]);

  if (isAuthenticated) {
    return element;
  }

  return <Navigate to="/login" />;
};

export default PrivateRoute;
