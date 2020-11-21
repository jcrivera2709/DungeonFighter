package firstGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class fastEnemy extends GameObject{
private Handler handler;
	
	public fastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velocityX = 2;
		velocityY = 9;
		
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48)
			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 25)
			velocityX *= -1;
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.cyan, 16, 16, 0.1f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.cyan);	
		g.fillRect((int)x,(int) y, 16, 16);
	}

}
