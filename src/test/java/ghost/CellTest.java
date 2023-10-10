package ghost;
import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CellTest{

    // The following four tesr cases test for enums x and y values
    @Test
    public void leftTest(){
        assertEquals(Cell.Direction.LEFT.xVal, -1);
        assertEquals(Cell.Direction.LEFT.yVal, 0);
    }

    @Test
    public void rightTest(){
        assertEquals(Cell.Direction.RIGHT.xVal, 1);
        assertEquals(Cell.Direction.RIGHT.yVal, 0);
    }

    @Test
    public void upTest(){
        assertEquals(Cell.Direction.UP.xVal, 0);
        assertEquals(Cell.Direction.UP.yVal, -1);
    }

    @Test
    public void downTest(){
        assertEquals(Cell.Direction.DOWN.xVal, 0);
        assertEquals(Cell.Direction.DOWN.yVal, 1);
    }

    // The rest of following are testing the functionality of collideWithObject methods with different positions
    @Test
    public void collision_1(){
        App app = new App();
        Wall w = new Wall(30, 30, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_2(){
        App app = new App();
        Wall w = new Wall(16, 30, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_3(){
        App app = new App();
        Wall w = new Wall(6, 30, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_4(){
        App app = new App();
        Wall w = new Wall(30, 6, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_5(){
        App app = new App();
        Wall w = new Wall(16, 6, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_6(){
        App app = new App();
        Wall w = new Wall(6, 6, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_7(){
        App app = new App();
        Wall w = new Wall(40, 40, app.loadImage("w"));
        assertFalse(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_8(){
        App app = new App();
        Wall w = new Wall(35, 40, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(40, 40, w));
    }

    @Test
    public void collision_9(){
        App app = new App();
        Wall w = new Wall(45, 40, app.loadImage("w"));
        assertTrue(Cell.collideWithObject(40, 40, w));
    }

    @Test
    public void collision_10(){
        App app = new App();
        Wall w = new Wall(30, 200, app.loadImage("w"));
        assertFalse(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_11(){
        App app = new App();
        Wall w = new Wall(7, 400, app.loadImage("w"));
        assertFalse(Cell.collideWithObject(16, 16, w));
    }

    @Test
    public void collision_12(){
        App app = new App();
        Wall w = new Wall(16, 400, app.loadImage("w"));
        assertFalse(Cell.collideWithObject(16, 16, w));
    }

}