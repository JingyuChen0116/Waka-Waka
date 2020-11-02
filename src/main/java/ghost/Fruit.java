package ghost;

import processing.core.*;

public class Fruit extends Cell{

    private int x;
    private int y;

    private PImage fruit;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Fruit(int x, int y, PImage fruit){
        this.x = x;
        this.y = y;
        this.fruit = fruit;
    }

    public boolean eaten(int playerX, int playerY){
        int fruitLeft = this.x;
        int fruitRight = this.x + 16;
        int fruitTop = this.y;
        int fruitBottom = this.y + 16;

        int playerLeft = playerX;
        int playerRight = playerX + 16;
        int playerTop = playerY;
        int playerBottom = playerY + 16;

        if (playerRight > fruitLeft && playerRight < fruitRight){
            if (
                (playerBottom < fruitBottom && playerBottom > fruitTop) 
                || (playerBottom == fruitBottom && playerTop == fruitTop) 
                || (playerTop < fruitBottom && playerTop > fruitTop)
            ) {
                return true;
            }
        }
        else if (playerLeft > fruitLeft && playerLeft < fruitRight) {
            if (
                (playerBottom < fruitBottom && playerBottom > fruitTop) 
                || (playerBottom == fruitBottom && playerTop == fruitTop) 
                || (playerTop < fruitBottom && playerTop > fruitTop)
            ) {
                return true;
            }
        }
        else if (playerLeft== fruitLeft && playerRight == fruitRight) {
            if (
                (playerTop > fruitTop && playerTop < fruitBottom) 
                || (playerBottom > fruitTop && playerBottom < fruitBottom)
            ) {
                return true;
            }
        }
        return false;
    }

    public void tick(){
        return;
    }

    public void draw(PApplet app){
        app.image(this.fruit, this.x , this.y);
    }
}