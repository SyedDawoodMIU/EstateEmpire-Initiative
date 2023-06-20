import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { PropertyService } from "./PropertyService";
import { iProperty } from "../../../types/PropertyTypes";
import PropertiesService from "../../../services/PropertiesService";
import { Badge, Button, Col, Row } from "react-bootstrap";

const PropertyDetails = (props: iProperty) => {
  const params: any = useParams();
  const navigate = useNavigate();

  const [propertyDetail, setPropertyDetail] = useState({} as iProperty);
  const [flag, setFlag] = useState(false);

  useEffect(() => {
    PropertiesService.getPropertyById(params.id).then((response: any) => {
      setPropertyDetail(response);
    });
  }, [flag]);

  const deleteProperty = () => {
    PropertyService.deletePropertyById(params.id).then((response: any) => {
      setFlag(!flag);
      navigate("/properties");
    });
  };

  const firstElement = propertyDetail.propertyImage?.at(0);

  return (
    <Row>
      <Col lg="1">
          <ul className="nav nav-tabs row text-center pro-details" id="myTab" role="tablist">
            {
              propertyDetail.propertyImage?.map(image => {
                return <li className="nav-item col-lg-12 mb-2">
                            <a href="#home">
                              <img className="img-fluid active h-100" src={image.downloadURL}  id={image.id} data-toggle="tab" role="tab" aria-controls="home" aria-selected="false"/>
                            </a>
                      </li>
              })
            }
          </ul>
      </Col>
      <Col lg="4">
          <div className="tab-content row h-100 d-flex justify-content-center align-items-center" id="myTabContent">
              <div className="tab-pane fade show active col-lg-12" role="tabpanel" aria-labelledby="home-tab">
                <img className="img-fluid" src={firstElement?.downloadURL} />
              </div>
          </div>
      </Col>
      <Col lg="7">
        <Button variant="secondary">
          <i className="bi bi-eye"></i> Views <Badge bg="secondary">{propertyDetail.viewCount}</Badge>
        </Button>
        <h5> Property ID : {propertyDetail.propertyId} </h5>
            <h3> ${propertyDetail.propertyDetails?.rentAmount} </h3>
            <h6>{propertyDetail.propertyDetails?.bedrooms} bds | 
          {" "+propertyDetail.propertyDetails?.bathrooms} bas | 
          {" "+propertyDetail.propertyDetails?.lotSize} sqft -
          House for {propertyDetail.type === 'FOR_BUY' ? 'buy' : 'rent'}</h6>
            <p>
                <strong>Address</strong>
            </p>
            <p>
            {propertyDetail.address?.street}, {propertyDetail.address?.city}, {propertyDetail.address?.state} {propertyDetail.address?.zipCode}
            </p>
            <p>
              <strong>Description</strong>
            </p>
            {propertyDetail.propertyDetails?.description}
      </Col>
    </Row>
  );
};

export default PropertyDetails;
