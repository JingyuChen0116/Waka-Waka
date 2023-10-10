package ghost;

import processing.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class FruitTest{

//     // player eats a fruit and a superfruit
//     @Test
//     public void checkFruitTest_1(){
//         App app = new App();
//         app.player = new Player(40, 40, 1, new PImage[]{app.loadImage("ambusher")}, null);
//         Fruit f1 = new Fruit(35, 35, app.loadImage("ambusher"), app);
//         app.fruits.add(f1);
//         Fruit f2 = new Fruit(1, 1, app.loadImage("ambusher"), app);
//         app.fruits.add(f2);
//         Fruit f3 = new Fruit(80, 80, app.loadImage("ambusher"), app);
//         app.fruits.add(f3);
//         Fruit f4 = new SuperFruit(42, 42, app.loadImage("ambusher"), app);
//         app.fruits.add(f4);
//         Fruit.checkFruit(app);
//         assertTrue(app.fruits.size() == 2);
//         assertTrue(app.fruits.contains(f2));
//         assertTrue(app.fruits.contains(f3));
//         assertFalse(app.fruits.contains(f1));
//         assertFalse(app.fruits.contains(f4));
//         assertTrue(app.frightened);
//     }

//     // player eats a fruit and a soda can
//     @Test
//     public void checkFruitTest_2(){
//         App app = new App();
//         app.player = new Player(40, 40, 1, new PImage[]{app.loadImage("ambusher")}, null);
//         Fruit f1 = new Fruit(35, 35, app.loadImage("ambusher"), app);
//         app.fruits.add(f1);
//         Fruit f2 = new Fruit(1, 1, app.loadImage("ambusher"), app);
//         app.fruits.add(f2);
//         Fruit f3 = new Fruit(80, 80, app.loadImage("ambusher"), app);
//         app.fruits.add(f3);
//         Fruit f4 = new SodaCan(42, 42, app.loadImage("ambusher"), app);
//         app.fruits.add(f4);
//         Fruit.checkFruit(app);
//         assertTrue(app.fruits.size() == 2);
//         assertTrue(app.fruits.contains(f2));
//         assertTrue(app.fruits.contains(f3));
//         assertFalse(app.fruits.contains(f1));
//         assertFalse(app.fruits.contains(f4));
//         assertTrue(app.soda);
//     }

//     // player eats a super fruit during soda mode
//     @Test
//     public void checkFruitTest_3(){
//         App app = new App();
//         app.soda = true;
//         app.player = new Player(40, 40, 1, new PImage[]{app.loadImage("ambusher")}, null);
//         Fruit f3 = new Fruit(80, 80, app.loadImage("ambusher"), app);
//         app.fruits.add(f3);
//         Fruit f4 = new SuperFruit(42, 42, app.loadImage("ambusher"), app);
//         app.fruits.add(f4);
//         Fruit.checkFruit(app);
//         assertTrue(app.frightened);
//         assertFalse(app.soda);
//     }

//     /**
//      * player eats a soda can during frightened mode
//      */
//     @Test
//     public void checkFruitTest_4(){
//         App app = new App();
//         app.frightened = true;
//         app.player = new Player(40, 40, 1, new PImage[]{app.loadImage("ambusher")}, null);
//         Fruit f3 = new Fruit(80, 80, app.loadImage("ambusher"), app);
//         app.fruits.add(f3);
//         Fruit f4 = new SodaCan(42, 42, app.loadImage("ambusher"), app);
//         app.fruits.add(f4);
//         Fruit.checkFruit(app);
//         assertTrue(app.soda);
//         assertFalse(app.frightened);
//     }
}