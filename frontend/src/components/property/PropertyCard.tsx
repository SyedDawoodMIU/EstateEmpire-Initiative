import React from "react";
import { iProperty } from "../../types/PropertyTypes";

interface PropertyCardProps {
  property: iProperty;
}

const PropertyCard: React.FC<PropertyCardProps> = ({ property }) => {
  return (
    <div className="property-card">
      <h3>{property.title}</h3>
      <p>Price: ${property.price}</p>
      {/* Add more property details here */}
    </div>
  );
};

export default PropertyCard;
