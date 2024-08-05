package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(int id);

    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);
}
