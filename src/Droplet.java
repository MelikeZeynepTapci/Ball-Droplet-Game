public class Droplet {
    private int x,y, dropletSpeed;
    //Constructor:
    public Droplet(int x, int y, int dropletSpeed){
        this.x=x;
        this.y=y;
        this.dropletSpeed=dropletSpeed;
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

    public int getDropletSpeed() {
        return dropletSpeed;
    }

    public void setDropletSpeed(int dropletSpeed) {
        this.dropletSpeed = dropletSpeed;
    }
}
