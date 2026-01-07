package data;

import java.sql.*;
import java.util.*;

public class ScoreDAO {

    public static void insertScore(String username, int score) {
        try {
            PreparedStatement ps = Connect.getConnection()
                    .getConnect()
                    .prepareStatement("INSERT INTO scores VALUES (NULL, ?, ?)");
            ps.setString(1, username);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Score> getTopScores() {
        ArrayList<Score> list = new ArrayList<>();
        try {
            Statement st = Connect.getConnection().getConnect().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM scores ORDER BY score DESC");
            while (rs.next()) {
                list.add(new Score(
                        rs.getString("username"),
                        rs.getInt("score")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
