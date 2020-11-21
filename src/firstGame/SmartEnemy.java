package firstGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX())* (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velocityX = (float) ((-1.0 / distance) * diffX);
		velocityY = (float) ((-1.0 / distance) * diffY);
		
		if(y <= 0 || y >= Game.HEIGHT - 48)
			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 25)
			velocityX *= -1;
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.blue, 16, 16, 0.1f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.blue);	
		g.fillRect((int)x, (int)y, 16, 16);
	}

}