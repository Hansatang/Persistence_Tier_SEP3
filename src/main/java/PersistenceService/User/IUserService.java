package PersistenceService.User;

import Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<User> validateUser(String json);

    ResponseEntity<Void> registerUser(String json);



    ResponseEntity<Void> editUser(String json, int user_id);

    ResponseEntity<Void> deleteUser(int userId);

    ResponseEntity<List<User>> getUserList(String json);
}
