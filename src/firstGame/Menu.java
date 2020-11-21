package firstGame;

import firstGame.Game.STATE;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

  private Game game;
  private Handler handler;
  private HUD hud;
  private Random randomNumber = new Random();

  public Menu(Game game, Handler handler, HUD hud) {
    this.game = game;
    this.handler = handler;
    this.hud = hud;
  }

  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();

    // options at the menu and what they do when you press them
    if (Game.gameState == STATE.Menu) {
      // for the play button
      if (mouseOver(mx, my, 150)) {
        Game.gameState = STATE.Game;
        handler.clearAll();
        handler.addObject(
            new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        handler.addObject(
            new BasicEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.BasicEnemy,
                handler));
      }

      // for the quit button
      if (mouseOver(mx, my, 350)) {
        System.exit(1);
      }

      // for the help button
      if (mouseOver(mx, my, 250)) {
        Game.gameState = STATE.Help;
      }
    }

    // back button for help
    if (Game.gameState == STATE.Help) {
      if (mouseOver(mx, my, 350)) {
        Game.gameState = STATE.Menu;
      }
    }

    // Try again button in end state
    if (Game.gameState == STATE.End) {
      if (mouseOver(mx, my, 350)) {
        Game.gameState = STATE.Game;
        hud.setScore(0);
        hud.setLevel(0);
        handler.addObject(
            new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
      }
    }
  }

  public void mouseReleased(MouseEvent e) {}

  private boolean mouseOver(int mx, int my, int y) {
    if (mx > 315 && mx < 315 + 200) {
        return my > y && my < y + 64;
    } else return false;
  }

  public void tick() {}

  public void render(Graphics g) {
    if (Game.gameState == STATE.Menu) {
      Font titleFont = new Font("arial", Font.BOLD, 50);
      Font boxText = new Font("arial", Font.BOLD, 30);

      g.setFont(titleFont);
      g.setColor(Color.white);
      g.drawString("Survival!", 250, 70);

      g.setFont(boxText);
      g.setColor(Color.white);
      g.drawRect(315, 150, 200, 64);
      g.drawString("Start", 385, 195);

      g.setColor(Color.white);
      g.drawRect(315, 250, 200, 64);
      g.drawString("Help", 385, 295);

      g.setColor(Color.white);
      g.drawRect(315, 350, 200, 64);
      g.drawString("Quit", 385, 395);

    } else if (Game.gameState == STATE.Help) {
      Font titleFont = new Font("arial", Font.BOLD, 50);
      Font boxText = new Font("arial", Font.BOLD, 30);
      Font longText = new Font("arial", Font.BOLD, 15);

      g.setFont(titleFont);
      g.setColor(Color.white);
      g.drawString("How to Play", 275, 70);

      g.setFont(boxText);
      g.setColor(Color.white);
      g.drawRect(315, 350, 200, 64);
      g.drawString("Back", 370, 395);

      g.setFont(longText);
      g.setColor(Color.white);

      g.drawString("1) WASD helps you control the Player.", 290, 150);
      g.drawString("2) Avoid enemies as much as possible.", 292, 185);
      g.drawString("3) Press space bar to shoot bullets in random directions.", 292, 215);
      g.drawString("Hit enemies to regain health.", 309, 230);
    } else if (Game.gameState == STATE.End) {
      Font titleFont = new Font("arial", Font.BOLD, 50);
      Font boxText = new Font("arial", Font.BOLD, 30);

      g.setFont(titleFont);
      g.setColor(Color.red);
      g.drawString("Game Over!", 275, 70);

      g.setFont(boxText);
      g.setColor(Color.white);
      g.drawString("You lost with a score with: " + hud.getScore(), 200, 195);

      g.setFont(boxText);
      g.setColor(Color.white);
      g.drawRect(315, 350, 200, 64);
      g.drawString("Try Again	?", 330, 395);
    }
  }
}
