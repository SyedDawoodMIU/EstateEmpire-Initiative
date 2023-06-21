import axios from "axios";
import { iProperty } from "../../../types/PropertyTypes";

const API = axios.create({
    baseURL: 'http://localhost:8080/api/v1/'
})

const getAllProperties = async () => {
    try {
        let data = [];
        data = (await API.get('properties')).data;
        return data;
    } catch (error) {
        console.log(error)
        return [];
    }
};

const getPropertyById = async (id:string)=> {
    try{
        let result:any = [];
        result = await API.get('properties/', {params: {id: id}});
    } catch(error) {
        console.log("Error with GetPropertyById: ", error);
        return {};
    }
} 
const postProperty = async (data:any) => {
    try {
        let result:any = [];
        result = await API.post('properties', data);
        return result;
    } catch (error) {
        console.log("Error with POST: ", error);
        return [];
    }
};

const deletePropertyById = async (id:string)=> {
    try{
        let result:any = [];
        result = await API.delete('properties/', {params: {id: id}});
    } catch(error) {
        console.log("Error with Delete: ", error);
        return [];
    }
}

const updatePropertyById = async (id:string, data:iProperty)=> {
    try{
        let result:any = [];
        result = await API.put('properties/', {params: {id: id}});
    } catch(error) {
        console.log("Error with Update: ", error);
        return [];
    }
}

export const PropertyService = {
    getAllProperties,
    getPropertyById,
    postProperty,
    deletePropertyById,
    updatePropertyById
}