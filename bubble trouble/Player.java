/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this class is for the player.it has three methods
one is for determining players position in the next frame
one is for drawing the player
one is for checking whether the player touches any ball or not
*/
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player{
    public double xPosition;
    Player(double xPosition ){
        this.xPosition = xPosition;
    }
    public void nextFrameForPlayer(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            if(xPosition < 15.59){
                xPosition += 0.08;
            }
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            if(xPosition > 0.41){
                xPosition -= 0.08;
            }
        }
    }
    public void drawPlayer(){
        StdDraw.picture(xPosition, 0.5625,"images/player_back.png",0.821,1.125);
    }
    public boolean doesItTouchAnyBall(ArrayList<Ball> ballArrayList){
        for(int i = 0; i < ballArrayList.size();i++){
            if(ballArrayList.get(i).yPosition < 1.125 + ballArrayList.get(i).radius/2 && ballArrayList.get(i).xPosition > xPosition-0.410-ballArrayList.get(i).radius/2 && ballArrayList.get(i).xPosition < xPosition+0.410+ballArrayList.get(i).radius/2){
                if(ballArrayList.get(i).yPosition < 1.125){
                    return true;
                }
                if(ballArrayList.get(i).xPosition > xPosition-0.410 && ballArrayList.get(i).xPosition < xPosition+0.410){
                    return true;
                }
                if(Math.pow(ballArrayList.get(i).xPosition-xPosition+0.410,2) + Math.pow(ballArrayList.get(i).yPosition-1.125,2) < Math.pow(ballArrayList.get(i).radius/2 ,2)){
                    return true;
                }
                if(Math.pow(ballArrayList.get(i).xPosition-xPosition-0.410,2) + Math.pow(ballArrayList.get(i).yPosition-1.125,2) < Math.pow(ballArrayList.get(i).radius/2 ,2) ){
                    return true;
                }
            }
        }
        return false;
    }
}
