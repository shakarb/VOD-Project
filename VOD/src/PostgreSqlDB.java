import java.sql.*;

public class PostgreSqlDB {
    private static PostgreSqlDB instance;
    private Connection conn;
    private String url = "jdbc:postgresql://localhost:5432/VodDB?characterEncoding=utf8";
    private String username = "postgres";
    private String password = "root";

    private PostgreSqlDB() {
        try {
            // load the driver class
            Class.forName("org.postgresql.Driver");
            // create the connection object
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("DB connection String is wrong : " + ex.getMessage());
        }
    }

    public static PostgreSqlDB getVodInstance(){
        if (instance == null) {
            instance = new PostgreSqlDB();
        }
        return instance;
    }

    public ResultSet fetch(String query, Object... params) throws SQLException {
        ResultSet result = null;
        try {
            // create the statement object
            PreparedStatement stmt = conn.prepareStatement(query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    stmt.setObject(i + 1, param);
                }
            }
            // execute the query
            //stmt.executeUpdate();
            //result = stmt.executeQuery();
            ///*
            if(query.contains("SELECT")) {
                result = stmt.executeQuery();
            } else {
                stmt.executeUpdate();
            }//*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}