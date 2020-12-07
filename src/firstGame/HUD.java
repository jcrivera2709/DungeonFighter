package firstGame;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

  public static float HEALTH = 100;
  private int score = 0;
  private int level = 1;

	/**
	 * As the game goes on the score will incease which will spawn new enemies.
	 */
	public void tick() {

    HEALTH = Game.clamp(HEALTH, 0, 100);

    score++;
  }

	/**
	 * Helps display current level anc score.
	 * @param graphics
	 */
  public void render(Graphics graphics) {
    graphics.setColor(Color.gray);
    graphics.fillRect(15, 15, 200, 32);
    graphics.setColor(Color.green);
    graphics.fillRect(15, 15, (int) (HEALTH * 2), 32);
    graphics.setColor(Color.white);
    graphics.drawRect(15, 15, 200, 32);

    graphics.drawString("Score: " + score, 10, 64);
    graphics.drawString("Level: " + level, 10, 84);
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }
}
