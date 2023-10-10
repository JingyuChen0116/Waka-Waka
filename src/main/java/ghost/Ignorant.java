package ghost;

import processing.core.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Ignorant extends Ghost {

    /**
	 * Constructor for an ignorant
     * @param x, x-coordinate of the ignorant
     * @param y, y-coordinate of the ignorant
     * @param ghost, the image to be displayed onto the game screen
     * @param app, game which is currently running
	 */
    public Ignorant(int x, int y, PImage ghost, App app) {
        super(x, y, ghost, app);
    }

    /**
	 * Targetting on the bottom left corner when in scatter mode
	 * @return the index of bottom left corner
	 */
    public int getScatterCorner() {
        return 2;
     }

    /**
	 * Setting target position when in chase mode
	 * Targetting on current position of player if more than eight units away from player
     * Targetting on bottom left corner if less than eight units away from player
	 */
    public void setTarget(){
        double distance = Math.pow(this.app.player.getX() - this.x, 2) 
                        + Math.pow(this.app.player.getY() - this.y, 2);
        if (distance > Math.pow(8 * 16, 2)) {
            this.targetX = this.app.player.getX();
            this.targetY = this.app.player.getY();
        }
        else {
            this.targetX = this.app.corners[2][0];
            this.targetY = this.app.corners[2][1];
        }
    }
     
}