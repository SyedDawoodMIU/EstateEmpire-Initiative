import React, { useEffect, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { iUserData } from "../../types/UserTypes";
import { useAppDispatch, useAppSelector } from "../../store/StoreHooks";
import { RootState } from "../../store/Store";
import {
  deleteUserAction,
  fetchUsers,
  updateUserAction,
} from "../../actions/UserActions";

const UserGrid = () => {
  const dispatch = useAppDispatch();
  const usersState = useAppSelector((state: RootState) => state.user.users);
  const [users, setUsers] = useState<iUserData[]>(usersState ?? []);

  useEffect(() => {
    dispatch(fetchUsers());
  }, [dispatch]);

  useEffect(() => {
    setUsers(usersState ?? []);
  }, [usersState]);

  const handleDisable = (userId: number | undefined) => {
    if (userId) {
      const updatedUsers = users.map((user) => {
        if (user.userId === userId) {
          dispatch(updateUserAction(user.userId, user));
          return { ...user, disabled: !user.isDisabled };
        }
        return user;
      });
      setUsers(updatedUsers);
    }
  };

  const handleRemove = (userId: number | undefined) => {
    if (userId) {
      const deletedUser = users.filter((user) => user.userId === userId);
      if (deletedUser) {
        dispatch(deleteUserAction(userId));
      }
      const updatedUsers = users.filter((user) => user.userId !== userId);
      setUsers(updatedUsers);
    }
  };

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Role</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user) => (
          <tr key={user.userId}>
            <td>{user.userId}</td>
            <td>{user.name}</td>
            <td>{user.role}</td>
            <td>
              <Button
                variant={user.isDisabled ? "success" : "danger"}
                onClick={() => handleDisable(user.userId)}
              >
                {user.isDisabled ? "Enable" : "Disable"}
              </Button>{" "}
              <Button
                variant="danger"
                onClick={() => handleRemove(user.userId)}
              >
                Remove
              </Button>{" "}
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default UserGrid;
