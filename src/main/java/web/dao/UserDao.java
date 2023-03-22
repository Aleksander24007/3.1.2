package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    User getUserById(int id);

    void removeUser(int id);

    void updateUser(User user);

    List<User> getAllUser();
}
