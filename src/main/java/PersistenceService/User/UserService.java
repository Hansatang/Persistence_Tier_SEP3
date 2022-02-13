package PersistenceService.User;

import Model.User;
import PersistenceService.PersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService extends PersistenceService implements IUserService{

    /**
     * Method which takes users from the table users
     * and stores them in userList to retrieve them
     * @param usersName
     * @return userList, HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<List<User>> getUserList(String usersName) {

        try {
            List<User> userList = new ArrayList<>();
            String sqlStatement = "SELECT * FROM notelender.users WHERE username LIKE ?";
            PreparedStatement getGroup = connection.prepareStatement(sqlStatement);
            getGroup.setString(1, "%" + usersName + "%");
            ResultSet rs = getGroup.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), null));
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Method validates user by checking username from useres table in database
     * @param user
     * @return validatedUser, HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<User> validateUser(String user) {
        User validatedUser = null;
        User temp = gson.fromJson(user, User.class);
        String sqlStatement = "SELECT * FROM notelender.users WHERE username =  ?";
        try {
            PreparedStatement validateUser = connection.prepareStatement(sqlStatement);
            validateUser.setString(1, temp.getUsername());
            ResultSet rs = validateUser.executeQuery();
            while (rs.next()) {
                validatedUser = new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5));
            }
            return new ResponseEntity<>(validatedUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method adds new user in useres table in database
     * @param user
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> registerUser(String user) {
        User temp = gson.fromJson(user, User.class);
        String sqlStatement = "INSERT INTO notelender.users (firstname,lastname,username,password) VALUES (?,?,?,?)";
        try {
            PreparedStatement registerUser = connection.prepareStatement(sqlStatement);
            registerUser.setString(1, temp.getFirstName());
            registerUser.setString(2, temp.getLastName());
            registerUser.setString(3, temp.getUsername());
            registerUser.setString(4, temp.getPassword());
            registerUser.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method can edit user by updating password in users table in database
     * @param user
     * @param user_id
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> editUser(String user, int user_id) {
        User temp = gson.fromJson(user, User.class);
        String sqlStatement = "UPDATE notelender.users SET password= ? WHERE id= ?";
        try {
            PreparedStatement editUser = connection.prepareStatement(sqlStatement);
            editUser.setString(1, temp.getPassword());
            editUser.setInt(2, user_id);
            editUser.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Removes user from useres table in database using id
     * @param userId
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> deleteUser(int userId) {
        String sqlStatement = "DELETE FROM notelender.users WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            statement.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
