package firstGame;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x,y;
	protected ID id;
	protected float velocityX, velocityY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics graphics);
	public abstract Rectangle getBounds();
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getID() {
		return id;
	}
	
	public void setVelocityX(float velX) {
		this.velocityX = velX;
	}
	public void setVelocityY(float velY) {
		this.velocityY = velY;
	}
	
	public float getvelX() {
		return velocityX;
	}
	public float getvelY() {
		return velocityY;
	}
	
	
	
}
