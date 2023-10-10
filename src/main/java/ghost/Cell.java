package ghost;

import processing.core.*;
import java.util.ArrayList;

public abstract class Cell{
    /**
	 * Directions that the player and ghosts can move in
	 */
    enum Direction {
        /**
	     * moving right with x-coordinate increasing by 1
	     */
        RIGHT(1, 0),

        /**
	     * moving left with x-coordinate reducing by 1
	     */
        LEFT(-1, 0),
        
        /**
	     * moving up with y-coordinate reducing by 1
	     */
        UP(0, -1),

        /**
	     * moving down with y-coordinate increasing by 1
	     */
        DOWN(0, 1);

        /**
	     * The change of x-coordinate of the direction
	     */
        public final int xVal;

        /**
	     * The change of y-coordinate of the direction
	     */
        public final int yVal;

        /**
	     * Constructor for a direction
	     */
        private Direction(int xVal, int yVal){
            this.xVal = xVal;
            this.yVal = yVal;
        }
    }

    public abstract int getX();
    public abstract int getY();

    /**
     * Given a pair of coordinates and a cell object which can be a player, ghost, wall or fruit,
     * check whether the coordinates collides with the object or not
     * If collides, return true; otherwise false
     * @param x, the x-coordinate
     * @param y, the y-coordinate
     * @param object, the cell object to be checked for the collision
     * @return collision
     */
    public static boolean collideWithObject(int x, int y, Cell object){
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
    
        return false;
    }
}