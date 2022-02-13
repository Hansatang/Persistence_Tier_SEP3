package PersistenceServer;

import Model.User;
import PersistenceService.User.IUserService;
import PersistenceService.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /**
     * Instance variable
     */
    private final IUserService userService;

    /**
     * Constructor for PersistenceServerController
     */
    public UserController() {
        this.userService = new UserService();
    }

    /**
     * Get userList
     *
     * @param username
     * @return persistenceService.getUserList(username)
     */
    @GetMapping("/users/{username}")
    public ResponseEntity<List<User>> getUserList(@PathVariable(value = "username") String username) {
        return userService.getUserList(username);
    }

    /**
     * Validate user
     *
     * @param requestBody
     * @return persistenceService.validateUser(requestBody)
     */
    @PostMapping("/user")
    public ResponseEntity<User> ValidateUser(@RequestBody String requestBody) {
        return userService.validateUser(requestBody);
    }

    /**
     * Registers user
     *
     * @param requestBody
     * @return persistenceService.registerUser(requestBody)
     */
    @PostMapping("/unregisteruser")
    public ResponseEntity<Void> registerUser(@RequestBody String requestBody) {
        return userService.registerUser(requestBody);
    }

    /**
     * Edit user
     *
     * @param requestBody
     * @param user_id
     * @return persistenceService.editUser(requestBody, user_id)
     */
    @PostMapping("/user/{user_id}")
    public ResponseEntity<Void> EditUser(@RequestBody String requestBody, @PathVariable(value = "user_id") int user_id) {
        return userService.editUser(requestBody, user_id);
    }

    /**
     * Delete user
     *
     * @param userId
     * @return persistenceService.deleteUser(userId)
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") int userId) {
        return userService.deleteUser(userId);
    }
}