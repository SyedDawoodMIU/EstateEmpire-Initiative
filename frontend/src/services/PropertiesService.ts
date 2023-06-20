import React, { useEffect } from 'react'
import { get } from './baseService';

const PropertiesService =  {

  getAllProperty: async () => {
    const response =  await get("properties","",true);
    return response;
  },

  getPropertyById:async (id:number) => {
    const response = await get("properties/"+id,"", true);
    return response;
  }

}

export default PropertiesService;
