package ghost;
import processing.core.*;

public class Fruit extends Cell{

    /**
	 * x-coordinate of the fruit
	 */
    protected int x;

    /**
	 * y-coordinate of the fruit
	 */
    protected int y;

    /**
	 * the image of the fruit to be drawn onto the screen
	 */
    protected PImage fruit;

    /**
	 * The game that is currently running
	 */
    protected App app;

    /**
	 * Constructor for a fruit requires x,y coordinates, an image, a game and a boolean expression stands for superfruit
	 * @param x, x-coordinate of ghost
	 * @param y, y-coordinate of ghost
	 * @param fruit, the image of ghost
	 * @param app, the game which is currently running
	 */
    public Fruit(int x, int y, PImage fruit, App app){
        this.x = x;
        this.y = y;
        this.fruit = fruit;
        this.app = app;
    }

    /**
	 * get x-coordinate of fruit
	 * @return x-coordinate
	 */
    public int getX(){
        return this.x;
    }

    /**
	 * get y-coordinate of fruit
	 * @return y-coordinate
	 */
    public int getY(){
        return this.y;
    }

    /**
	 * Checking if any fruits being eaten by player
     * If player collides with any fruits, the fruits are removed
     * If player collides with a super fruit, start frightened mode
     * If player collides with a soda can, ghosts become invisible
	 * @param app, the game that is currently running
	 */
    public static void checkFruit(App app){
        for (int i = 0; i < app.fruits.size(); i++) {
            if (Cell.collideWithObject(app.player.getX(), app.player.getY(), app.fruits.get(i))){
                if (app.fruits.get(i) instanceof SuperFruit){
                    if (app.soda){
                        app.soda = false;
                    }
                    app.frightened = true;
                    app.frightenedCount = 0;
                }
                else if (app.fruits.get(i) instanceof SodaCan){
                    if (app.frightened){
                        app.frightened = false;
                    }
                    app.soda = true;
                    app.sodaCount = 0;
                }
                app.fruits.remove(i);
            }
        }
    }

    /**
	 * draw the fruit
	 * @param app, the PApplet object to be drawn the fruit onto
	 */
    public void draw(PApplet app){
        app.image(this.fruit, this.x , this.y);
    }
}