//package TestSite;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;


public class Keyboardmove implements Runnable {
  
  JFrame frame;
  int myX = 240;
  int myY = 400;
  static int enemycount = 0;
  static int bosscheck = 0;
  static int level = 1;//this determines wether or not they face the final boss 
  //level must be equal to 4 to face the final boss
  int x1 = (int) (Math.random () * 30) +220; 
  int y1= (int) (Math.random () * 10) +30;
  
  int x2= (int) (Math.random () * 70) +310;
  int y2= (int) (Math.random () * 30) +245;
  
  int x3=(int) (Math.random () * 30) +210;
  int y3=(int) (Math.random () * 5) +210;
  
  int x4=(int) (Math.random () * 20) +230;
  int y4=(int) (Math.random () * 40) +439;
  
  
  Canvas canvas;
  BufferStrategy bufferStrategy;
  boolean running = true;
  
  public Keyboardmove() {
    Battle.catastrophecount = 0;
    Battle.healcount = 0;
    Battle.brasscount = 0;
    Battle.brilliance = 0;
    Battle.supernova = 0;
    Battle.healcount = 0;
    int check = Battle.getstatus();
    System.out.println (enemycount + "-enemies defeated");
    System.out.println ("Current level: " + level);
    if (check !=1)
    {
      frame = new JFrame("Role-Playing Game");
      JPanel panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(500, 500));
      panel.setLayout(null);
      canvas = new Canvas();
      canvas.setBounds(0, 0, 500, 500);
      canvas.setIgnoreRepaint(true);
      panel.add(canvas);
      canvas.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent evt) {
          moveIt(evt);
        }
      });
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
    }
    else 
    {}
  }
  public void run() {
    int check = Battle.getstatus ();
    if (check !=1)
    {
      while (running = true) {
        Paint();
        try {
          Thread.sleep(25);
        } catch (InterruptedException e) {
        }
      }
    }
  }
  public static void main(String[] args) {
    Battle.getcharacterinfo ();
    Battle.charmove [0]= "Cloudkill";
    Battle.charmove [1]= "Divine Heal";
    Battle.charmove [2]= "Chemtrail Smog";
    Battle.charmove [3]= "Oblivion Contract";
    Keyboardmove ex = new Keyboardmove();
    new Thread(ex).start();
  }
  public void Paint() {
    int check = Battle.getstatus ();
    
    if (check != 1)
    {
      try
      {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
        Paint(g);
        bufferStrategy.show();
      }
      catch (IllegalStateException e)
      {
      }
      
      
    }
    else
    {
      
    }
  }
  
  protected void Paint(Graphics2D g) {
    g.setColor (Color.GREEN);
    g.fillRect (0,0,500,500);
    g.setColor (Color.ORANGE);
    g.fillRect (220,0,80,1000);
    g.fillRect (0,220,1000,80);
    g.fillOval(185, 185, 150, 150);
    g.setColor (Color.GRAY);
    g.fillOval(230, 230, 50, 50);
    g.setColor (Color.CYAN);
    g.fillOval(235, 235, 40, 40);
    g.setColor (Color.RED);
    g.drawString ("Current Area: " + level,366,30);
    if (level > 4)
    {
      g.drawString("You have beaten the game!",340,40);
    }
    if (enemycount != 3)
    {
      g.drawString("move towards an ENEMY",340,60);
      g.drawString("using arrow keys",360,80);
    }
    else {
      if (level <= 4)
      {
      g.drawString("move towards YELLOW target",310,350);
      g.drawString("to face the BOSS",330,370);
      }
    }
    if (enemycount < 3)
    {
      g.fillOval(x1,y1 , 20, 20);
      
      g.fillOval(x2,y2 , 20, 20);
      
      g.fillOval(x3,y3 , 20, 20);
      
      g.fillOval(x4,y4 , 20, 20);
      if (enemycount == 3)
      g.setColor (Color.YELLOW);
      g.fillOval (245,245 , 20, 20);
    }
    if (enemycount == 3)
    {
      g.setColor (Color.YELLOW);
      g.fillOval (245,245 , 20, 20); 
    }
    g.setColor (Color.BLACK);
    g.fillOval(myX, myY, 20, 20);
  }
  public void moveIt(KeyEvent evt) {
    
    if (enemycount <= 2)
    {
      if (((x1 <= myX+20 && x1 >= myX-20) || (x2 <= myX+20 && x2 >= myX-20) || (x3 <= myX+20 && x3 >= myX-20) || (x4 <= myX+20 && x4 >= myX-20)) && ((y1 <= myY+20 && y1 >= myY-20) || (y2 <= myY+20 && y2 >= myY-20) || (y3 <= myY+20 && y3 >= myY-20) || (y4 <= myY+20 && y4 >= myY-20)))
      {
        frame.dispose();
        frame = new JFrame ();
        Battle.MapGUI (frame.getContentPane(), frame);
        ////if they arent on a  boss
        return;
      }
    }
    
    if (enemycount == 3)
    {
      
      if ((myX >=220 && myX <=270) && (myY >=220 && myY <=270))
      {
        frame.dispose();
        frame = new JFrame ();
        enemycount = 0;
        bosscheck =1;
        
        Battle.MapGUI (frame.getContentPane(), frame);
        ////if they are on a  boss
        
        
        return;
        
        // if they are on the boss make sure myY and myX are in close proximity to the star in order for the map to close
      }
    }
    switch (evt.getKeyCode()) {
      case KeyEvent.VK_DOWN:
        myY +=5;
        if (myY==280) 
        {
          myY -=5;
          if (myX < 280 && myX > 215)
          {
            myY +=5;
            
          }
        }
        break;
      case KeyEvent.VK_UP:
        myY -= 5;
        if (myY==215) ///imits
        {
          myY +=5;
          if (myX < 280 && myX > 215)
          {
            myY -=5;
          }
        }
        break;
      case KeyEvent.VK_LEFT:
        myX -=5;
        if (myX==215) //make sure its 215///imits
        {
          myX +=5;
          if (myY < 280 && myY > 215)
          {
            myX -=5;
          }
        }
        break;
      case KeyEvent.VK_RIGHT:
        myX +=5;
        if (myX==280) ///imits
        {
          myX -=5;
          if (myY < 280 && myY > 215)
          {
            myX +=5; 
            
          }
        }
        break;   
    }
    
  }
}