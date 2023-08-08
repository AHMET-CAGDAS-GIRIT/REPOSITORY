/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this is the class that essentially runs the game
is has an arrow, player, bar, time remaining and an arraylist of balls
didThePlayerPassTheLevel method both runs the game and tells the code in Ahmet_cagdas_girit method whether the player passed the level or not
it runs the game frame by frame with frame interval being 15 ms
in each frame:
it moves player , creates and destroys arrow, checks whether any ball touches the player or the arrow (and creates two new balls if it touches an arrow),
creates arrow when touched space and checks whether the arrow touches the ceiling or not
checks is there any ball left and calls display method for drawing the game

display method draws everything in the game in each frame
*/
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Environment {
    public boolean isThereArrow = false;
    public ArrayList<Ball> ballArrayList = new ArrayList<Ball>(0);
    public Bar theBar = new Bar();
    public Player player = new Player(8.0);
    public Arrow theArrow = new Arrow();
    public double timeRemaining;
    Environment(){
        timeRemaining = 1;
    }
    public boolean didThePlayerPassTheLevel(int level){
        ballArrayList.removeAll(ballArrayList);
        timeRemaining = 1;
        isThereArrow = false;
        player.xPosition =8.0;
        if(level == 0){
            ballArrayList.add(new Ball(4.0,4.5,0.1,0.1,0));
            ballArrayList.add(new Ball(5.33,4.5,-0.1,0.1,1));
            ballArrayList.add(new Ball(4.0,4.5,0.1,0.1,2));
        }
        // first, it had 3 levels because I understood the assigment wrong.but new levels can be added easily
        /*if(level == 1){
            ballArrayList.add(new Ball(5.33,4.5,-0.1,0.1,1));
        }
        if(level == 2){
            ballArrayList.add(new Ball(4.0,4.5,0.1,0.1,2));
        }*/
        while(timeRemaining > 0) {
            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !isThereArrow){
                isThereArrow = true;
                theArrow.xPosition = player.xPosition;
                theArrow.yPosition = -4.5;
            }
            if(theArrow.yPosition > 4.5){
                isThereArrow =false;
            }
            StdDraw.clear();
            display();
            StdDraw.show();
            StdDraw.pause(15);
            timeRemaining -= 15.0/40000;
            if(ballArrayList.size() == 0){
                return true;
            }
            if(player.doesItTouchAnyBall(ballArrayList)){
                break;
            }
            for(int i = 0;i < ballArrayList.size(); i++){
                if(isThereArrow){
                    if(ballArrayList.get(i).doesItTouchAnyArrow(theArrow)){
                        isThereArrow = false;
                        if(ballArrayList.get(i).ballLevel > 0){
                            ballArrayList.add(new Ball(ballArrayList.get(i).xPosition,ballArrayList.get(i).yPosition,0.1,0.1,ballArrayList.get(i).ballLevel-1));
                            ballArrayList.add(new Ball(ballArrayList.get(i).xPosition,ballArrayList.get(i).yPosition,-0.1,0.1,ballArrayList.get(i).ballLevel-1));
                        }
                        ballArrayList.remove(i);
                        break;
                    }
                }
            }
        }
        return false;
    }
    private void display(){
        StdDraw.picture(8.0,4.5,"images/background.png",16.0,9.0);
        theArrow.nextFrameForArrow(isThereArrow);
        theArrow.drawArrow(isThereArrow);
        StdDraw.picture(8.0,-0.5,"images/bar.png",16.0,1.0);
        theBar.drawBar(timeRemaining);
        for(int i=0;i < ballArrayList.size();i++){
            ballArrayList.get(i).nextFrameForBall();
            ballArrayList.get(i).drawBall();
        }
        player.nextFrameForPlayer();
        player.drawPlayer();
    }
}
