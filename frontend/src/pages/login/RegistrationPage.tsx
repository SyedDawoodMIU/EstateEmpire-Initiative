import React, { useEffect, useState } from "react";
import { iUserData } from "../../types/UserTypes";
import { isTokenExpired, handleRegistration } from "../../auth/AuthAction";
import { useAppDispatch } from "../../store/storeHooks";
import { Form, Button, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const RegistrationPage = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState<"OWNER" | "CUSTOMER">("OWNER");

  useEffect(() => {
    if (!isTokenExpired()) {
      navigate("/dashboard");
    }
  }, [navigate]);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();

    // Create a new user data object
    const userData: iUserData = {
      name,
      email,
      password,
      role: userType,
    };

    dispatch(handleRegistration(userData));

    // Reset the form inputs
    setName("");
    setEmail("");
    setPassword("");
    setUserType("OWNER");
    navigate("/dashboard");
  };

  return (
    <div>
      <h1 className="text-center mb-4">Register</h1>

      <Container
        className="d-flex justify-content-center "
        style={{ height: "100vh" }}
      >
        <Form
          onSubmit={handleSubmit}
          className="w-100"
          style={{ maxWidth: "400px" }}
        >
          <Form.Group controlId="name" className="mb-3">
            <Form.Label>Name:</Form.Label>
            <Form.Control
              type="text"
              value={name}
              onChange={(event) => setName(event.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="email" className="mb-3">
            <Form.Label>Email:</Form.Label>
            <Form.Control
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="password" className="mb-3">
            <Form.Label>Password:</Form.Label>
            <Form.Control
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="userType" className="mb-3">
            <Form.Label>User Type:</Form.Label>
            <Form.Control
              as="select"
              value={userType}
              onChange={(event) =>
                setUserType(event.target.value as "OWNER" | "CUSTOMER")
              }
            >
              <option value="OWNER">Owner</option>
              <option value="CUSTOMER">Customer</option>
            </Form.Control>
          </Form.Group>

          <Button type="submit">Register</Button>
        </Form>
      </Container>
    </div>
  );
};

export default RegistrationPage;
