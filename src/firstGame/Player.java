package firstGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
  Random random = new Random();
  Handler handler;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 32, 32);
  }

  public void tick() {
    x += velocityX;
    y += velocityY;

    x = Game.clamp((int) x, 0, Game.WIDTH - 37);
    y = Game.clamp((int) y, 0, Game.HEIGHT - 60);

    collision();
  }

  private void collision() {
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getID() == ID.BasicEnemy
          || tempObject.getID() == ID.fastEnemy
          || tempObject.getID() == ID.SmartEnemy
          || tempObject.getID() == ID.BossBullet) {
        if (getBounds().intersects(tempObject.getBounds())) {
          // what happens when intersect happens
          HUD.HEALTH -= 2;
        }
      }
      if (tempObject.getID() == ID.EnemyBoss) {
        if (getBounds().intersects(tempObject.getBounds())) {
          // what happens when intersect happens
          HUD.HEALTH = 0;
        }
      }
    }
  }

  public void render(Graphics graphics) {
    graphics.setColor(Color.white);
    graphics.fillRect((int) x, (int) y, 32, 32);
  }
}
