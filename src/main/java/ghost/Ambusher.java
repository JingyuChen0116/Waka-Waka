package ghost;

import processing.core.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Ambusher extends Ghost {

    /**
	 * Constructor for an ambusher
     * @param x, x-coordinate of the ambusher
     * @param y, y-coordinate of the ambusher
     * @param ghost, the image to be displayed onto the game screen
     * @param app, game which is currently running
	 */
    public Ambusher(int x, int y, PImage ghost, App app) {
        super(x, y, ghost, app);
    }

    /**
	 * Targetting on the top right corner when in scatter mode
	 * @return the index of top right corner
	 */
    public int getScatterCorner() {
        return 1;
    }

    /**
	 * Setting target position when in chase mode
	 * Targetting on four grid spaces ahead of player
     * If current position of player is null, assume it moving towards right
	 */
    public void setTarget(){
        Direction dir = this.app.player.getCurDirection();
        if (dir == null){
            dir = Direction.RIGHT;
        }
        this.targetX = this.app.player.getX() + 4 * 16 * dir.xVal;
        this.targetY = this.app.player.getX() + 4 * 16 * dir.yVal;
    }
}