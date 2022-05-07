//Name: Melike Zeynep Tapcı
//ID: 120200067

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener, MouseListener {
    JPanel healthPointsPanel;
    JMenuBar menuBar;
    JMenu gameMenu, debugMenu;
    JMenuItem startItem, pauseItem, exitItem, ballSpeedItem, dropletSpeedItem ;
    JTextField points_txt,health_txt;
    JLabel points_label,health_label ;
    int health ;
    double points;
    Timer timer ;
    Droplet d;
    Ball b;
    boolean ballRight, ballDown, dropletDown;
    BufferedImage droplet_image, ball_image;
    Rectangle r1,r2;


    // The game is going to be executed on gamePanel, it's going to be placed in the center of our frame border-layout
    //In the north of our layout health and score (points) panel is placed.

    public gamePanel(){
        timer = new Timer(30,this);
        addMouseListener(this);

        ballRight=true;
        ballDown=true;
        dropletDown=true;

        //Creating two new objects from Ball and Droplet classes

        b = new Ball(10,50,50, 50);
        d= new Droplet(300,50,50);


        //Creating menubar, menu items and menus. It's going to be added to the top of the panel
        menuBar = new JMenuBar();
        startItem = new JMenuItem("Start");
        pauseItem = new JMenuItem("Pause");
        exitItem = new JMenuItem("Exit");
        ballSpeedItem = new JMenuItem("Change speed of the ball");
        dropletSpeedItem = new JMenuItem("Change speed of the droplets");

        gameMenu = new JMenu("Game");
        debugMenu = new JMenu("Debug");
        gameMenu.add(startItem);
        gameMenu.add(pauseItem);
        gameMenu.add(exitItem);
        debugMenu.add(ballSpeedItem);
        debugMenu.add(dropletSpeedItem);
        menuBar.add(gameMenu);
        menuBar.add((debugMenu));


        //Properties of gamePanel
        setBounds(0,0,1000,700);
        Color color1 = new Color(202, 233,255);
        setBackground(color1);


        //Creating health and points panel
        healthPointsPanel = new JPanel();
        healthPointsPanel.setLayout(new FlowLayout());
        healthPointsPanel.setBounds(0,700,1000,300);
        Color color2 = new Color(255,214,227);
        healthPointsPanel.setBackground(color2);


        health_label = new JLabel("Health: ");
        health_label.setFont(new Font("Courier", Font.BOLD, 25));
        health_label.setForeground(new Color(90,24,154));
        healthPointsPanel.add(health_label);

        health=100;
        health_txt = new JTextField(Integer.toString(health));
        health_txt.setEditable(false);
        health_txt.setHorizontalAlignment(JTextField.CENTER);
        health_txt.setColumns(20);
        healthPointsPanel.add(health_txt);

        points_label = new JLabel("Score: ");
        points_label.setFont(new Font("Courier", Font.BOLD, 25));
        points_label.setForeground(new Color(90,24,154));
        healthPointsPanel.add(points_label);

        points = 0 ;
        points_txt = new JTextField(Double.toString(points));
        points_txt.setEditable(false);
        points_txt.setHorizontalAlignment(JTextField.CENTER);
        points_txt.setColumns(20);
        healthPointsPanel.add(points_txt);


        //ActionListener for the items in menubar
        startItem.addActionListener(this);
        pauseItem.addActionListener(this);
        exitItem.addActionListener(this);
        ballSpeedItem.addActionListener(this);
        dropletSpeedItem.addActionListener(this);

        //Importing images (They will be used in paint method with drawImage later on)
        try {
            droplet_image = ImageIO.read(new FileImageInputStream(new File("droplet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ball_image = ImageIO.read(new FileImageInputStream(new File("ball.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Rectangles for mouse-event checking:
        r1 = new Rectangle(b.getX(),b.getY(),80,80);
        r2 = new Rectangle(d.getX(),d.getY(),166,166);


}


    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(droplet_image, d.getX(),d.getY(),droplet_image.getWidth()/15,droplet_image.getHeight()/15,this);
        g.drawImage(ball_image, b.getX(), b.getY(),ball_image.getWidth()/20,ball_image.getHeight()/20,this);

    }

    //METHODS FOR MENU ITEMS:
    //I took the zero level from 25 because of the menubar and I took the ending point as 950 since my panel has a width of 1000 and my image has a size.
    public void moveBall(){
        //Ball's movement in x coordinates:
        Random r1 = new Random();
        int changeX = r1.nextInt(20);
        if(b.getX()<950 && ballRight) { // move to right
            int a = b.getX();
            b.setX(a+changeX);

        }

        else if(b.getX()>25)  {
            int changeX2 = r1.nextInt(20);
            ballRight=false;
            int c = b.getX();
            b.setX(c-changeX2);

        }
        else {
            ballRight=true;
        }
        //Ball's movement in Y coordinates:
        int changeY = r1.nextInt(20);
        if(b.getY()<700 && ballDown) {
            int e = b.getY();
            b.setY(e+changeY);

        }

        else if(b.getY()>24)  {
            ballDown=false;
            int changeY2 = r1.nextInt(20);
            int h = b.getY();
            b.setY(h-changeY2);


        }
        else{
            ballDown=true;
        }

    }
    //Droplet's movement in Y coordinate and its creation in a random X coordinate
    private void fallDroplet() {

        Random r = new Random();

        if(d.getY() < 700) {
            int c = d.getY();
            d.setY(c+10);
        }
        else if(d.getY() >= 700) {
            d.setY(24);
            int f = r.nextInt(950);
            d.setX(f);
        }

    //increaseScore() method will be used in ActioneEvent part with timer
    }
    public void increaseScore(){
        points+=0.1;
        points_txt.setText(Double.toString(points));
    }
    //I created two different rectangles in the same location as our images to check if they intersect
    public void checkCollusion(){
        Rectangle r3 = new Rectangle(b.getX(),b.getY(),50,50);
        Rectangle r4 = new Rectangle(d.getX(),d.getY(),50,50);

        if(r3.intersects(r4)){
            health -=5;
            health_txt.setText(Integer.toString(health));
        }
    }
    //ballClick() and dropletClick() methods are going to be used in mouse-event part
    public void ballClick(){

        health +=1;
        health_txt.setText(Integer.toString(health));
    }
    public void dropletClick(){
        health +=3;
        health_txt.setText(Integer.toString(health));
    }
    //dropletOnFloor() method is going to be used in actionEvent part
    public void dropletOnFloor(){
        if(d.getY()>=700){
            health-=3;
        }
        health_txt.setText(Integer.toString(health));
    }
//moveBallFaster() and moveDropletFaster() are going to be used for the debug menu items in Action-event part
    public void moveBallFaster(){
        int m = b.getBallSpeedX();
        int n = b.getBallSpeedY();
        //Ball's movement in x coordinates:

        if(b.getX()<950 && ballRight) {
            int a = b.getX();
            b.setX(a+m);

        }

        else if(b.getX()>25)  {
            ballRight=false;
            int c = b.getX();
            b.setX(c-m);

        }
        else {
            ballRight=true;
        }
        //Ball's movement in Y coordinates:

        if(b.getY()<700 && ballDown) {
            int e = b.getY();
            b.setY(e+n);

        }

        else if(b.getY()>24)  {
            ballDown=false;
            int h = b.getY();
            b.setY(h-n);


        }
        else{
            ballDown=true;
        }

    }

    public void moveDropletFaster(){
        Random r = new Random();
        if(d.getY() < 700) {
            int c = d.getY();
            d.setY(c+d.getDropletSpeed());
        }
        else if(d.getY() >= 700) {
            d.setY(24);
            int f = r.nextInt(950);
            d.setX(f);
        }
    }
    //endGame() is going to be used to show message dialog and make the game closed itself when health is equal to or below zero
    public void endGame(){
        if(health<=0){
            health=0;
           setVisible(false);
           JOptionPane.showMessageDialog(this,"Game is over.\n Your score: " + points);
           System.exit(0);

        }
    }



    //Main method:

    public static void main(String[] args) {
        gamePanel myPanel = new gamePanel();
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLayout(new BorderLayout());

        frame.add(myPanel,BorderLayout.CENTER);
        frame.add(myPanel.menuBar,BorderLayout.NORTH);
        frame.add(myPanel.healthPointsPanel,BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(timer)){
            moveBall();
            fallDroplet();
            increaseScore();
            checkCollusion();
            dropletOnFloor();
            endGame();
        }
        else if(e.getSource().equals(startItem)){
            timer.start();

        }
        else if(e.getSource().equals(pauseItem)){
            timer.stop();
        }
        else if(e.getSource().equals(exitItem)){
            System.exit(0);
        }
        else if(e.getSource().equals(ballSpeedItem)){
            fallDroplet();
            moveBallFaster();
        }
        else if(e.getSource().equals(dropletSpeedItem)){
            moveDropletFaster();
        }
        repaint();

    }
    //If the rectangles contain the location that is clicked inside, ballClick() and DropletClick() methods will be executed
    @Override
    public void mouseClicked(MouseEvent e) {
        if(r1.contains(e.getLocationOnScreen())){
            ballClick();
        }
        else if(r2.contains(e.getLocationOnScreen())){
            dropletClick();
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
