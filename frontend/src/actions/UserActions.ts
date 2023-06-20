import {
  getUsersStart,
  getUsersSuccess,
  getUsersFailure,
} from "../store/UserStore";
import { deleteUser, getUsers, updateUser } from "../services/UserService";
import { iUserData } from "../types/UserTypes";

export const fetchUsers = () => async (dispatch: any) => {
  dispatch(getUsersStart());
  getUsers()
    .then((response) => {
      dispatch(getUsersSuccess(response));
    })
    .catch((error) => {
      dispatch(getUsersFailure(error.message));
    });
};

export const updateUserAction =
  (id: number, data: iUserData) => async (dispatch: any) => {
    updateUser(id, data).catch((error) => {
      dispatch(getUsersFailure(error.message));
    });
  };

export const deleteUserAction = (id: number) => async (dispatch: any) => {
  deleteUser(id).catch((error) => {
    dispatch(getUsersFailure(error.message));
  });
};
