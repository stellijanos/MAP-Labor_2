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
        _userView.print_viewAll(_userRepository.readAll());
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
