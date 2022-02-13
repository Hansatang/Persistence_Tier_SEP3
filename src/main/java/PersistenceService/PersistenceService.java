package PersistenceService;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class PersistenceService {
    /**
     * Instance variables
     */
    protected static final Gson gson = new Gson();
    private final String URL = "jdbc:postgresql://tai.db.elephantsql.com:5432/seitjdhj";
    private final String USER = "seitjdhj";
    private final String PASSWORD = "9LEmAjua_Uo0YR5sGqAFHn0Kgm9DDKu1";
    protected static Connection connection;

    /**
     * Constructor, throws exception if it is not connected.
     */
    public PersistenceService() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}


