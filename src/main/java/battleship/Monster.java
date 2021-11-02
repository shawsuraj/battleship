package battleship;

import java.awt.Point;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Monster {
	private int health;
	private Point position = new Point(0,0);
	private String name;
	private String fullName;

	public Monster(String name, String fullName) {
		this.name = name;
		this.fullName = fullName;
	}

	public Point newPosition() throws NoSuchAlgorithmException {
		Random rand = SecureRandom.getInstanceStrong();

		this.position.x = rand.nextInt(10);
		this.position.y = rand.nextInt(10);

		return position;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the pos
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
		this.position = pos;
	}


}
