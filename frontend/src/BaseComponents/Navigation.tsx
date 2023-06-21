import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { RootState } from "../store/store";
import { useAppDispatch, useAppSelector } from "../store/storeHooks";
import { handleLogout } from "../auth/AuthAction";
import { useNavigate } from "react-router-dom";

function Navigation() {
  const dispatch = useAppDispatch();
  const isAuthenticated = useAppSelector(
    (state: RootState) => state.auth.isAuthenticated
  );

  return (
    <Navbar expand="lg" bg="light" variant="light">
      <Container>
        <Navbar.Brand href="#home">EstateEmpire</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#home">Buy</Nav.Link>
            <Nav.Link href="#link">Rent</Nav.Link>
            <NavDropdown title="Sell" id="sell-dropdown">
              <NavDropdown.Item href="#action/3.1">
                Sell Your Home
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                List Your Property
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link href="#link">Mortgages</Nav.Link>
            <Nav.Link href="#link">Agents</Nav.Link>
            <Nav.Link href="#link">Listings</Nav.Link>
            <Nav.Link href="#link">Blog</Nav.Link>
          </Nav>
          <Nav className="ml-auto">
            <Nav.Link
              href="login"
              onClick={
                isAuthenticated
                  ? () => {
                      dispatch(handleLogout());
                    }
                  : undefined
              }
            >
              Sign {isAuthenticated ? "Out" : "In"}
            </Nav.Link>
            {isAuthenticated ?? <Nav.Link href="#link">Register</Nav.Link>}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navigation;
