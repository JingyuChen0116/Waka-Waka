package ghost;

import processing.core.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Whim extends Ghost {

    /**
	 * Constructor for a whim
     * @param x, x-coordinate of the whim
     * @param y, y-coordinate of the whim
     * @param ghost, the image to be displayed onto the game screen
     * @param app, game which is currently running
	 */
    public Whim(int x, int y, PImage ghost, App app) {
        super(x, y, ghost, app);
    }

    /**
	 * Targetting on the bottom right corner when in scatter mode
	 * @return the index of bottom right corner
	 */
    public int getScatterCorner() {
        return 3;
    }

    /**
	 * Setting target positions when in chase mode
	 * Targetting on the position that double the vector from chaser to 2 grid spaces ahead of player
	 */
    public void setTarget(){
        int chaserX = 0; 
        int chaserY = 0;
        for (Ghost ghost: this.app.ghosts){
            if (ghost instanceof Chaser){
                chaserX = ghost.getX();
                chaserY = ghost.getY();
            }
        }

        this.targetX = this.app.player.getX() * 2 + chaserX;
        this.targetY = this.app.player.getY() * 2 + chaserY;

    }
     
}