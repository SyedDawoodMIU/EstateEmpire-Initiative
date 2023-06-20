import React, { useState } from "react";
import { iUserData } from "../../types/UserTypes";
import { handleRegistration } from "../../auth/AuthAction";
import { useAppDispatch } from "../../store/storeHooks";

interface RegistrationFormProps {
  onSubmit: (userData: iUserData) => void;
}

const RegistrationPage = () => {
    const dispatch = useAppDispatch();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState<"OWNER" | "CUSTOMER">("OWNER");

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
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(event) => setName(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="userType">User Type:</label>
        <select
          id="userType"
          value={userType}
          onChange={(event) =>
            setUserType(event.target.value as "OWNER" | "CUSTOMER")
          }
        >
          <option value="Owner">Owner</option>
          <option value="Customer">Customer</option>
        </select>
      </div>
      <button type="submit">Register</button>
    </form>
  );
};

export default RegistrationPage;
