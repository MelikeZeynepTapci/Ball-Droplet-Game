public class Ball {
    private int x,y, ballSpeedX, ballSpeedY;

    //Constructor:
    public Ball(int x,int y, int ballSpeedX, int ballSpeedY){
        this.x=x;
        this.y=y;
        this.ballSpeedX=ballSpeedX;
        this.ballSpeedY=ballSpeedY;
    }
    //Getter and Setter methods:
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBallSpeedX() {
        return ballSpeedX;
    }

    public void setBallSpeedX(int ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }
    public int getBallSpeedY() {
        return ballSpeedX;
    }

    public void setBallSpeedY(int ballSpeedY) {
        this.ballSpeedX = ballSpeedY;
    }
}
