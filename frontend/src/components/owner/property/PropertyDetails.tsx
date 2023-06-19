import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { PropertyService } from "./PropertyService";
import { iProperty } from "../../../types/PropertyTypes";

const PropertyDetails = (props: iProperty) => {
  const params: any = useParams();
  const navigate = useNavigate();

  const [propertyDetail, setPropertyDetail] = useState({});
  const [flag, setFlag] = useState(false);

  useEffect(() => {
    PropertyService.getPropertyById(params.id).then((response: any) => {
      setPropertyDetail(response);
    });
  }, [flag]);

  const deleteProperty = () => {
    PropertyService.deletePropertyById(params.id).then((response: any) => {
      setFlag(!flag);
      navigate("/properties");
    });
  };

  return (
    <div>
      <div className="Content">
        <p>{propertyDetail.title}</p>
        <div className="Info">
          <br />
          <div className="location">{propertyDetail.location}</div>
          <div className="price">{propertyDetail.price}$</div>
          <div className="image">{propertyDetail.imageUrl}</div>
          <div className="description">{propertyDetail.description}</div>
        </div>
        <button type="submit" className="btn btn-primary" onClick={deleteProperty}>
          Delete
        </button>
      </div>
    </div>
  );
};

export default PropertyDetails;
