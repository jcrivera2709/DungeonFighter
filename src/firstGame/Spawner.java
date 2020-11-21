package firstGame;

import java.util.Random;

public class Spawner {
  private Handler handler;
  private HUD hud;
  private Random randomNumber = new Random();

  private int scoreKeep = 0;

  public Spawner(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
  }

  public void tick() {
    scoreKeep++;

    // Will add a level every 200 score points.
    if (scoreKeep >= 200) {
      scoreKeep = 0;
      hud.setLevel(hud.getLevel() + 1);

      // Adds enemies based on level.
      if (hud.getLevel() == 2) {
        handler.addObject(
            new BasicEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.BasicEnemy,
                handler));
        handler.addObject(
            new fastEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.fastEnemy,
                handler));
      }
      if (hud.getLevel() == 3) {
        handler.addObject(
            new SmartEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.SmartEnemy,
                handler));
      }
      if (hud.getLevel() == 5) {
        handler.addObject(
            new SmartEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.SmartEnemy,
                handler));
        handler.addObject(
            new fastEnemy(
                randomNumber.nextInt(Game.WIDTH) - 32,
                randomNumber.nextInt(Game.HEIGHT) - 32,
                ID.fastEnemy,
                handler));
      }
      if (hud.getLevel() == 10) {
        handler.clearAll();
        handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
      }
    }
  }
}
