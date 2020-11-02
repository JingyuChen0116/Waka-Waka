package ghost;

import processing.core.*;
import java.util.ArrayList;

public class Player extends Cell{

    private int x;
    private int y;

    private int speed;
    private PImage[] player;
    private ArrayList<Wall> walls;
    private Direction cur_direction;
    private Direction next_direction;
    private boolean stop;

    public Player(int x, int y, long speed, PImage[] player, ArrayList<Wall> walls){
        this.x = x;
        this.y = y;
        this.speed = (int) speed;
        this.player = player;
        this.walls = walls;
        this.next_direction = null;
        this.cur_direction = null;
        this.stop = false;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(){
        this.y = y;
    }

    // set next direction when a key is pressed.
    public void keyPressed(int keyCode){
        if (keyCode == 37){
            this.next_direction = Direction.LEFT;
        }
        else if (keyCode == 38){
            this.next_direction = Direction.UP;
        }
        else if (keyCode == 39){
            this.next_direction = Direction.RIGHT;
        }
        else if (keyCode == 40){
            this.next_direction = Direction.DOWN;
        }

        if (cur_direction == null) {
            cur_direction = next_direction;
            next_direction = null;
        }

    }

    public void tick(){
        if (this.next_direction != null){
            if (! this.collideWithWall(this.x + (next_direction.xVal * speed), this.y + (next_direction.yVal * speed), this.walls)){
                cur_direction = next_direction;
                next_direction = null;
            }
        }
        if (cur_direction != null){
            if (! this.collideWithWall(this.x + (cur_direction.xVal * speed), this.y + (cur_direction.yVal * speed), this.walls)){
                this.x += (cur_direction.xVal * speed);
                this.y += (cur_direction.yVal * speed);
                this.stop = false;
            }
            else {
                this.stop = true;
            }
        }
    }

    public PImage getImage(int frames){
        if (this.stop || frames % 8 >= 4){
            if (this.cur_direction == Direction.LEFT){
                return this.player[1];
            }
            else if (this.cur_direction == Direction.RIGHT){
                return this.player[2];
            }
            else if (this.cur_direction == Direction.UP){
                return this.player[3];
            }
            else if (this.cur_direction == Direction.DOWN){
                return this.player[4];
            }
            else {
                return this.player[2];
            }
        }
        else {
            return this.player[0];
        }
    
    }
    
    public void draw(PApplet app){
        app.image(this.getImage(app.frameCount), this.x, this.y);
    }
}