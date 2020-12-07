package firstGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
  private Game game;
  private Handler handler;
  private Random random = new Random();

  private Color col;
  private static int amount = 0;

  public MenuParticle(float x, float y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;

    velocityY = (random.nextInt(7) + 1) * -1;

    col = new Color(255, random.nextInt(255), 0);

    amount++;
  }

  public static int getAmount() {
    return amount;
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 16, 16);
  }

  public void tick() {
    x += velocityX;
    y += velocityY;

    handler.addObject(new BasicTrail(x, y, ID.BasicTrail, col, 16, 16, 0.1f, handler));
    outOfBounds();
    getAmount();
  }

  private void outOfBounds() {
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getID() == ID.MenuParticle) {
        if (tempObject.getX() > Game.WIDTH || tempObject.getX() < 0) {
          handler.removeObject(this);
          amount--;
        }
        if (tempObject.getY() > Game.HEIGHT || tempObject.getY() < 0) {
          handler.removeObject(this);
          amount--;
        }
      }
    }
  }

  public void render(Graphics graphics) {
    graphics.setColor(col);
    graphics.fillRect((int) x, (int) y, 16, 16);
  }
}
