package ghost;

import processing.core.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.Timer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Processing{

	/**g
	 * Reading the game configuration file and initialise states
	 * @param jsonFile, the name of given configuration file
	 * @param app, the game which is currently running
	 */
    public static void jsonRead(String jsonFile, App app){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(jsonFile));
			JSONObject jsonObject = (JSONObject) obj;
			app.mapFile = (String) jsonObject.get("map");
			app.lives = (Long) jsonObject.get("lives");
			app.speed = (Long) jsonObject.get("speed");
			app.frightenedLength = (Long) jsonObject.get("frightenedLength");
			app.modeLengths = (JSONArray) jsonObject.get("modeLengths");
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
	}

	/**g
	 * collection of all the images shown on the screen
	 * @param app, the game which is currently running
	 */
	public static void loadImages(App app){
		app.images.put("wall", new String[]{"src/main/resources/horizontal.png",
											"src/main/resources/vertical.png",
											"src/main/resources/upLeft.png",
											"src/main/resources/upRight.png",
											"src/main/resources/downLeft.png",
											"src/main/resources/downRight.png"
											});
		app.images.put("fruit", new String[]{"src/main/resources/fruit.png",
											"src/main/resources/superFruit.png",
											"src/main/resources/sodaCan.png"
											});
		app.images.put("player", new String[]{"src/main/resources/playerClosed.png",
											"src/main/resources/playerLeft.png",
											"src/main/resources/playerRight.png",
											"src/main/resources/playerUp.png",
											"src/main/resources/playerDown.png"
											});
		app.images.put("ghost", new String[]{"src/main/resources/ambusher.png",
											"src/main/resources/chaser.png",
											"src/main/resources/ignorant.png",
											"src/main/resources/whim.png"
											});
	}

	/**
	 * Given an app, parse the map file to create objects: player, fruits, walls and ghosts
	 * Find the position of four corners of the map
	 * Store the objects being created, the corners and ghosts and player's initial positions into app
	 * @param app, the game to be initialised
	 */
    public static void mapRead(App app){
		try{
			File f = new File(app.mapFile);
			Scanner reader = new Scanner(f);
			int i = 0;
			while (reader.hasNextLine()){
				String[] list_of_line = reader.nextLine().split("");
				for (int j = 0; j < list_of_line.length; j++){
					if (list_of_line[j].equals("0")) {
						continue;
					}
					else if (list_of_line[j].equals("1")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[0])));
					}
					else if (list_of_line[j].equals("2")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[1])));
					}
					else if (list_of_line[j].equals("3")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[2])));
						if (app.corners[3][0] == 0 && app.corners[3][1] == 0){
							app.corners[3][0] = j * 16;
							app.corners[3][1] = i * 16;
						}
						else if (i * 16 >= app.corners[2][1] && j * 16 >= app.corners[2][0]){
							app.corners[3][0] = j * 16;
							app.corners[3][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("4")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[3])));
						if (app.corners[2][0] == 0 && app.corners[2][1] == 0){
							app.corners[2][0] = j * 16;
							app.corners[2][1] = i * 16;
						}
						else if (i * 16 >= app.corners[2][1] && j * 16 <= app.corners[2][0]){
							app.corners[2][0] = j * 16;
							app.corners[2][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("5")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[4])));
						if (app.corners[1][0] == 0 && app.corners[1][1] == 0){
							app.corners[1][0] = j * 16;
							app.corners[1][1] = i * 16;
						}
						else if (i * 16 <= app.corners[1][1] && j * 16 >= app.corners[1][0]){
							app.corners[1][0] = j * 16;
							app.corners[1][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("6")) {
						app.walls.add(new Wall(j*16, i*16, app.loadImage(app.images.get("wall")[5])));
						if (app.corners[0][0] == 0 && app.corners[0][1] == 0){
							app.corners[0][0] = j * 16;
							app.corners[0][1] = i * 16;
						}
					}
					else if (list_of_line[j].equals("7")) {
						app.fruits.add(new Fruit(j*16, i*16, app.loadImage(app.images.get("fruit")[0]), app));
					}
					else if (list_of_line[j].equals("8")) {
						app.fruits.add(new SuperFruit(j*16-8, i*16-8, app.loadImage(app.images.get("fruit")[1]), app));
					}
					else if (list_of_line[j].equals("s")) {
						app.fruits.add(new SodaCan(j*16, i*16, app.loadImage(app.images.get("fruit")[2]), app));
					}
					else if (list_of_line[j].equals("p")) {
						PImage[] players = new PImage[]{app.loadImage(app.images.get("player")[0]),
														app.loadImage(app.images.get("player")[1]),
														app.loadImage(app.images.get("player")[2]),
														app.loadImage(app.images.get("player")[3]),
														app.loadImage(app.images.get("player")[4]),
														};
						app.player = new Player(j*16, i*16, app.speed, players, app.walls);
						app.playerInit[0] = j * 16;
						app.playerInit[1] = i * 16;
					}
					else if (list_of_line[j].equals("a")) {
						app.ghosts.add(new Ambusher(j*16, i*16, app.loadImage(app.images.get("ghost")[0]), app));
						app.ghostsInit.add(new int[]{j * 16, i * 16});
					}
					else if (list_of_line[j].equals("c")) {
						app.ghosts.add(new Chaser(j*16, i*16, app.loadImage(app.images.get("ghost")[1]), app));
						app.ghostsInit.add(new int[]{j * 16, i * 16});
					}
					else if (list_of_line[j].equals("i")) {
						app.ghosts.add(new Ignorant(j*16, i*16, app.loadImage(app.images.get("ghost")[2]), app));
						app.ghostsInit.add(new int[]{j * 16, i * 16});
					}
					else if (list_of_line[j].equals("w")) {
						app.ghosts.add(new Whim(j*16, i*16, app.loadImage(app.images.get("ghost")[3]), app));
						app.ghostsInit.add(new int[]{j * 16, i * 16});
					}
					else {
						throw new Exception("wrong in " + list_of_line[j]);
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
    
	/**
	 * Check if the game has finished
	 * If the player has no more lives, the game is over and the player loses the game
	 * If the player has eaten all the fruits, the player wins
	 * Stop the loop of app
	 * @param app, to show the result screen 
	 */
	public static void checkEnd(App app){
		if (app.lives == 0 || app.fruits.size() == 0) {
			app.noLoop();
			app.background(0,0,0);
			if (app.lives == 0) {
				app.text("GAME OVER", 70, 250);
			}
			else {
				app.text("YOU WIN", 95, 250);
			}
			waitTenSecond(app);
		}
	}

	/**
	 * handle the valid key
	 * @param app, the game which is running
	 */
	public static void checkKey(App app){
		if (app.key == 32){
			app.debug = !app.debug;
		}

		if (app.key >= 37 && app.key <= 40) {
			app.player.keyPressed(app.key);
		}
		else{
			return;
		}
	}


	/**
	 * Wait for ten seconds
	 * @param app, the game which is running
	 */
	public static void waitTenSecond(App app){
		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				restart(app);
			}
		};
		app.gameOver = new Timer(10000, task);
		app.gameOver.setRepeats(false);
		app.gameOver.start();
	}

	/**
	 * Restarting the game
	 * Reinitialise the variables of app
	 * Start the loop of app
	 * @param app, the game to be reinitialised and restarted
	 */
	public static void restart(App app){
		Ghost.restart();
		app.ghosts = new ArrayList<Ghost>();
		app.walls = new ArrayList<Wall>();
		app.fruits = new ArrayList<Fruit>();
		app.corners = new int[4][2];
		app.playerInit = new int[2];
		app.ghostsInit = new ArrayList<int[]>();
		app.frightenedCount = 0;
		app.debug = false;
		app.frightened = false;
		jsonRead("config.json", app);
		mapRead(app);
		app.loop();
	}

	/**
	 * Finishing the frightened mode when then time exceeds frightened mode duration
	 * Increasing the number of frames started from frightened mode if in frightened mode and not finished yet
	 * @param app, game that is currently running
	 */
	public static void checkMode(App app){
		if (app.frightened){
            if (app.frightenedCount / app.frameRate >= app.frightenedLength) {
                app.frightened = false;
            }
			else {
				app.frightenedCount++;
			}
        }

		if (app.soda){
            if (app.sodaCount / app.frameRate >= 15) {
                app.soda = false;
            }
			else {
				app.sodaCount++;
			}
        }
	}
}