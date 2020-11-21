package firstGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  private Handler handler;
  private KeyInput input;
  private boolean[] keyDown = new boolean[5];

  public int key;

  public KeyInput(Handler handler) {
    this.handler = handler;

    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
    keyDown[4] = false;
  }

  public void keyPressed(KeyEvent e) {
    key = e.getKeyCode();

    // performs actions based on what button is pressed
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getID() == ID.Player) {

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
          tempObject.setVelocityY(-5);
          keyDown[0] = true;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
          tempObject.setVelocityX(-5);
          keyDown[1] = true;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
          tempObject.setVelocityY(5);
          keyDown[2] = true;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
          tempObject.setVelocityX(5);
          keyDown[3] = true;
        }
        if (key == KeyEvent.VK_SPACE) {
          handler.addObject(
              new Bullet(
                  tempObject.getX() + 16, tempObject.getY() + 16, ID.Bullet, input, handler));
          keyDown[4] = true;
        }
      }
    }
    if (key == KeyEvent.VK_ESCAPE) System.exit(1);
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getID() == ID.Player) {

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[1] = false;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[2] = false;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;
        if (key == KeyEvent.VK_SPACE) keyDown[4] = false;

        // for vertical movement
        if (!keyDown[0] && !keyDown[2]) tempObject.setVelocityY(0);

        // for horizontal movement
        if (!keyDown[1] && !keyDown[3]) tempObject.setVelocityX(0);
      }
    }
  }

  public void key(int key) {
    this.key = key;
  }

  public int getKey() {
    return key;
  }
}
