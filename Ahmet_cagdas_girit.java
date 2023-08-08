/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this class is the class with the main method (which is the only method it has)
it calls didThePlayerPassTheLevel method in Player class to run the game and determine whether the player passes that particular level
if the player loses or wins all the levels it shows a game over screen that says "game over" or "you won" depending on whether you passes all the levels or not
after that it waits you to press "y" or "n"
pressing "y" resets everything and opens the game again from level 1
pressing "n" ends the game and a "thanks for playing" text shows up
note: I originally misunderstood the assignment and though the game needed to have multiple levels.
  Even though the game has 1 level,with a little adjustment (changing the numberOFLevels to something bigger than 1)
  we can make it have multiple levels (each level can designed in the Environment class didThePlayerPassTheLevel method)
*/

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ahmet_cagdas_girit {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 500);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        Environment theGame = new Environment();
        int numberOfLevels = 1;
        boolean isItRestart = true;
        int level;
        while(isItRestart){
            isItRestart = false;
            level = 0;
            while (level < numberOfLevels) {
                if (theGame.didThePlayerPassTheLevel(level)) {
                    level++;
                }else{
                    StdDraw.picture(8.0, 3.58, "images/game_screen.png", 4.21, 2.5);
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                    StdDraw.textLeft(6.5, 4.0, "Game Over!");
                    break;
                }
            }
            if(level >= numberOfLevels) {
                StdDraw.picture(8.0, 3.58, "images/game_screen.png", 4.21, 2.5);
                StdDraw.setPenColor(Color.black);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.textLeft(6.5, 4.0, "You Won!");
            }
            StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
            StdDraw.textLeft(6.5, 3.34, "To Replay Click \042Y\042");
            StdDraw.textLeft(6.5, 2.84, "To Quit Click \042N\042 ");
            StdDraw.show();
            while(true){
                StdDraw.pause(15);
                if(StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                    isItRestart = true;
                    break;
                }
                if(StdDraw.isKeyPressed(KeyEvent.VK_N)){
                    break;
                }
            }
        }
        StdDraw.clear();
        StdDraw.setFont(new Font("Helvetica", Font.BOLD, 40));
        StdDraw.textLeft(4.0,4.0,"Thanks For Playing");
        StdDraw.show();
    }
}
