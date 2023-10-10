package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class AmbusherTest{

    test for scatter mode
    @Test
    public void getScatterCornerTest(){
        App app = new App();
        PImage p = app.loadImage("ambusher");
        Ambusher a = new Ambusher(30, 30, p, app);
        assertTrue(a.getScatterCorner() == 1);
    }

    // test for chase mode: player direction is not none
    @Test
    public void setTargetTest_1(){
        App app = new App();
        app.player = new Player(80, 80, 1, new PImage[]{app.loadImage("ambusher")}, null);
        app.player.setCurDirection(Cell.Direction.LEFT);
        Ambusher a = new Ambusher(30, 30, app.loadImage("ambusher"), app);
        a.setTarget();
        assertTrue(a.getTargetX() == 16);
        assertTrue(a.getTargetY() == 80);
    }

    // test for chase mode: player direction is none
    @Test
    public void setTargetTest_2(){
        App app = new App();
        app.player = new Player(80, 80, 1, new PImage[]{app.loadImage("ambusher")}, null);
        Ambusher a = new Ambusher(30, 30, app.loadImage("ambusher"), app);
        a.setTarget();
        assertTrue(a.getTargetX() == 144);
        assertTrue(a.getTargetY() == 80);
    }
}