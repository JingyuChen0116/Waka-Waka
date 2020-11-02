package ghost;

import processing.core.*;
import java.util.ArrayList;

public class Cell{
    enum Direction {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        public final int xVal;
        public final int yVal;

        private Direction(int xVal, int yVal){
            this.xVal = xVal;
            this.yVal = yVal;
        }
    }

    public boolean collideWithWall(int x, int y, ArrayList<Wall> objects){
        for (Wall object: objects){
            int objLeft = object.getX();
            int objRight = object.getX() + 16;
            int objTop = object.getY();
            int objBottom = object.getY() + 16;

            int cellLeft = x;
            int cellRight = x + 16;
            int cellTop = y;
            int cellBottom = y + 16;

            if (cellRight > objLeft && cellRight < objRight){
                if (
                    (cellBottom < objBottom && cellBottom > objTop) 
                    || (cellBottom == objBottom && cellTop == objTop) 
                    || (cellTop < objBottom && cellTop > objTop)
                ) {
                    return true;
                }
            }
            else if (cellLeft > objLeft && cellLeft < objRight) {
                if (
                    (cellBottom < objBottom && cellBottom > objTop) 
                    || (cellBottom == objBottom && cellTop == objTop) 
                    || (cellTop < objBottom && cellTop > objTop)
                ) {
                    return true;
                }
            }
            else if (cellLeft== objLeft && cellRight == objRight) {
                if (
                    (cellTop > objTop && cellTop < objBottom) 
                    || (cellBottom > objTop && cellBottom < objBottom)
                ) {
                    return true;
                }
            }
        }
        return false;
    }
}