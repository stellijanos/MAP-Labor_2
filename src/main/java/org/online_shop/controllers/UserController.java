package org.online_shop.controllers;

import org.online_shop.Env;
import org.online_shop.models.User;
import org.online_shop.repositories.UserRepository;
import org.online_shop.views.UserView;


public class UserController extends Controller {

    private final UserView _userView;
    private final UserRepository _userRepository;


    public UserController(UserView userView, UserRepository userRepository) {
        _userView = userView;
        _userRepository = userRepository;
    }

    public Response createUser(String firstname, String lastname, String email, String password) {
        if (logInUser(email, password) == Response.SESSION_START) {
            return Response.USER_EXISTS;
        }
        User user = new User();
        user.set_firstname(firstname);
        user.set_lastname(lastname);
        user.set_email(email);
        user.set_password(password);

        user.set_id(_userRepository.readAll().size() + 1);


        if (!_userRepository.create(user)) {
            return Response.SOMETHING_WENT_WRONG;
        }
        return Response.USER_CREATED_SUCCESSFULLY;
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


        if (_userRepository.update(updatedUser, currentEmail)) {
            return Response.USER_UPDATED_SUCCESSFULLY;
        }
        return Response.SOMETHING_WENT_WRONG;
    }


    public Response logInUser(String email, String password) {
        User user = _userRepository.read(email);
        if (user.get_email() == null) {
            return Response.INCORRECT_EMAIL;
        }
        if (!user.get_password().equals(password)) {
            return Response.INCORRECT_PASSWORD;
        }
        return Response.SESSION_START;
    }

    public User getUser(String email) {

        return _userRepository.read(email);
    }

    public void listAllUsers() {
        _userView.print_viewAll(_userRepository.readAll());
        System.out.println(_userRepository.readAll().size());
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
