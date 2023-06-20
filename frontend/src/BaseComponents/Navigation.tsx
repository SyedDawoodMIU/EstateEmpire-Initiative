import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

function Navigation() {
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
            <Nav.Link href="#link">Sign In</Nav.Link>
            <Nav.Link href="#link">Register</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navigation;
