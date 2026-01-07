package data;

public class Score {
    private String username;
    private int score;

    public Score(String u, int s) {
        username = u;
        score = s;
    }

	public String getUsername() {
		return username;
	}


	public int getScore() {
		return score;
	}

    
}
