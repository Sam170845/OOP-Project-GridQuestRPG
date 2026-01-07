package data;

import java.sql.*;

public final class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "GridQuestRPG";
    private final String HOST = "localhost:3306";
    private final String CONNECTION =
            String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private Connection con;
    private static Connect instance;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connect getConnection() {
        if (instance == null) instance = new Connect();
        return instance;
    }

    public Connection getConnect() {
        return con;
    }
}
