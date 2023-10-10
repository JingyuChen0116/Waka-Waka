package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import processing.core.*;

public class ProcessingTest{

//     // Reading the json file with normal data 
//     @Test
//     public void jsonReadTest_1(){
//         App app = new App();
//         Processing.jsonRead("config.json", app);
//         assertTrue(app.mapFile.equals("map.txt"));
//         assertTrue(app.lives == 3);
//         assertTrue(app.speed == 1);
//         assertTrue(app.frightenedLength == 15);
//         long[] lengths = new long[]{10, 20, 15, 20, 15, 20, 15, 1000};
//         for(int i = 0; i < app.modeLengths.size(); i++){
//             assertTrue((long) app.modeLengths.get(i) == lengths[i]);
//         }
//     }

//     // Reading the json file with different order and weird data 
//     @Test
//     public void jsonReadTest_2(){
//         App app = new App();
//         Processing.jsonRead("test.json", app);
//         assertTrue(app.mapFile.equals("map1.txt"));
//         assertTrue(app.lives == 32);
//         assertTrue(app.speed == 3);
//         assertTrue(app.frightenedLength == 15);
//         long[] lengths = new long[]{1000, 2, 15500, 2020, 15938, 20, 15, 1000};
//         for(int i = 0; i < app.modeLengths.size(); i++){
//             assertTrue((long) app.modeLengths.get(i) == lengths[i]);
//         }
//     }

//     // Reading nonexistent json file
//     @Test
//     public void jsonReadTest_3(){
//         App app = new App();
//         Processing.jsonRead("noexist.json", app);
//         assertTrue(app.mapFile == null);
//         assertTrue(app.lives == 0);
//         assertTrue(app.speed == 0);
//         assertTrue(app.frightenedLength == 0);
//     }

//     // Reading json file with missed information
//     @Test
//     public void jsonReadTest_4(){
//         App app = new App();
//         Processing.jsonRead("error.json", app);
//         assertTrue(app.mapFile.equals("map.txt"));
//         assertTrue(app.lives == 3);
//         assertTrue(app.speed == 0);
//         assertTrue(app.frightenedLength == 0);
//     }

//     // add images to app
//     @Test
//     public void loadImagesTest(){
//         App app = new App();
//         Processing.loadImages(app);
//         assertTrue(app.images.get("wall").length == 6);
//         assertTrue(app.images.get("fruit").length == 3);
//         assertTrue(app.images.get("player").length == 5);
//         assertTrue(app.images.get("ghost").length == 4);
//     }

//     // Reading nonexistent map file
//     @Test
//     public void mapReadTest_1(){
//         App app = new App();
//         app.mapFile = "notExist.txt";
//         Processing.mapRead(app);

//         assertNull(app.player);
//         assertTrue(app.walls.size() == 0);
//         assertTrue(app.fruits.size() == 0);
//         assertTrue(app.ghosts.size() == 0);
//     }

//     // Reading map with diferent elements
//     @Test
//     public void mapReadTest_2(){
//         App app = new App();
//         app.images.put("wall", new String[]{"a", "a", "a", "a", "a", "a"});
// 		app.images.put("fruit", new String[]{"a", "a", "a"});
// 		app.images.put("player", new String[]{"a", "a", "a", "a", "a"});
// 		app.images.put("ghost", new String[]{"a","a", "a", "a"});

//         app.mapFile = "map.txt";
//         Processing.mapRead(app);
//         assertTrue(app.walls.size() == 490);
//         assertTrue(app.fruits.size() == 303);
//         assertTrue(app.ghosts.size() == 4);
//         assertNotNull(app.player);
//         assertTrue(app.player.getX() == 208);
//         assertTrue(app.player.getY() == 320);
//     }

//     // Reading map with wrong symbol
//      @Test
//     public void mapRead_3(){
//         App app = new App();
//         app.mapFile = "wrong.txt";
//         Processing.mapRead(app);
//         assertNull(app.player);
//         assertTrue(app.walls.size() == 0);
//         assertTrue(app.fruits.size() == 0);
//         assertTrue(app.ghosts.size() == 0);
//     }

//     // check end when the game has not ended
//     @Test
//     public void checkEndTest_1(){
//         App app = new App();
//         app.lives = 1;
//         app.walls.add(new Wall(10, 10, app.loadImage("a")));
//         app.fruits.add(new Fruit(20, 20, app.loadImage("a"), app));
//         app.corners[0][1] = 3;
//         app.corners[0][0] = 4;
//         app.playerInit[0] = 5;
//         app.playerInit[1] = 6;
//         app.frightenedCount = 300;
//         app.frightened = true;
//         Processing.checkEnd(app);
//         assertTrue(app.walls.size() == 1);
//         assertTrue(app.fruits.size() == 1);
//         assertTrue(app.corners[0][1] == 3);
//         assertTrue(app.playerInit[1] == 6);
//         assertTrue(app.frightenedCount == 300);
//     }

//     // check debug mode by a key
//     @Test 
//     public void checkKeyTest() {
//         App app = new App();
//         app.key = 32;
//         Processing.checkKey(app);
//         assertTrue(app.debug);
//         Processing.checkKey(app);
//         assertFalse(app.debug);
//     }

//     // check debug mode by a key
//     @Test 
//     public void checkKeyTest_1() {
//         App app = new App();
//         app.key = 32;
//         Processing.checkKey(app);
//         assertTrue(app.debug);
//         Processing.checkKey(app);
//         assertFalse(app.debug);
//     }

//     // check debug mode by a key
//     @Test 
//     public void checkKeyTest_2() {
//         App app = new App();
//         app.player = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, app.walls);
//         app.player.setCurDirection(Cell.Direction.UP);
//         app.key = 37;
//         Processing.checkKey(app);
//         assertTrue(app.player.getNextDirection() == Cell.Direction.LEFT);
//     }

//     // Test if the screen with message is able to stay for 10 secs
//     @Test
//     public void waitTenSecondTest(){
//         App app = new App();
//         Processing.waitTenSecond(app);
//     }

//     // Testing the functionality to restart the game
//     @Test
//     public void restartTest(){
//         App app = new App();
//         app.walls.add(new Wall(10, 10, app.loadImage("a")));
//         app.fruits.add(new Fruit(20, 20, app.loadImage("a"), app));
//         app.corners[0][1] = 3;
//         app.corners[0][0] = 4;
//         app.playerInit[0] = 5;
//         app.playerInit[1] = 6;
//         app.frightenedCount = 300;
//         app.frightened = true;
//         Processing.restart(app);
//         assertTrue(app.walls.size() == 0);
//         assertTrue(app.fruits.size() == 0);
//         assertTrue(app.corners[0][1] == 0);
//         assertTrue(app.playerInit[1] == 0);
//         assertTrue(app.frightenedCount == 0);
//         assertTrue(app.frightened == false);
//     }

//     // Checking for frightened mode 
//     @Test
//     public void checkModeTest_1(){
//         App app = new App();
//         app.frameRate = 60;
//         app.frightenedLength = 6;
//         app.frightenedCount = 359;
//         Processing.checkMode(app);
//         assertTrue(app.frightenedCount == 359);

//         app.frightened = true;
//         Processing.checkMode(app);
//         assertTrue(app.frightenedCount == 360);
        
//         Processing.checkMode(app);
//         assertFalse(app.frightened);
//     }

//     // Checking for soda mode 
//     @Test
//     public void checkModeTest_2(){
//         App app = new App();
//         app.frameRate = 60;
//         app.sodaCount = 899;
//         Processing.checkMode(app);
//         assertTrue(app.sodaCount == 899);

//         app.soda = true;
//         Processing.checkMode(app);
//         assertTrue(app.sodaCount == 900);
        
//         Processing.checkMode(app);
//         assertFalse(app.soda);
//     }

}