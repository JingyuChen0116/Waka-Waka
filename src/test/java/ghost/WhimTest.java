package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class WhimTest{
    
    // test for scatter mode: targetting on bottom right corner
    @Test
    public void getScatterCornerTest(){
        App app = new App();
        PImage p = app.loadImage("ambusher");
        Whim a = new Whim(30, 30, p, app);
        assertTrue(a.getScatterCorner() == 3);
    }

    // test for chase mode
    @Test
    public void setTargetTest_1(){
        App app = new App();
        app.player = new Player(80, 80, 1, new PImage[]{app.loadImage("ambusher")}, null);
        Whim w = new Whim(30, 30, app.loadImage("ambusher"), app);
        Chaser c = new Chaser(20, 20, app.loadImage("ambusher"), app);
        Ambusher a = new Ambusher(40, 40, app.loadImage("am"), app);
        app.ghosts.add(c);
        app.ghosts.add(a);
        w.setTarget();
        assertTrue(w.getTargetX() == 180);
        assertTrue(w.getTargetY() == 180);
    }

}