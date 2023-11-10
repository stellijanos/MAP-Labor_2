package org.online_shop.controllers;

import org.online_shop.repositories.Env;
import org.online_shop.enums.Response;
import org.online_shop.models.User;
import org.online_shop.repositories.UserRepository;

import java.util.List;


public class UserController {

    private final UserRepository _userRepository;


    public UserController(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public Response createUser(String firstname, String lastname, String email, String password) {
        if (logInUser(email, password) == Response.USER_LOGIN_SUCCESSFUL) {
            return Response.USER_EXISTS;
        }
        User user = new User();
        user.set_firstname(firstname);
        user.set_lastname(lastname);
        user.set_email(email);
        user.set_password(password);

        user.set_id(_userRepository.readAll().size() + 1);

        return _userRepository.create(user) ? Response.USER_CREATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }


    public Response updateUser(String newFirstname, String newLastname, String newEmail, String currentEmail) {
        User currentUser = _userRepository.read(currentEmail);

        if (currentUser.get_email() == null) {
            return Response.USER_NOT_FOUND;
        }

        User existingUser = _userRepository.read(newEmail);

        if (!newEmail.isEmpty() && existingUser.get_email() != null)
            return Response.USER_EXISTS;

        User updatedUser = new User();

        updatedUser.set_firstname(newFirstname.isEmpty() ? currentUser.get_firstname() : newFirstname);
        updatedUser.set_lastname(newLastname.isEmpty() ? currentUser.get_lastname() : newLastname);
        updatedUser.set_email(newEmail.isEmpty() ? currentUser.get_email() : newEmail);

        return _userRepository.update(updatedUser, currentEmail) ? Response.USER_UPDATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response updateUserPassword(String currentPassword, String newPassword, String confirmPassword, String currentEmail) {
        User currentUser = _userRepository.read(currentEmail);

        return currentUser.get_email() == null ? Response.USER_NOT_FOUND :
                !currentUser.get_password().equals(currentPassword) ? Response.INCORRECT_PASSWORD :
                        !newPassword.equals(confirmPassword) ? Response.PASSWORDS_DO_NOT_MATCH :
                                _userRepository.updatePassword(newPassword, currentEmail) ? Response.PASSWORD_UPDATE_SUCCESSFUL :
                                        Response.SOMETHING_WENT_WRONG;
    }

    public Response deleteUser(String email, String password) {
        User user = _userRepository.read(email);

        return user.get_email() == null ? Response.INCORRECT_EMAIL :
                !user.get_password().equals(password) ? Response.INCORRECT_PASSWORD :
                        _userRepository.delete(email) ? Response.USER_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response logInUser(String email, String password) {
        User user = _userRepository.read(email);

        return user.get_email() == null ? Response.INCORRECT_EMAIL :
                user.get_password().equals(password) ? Response.USER_LOGIN_SUCCESSFUL : Response.INCORRECT_PASSWORD;
    }

    public User getUser(String email) {

        return _userRepository.read(email);
    }

    public List<User> listAllUsers() {
        return _userRepository.readAll();
    }


    // hardcode admin values until real database connection does not exist
    public void createAdmin() {

        Env env = new Env();
        User user = new User();

        user.set_firstname(env.load().get("ADMIN_FIRSTNAME"));
        user.set_lastname(env.load().get("ADMIN_LASTNAME"));
        user.set_email(env.load().get("ADMIN_EMAIL"));
        user.set_password(env.load().get("ADMIN_PASSWORD"));

        _userRepository.create(user);
    }
}
