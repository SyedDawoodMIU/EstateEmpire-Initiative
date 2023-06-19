import React from "react";
import PropertyForm from "../../components/property/PropertyForm";

const SellPage: React.FC = () => {
  return (
    <div>
      <h2>List your property for sale</h2>
      <PropertyForm onSubmit={(property) => console.log(property)} />
    </div>
  );
};

export default SellPage;
