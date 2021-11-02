package battleship;

public class Score {
	private String playerName;
	private int score;
	private float hm_ratio;
	
	public Score(String name, int score, float hm_ratio) {
		this.playerName = name;
		this.score = score;
		this.hm_ratio = hm_ratio;
	}
	
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the hm_ratio
	 */
	public float getHm_ratio() {
		return hm_ratio;
	}
	/**
	 * @param hm_ratio the hm_ratio to set
	 */
	public void setHm_ratio(float hm_ratio) {
		this.hm_ratio = hm_ratio;
	}
}
