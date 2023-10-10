package ghost;

import processing.core.*;

public class Wall extends Cell{
    /**
	 * x-coordinate of the wall
	 */
    private int x;

    /**
	 * y-coordinate of the wall
	 */
    private int y;

    /**
	 * the image of the wall to be drawn onto the screen
	 */
    private PImage wall;

    /**
	 * Constructor for a wall
     * @param x, x-coordinate of the wall
     * @param y, y-coordinate of the wall
     * @param wall, the image to be displayed on the game screen
	 */
    public Wall(int x, int y, PImage wall){
        this.x = x;
        this.y = y;
        this.wall = wall;
    }

    /**
	 * get x-coordinate of wall
	 * @return x-coordinate
	 */
    public int getX(){
        return this.x;
    }

    /**
	 * get y-coordinate of wall
	 * @return y-coordinate
	 */
    public int getY(){
        return this.y;
    }

    /**
	 * draw the wall
	 * @param app, the PApplet object to be drawn the wall onto
	 */
	public void draw(PApplet app) {
		app.image(this.wall, this.x, this.y); 
	}
}