import React from 'react'
import { iProperty } from '../../../types/PropertyTypes'

const Property = (props:iProperty) => {

    return (
        <div className="Content">
            <p>{props.propertyId}</p>
            <div className="Info">
                <br/>
                <div className="propertyLocation">{props.address?.country}</div>
                <div className="propertyPrice">{props.propertyDetails?.rentAmount}$</div>
                {/* <div className="propertyImage">{props.imageUrl}</div> */}
                {/* <div className="propertyDescription">{props.description}</div> */}
            </div>
        </div>
    )
}

export default Property
