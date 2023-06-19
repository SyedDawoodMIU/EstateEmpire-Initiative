import { useRef } from "react";
import { PropertyService } from './PropertyService';
import { iProperty } from "../../../types/PropertyTypes";

export const AddHandler = (property: iProperty) => {
    // const formRef = useRef(null);
    // if (property.formRef.current) {
    //     property.formRef.current.reset();
    //   }
}
export const UpdateHandler = (id:string, property: any) => {
    const formRef = useRef(null);
    // const form = formRef.current;
    // const data = {
    //     name : form.name.value,
    //     location : form.location.value,
    //     price : form.price.value,
    //     description : form.description.value,
    // };
    // PropertyService.updatePropertyById(id, data);
}


