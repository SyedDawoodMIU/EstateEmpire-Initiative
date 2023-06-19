import React from 'react'
import { iProperty } from '../../../types/PropertyTypes'

const Property = (props:iProperty) => {

    return (
        <div className="Content">
            <p>{props.title}</p>
            <div className="Info">
                <br/>
                <div className="propertyLocation">{props.location}</div>
                <div className="propertyPrice">{props.price}$</div>
                <div className="propertyImage">{props.imageUrl}</div>
                <div className="propertyDescription">{props.description}</div>
            </div>
        </div>
    )
}

export default Property
