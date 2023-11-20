package org.online_shop.controllers;

import org.online_shop.repositories.Env;
import org.online_shop.enums.Response;
import org.online_shop.models.User;
import org.online_shop.repositories.FavouriteRepository;
import org.online_shop.repositories.ProductRepository;
import org.online_shop.repositories.UserRepository;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;


public class UserController {

    private final UserRepository _userRepository;
    private final FavouriteRepository _favouriteRepository = new FavouriteRepository();

    private final ProductRepository _productRepository = new ProductRepository();


    public UserController(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public Response createUser(String firstname, String lastname, String email, String password) {
        if (logInUser(email, password) == Response.USER_LOGIN_SUCCESSFUL) {
            return Response.USER_EXISTS;
        }
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        user.setId(_userRepository.readAll().size() + 1);

        return _userRepository.create(user) ? Response.USER_CREATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }


    public boolean isAvailableEmail(String email) {
        User user = _userRepository.read(email);
        return user.getEmail() == null;
    }

    public Response updateUser(String newFirstname, String newLastname, String newEmail, String currentEmail) {
        User currentUser = _userRepository.read(currentEmail);

        if (currentUser.getEmail() == null)
            return Response.USER_NOT_FOUND;

        if (!newEmail.isEmpty() && !isAvailableEmail(newEmail))
            return Response.USER_EXISTS;

        User updatedUser = new User();

        currentUser.notifyObservers();

        updatedUser.setFirstname(newFirstname.isEmpty() ? currentUser.getFirstname() : newFirstname);
        updatedUser.setLastname(newLastname.isEmpty() ? currentUser.getLastname() : newLastname);
        updatedUser.setEmail(newEmail.isEmpty() ? currentUser.getEmail() : newEmail);

        return _userRepository.update(updatedUser, currentEmail) ? Response.USER_UPDATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response updateUserPassword(String currentPassword, String newPassword, String confirmPassword, String currentEmail) {
        User currentUser = _userRepository.read(currentEmail);

        return currentUser.getEmail() == null ? Response.USER_NOT_FOUND :
//                !BCrypt.checkpw(currentPassword, currentUser.getPassword()) ? Response.INCORRECT_PASSWORD :
//                        !newPassword.equals(confirmPassword) ? Response.PASSWORDS_DO_NOT_MATCH ;
//                                _userRepository.updatePassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()), currentEmail) ? Response.PASSWORD_UPDATE_SUCCESSFUL :
                                        Response.SOMETHING_WENT_WRONG;
    }

    public Response deleteUser(String email, String password) {
        User user = _userRepository.read(email);

        return user.getEmail() == null ? Response.INCORRECT_EMAIL :
                !BCrypt.checkpw(password, user.getPassword()) ? Response.INCORRECT_PASSWORD :
                        _userRepository.delete(email) ? Response.USER_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }


    public Response removeALlUsers(String adminPassword) {
        Env env = new Env();
        return !BCrypt.checkpw(adminPassword, env.load().get("ADMIN_PASSWORD")) ?
                Response.INCORRECT_PASSWORD : _userRepository.deleteAll() ? Response.ALL_USERS_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response logInUser(String email, String password) {
        User user = _userRepository.read(email);

        return user.getEmail() == null ? Response.INCORRECT_EMAIL :
                BCrypt.checkpw(password, user.getPassword()) ? Response.USER_LOGIN_SUCCESSFUL : Response.INCORRECT_PASSWORD;
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

        user.setFirstname(env.load().get("ADMIN_FIRSTNAME"));
        user.setLastname(env.load().get("ADMIN_LASTNAME"));
        user.setEmail(env.load().get("ADMIN_EMAIL"));
        user.setPassword(env.load().get("ADMIN_PASSWORD"));

        _userRepository.create(user);
    }
}
