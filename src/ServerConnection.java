import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerConnection {

    public String dbName;
    public String userName;
    public String password;
    public String urlString;

    private String serverURL;
    private String serverDbSchema;
    private String serverTimeZone;

    public ServerConnection(int db) throws SQLException {
        getConnectionSettings(db);
    }

    // Empty constructor
    public ServerConnection() {

    }

    // Establish the connection settings
    public Connection getConnectionSettings(int db) throws SQLException {
        Connection connection=null;
        Properties connectionProperties=new Properties();

        serverURL="jdbc:mysql://stoves-dev.duckdns.org:50931";
        serverTimeZone="?serverTimezone=CST";
        userName="table_editor";
        password="!sleekPanda!";

        connectionProperties.put("user",userName);
        connectionProperties.put("password",password);

        switch (db){
            case 1: serverDbSchema="/restaurant";
                    break;
            case 2: serverDbSchema="/student";
                    break;
            default: serverDbSchema="InvalidURL";
        }
        urlString=serverURL+serverDbSchema+serverTimeZone;
        connection=DriverManager.getConnection(urlString,connectionProperties);
        return connection;
    }

    // Close the current connection
    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            System.err.print(e);
        }
    }

    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    public String getUrlString() { return urlString; }

}
