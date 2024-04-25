import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    private static MySQLConnector connector;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/haushaltsverwaltung";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public MySQLConnector() throws SQLException {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (Exception e){
            throw e;
        }
    }

    public static Connection getInstance() throws SQLException {
        if (connector == null){
            connector = new MySQLConnector();
        }else if (connector.connection.isClosed()){
            connector = new MySQLConnector();
        }
        return connector.connection;
    }
}
