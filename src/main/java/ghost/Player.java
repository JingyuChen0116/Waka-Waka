package ghost;

import processing.core.*;
import java.util.ArrayList;

public class Player extends Cell{
    /**
	 * x-coordinate of the player
	 */
    private int x;

    /**
	 * y-coordinate of the player
	 */
    private int y;

    /**
	 * The moving speed of the player
	 */
    private int speed;

    /**
	 * The array of images of the player to be drawn onto the screen
	 */
    private PImage[] player;

    /**
	 * The list of the walls of the game
	 */
    private ArrayList<Wall> walls;

    /**
	 * Current direction of player
	 */
    private Direction curDirection;

    /**
	 * Next direction of player
	 */
    private Direction nextDirection;

    /**
	 * Marks if the player stops
	 */
    private boolean stop;

    /**
	 * Constructor for a player, requires x,y coordinates, speed, an image and a list of walls
     * default current direction is null
     * default next direction is null
     * default state for stop is false
     * @param x, x-coordinate of player
	 * @param y, y-coordinate of player
     * @param speed, moving speed of the player
	 * @param player, the images of player
	 * @param walls, the list of walls
	 */
    public Player(int x, int y, long speed, PImage[] player, ArrayList<Wall> walls){
        this.x = x;
        this.y = y;
        this.speed = (int) speed;
        this.player = player;
        this.walls = walls;
        this.nextDirection = null;
        this.curDirection = null;
        this.stop = false;
    }

    /**
	 * get x-coordinate of player
	 * @return x-coordinate
	 */
    public int getX(){
        return this.x;
    }

    /**
	 * set the x-coordinate of player to the given number
	 * @param x, x-coordinate to be set
	 */
    public void setX(int x){
        this.x = x;
    }

    /**
	 * get y-coordinate of player
	 * @return y-coordinate
	 */
    public int getY(){
        return this.y;
    }

    /**
	 * set the y-coordinate of player to the given number
	 * @param y, y-coordinate to be set
	 */
    public void setY(int y){
        this.y = y;
    }

    /**
	 * Get the current direction of player
     * @return current direction of player
	 */
    public Direction getCurDirection(){
        return this.curDirection;
    }

    /**
	 * Set the current direction of player to the given direction
     * @param dir, to be set as current direction
	 */
    public void setCurDirection(Direction dir){
        this.curDirection = dir;
    }

    /**
	 * Get the next direction of player
     * @return next direction of player
	 */
    public Direction getNextDirection(){
        return this.nextDirection;
    }

    /**
	 * Set the next direction of player to the given direction
     * @param dir, to be set as next direction
	 */
    public void setNextDirection(Direction dir){
        this.nextDirection = dir;
    }

    /**
	 * Reinitialising the starting directions of player when player loses a life
	 */
    public void initDirection(){
        this.curDirection = null;
        this.nextDirection = null;
    }

    /**
	 * Setting next direction of player given a key code
     * If current direction of player is null, set the direction as current direction
	 * @param keyCode, the direction to be set
	 */
    public void keyPressed(int keyCode){
        if (keyCode == 37){
            this.nextDirection = Direction.LEFT;
        }
        else if (keyCode == 38){
            this.nextDirection = Direction.UP;
        }
        else if (keyCode == 39){
            this.nextDirection = Direction.RIGHT;
        }
        else if (keyCode == 40){
            this.nextDirection = Direction.DOWN;
        }

        if (curDirection == null) {
            curDirection = nextDirection;
            nextDirection = null;
        }

    }

    /**
	 * Checking if player collides with any ghosts
     * If collides and the ghost is not dead, ghost becomes dead if in frightened mode, 
     * otherwise player loses one life and player and all ghosts return to initial position
	 * @param app, the game is currently running
	 */
    public static void collideWithGhost(App app){
        for (Ghost ghost: app.ghosts){
            if (Cell.collideWithObject(app.player.getX(), app.player.getY(), ghost)){
                if (!ghost.isAlive()){
                    continue;
                }
                if (app.frightened){
                    ghost.aliveOrDead();
                }
                else if (app.soda){
                    continue;
                }
                else{
                    app.lives--;
                    app.player.setX(app.playerInit[0]);
                    app.player.setY(app.playerInit[1]);
                    app.player.initDirection();

                    for (int i = 0; i < app.ghosts.size(); i++){
                        if (!app.ghosts.get(i).isAlive()){
                            app.ghosts.get(i).aliveOrDead();
                        }
                        app.ghosts.get(i).setX(app.ghostsInit.get(i)[0]);
                        app.ghosts.get(i).setY(app.ghostsInit.get(i)[1]);
                    }
                    break;
                }
            }
        }
    }

    /**
	 * If position the player moving one step towards the next direction does not collide with any walls, 
     * the player is able to turn
	 */
    public void checkNextDirection(){
        if (this.nextDirection != null){
            boolean collide = false;
            for (Wall wall: this.walls){
                if (Cell.collideWithObject(this.x + (nextDirection.xVal * speed), this.y + (nextDirection.yVal * speed), wall)){
                    collide = true;
                }
            }
            if (!collide){
                curDirection = nextDirection;
                nextDirection = null;
            }
        }
    }

    /**
	 * If position the player moving one step towards the current direction does not collide with any walls, 
     * the player is able to continue moving, otherwise, the player stops
	 */
    public void checkCurDirection(){
        if (curDirection != null){
            boolean collide = false;
            for (Wall wall: this.walls){
                if (this.collideWithObject(this.x + (curDirection.xVal * speed), this.y + (curDirection.yVal * speed), wall)){
                    collide = true;
                }
            }
            if (!collide){
                this.x += (curDirection.xVal * speed);
                this.y += (curDirection.yVal * speed);
                this.stop = false;
            }
            else {
                this.stop = true;
            }
        }
    }
    
    /**
	 * Logic for player
	 */
    public void tick(){
        this.checkNextDirection();
        this.checkCurDirection();
    }

    /**
	 * Getting the image of player to be shown on the screen according to the given number of frames
     * If the player stops, showing open-mouth image relate to the direction it is moving
     * Alternating open-mouth and closed-mouth every 8 frames
     * @param frames, the number of total game frames
     * @return image to be displayed on the game screen
	 */
    public PImage getImage(int frames){
        if (this.stop || frames % 8 >= 4){
            if (this.curDirection == Direction.LEFT){
                return this.player[1];
            }
            else if (this.curDirection == Direction.RIGHT){
                return this.player[2];
            }
            else if (this.curDirection == Direction.UP){
                return this.player[3];
            }
            else if (this.curDirection == Direction.DOWN){
                return this.player[4];
            }
            else {
                return this.player[2];
            }
        }
        else {
            return this.player[0];
        }
    
    }
    
    /**
	 * draw the player
	 * @param app, the PApplet object to be drawn the player onto
	 */
    public void draw(PApplet app){
        app.image(this.getImage(app.frameCount), this.x, this.y);
    }
}