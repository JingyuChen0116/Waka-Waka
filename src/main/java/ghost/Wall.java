package ghost;

import processing.core.*;

public class Wall extends Cell{

    private int x;
    private int y;

    private PImage wall;

    public Wall(int x, int y, PImage wall){
        this.x = x;
        this.y = y;
        this.wall = wall;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void tick() {
		// handles logic
        return;
	}

	public void draw(PApplet app) {
		// handling graphics, only couple of lines
		app.image(this.wall, this.x, this.y); 
	}
}