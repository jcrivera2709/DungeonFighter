package firstGame;
import firstGame.Game.STATE;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList();

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics graphics) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(graphics);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void clearAll() {
		for(int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if(tempObject.getID() != ID.Player) {
				this.removeObject(tempObject);
				i--;
			}
		}
		if(Game.gameState == STATE.End) {
			object.clear();
		}
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
