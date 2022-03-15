import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseListener{
boolean move = true;
boolean lost = false;
boolean play = false;
private int birdY = 350;
private double gravity = 1;
private Timer timer;
private double delay = 1;
private int mvmt = -1;
private int obsOne = 500;
private int obsTwo = 850;
private int safeZoneOne = (int)(Math.random()*200)+100;
private int safeZoneTwo = (int)(Math.random()*200)+100;
private int score = 0;
private int mvmtCount = 99990;
public Gameplay() {
addKeyListener(this);
addMouseListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(false);
timer = new Timer((int)delay,this);
timer.start();
System.nanoTime();
}
public void paint(Graphics g) {
//background
g.setColor(Color.BLUE);
g.fillRect(0, 0, 500, 700);
//ground
g.setColor(Color.ORANGE);
g.fillRect(0, 650, 600, 50);
//obstacle one
g.setColor(Color.black);
g.fillRect(obsOne, 0, 100, safeZoneOne);
g.fillRect(obsOne-20, safeZoneOne-30, 140, 30);
g.fillRect(obsOne, safeZoneOne+150, 100, 700);
g.fillRect(obsOne-20, safeZoneOne+150, 140, 30);
g.setColor(Color.green);
g.fillRect(obsOne+4,-1,92,safeZoneOne-2);
g.fillRect(obsOne-16, safeZoneOne-28, 132, 26);
g.fillRect(obsOne+4, safeZoneOne+153, 92, 700);
g.fillRect(obsOne-16, safeZoneOne+152, 132, 26);
//obstacle two
g.setColor(Color.black);
g.fillRect(obsTwo, 0, 100, safeZoneTwo);
g.fillRect(obsTwo-20, safeZoneTwo-30, 140, 30);
g.fillRect(obsTwo, safeZoneTwo+150, 100, 700);
g.fillRect(obsTwo-20, safeZoneTwo+150, 140, 30);
g.setColor(Color.green);
g.fillRect(obsTwo+4,-1,92,safeZoneTwo-2);
g.fillRect(obsTwo-16, safeZoneTwo-28, 132, 26);
g.fillRect(obsTwo+4, safeZoneTwo+153, 92, 700);
g.fillRect(obsTwo-16, safeZoneTwo+152, 132, 26);
//bird
g.setColor(Color.BLACK);
g.fillOval(50, birdY, 31, 30);
g.setColor(Color.YELLOW);
g.fillOval(52,birdY+2,28,26);
g.setColor(Color.black);
g.fillOval(63, birdY-2, 18, 15);
g.fillOval(46, birdY+8, 18, 15);
g.setColor(Color.white);
g.fillOval(64, birdY-1, 16, 13);
g.fillOval(48, birdY+9, 15, 13);
g.setColor(Color.black);
g.fillOval(70, birdY+1, 7, 7);
//scores
g.setColor(Color.white);
g.setFont(new Font("sanfserif",Font.BOLD,76));
g.drawString(""+score, 225, 65);
//final Score
if (lost == true) {
g.setColor(Color.black);
g.fillRect(100, 226, 300, 175);
g.setColor(Color.green);{
g.fillRect(104, 230, 292, 167);
g.setColor(Color.red);
g.fillRect(108, 318, 284, 75);
g.setFont(new Font("sanfserif",Font.BOLD,35));
g.drawString("FINAL SCORE "+score, 115, 285);
//g.drawString("HIGH SCORE " +score, 108, 304);
g.setColor(Color.white);
g.drawString("PLAY AGAIN",150,370);
}
}
}
public void actionPerformed(ActionEvent e) {
if (play == true) {
timer.start();
obstacleCheck();
playerCheck();
scoreAdder();
obstacleMover();
birdMovement();
}
repaint();
}
public void scoreAdder(){
if (zoneCheck()){
addScore();
}
}
public void addScore(){
score++;
}
public boolean zoneCheck(){
if (birdY >= safeZoneOne && birdY <= safeZoneOne+150 && obsOne == 50) {
return true;
}
if (birdY >= safeZoneTwo && birdY <= safeZoneTwo+150 && obsTwo == 50) {
return true;
}
return false;
}
public void obstacleCheck(){
if (outOfBounds(obsOne)) {
obsOne = 600;
zoneOne();
}
if (outOfBounds(obsTwo)){
obsTwo = 600;
zoneTwo();
}
}
public void zoneOne(){
safeZoneOne = (int)(Math.random()*350)+100;
}
public void zoneTwo(){
safeZoneTwo = (int)(Math.random()*350)+100;
}
public boolean outOfBounds(int x){
if (x == -100){
return true;
}
return false;
}
public void playerCheck(){
if (new Rectangle(50,birdY,30,30).intersects(obsOne,0,100,safeZoneOne) || new Rectangle(50,birdY,30,30).intersects(new Rectangle(obsOne, safeZoneOne+150, 100, 700))) {
gameStateChanger(play, lost, move);
}
if (new Rectangle(50,birdY,30,30).intersects(obsTwo, 0, 100, safeZoneTwo) || new Rectangle(50,birdY,30,30).intersects(obsTwo, safeZoneTwo+150, 100, 700) ) {
gameStateChanger(play, lost, move);
}
if (birdY >= 620) {
gameStateChanger(play, lost, move);
}
}
public void obstacleMover(){
obsOne += mvmt;
obsTwo += mvmt;
}
public void birdMovement(){
if (mvmtCount > 40) {
birdY += gravity;
}
if (mvmtCount <= 40) {
mvmtCount++;
birdY+=mvmt-1;
}
}
public void keyPressed(KeyEvent e) {
if (e.getKeyCode() == KeyEvent.VK_SPACE && move == true) {
play = true;
mvmtCount = 0;
}
}
public void gameStateChanger(boolean p, boolean l, boolean m){
if (p == true && l == false && m == true){
play = false;
lost = true;
move = false;
}
else if (lost = true){
play = false;
lost = false;
move = true;
}
}
@Override
public void keyReleased(KeyEvent arg0) {
// TODO Auto-generated method stub
}
@Override
public void keyTyped(KeyEvent arg0) {
// TODO Auto-generated method stub
}
@Override
public void mouseClicked(MouseEvent e) {
}
@Override
public void mouseEntered(MouseEvent arg0) {
// TODO Auto-generated method stub
}
@Override
public void mouseExited(MouseEvent arg0) {
// TODO Auto-generated method stub
}
@Override
public void mousePressed(MouseEvent e) {
// TODO Auto-generated method stub
int x = e.getX();
int y = e.getY();
if (e.getX() <= 392 && e.getX() >= 108 && e.getY() >= 318 && e.getY() <= 393 && lost == true) {
gameStateChanger(play, lost, move);
mvmtCount = 999990;
score = 0;
birdY = 350;
obsOne = 500;
obsTwo = 850;
safeZoneOne = (int)(Math.random()*200)+120;
safeZoneTwo = (int)(Math.random()*200)+120;
}
}
@Override
public void mouseReleased(MouseEvent arg0) {
// TODO Auto-generated method stub
}
}