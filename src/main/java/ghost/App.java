package ghost;

import processing.core.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class App extends PApplet {

	public static final int WIDTH = 448;
	public static final int HEIGHT = 576;

	private ArrayList<Wall> walls;
	private ArrayList<Ghost> ghosts;
	private Player player;
	private ArrayList<Fruit> fruits;
	private int[][] corners;
	private int[] player_init;
	private ArrayList<int[]> ghosts_init;

	private long lives;
	private long speed;
	private String mapFile;
	private	JSONArray modeLengths;

	private boolean gameEnd;
	private boolean debug;


	public App() {
		this.jsonRead("config.json");
		this.ghosts = new ArrayList<Ghost>();
		this.walls = new ArrayList<Wall>();
		this.fruits = new ArrayList<Fruit>();
		this.corners = new int[4][2];
		this.gameEnd = false;
		this.debug = false;
		this.player_init = new int[2];
		this.ghosts_init = new ArrayList<int[]>();
	}

	public void jsonRead(String jsonFile){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(jsonFile));
			JSONObject jsonObject = (JSONObject) obj;
			this.mapFile = (String) jsonObject.get("map");
			this.lives = (Long) jsonObject.get("lives");
			this.speed = (Long) jsonObject.get("speed");
			this.modeLengths = (JSONArray) jsonObject.get("modeLengths");
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
	}

	public void mapRead(){
		try{
			File f = new File(mapFile);
			Scanner reader = new Scanner(f);
			int i = 0;
			while (reader.hasNextLine()){
				String[] list_of_line = reader.nextLine().split("");
				for (int j = 0; j < list_of_line.length; j++){
					if (list_of_line[j].equals("0")) {
						continue;
					}
					else if (list_of_line[j].equals("1")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/horizontal.png")));
					}
					else if (list_of_line[j].equals("2")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/vertical.png")));
					}
					else if (list_of_line[j].equals("3")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/upLeft.png")));
						if (this.corners[3][0] == 0 && this.corners[3][1] == 0){
							this.corners[3][0] = j * 16;
							this.corners[3][1] = i * 16;
						}
						else if (i * 16 >= this.corners[2][1] && j * 16 >= this.corners[2][0]){
							this.corners[3][0] = j * 16;
							this.corners[3][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("4")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/upRight.png")));
						if (this.corners[2][0] == 0 && this.corners[2][1] == 0){
							this.corners[2][0] = j * 16;
							this.corners[2][1] = i * 16;
						}
						else if (i * 16 >= this.corners[2][1] && j * 16 <= this.corners[2][0]){
							this.corners[2][0] = j * 16;
							this.corners[2][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("5")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/downLeft.png")));
						if (this.corners[1][0] == 0 && this.corners[1][1] == 0){
							this.corners[1][0] = j * 16;
							this.corners[1][1] = i * 16;
						}
						else if (i * 16 <= this.corners[1][1] && j * 16 >= this.corners[1][0]){
							this.corners[1][0] = j * 16;
							this.corners[1][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("6")) {
						this.walls.add(new Wall(j*16, i*16, this.loadImage("src/main/resources/downRight.png")));
						if (this.corners[0][0] == 0 && this.corners[0][1] == 0){
							this.corners[0][0] = j * 16;
							this.corners[0][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("7")) {
						this.fruits.add(new Fruit(j*16, i*16, this.loadImage("src/main/resources/fruit.png")));
					}
					else if (list_of_line[j].equals("p")) {
						PImage[] player = new PImage[5];
						player[0] = this.loadImage("src/main/resources/playerClosed.png");
						player[1] = this.loadImage("src/main/resources/playerLeft.png");
						player[2] = this.loadImage("src/main/resources/playerRight.png");
						player[3] = this.loadImage("src/main/resources/playerUp.png");
						player[4] = this.loadImage("src/main/resources/playerDown.png");
						this.player = new Player(j*16, i*16, speed, player, this.walls);
					}
					else if (list_of_line[j].equals("g")) {
						this.ghosts.add(new Ghost(j*16, i*16, speed, this.loadImage("src/main/resources/ghost.png"), this.walls, this.modeLengths, this.corners));
					}
					else {
						throw new Exception("wrong in " + mapFile);
					}
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}	


	public void setup() {
		frameRate(60);
		// parse the map and load images
		this.mapRead();
		
	}

	public void settings() {
		size(WIDTH, HEIGHT);
	}

	public void draw() { 
		this.rect(-1, -1, WIDTH, HEIGHT);
		background(0, 0, 0);

		// draw the number of lives
		for (int i = 0; i < this.lives; i++){
			this.image(this.loadImage("src/main/resources/playerRight.png"), i * 28, 544);
		}

		for (Wall wall: walls){
			wall.draw(this);
		}

		this.player.tick();
		this.player.draw(this);


		for (Ghost ghost: ghosts){
			ghost.tick(this.frameRate, this.player.getX(), this.player.getY());
			ghost.draw(this, this.debug);
		}

		this.checkFruit();
		for (Fruit fruit: fruits){
			fruit.draw(this);
		}

	}

	public void checkFruit(){
		for (int i = 0; i < fruits.size(); i++){
			if (fruits.get(i).eaten(this.player.getX(), this.player.getY())){
				fruits.remove(i);
			}
		}
	}

	public void keyPressed(){
		if (keyCode == 32){
			this.debug = !this.debug;
		}
		if (key == CODED) {
			this.player.keyPressed(keyCode);
		}
		else{
			return;
		}
	}

	public static void main(String[] args) {
		PApplet.main("ghost.App");
	}

}
