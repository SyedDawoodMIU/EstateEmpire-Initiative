import React from "react";
import { iProperty } from "../../types/PropertyTypes";
import "./property.css"
import { Link } from "react-router-dom";
import { Button, Card } from "react-bootstrap";

interface PropertyCardProps {
  property: iProperty;
}

const PropertyCard: React.FC<PropertyCardProps> = ({ property }) => {

  const firstImage = property.propertyImage?.at(0);

  return (
    <Link to={"/properties/"+property.propertyId}>
      <Card className="inline-blc" style={{ width: '20rem', float: 'left' }}>
      <Card.Img variant="top" src={firstImage?.downloadURL}/>
      <Card.Body>
        <Card.Title>${property.propertyDetails?.rentAmount}</Card.Title>
        <Card.Text>
         <small>
            {property.propertyDetails?.bedrooms} bds | 
            {property.propertyDetails?.bathrooms} bas | 
            {property.propertyDetails?.lotSize} sqft -
            House for {property.type === 'FOR_BUY' ? 'buy' : 'rent'}
          </small><br/>
          <small>
            {property.address?.street}, {property.address?.city}, {property.address?.state} {property.address?.zipCode}
          </small>
        </Card.Text>
        <Button variant="primary">Add to favorites</Button>
      </Card.Body>
    </Card>
    </Link>
  );
};

export default PropertyCard;
