import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.sql.Connection;

public class SQLCommands {


    // NOTICE!
    // setConnection and readRestaurantDataBase are still in development.
    // Will replace both of the other readDB functions when completed.
    public CachedRowSet creatCachedRowSet(ServerConnection connection,String query) throws SQLException {

        CachedRowSet cachedRowset = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowset.setUrl(connection.urlString);
        cachedRowset.setUsername(connection.userName);
        cachedRowset.setPassword(connection.password);
        cachedRowset.setCommand(query);

        return cachedRowset;
    }


    public CachedRowSet readDataBase(int dbID, String query) throws Exception {
        ServerConnection connection =new ServerConnection(dbID);
        CachedRowSet cachedRowSet= creatCachedRowSet(connection,query);
        try {
            cachedRowSet.execute();
            return cachedRowSet;
        }catch (Exception e){
            System.err.print("ERROR!\nFunction: readDataBase\nClass: SQLCommands\n");
            throw e;
        }finally {
            connection.closeConnection();
        }
    }

//    public CachedRowSet readRestaurantDataBase(String query) throws Exception {
//        ServerConnection connection =new ServerConnection(1);
//
//        try{
//            CachedRowSet cachedRowSet=new CachedRowSet(connection);
//
//            cachedRowset.setCommand(query);
//            cachedRowset.execute();
//
//            return cachedRowset;
//        }catch (Exception e){
//            throw e;
//        }finally {
//           connection.closeConnection();
//        }
//    }
//
//    public CachedRowSet readStudentDatabase(String query) throws Exception {
//        try{
//
//            CachedRowSet cachedRowset=setConnection(2);
//            cachedRowset.setCommand(query);
//            cachedRowset.execute();
//
//            return cachedRowset;
//        }catch (Exception e){
//            throw e;
//        }finally {
//            close();
//        }
//    }

}
