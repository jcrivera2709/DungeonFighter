package firstGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullet extends GameObject {

  private Handler handler;
  private KeyInput input;
  Random r = new Random();

  public Bullet(float x, float y, ID id, KeyInput input, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.input = input;

    velocityY = -5;
  }

  public void tick() {
    y += velocityY;

    collision();
    outOfBounds();
  }

  private void outOfBounds() {

    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getID() == ID.Bullet) {
        if (tempObject.getX() > Game.WIDTH || tempObject.getX() < 0) {
          handler.removeObject(this);
        }
        if (tempObject.getY() > Game.HEIGHT || tempObject.getY() < 0) {
          handler.removeObject(this);
        }
      }
    }
  }

  /**
   * If bullet collides with enemy it will give health to the player.
   */
  private void collision() {
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject enemyObject = handler.object.get(i);

      if (enemyObject.getID() == ID.BasicEnemy
          || enemyObject.getID() == ID.fastEnemy
          || enemyObject.getID() == ID.SmartEnemy) {
        if (getBounds().intersects(enemyObject.getBounds())) {
          HUD.HEALTH += 1;
        }
      }
    }
  }

  public void render(Graphics graphics) {
    graphics.setColor(Color.white);
    graphics.fillRect((int) x, (int) y, 10, 10);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 10, 10);
  }
}
