import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class SQLCommands {

    private Connection connection=null;
    private PreparedStatement statement=null;

    private final String urlRestaurant="jdbc:mysql://stoves-dev.duckdns.org:50931/restaurant?serverTimezone=CST";
    private final String urlStudent="jdbc:mysql://stoves-dev.duckdns.org:50931/student?serverTimezone=CST";
    private final String dbDriver="com.mysql.jdbc.Driver";
    private final String username="table_editor";
    private final String password="!sleekPanda!";

    // NOTICE!
    // setConnection and readRestaurantDataBase are still in development.
    // Will replace both of the other readDB functions when completed.
    public CachedRowSet setConnection(int db) throws SQLException {
        String url=null;
        switch (db){
            case 1: url=urlRestaurant;
                    break;
            case 2: url=urlStudent;
                    break;
            default: url="Invalid URL";
                    break;
        }
        CachedRowSet cachedRowset=RowSetProvider.newFactory().createCachedRowSet();
        cachedRowset.setUrl(url);
        cachedRowset.setUsername(username);
        cachedRowset.setPassword(password);

        return cachedRowset;
    }
    public CachedRowSet readDataBase(int dbID, String query) throws Exception {
        int db=dbID;
        try {
            CachedRowSet cachedRowset=setConnection(db);
            cachedRowset.setCommand(query);
            statement=connection.prepareStatement(cachedRowset.getCommand());
            statement.execute();

            return cachedRowset;
        }catch (Exception e){
            System.err.print("ERROR!\nFunction: readDataBase\nClass: SQLCommands\n");
            throw e;
        }finally {
            close();
        }
    }

    public CachedRowSet readRestaurantDataBase(String query) throws Exception {
        try{
            CachedRowSet cachedRowset=setConnection(1);
            cachedRowset.setCommand(query);
            cachedRowset.execute();

            return cachedRowset;
        }catch (Exception e){
            throw e;
        }finally {
            close();
        }
    }

    public CachedRowSet readStudentDatabase(String query) throws Exception {
        try{

            CachedRowSet cachedRowset=setConnection(2);
            cachedRowset.setCommand(query);
            cachedRowset.execute();

            return cachedRowset;
        }catch (Exception e){
            throw e;
        }finally {
            close();
        }
    }

    public void close(){
        try{
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (Exception e){

        }
    }
}
