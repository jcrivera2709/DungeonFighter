package firstGame;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicTrail extends GameObject {

  private float alpha = 1;
  private float life;
  private Handler handler;
  private Color color;

  private float width, height;

  public BasicTrail(
      float x,
      float y,
      ID id,
      Color color,
      float width,
      float height,
      float life,
      Handler handler) {
    super(x, y, id);
    this.color = color;
    this.width = width;
    this.height = height;
    this.life = life;
    this.handler = handler;
  }

  public void tick() {
    if (alpha > life) {
      alpha -= (life - 0.05f);
    } else handler.removeObject(this);
  }

  public void render(Graphics graphics) {
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.setComposite(makeTransparent(alpha));

    graphics.setColor(color);
    graphics.fillRect((int) x, (int) y, (int) width, (int) height);

    g2d.setComposite(makeTransparent(1));
  }

  private AlphaComposite makeTransparent(float alpha) {
    float type = AlphaComposite.SRC_OVER;
    return (AlphaComposite.getInstance((int) type, alpha));
  }

  public Rectangle getBounds() {
    return null;
  }
}
