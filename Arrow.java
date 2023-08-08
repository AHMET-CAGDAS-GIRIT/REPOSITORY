/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this class is for the arrow
it has x position and y position
it has two methods
one for drawing arrow
one for determining its position for the next frame
*/
public class Arrow{
    public double xPosition;
    public double yPosition;
    Arrow() {
    }
    Arrow(double xPosition) {
        this.xPosition = xPosition;
    }
    public void nextFrameForArrow(boolean isThereArrow){
        if(isThereArrow ){
            yPosition += 0.3;
        }
    }
    public void drawArrow(boolean isThereArrow){
        if(isThereArrow ){
            StdDraw.picture(xPosition,yPosition ,"images/arrow.png",0.2,9.0);
        }
    }
}
