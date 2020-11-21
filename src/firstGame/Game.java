package firstGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = -1442798787354930462L;

  // screen dimensions
  public static final int WIDTH = 840, HEIGHT = 600;

  private Thread thread;
  private boolean running = false;
  private Handler handler;
  private Random r;
  private HUD hud;
  private Spawner spawner;
  private Menu menu;
  private int maxParticles = 20;

  public enum STATE {
    Menu,
    Game,
    Help,
    End;
  };

  public static STATE gameState = STATE.Menu;

  // calls a new window with title
  public Game() {
    handler = new Handler();

    hud = new HUD();
    spawner = new Spawner(handler, hud);
    r = new Random();

    menu = new Menu(this, handler, hud);
    this.addKeyListener(new KeyInput(handler));
    this.addMouseListener(menu);

    new Window(WIDTH, HEIGHT, "My First Game!", this);

    if (gameState == STATE.Menu) {
      for (int i = 0; i < maxParticles; i++) {
        handler.addObject(
            new MenuParticle(r.nextInt(WIDTH), HEIGHT - 64, ID.MenuParticle, handler));
      }
    }
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The objective of this method is to creates a game loop for the program to use. By getting the
   * time in two different points we can get a difference in time, allowing us to create a frame and
   * for each second there should be at least 60 frames. We also print the frames for testing.
   */
  public void run() {

    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;

    // Determines how many times we can divide 60 into 1e9 or about 1 second
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (running) {

      long currentTime = System.nanoTime();
      // adds the amount of time since last loop
      delta += (currentTime - lastTime) / ns;
      // sets the lastTime to prepare for the next loop
      lastTime = currentTime;

      // lower our delta to 0 to start our next 'frame'
      while (delta >= 1) {
        tick();
        delta--;
      }
      if (running) {
        render();
      }
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    // no longer running stops the thread
    stop();
  }

  private void tick() {
    handler.tick();

    if (gameState == STATE.Game) {
      hud.tick();
      spawner.tick();

      if (HUD.HEALTH <= 0) {
        HUD.HEALTH = 100;
        for (int i = 0; i < this.handler.object.size(); i++) {
          GameObject tempObject = this.handler.object.get(i);
          this.handler.removeObject(tempObject);
          i--;
        }
        gameState = STATE.End;
      }
    } else if (gameState == STATE.Menu || gameState == STATE.End) {
      menu.tick();
      // adds menu particles
      if (MenuParticle.getAmount() < maxParticles) {
        handler.addObject(
            new MenuParticle(r.nextInt(Game.WIDTH), Game.HEIGHT - 40, ID.MenuParticle, handler));
      }
    }
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    handler.render(g);

    if (gameState == STATE.Game) {
      hud.render(g);
    } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
      menu.render(g);
    }

    g.dispose();
    bs.show();
  }

  public static float clamp(float velX, float min, float max) {
    if (velX >= max) return (velX = max);
    else if (velX <= min) return (velX = min);
    else return velX;
  }

  public static void main(String[] args) {
    new Game();
  }
}
