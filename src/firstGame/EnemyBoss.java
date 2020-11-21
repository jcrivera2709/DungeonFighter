package firstGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {
	private Handler handler;
	Random r = new Random();
	
	private int timer = 50;
	private int timer2 = 50; 
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velocityX = 0;
		velocityY = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if(timer <= 0) velocityY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) 
		{
			if(velocityX == 0) velocityX = 2;
			
			if(velocityX > 0) velocityX += 0.005f;
			else if(velocityX < 0) velocityX -= 0.005f;
			
			velocityX = Game.clamp(velocityX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0)
				handler.addObject(new BossBullet((int)x + 48, (int)y + 48, ID.BossBullet, handler));
		}
		
		
		
		if(x <= 0 || x >= Game.WIDTH - 96) velocityX *= -1;
		//handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.red, 96, 96, 0.1f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.red);	
		g.fillRect((int)x, (int)y, 96, 96);
	}

}
