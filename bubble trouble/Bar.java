/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this class is for the bar that becomes redder and shrinks as the time goes
it has one method for drawing the bar
*/
import java.awt.*;
public class Bar{
    Bar(){
    }
    public void drawBar(double timeRemaining){
        StdDraw.setPenColor(255,(int)(timeRemaining*255) ,0);
        StdDraw.filledRectangle(timeRemaining*16.0/2,-0.5,timeRemaining*16.0/2,0.25);
    }
}
