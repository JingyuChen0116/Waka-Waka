package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GhostTest{

//     // Checking functionality of reinitialising the variables
//     @Test
//     public void restartTest(){
//         Ghost.setCursor(1);
//         Ghost.setCount(2);
//         Ghost.restart();
//         assertTrue(Ghost.getCursor() == 0);
//         assertTrue(Ghost.getCount() == 0);
//     }

//     // Testing the return value of isAlive method
//     @Test
//     public void isAliveTest(){
//         App app = new App();
//         Ambusher g = new Ambusher(30, 30, app.loadImage("ambusher"), app);
//         assertTrue(g.isAlive() == true);

//     }

//     // Testing the functionality of aliveOrDead method
//     @Test
//     public void aliveOrDeadTest(){
//         App app = new App();
//         Ambusher g = new Ambusher(30, 30, app.loadImage("ambusher"), app);
//         g.aliveOrDead();
//         assertTrue(g.isAlive() == false);
//     }

//     // Testing the functionality of addCount method when restarting the game
//     @Test
//     public void addCountTest_1(){
//         App app  = new App();
//         Ghost.restart();
//         Ghost.addCount(app);
//         assertEquals(Ghost.getCount(),1);
//     }

//     // Testing the functionality of addCount method in normal scenario
//     @Test
//     public void addCountTest_2(){
//         App app = new App();
//         app.frightened = true;
//         Ghost.setCount(3);
//         Ghost.addCount(app);
//         assertEquals(Ghost.getCount(),3);
//     }

//     // Testing the functionality of checkDirection method in normal scenario
//     @Test
//     public void checkDirectionTest_1(){
//         App app = new App();
//         app.images.put("wall", new String[]{"a", "a", "a", "a", "a", "a"});
// 		app.images.put("fruit", new String[]{"a", "a", "a"});
// 		app.images.put("player", new String[]{"a", "a", "a", "a", "a"});
// 		app.images.put("ghost", new String[]{"a","a", "a", "a"});
//         Processing.jsonRead("config.json", app);
//         Processing.mapRead(app);
//         Ghost g = app.ghosts.get(0);
//         g.setCurDirection(Cell.Direction.LEFT);
//         g.setTargetX(32);
//         g.setTargetY(64);
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.LEFT);

//         app.frightened = true;
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.LEFT);
//     }

//     // The ghost should turn back if it is trapped
//     @Test
//     public void checkDirectionTest_2(){
//         App app = new App();
//         app.images.put("wall", new String[]{"a", "a", "a", "a", "a", "a"});
// 		app.images.put("fruit", new String[]{"a", "a", "a"});
// 		app.images.put("player", new String[]{"a", "a", "a", "a", "a"});
// 		app.images.put("ghost", new String[]{"a","a", "a", "a"});
//         Processing.jsonRead("test.json", app);
//         Processing.mapRead(app);
//         Ghost g = app.ghosts.get(0);
//         g.setCurDirection(Cell.Direction.LEFT);
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.RIGHT);

//         g.setCurDirection(Cell.Direction.UP);
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.DOWN);

//         g.setCurDirection(Cell.Direction.DOWN);
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.UP);
        
//         g.setCurDirection(Cell.Direction.RIGHT);
//         g.checkDirection();
//         assertTrue(g.getCurDirection() == Cell.Direction.LEFT);
//     }

//     // Testing if the ghost can move as expected
//     @Test
//     public void tickTest(){
//         App app = new App();
//         app.corners[0][0] = 0;
//         app.corners[0][1] = 0;
//         app.player = new Player(100, 100, 1, new PImage[]{app.loadImage("a")}, app.walls);
//         Processing.jsonRead("config.json", app);
//         Ghost g = new Chaser(20, 20, app.loadImage("a"), app);

//         g.setCount(300);
//         g.setCursor(0);
//         g.tick();
//         assertTrue(g.getTargetX() == 0);
//         assertTrue(g.getTargetY() == 0);

//         g.setCursor(1);
//         g.tick();
//         assertTrue(g.getTargetX() == 100);
//         assertTrue(g.getTargetY() == 100);

//         app.player = new Player(-50, -50, 1, new PImage[]{app.loadImage("a")}, app.walls);
//         g.tick();
//         assertTrue(g.getTargetX() == 0);
//         assertTrue(g.getTargetY() == 0);

//         app.player = new Player(3000, 3000, 1, new PImage[]{app.loadImage("a")}, app.walls);
//         g.tick();
//         assertTrue(g.getTargetX() == app.WIDTH);
//         assertTrue(g.getTargetY() == app.HEIGHT);

//         g.setCursor(0);
//         g.setCount(5000);
//         g.tick();
//         assert(g.getCursor() == 1);
//         assert(g.getCount() == 0);
        
//         g.setCursor(7);
//         g.setCount(5000000);
//         g.tick();
//         assert(g.getCursor() == 0);
//         assert(g.getCount() == 0);
//     }
}