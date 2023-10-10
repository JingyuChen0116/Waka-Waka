package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class PlayerTest{

	// // change player's directions when a key is pressed
  //    @Test
  //    public void keyPressedTest(){
  //       App app = new App();
  //       Player player = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, null);
  //       player.keyPressed(37);
  //       assertTrue(player.getCurDirection() == Cell.Direction.LEFT);
  //       assertTrue(player.getNextDirection() == null);

  //       player.keyPressed(38);
  //       assertTrue(player.getCurDirection() == Cell.Direction.LEFT);
  //       assertTrue(player.getNextDirection() == Cell.Direction.UP);

  //       player.keyPressed(40);
  //       assertTrue(player.getCurDirection() == Cell.Direction.LEFT);
  //       assertTrue(player.getNextDirection() == Cell.Direction.DOWN);

  //       player.keyPressed(39);
  //       assertTrue(player.getCurDirection() == Cell.Direction.LEFT);
  //       assertTrue(player.getNextDirection() == Cell.Direction.RIGHT);
  //    }

	// // Checking player's collision with ghosts when not in frightened mode
  //   @Test
  //    public void collideWithGhostTest_1(){
  //       App app = new App();
  //       app.lives = 3;
  //       app.playerInit[0] = 50;
  //       app.playerInit[1] = 50;
  //       app.ghostsInit.add(new int[]{0,0});
  //       app.ghostsInit.add(new int[]{0,0});
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, null);
  //       Ambusher a = new Ambusher(30, 30, app.loadImage("ambusher"), app);
  //       Whim w = new Whim(300, 300, app.loadImage("a"), app);
  //       w.aliveOrDead();
  //       a.aliveOrDead();
  //       app.ghosts.add(a);
  //       app.ghosts.add(w);
  //       app.player = p;

  //       Player.collideWithGhost(app);
  //       assertTrue(app.lives == 3);

  //       a.aliveOrDead();
  //       Player.collideWithGhost(app);
  //       assertTrue(app.lives == 2);
  //       assertTrue(app.player.getX() == 50);
  //       assertTrue(app.player.getY() == 50);
  //       assertTrue(app.player.getCurDirection() == null);
  //       assertTrue(app.player.getNextDirection() == null);
  //       assertTrue(w.isAlive());
  //       assertTrue(a.getX() == 0);
  //       assertTrue(w.getX() == 0);
  //       assertTrue(a.getY() == 0);
  //       assertTrue(w.getY() == 0);
  //    }

	// // Checking player's collision with ghosts when in frightened mode
  //    @Test
  //   public void collideWithGhostTest_2(){
  //       App app = new App();
  //       app.frightened = true;
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, null);
  //       Ambusher a = new Ambusher(30, 30, app.loadImage("ambusher"), app);
  //       app.ghosts.add(a);
  //       app.player = p;

  //       Player.collideWithGhost(app);
  //       assertFalse(a.isAlive());
  //   }

	// // Checking player's collision with ghosts when in soda mode
  //    @Test
  //   public void collideWithGhostTest_3(){
  //       App app = new App();
  //       app.soda = true;
  //       app.lives = 3;
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, null);
  //       Ambusher a = new Ambusher(30, 30, app.loadImage("ambusher"), app);
  //       app.ghosts.add(a);
  //       app.player = p;

  //       Player.collideWithGhost(app);
  //       assertTrue(app.lives == 3);
  //   }

	// // Player is movable to next direction
  //    @Test
  //   public void checkNextDirectionTest_1(){
  //       App app = new App();
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, app.walls);
  //       p.setCurDirection(Cell.Direction.LEFT);
  //       p.setNextDirection(Cell.Direction.UP);

  //       p.checkNextDirection();
  //       assertTrue(p.getCurDirection() == Cell.Direction.UP);
  //       assertNull(p.getNextDirection());
  //   }

	// // Player cannot movable to next direction
  //    @Test
  //   public void checkNextDirectionTest_2(){
  //       App app = new App();
  //       app.walls.add(new Wall(20, 4, app.loadImage("a")));
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, app.walls);
  //       p.setCurDirection(Cell.Direction.LEFT);
  //       p.setNextDirection(Cell.Direction.UP);

  //       p.checkNextDirection();
  //       assertTrue(p.getCurDirection() == Cell.Direction.LEFT);
  //       assertTrue(p.getNextDirection() == Cell.Direction.UP);
  //   }

	// // Checking if player is able to stop
  //    @Test
  //   public void checkCurDirectionTest(){
  //       App app = new App();
  //       app.walls.add(new Wall(20, 4, app.loadImage("a")));
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, app.walls);
  //       p.setCurDirection(Cell.Direction.UP);

  //       p.checkCurDirection();
  //       assertTrue(p.getX() == 20);
  //       assertTrue(p.getY() == 20);

  //       p.setCurDirection(Cell.Direction.LEFT);
  //       p.checkCurDirection();
  //       assertTrue(p.getX() == 19);
  //       assertTrue(p.getY() == 20);
  //   }

	// // Checking player's image
  //   @Test
  //   public void getImageTest(){
  //       App app = new App();
  //       PImage p1 = app.loadImage("1");
  //       PImage p2 = app.loadImage("2");
  //       PImage p3 = app.loadImage("3");
  //       PImage p4 = app.loadImage("4");
  //       PImage p5 = app.loadImage("5");
  //       Player p = new Player(20, 20, 1, new PImage[]{p1, p2, p3, p4, p5}, null);

  //       assertEquals(p.getImage(6), p3);

  //       p.setCurDirection(Cell.Direction.LEFT);
  //       assertEquals(p.getImage(6), p2);

  //       p.setCurDirection(Cell.Direction.RIGHT);
  //       assertEquals(p.getImage(6), p3);

  //       p.setCurDirection(Cell.Direction.UP);
  //       assertEquals(p.getImage(6), p4);

  //       p.setCurDirection(Cell.Direction.DOWN);
  //       assertEquals(p.getImage(6), p5);

  //       assertEquals(p.getImage(3), p1);
  //   }

	// // Checking logic
  //   @Test
  //   public void tickTest(){
  //       App app = new App();
  //       Player p = new Player(20, 20, 1, new PImage[]{app.loadImage("a")}, app.walls);
  //       p.setCurDirection(Cell.Direction.LEFT);
  //       p.setNextDirection(Cell.Direction.UP);

  //       p.tick();
  //       assertTrue(p.getX() == 20);
  //       assertTrue(p.getY() == 19);
  //   }
}