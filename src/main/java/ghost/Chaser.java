package ghost;

import processing.core.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Chaser extends Ghost {

    /**
	 * Constructor for a chaser
     * @param x, x-coordinate of the chaser
     * @param y, y-coordinate of the chaser
     * @param ghost, the image to be displayed onto the game screen
     * @param app, game which is currently running
	 */
    public Chaser(int x, int y, PImage ghost, App app) {
        super(x, y, ghost, app);
    }

    /**
	 * Targetting on the top left corner when in scatter mode
	 * @return the index of top left corner
	 */
    public int getScatterCorner() {
        return 0;
    }

    /**
	 * Setting target position when in chase mode
	 * Targetting on current position of player
	 */
    public void setTarget(){
        this.targetX = this.app.player.getX();
        this.targetY = this.app.player.getY();
    }
     
}