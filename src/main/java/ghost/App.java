package ghost;
import processing.core.*;
import org.json.simple.JSONArray;
import java.util.*;
import javax.swing.Timer;

public class App extends PApplet{
	/**
	 * The width of game screen
	 */
	public static final int WIDTH = 448;
	public static final int HEIGHT = 576;

	/**
	 * List of walls in map
	 */
	public ArrayList<Wall> walls;

	/**
	 * List of ghosts in map
	 */
	public ArrayList<Ghost> ghosts;

	/**
	 * The unique player in map
	 */
	public Player player;

	/**
	 * List of fruits in map
	 */
	public ArrayList<Fruit> fruits;

	/**
	 * Array of coordinations of corners
	 */
	public int[][] corners;

	/**
	 * Array for storing player's initial position
	 * Player should return to this position when it hits a ghost
	 */
	public int[] playerInit;

	/**
	 * List of arrays for storing ghosts' initial positions
	 * Ghosts should return to these positions when the player hits one of the ghosts
	 */
	public ArrayList<int[]> ghostsInit;

	/**
	 * The remaining number of lives the player has
	 */
	public long lives;

	/**
	 * The moving speed of the player and ghosts
	 */
	public long speed;

	/**
	 * The file to initialise the map
	 */
	public String mapFile;

	/**
	 * Array of duration of chase and scatter modes
	 */
	public JSONArray modeLengths;

	/**
	 * Duration of frightened mode
	 */
	public long frightenedLength;

	/**
	 * Marks if the ghosts are in frightened mode or not
	 */
	public boolean frightened;

	/**
	 * The number of frames started from frightened mode
	 */
	public long frightenedCount;

		/**
	 * Marks if the ghosts are in invisible mode or not
	 */
	public boolean soda;

	/**
	 * The number of frames started from invisible mode
	 */
	public long sodaCount;

	/**
	 * Marks if debug mode is activated
	 */
	public boolean debug;

	/**
	 * Timer for 10 secs pause when the game finishes
	 */
	public Timer gameOver;

	/**
	 * framerate of the game
	 */
	public int frameRate;

	/**
	 * collection of the images
	 */
	public HashMap<String, String[]> images;

	/**
	 * The key being pressed;
	 */
	public int key;

	/**
	 * Constructor for an app
	 * default state for debug mode is false
	 * default state for frightened mode is false
	 * default number of frightenedCount is 0
	 */
	public App() {
		this.ghosts = new ArrayList<Ghost>();
		this.walls = new ArrayList<Wall>();
		this.fruits = new ArrayList<Fruit>();
		this.corners = new int[4][2];
		this.playerInit = new int[2];
		this.ghostsInit = new ArrayList<int[]>();
		this.images = new HashMap<String, String[]>();
		this.frightenedCount = 0;
		this.debug = false;
		this.frightened = false;
		this.frameRate = 60;
		// this.font = "PressStart2P-Regular.ttf";
	}

	/**
	 * Specify the frame rate
	 * Parse the game configuration file
	 * Parse the map file
	 * Create the text font
	 */
	public void setup() {
		frameRate(this.frameRate);
		this.textFont(createFont("PressStart2P-Regular.ttf", 35));
		Processing.jsonRead("config.json", this);
		Processing.loadImages(this);
		Processing.mapRead(this);
	}

	/**
	 * Set up the size of game screen
	 */
	public void settings() {
		size(WIDTH, HEIGHT);
	}

	/**
	 * Parse the method for logic of each element
	 * Draw all elements onto the screen
	 * Check if the player hits a ghost
	 * Check if the game finishes
	 */
	public void draw() { 
		this.rect(-1, -1, WIDTH, HEIGHT);
		background(0, 0, 0);

		for (int i = 0; i < this.lives; i++){
			this.image(this.loadImage(this.images.get("player")[2]), i * 28, 544);
		}

		for (Wall wall: walls){
			wall.draw(this);
		}
		
		this.player.tick();
		this.player.draw(this);

		Fruit.checkFruit(this);
		for (Fruit fruit: fruits){
			fruit.draw(this);
		}

		Processing.checkMode(this);

		Ghost.addCount(this);
		for (Ghost ghost: ghosts){
			ghost.tick();
			if (!this.soda){
				ghost.draw(this);
			}
		}

		Player.collideWithGhost(this);
		Processing.checkEnd(this);
	}

	/**
	 * The function is called when a key is pressed
	 * If whitespace is pressed, entering debug mode
	 * If an arrowkey is pressed, parsing the key to the player to move
	 */
	public void keyPressed(){
		this.key = keyCode;
		Processing.checkKey(this);
	}

	/**
	 * Run the game
	 * @param args, arguments
	 */
	public static void main(String[] args) {
		PApplet.main("ghost.App");
	}
}