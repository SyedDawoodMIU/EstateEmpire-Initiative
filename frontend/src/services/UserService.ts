import { get, post, put, patch, del } from "./BaseService";

export const getUsers = async () => await get("/users");
export const getUser = async (id: number) => await get(`/users/${id}`);
export const createUser = async (data: any) => await post("/users", data);
export const updateUser = async (id: number, data: any) =>
  await put(`/users/${id}`, data);
export const deleteUser = async (id: number) => await del(`/users/${id}`);
