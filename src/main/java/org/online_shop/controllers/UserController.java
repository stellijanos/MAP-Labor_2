package org.online_shop.controllers;

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

        if (!_userRepository.create(user)) {
            return Response.SOMETHING_WENT_WRONG;
        }
        return Response.USER_CREATED_SUCCESSFULLY;
    }

    public Response logInUser(String email, String password) {
        User user = _userRepository.read(email);
        if (user.get_email() == null) {
            return Response.SESSION_DESTROY;
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
        _userView.print_viewAll(_userRepository.users);
    }


    public void createAdmin() {
        User user = new User();
        user.set_firstname("Janos");
        user.set_lastname("Admin");
        user.set_email("admin@janos");
        user.set_password("Micutaetop5");

        _userRepository.create(user);
    }

}
