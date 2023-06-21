import React, { useState, FormEvent, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAppSelector, useAppDispatch } from "../../store/StoreHooks";
import { isTokenExpired, handleLogin } from "../../auth/AuthAction";
import { Container, Row, Form, Button } from "react-bootstrap";
import { getToken } from "../../utils/tokenUtils";
import { debug } from "console";

const LoginPage = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const login = async (e: FormEvent) => {
    e.preventDefault();
    navigate("/dashboard");

    try {
      dispatch(handleLogin(email, password));
    } catch (error: any) {
      setError(error.message);
    }
  };


  return (
    <div>
      <h1 className="text-center mb-4">Login</h1>
      <Container
        className="d-flex justify-content-center "
        style={{ height: "100vh" }}
      >
        <Form onSubmit={login} className="w-100" style={{ maxWidth: "400px" }}>
          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Link to="/register">Register</Link>
          </Form.Group>
          <Button type="submit" variant="primary">
            Login
          </Button>
        </Form>
      </Container>
    </div>
  );
};

export default LoginPage;
