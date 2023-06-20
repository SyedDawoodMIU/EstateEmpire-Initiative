import React, { useState } from "react";
import { iProperty } from "../../types/PropertyTypes";

interface PropertyFormProps {
  onSubmit: (property: iProperty) => void;
}

const PropertyForm: React.FC<PropertyFormProps> = ({ onSubmit }) => {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();

    // Create a new property object
    const property: iProperty = {
      title,
      price,
    };

    // Pass the property object to the parent component for submission
    onSubmit(property);

    // Reset the form inputs
    setTitle("");
    setPrice(0);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="title">Title:</label>
        <input
          type="text"
          id="title"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="price">Price:</label>
        <input
          type="number"
          id="price"
          value={price}
          onChange={(event) => setPrice(Number(event.target.value))}
        />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default PropertyForm;
