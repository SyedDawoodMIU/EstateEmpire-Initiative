import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import {PropertyService} from './PropertyService';
import Property from './Property';
import { iProperty } from '../../../types/PropertyTypes';

const PropertyList = () => {
    const navigate = useNavigate();

    const [property, setProperty] = useState([]);
    const [flag, setFlag] = useState(false);

    useEffect(() => {
        PropertyService.getAllProperties().then((response) => {
            setProperty(response.data);
        })
    }, [flag]);

    const propertyList = property.map((property:iProperty) => {
        return <Link to={`/properties/${property.propertyId}`} key={property.propertyId}>
                <Property 
                // title={property.title}
                // location={property.address?.city}
                // price={property.propertyDetails?.rentAmount}
                // imageUrl={property.imageUrl}
                // description={property.description}
                />  
            </Link> 
        });
    return (
        <div>
            {propertyList}
            {/* <button onClick={() => navigate('/addproperty')}>Add Property</button> */}
        </div>
    )
}

export default PropertyList