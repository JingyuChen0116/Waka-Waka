package ghost;

import processing.core.*;
import java.util.*;
import org.json.simple.JSONArray;
import java.util.Random;

public abstract class Ghost extends Cell{
	/**
	 * x-coordinate of the ghost
	 */
	protected int x;

	/**
	 * y-coordinate of the ghost
	 */
	protected int y;

	/**
	 * the image of the ghost to be drawn onto the screen
	 */
	private PImage ghost;

	/**
	 * The game that is currently running
	 */
	protected App app;

	/**
	 * the moving speed of the ghost
	 */
	private int speed;

	/**
	 * current direction of the ghost
	 */
	private Direction curDirection;

	/**
	 * The index of current mode
	 */
	private static int cursor = 0;

	/**
	 * The number of frames started from current mode
	 */
	private static int count = 0;

	/**
	 * x-coordinate of the ghost's target
	 */
	protected int targetX;

	/**
	 * y-coordinate of the ghost's target
	 */
	protected int targetY;

	/**
	 * Marks if the ghost is hit by the player during frightened mode
	 */
	private boolean dead;

	/**
	 * Constructor for a ghost, requires x,y coordinates, an image and a game
	 * default current direction is null
	 * default state for dead is false
	 * @param x, x-coordinate of ghost
	 * @param y, y-coordinate of ghost
	 * @param ghost, the image of ghost
	 * @param app, the game which is currently running
	 */
	public Ghost(int x, int y, PImage ghost, App app) {
		this.x = x;
		this.y = y;
		this.ghost = ghost;
		this.app = app;
		this.speed = (int) app.speed;
		this.curDirection = null;
		this.dead = false;
	}

	/**
	 * Reinitialise cursor and count when the game is restarted
	 */
	public static void restart(){
		cursor = 0;
		count = 0;
	}

	/**
	 * Checking if the ghost is alive
	 * @return if the ghost is alive
	 */
	public boolean isAlive(){
		return !this.dead;
	}

	/**
	 * If ghost is dead, relive it
	 * If ghost is alive, kill it
	 */
	public void aliveOrDead(){
		this.dead = !this.dead;
	}

	/**
	 * get x-coordinate of ghost
	 * @return x-coordinate
	 */
	public int getX(){
		return this.x;
	}

	/**
	 * set the x-coordinate of ghost to the given number
	 * @param x, x-coordinate to be set
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * get y-coordinate of ghost
	 * @return y-coordinate
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * set the y-coordinate of ghost to the given number
	 * @param y, y-coordinate to be set
	 */
	public void setY(int y){
		this.y = y;
	}

	/**
	 * @return current cursor
	 */
	public static int getCursor(){
		return cursor;
	}

	/**
	 * set the cursor to the given integer
	 * @param c, the given cursor to be set to
	 */
	public static void setCursor(int c){
		cursor = c;
	}

	/**
	 * @return the current count
	 */
	public static int getCount(){
		return count;
	}

	/**
	 * set the count to the given integer
	 * @param c, the given count to be set to
	 */
	public static void setCount(int c){
		count = c ;
	}

	/**
	 * @return current target x-coordinate
	 */
	public int getTargetX(){
		return this.targetX;
	}

	/**
	 * set current target x-coordinate
	 * @param x, given integer to be set as x-coordinate of target
	 */
	public void setTargetX(int x){
		this.targetX = x;
	}

	/**
	 * @return current target y-coordinate
	 */
	public int getTargetY(){
		return this.targetY;
	}

	/**
	 * set current target y-coordinate
	 * @param y, given integer to be set as y-coordinate of target
	 */
	public void setTargetY(int y){
		this.targetY = y;
	}

	/**
	 * @return current current direction
	 */
	public Direction getCurDirection(){
		return this.curDirection;
	}
	
	/**
	 * set current current direction
	 * @param dir, to be set as current direction
	 */
	public void setCurDirection(Direction dir){
		this.curDirection = dir;
	}

	public abstract int getScatterCorner();
	public abstract void setTarget();

	/**
	 * Add 1 to count in each frame when not in frightened mode
	 * @param app, the game to check whether in frightened mode
	 */
	public static void addCount(App app){
		if (!app.frightened){
			count++;
		}
	}

	/**
	 * Moving the ghost
	 * Check if current mode has finished. If so, getting into the next mode
	 * Check the moving mode of the ghost, scatter mode if cursor is even; chase mode if cursor is odd
	 */
	public void tick() {
		float secs = count / this.app.frameRate;
		long length = (long) this.app.modeLengths.get(cursor);
		if (length >= secs) {
			if (cursor % 2 == 0){
				int index = this.getScatterCorner();
				this.targetX = this.app.corners[index][0];
				this.targetY = this.app.corners[index][1];
			}
			else {
				this.setTarget();
				if (this.targetX < 0) {this.targetX = 0;}
				else if (this.targetX > App.WIDTH) {this.targetX = App.WIDTH;}

				if (this.targetY < 0) {this.targetY = 0;}
				else if (this.targetY > App.HEIGHT) {this.targetY = App.HEIGHT;}
			}	
			this.checkDirection();	
		}
		else {
			if (cursor == app.modeLengths.size() - 1){
				cursor = 0;
			}
			else {
				cursor++;
			}
			count = 0;
		}

		this.x += (this.curDirection.xVal * this.speed);
		this.y += (this.curDirection.yVal * this.speed);
	}

	/**
	 * Determine the moving direction of ghost and move the ghost
	 * Check the possible moving directions, excluding the opposite of current direction
	 * If there is no possible moving directions, the ghost moving back
	 * If there are any possible moving directions, if in frightened mode, turning randomly;
	 * otherwise, moving in the direction that closest to the target position
	 * Change x,y-coordinates of the ghost
	 */
	public void checkDirection(){
		ArrayList<Direction> availableDirs = new ArrayList<Direction>();
		for (Direction dir: Direction.values()){
			boolean collide = false;
			for (Wall wall: app.walls){
				if (Cell.collideWithObject(this.x + dir.xVal * this.speed, this.y + dir.yVal * this.speed, wall)){
					collide = true;
				}
			}
			if (!collide){
				if (
					(dir == Direction.LEFT && this.curDirection != Direction.RIGHT) 
					|| (dir == Direction.RIGHT && this.curDirection != Direction.LEFT) 
					|| (dir == Direction.UP && this.curDirection != Direction.DOWN) 
					|| (dir == Direction.DOWN && this.curDirection != Direction.UP)
				){
					availableDirs.add(dir);
				}
			}
		}
		if (availableDirs.size() == 0){
			if (this.curDirection == Direction.LEFT) {this.curDirection = Direction.RIGHT;}
			else if (this.curDirection == Direction.RIGHT) {this.curDirection = Direction.LEFT;}
			else if (this.curDirection == Direction.UP) {this.curDirection = Direction.DOWN;}
			else if (this.curDirection == Direction.DOWN) {this.curDirection = Direction.UP;}
			return;

		}

		if (this.app.frightened){
			Random rand = new Random();
			int index = rand.nextInt(availableDirs.size());
			this.curDirection = availableDirs.get(index);
		}
		else{
			ArrayList<Double> distances = new ArrayList<Double>();
			for (Direction dir: availableDirs){
				double distance = Math.pow(this.x + dir.xVal * speed - this.targetX, 2) 
								+ Math.pow(this.y + dir.yVal * speed - this.targetY, 2);
				distances.add(distance);
			}
			int smallestIndex = 0;
			for (int i = 0; i < distances.size(); i++){
				if (distances.get(i) < distances.get(smallestIndex)){
					smallestIndex = i;
				}
			}

			this.curDirection = availableDirs.get(smallestIndex);
			return;
		}
	} 

	/**
	 * draw the ghost
	 * Draw the ghost if the ghost is not dead
	 * If in frightened mode, draw the ghost with frightened image
	 * Draw the line from the ghost to target position if in debug mode
	 * @param app, the PApplet object to be drawn the ghost onto
	 */
	public void draw(PApplet app) {
		// handling graphics, only couple of lines
		if (!this.dead){
			if (this.app.frightened){
				app.image(app.loadImage("src/main/resources/frightened.png"), this.x, this.y);
			}
			else {
				app.image(this.ghost, this.x, this.y);
				if (this.app.debug){
					app.stroke(200);
					app.line((float) this.x, (float) this.y, (float) this.targetX, (float) this.targetY);
				}
			}
		}
	}

}