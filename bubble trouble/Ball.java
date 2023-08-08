/*
AHMET CAGDAS GIRIT
2022400144
13.04.2023
this class is for the ball object
ball has an x position, y position, x velocity,y velocity, level and radius (which is determined by the ball)
it has three method
one is for determining its position in the next frame
one is for drawing the ball
one is for determining whether any particular ball touches the arrow
*/
public class Ball{
    public int ballLevel;
    public double xPosition;
    public double yPosition;
    public double xVelocity = 0.0;
    public double yVelocity = 0.0;
    public double radius;
    private static final double gravity = 0.005;
    // 0.000003/0.015 = 0.005 .That's why I wrote 0.005 .You have to calculate the change in velocity per frame from change in velocity per second.
    Ball(double xPosition , double yPosition,int ballLevel){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.ballLevel = ballLevel;
        radius = 0.175 * Math.pow(2,1 + ballLevel);
    }
    Ball(double xPosition , double yPosition,double xVelocity,double yVelocity,int ballLevel){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.ballLevel = ballLevel;
        radius = 0.175 * Math.pow(2,1 + ballLevel);
    }

    public void nextFrameForBall(){
        xPosition += xVelocity;
        yPosition += yVelocity;
        yVelocity -= gravity;
        if(xPosition + radius > 16.0){
            xPosition = 16.0 -radius;
         xVelocity = -Math.abs(xVelocity);
        }
        if(xPosition - radius < 0.0){
            xPosition = radius;
         xVelocity = Math.abs(xVelocity );
        }
        if(yPosition - radius < 0.0){
            yPosition = radius;
            yVelocity = Math.abs(yVelocity);
        }
        if(yPosition > radius * 4){
            yPosition -= Math.abs(yVelocity)/2;
        }
    }
    public void drawBall(){
        StdDraw.picture(xPosition,yPosition,"images/ball.png",radius,radius);
    }
    public boolean doesItTouchAnyArrow(Arrow theArrow) {
        if(yPosition < theArrow.yPosition + 4.5 + radius/2 && xPosition > theArrow.xPosition - 0.1 - radius/2 && xPosition < theArrow.xPosition + 0.1 + radius/2){
            if(yPosition < theArrow.yPosition + 4.5){
                return true;
            }
            if(Math.pow(xPosition - theArrow.xPosition, 2) + Math.pow(yPosition - theArrow.yPosition - 4.5, 2) < Math.pow(radius / 2, 2)) {
                return true;
            }
        }
        return false;
    }
}
