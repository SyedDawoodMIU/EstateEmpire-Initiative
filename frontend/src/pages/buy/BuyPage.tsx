import React from "react";
import PropertyCard from "../../components/property/PropertyCard";

const BuyPage: React.FC = () => {
  // Assume you have an array of properties available for purchase
  const properties = [
    { id: 1, title: "Property 1", price: 100000 },
    { id: 2, title: "Property 2", price: 150000 },
    { id: 3, title: "Property 3", price: 200000 },
    // Add more properties here...
  ];

  return (
    <div>
      <h2>Properties available for purchase</h2>
      {properties.map((property) => (
        <PropertyCard key={property.id} property={property} />
      ))}
    </div>
  );
};

export default BuyPage;
