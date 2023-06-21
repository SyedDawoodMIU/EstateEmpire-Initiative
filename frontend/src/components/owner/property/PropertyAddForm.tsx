import React, { useRef } from "react";
import { PropertyService } from './PropertyService';
import { iProperty } from "../../../types/PropertyTypes";

const PropertyForm = (props:iProperty) => {
  const formRef = useRef(null);

  const AddHandler = async (event:React.FormEvent<HTMLFormElement>)=> {
    event.preventDefault();
    const form = formRef.current;
    if (form) {
      const formData = new FormData(form);
      const propertyData = Object.fromEntries(formData.entries());
      try {
        await PropertyService.postProperty(propertyData);
      } catch (error) {
        console.log("Error:", error);
      }
    }
  }

  return (
    <>
      <div>PropertyForm</div>
      <form ref={formRef} onSubmit={AddHandler}>
        <div className="form-group">
          <label htmlFor="name">Property Name: </label>
          <input
            name="name"
            type="text"
            className="form-control"
            id="name"
            placeholder="Enter Property Name"
          />
        </div>
        <div className="form-group">
          <label htmlFor="location">Property Location: </label>
          <input
            name="location"
            type="text"
            className="form-control"
            id="location"
            placeholder="Enter Property Location"
          />
        </div>
        <div className="form-group">
          <label htmlFor="price">Property Price: </label>
          <input
            name="price"
            type="text"
            className="form-control"
            id="price"
            placeholder="Enter Property Price"
          />
        </div>
        <div className="form-group">
          <label htmlFor="image">Property Image: </label>
          <input
            name="image"
            type="text"
            className="form-control"
            id="image"
            placeholder="Enter Property Image"
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Property Description: </label>
          <input
            type="text"
            name="description"
            className="form-control"
            id="description"
            placeholder="Enter Property Description"
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add
        </button>
        {/* <button type="submit" className="btn btn-primary">
          Update
        </button> */}
        
      </form>
    </>
  );
};

export default PropertyForm;
