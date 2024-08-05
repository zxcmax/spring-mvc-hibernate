package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(int id);

    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);
}
