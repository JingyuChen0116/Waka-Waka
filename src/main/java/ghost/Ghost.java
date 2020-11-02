package ghost;

import processing.core.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Ghost extends Cell{

	private int x;
	private int y;
	private Direction cur_direction;
	private ArrayList<Wall> walls;
	private int speed;
	private JSONArray modeLengths;
	private PImage ghost;
	private int count;
	private int cursor;
	private int[][] corners;
	private int targetX;
	private int targetY;

	public Ghost(int x, int y, long speed, PImage ghost, ArrayList<Wall> walls, JSONArray modeLengths, int[][] corners) {
		this.x = x;
		this.y = y;
		this.ghost = ghost;
		this.cur_direction = null;
		this.walls = walls;
		this.speed = (int) speed;
		this.modeLengths = modeLengths;
		this.count = 0;
		this.cursor = 0;
		this.corners = corners;
	}

	public int getX(){
		return this.x;
	}

	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return this.y;
	}

	public void setY(int y){
		this.y = y;
	}

	public void tick(float frameRate, int playerX, int playerY) {
		count++;
		float secs = count / frameRate;
		long length = (long) modeLengths.get(cursor);
		if (length >= secs) {
			// scatter when i is even, chase when cursor is odd;
			if (cursor % 2 == 0){
				int index = this.getClosestCorner();
				this.targetX = this.corners[index][0];
				this.targetY = this.corners[index][1];
			}
			else {
				this.targetX = playerX;
				this.targetY = playerY;
			}	
			this.checkDirection();	
		}
		else {
			if (cursor == modeLengths.size() - 1){
				cursor = 0;
			}
			else {
				cursor++;
			}
			count = 0;
		}
		this.x += (this.cur_direction.xVal * speed);
		this.y += (this.cur_direction.yVal * speed);
	}

	public int getClosestCorner(){
		int index = 0;
		for (int i = 0; i < 4; i++){
			double smallest = Math.pow(this.x - this.corners[index][0], 2) 
							+ Math.pow(this.y - this.corners[index][1], 2);
			double current = Math.pow(this.x - this.corners[i][0], 2) 
							+ Math.pow(this.y - this.corners[i][1], 2);
			if (current < smallest){
				index = i;
			}
		}
		return index;
	}

	public void checkDirection(){
		ArrayList<Direction> availableDirs = new ArrayList<Direction>();
		for (Direction dir: Direction.values()){
			if (! collideWithWall(this.x + dir.xVal * speed, this.y + dir.yVal * speed, this.walls)){
				if (
					dir == Direction.LEFT && this.cur_direction != Direction.RIGHT 
					|| dir == Direction.RIGHT && this.cur_direction != Direction.LEFT 
					|| dir == Direction.UP && this.cur_direction != Direction.DOWN 
					|| dir == Direction.DOWN && this.cur_direction != Direction.UP
				){
					availableDirs.add(dir);
				}
			}
		}
		// ghost being trapped, return back;
		if (availableDirs.size() == 0){
			if (this.cur_direction == Direction.LEFT) {this.cur_direction = Direction.RIGHT;}
			else if (this.cur_direction == Direction.RIGHT) {this.cur_direction = Direction.LEFT;}
			else if (this.cur_direction == Direction.UP) {this.cur_direction = Direction.DOWN;}
			else if (this.cur_direction == Direction.DOWN) {this.cur_direction = Direction.UP;}
			return;

		}
		else if (availableDirs.size() == 1){
			this.cur_direction = availableDirs.get(0);
			return;
		}
		else{
			ArrayList<Double> distances = new ArrayList<Double>();
			for (Direction dir: availableDirs){
				double distance = Math.pow(this.x + dir.xVal * speed - this.targetX, 2) 
								+ Math.pow(this.y + dir.yVal * speed - this.targetY, 2);
				distances.add(distance);
			}
			int smallest_index = 0;
			for (int i = 0; i < distances.size(); i++){
				if (distances.get(i) < distances.get(smallest_index)){
					smallest_index = i;
				}
			}
			this.cur_direction = availableDirs.get(smallest_index);
			return;
		}
	} 

	public void draw(PApplet app, boolean debug) {
		// handling graphics, only couple of lines
		app.image(this.ghost, this.x, this.y); 
		if (debug){
			app.stroke(200);
			app.line((float) this.x, (float) this.y, (float) this.targetX, (float) this.targetY);
		}
	}
}