import java.sql.*;

public class MySqlDB {
    private static MySqlDB instance;
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/vod_db?characterEncoding=utf8";
    private String username = "root";
    private String password = "root";

    private MySqlDB() {
        try {
            // load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            // create the connection object
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("DB connection String is wrong : " + ex.getMessage());
        }
    }

    public static MySqlDB getVodInstance(){
        if (instance == null) {
            instance = new MySqlDB();
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
            result = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}