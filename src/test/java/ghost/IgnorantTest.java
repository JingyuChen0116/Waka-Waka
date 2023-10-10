package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class IgnorantTest{

    // test for scatter mode
    @Test
    public void getScatterCornerTest(){
        App app = new App();
        PImage p = app.loadImage("ambusher");
        Ignorant a = new Ignorant(30, 30, p, app);
        assertTrue(a.getScatterCorner() == 2);
    }

    // test for chae mode: distance from the player less than 8 units
    @Test
    public void setTargetTest_1(){
        App app = new App();
        app.player = new Player(10, 10, 1, new PImage[]{app.loadImage("ambusher")}, null);
        Ignorant a = new Ignorant(240, 240, app.loadImage("ambusher"), app);
        a.setTarget();
        assertTrue(a.getTargetX() == 10);
        assertTrue(a.getTargetY() == 10);
    }

    // test for chase mode: distance from the player greater than 8 units
    @Test
    public void setTargetTest_2(){
        App app = new App();
        app.corners[2] = new int[] {0, 540};
        app.player = new Player(80, 80, 1, new PImage[]{app.loadImage("ambusher")}, null);
        Ignorant a = new Ignorant(60, 60, app.loadImage("ambusher"), app);
        a.setTarget();
        assertTrue(a.getTargetX() == 0);
        assertTrue(a.getTargetY() == 540);
    }
}