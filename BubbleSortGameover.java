// The "BubbleSortStrings" class.
//package TestSite;
import java.awt.*;
import hsa.Console;
import java.io.*;
public class BubbleSortGameover
{
  static Console a;           // The output console
  
  public static void endscreen () throws IOException
  {
    BubbleSortGameover.a = new Console ("Death");
    
    int howMany;
    howMany=0;
    int x;
    x=50;
    
    a.setColor(Color.RED);
    a.setFont (new Font ("Sansserif",Font.BOLD,50));
    BufferedReader input;
    String readcheck;
    // String filename= BubbleSortGameover.class.getResourceAsStream("/death.txt");
    input = new BufferedReader (new FileReader ("death.txt"));
    readcheck = input.readLine ();
    while (readcheck != null) //this is used to get the initial value of howMany
    {
      howMany++;
      readcheck = input.readLine ();
    }
    input.close();
    String data[] = new String [howMany];
    
    input = new BufferedReader (new FileReader ("death.txt")); 
    for (int i = 0 ; i < howMany ; i++)
    {
      data [i] = input.readLine ();
      
    }
    
//unsorted
    
    for (int i = 0 ; i < howMany ; i++)
    {
      try        
      {
        a.drawString (data [i],x,200);
        Thread.sleep(100);//delay anamation in milliseconds
      } 
      catch(InterruptedException ex) 
      {
        Thread.currentThread().interrupt();
      }
      x+=50;
    }
    
    String temp;
    
    // Bubble sort
    
    for (int i = 1 ; i <= howMany ; i++)
    {
      for (int j = 0 ; j < howMany - 1 ; j++)
      {
        if ((data [j].compareTo(data [j + 1])) > 0)
        {
          temp = data [j];
          data [j] = data [j + 1];
          data [j + 1] = temp;
        }
      }
    }
    //sorted
    x=50;
    try        
    {
      a.clear();
      Thread.sleep(50);//delay anamation in milliseconds
    } 
    catch(InterruptedException ex) 
    {
      Thread.currentThread().interrupt();
    }
    
    for (int i = 0 ; i < howMany ; i++)
    {
      try        
      {
        a.drawString (data [i],x,200);
        Thread.sleep(100);//delay anamation in milliseconds
      } 
      catch(InterruptedException ex) 
      {
        Thread.currentThread().interrupt();
      }
      x+=50;
    }
    try        
    {
      a.clear();
      Thread.sleep(50);//delay anamation in milliseconds
    } 
    catch(InterruptedException ex) 
    {
      Thread.currentThread().interrupt();
    }
    
    try        
    {
      a.drawString ("G A M E O V E R",150,200);
      Thread.sleep(3);//delay anamation in milliseconds
    } 
    catch(InterruptedException ex) 
    {
      Thread.currentThread().interrupt();
    }
    a.setColor(Color.BLACK);
    a.setFont (new Font ("Sansserif",Font.PLAIN,15));
    a.drawString ("The game will pardon your mistake and revive you shortly.",150,230);
    a.drawString ("However, if you win the battle it will not be counted.",150,250);
    for (int i = 5; i > 0; i--)
    {
      try        
      {
        a.print (i+"....");
        Thread.sleep(1000);//delay anamation in milliseconds
      } 
      catch(InterruptedException ex) 
      {
        Thread.currentThread().interrupt();
      }
    }
    a.close ();
    if (Keyboardmove.bosscheck ==1)
    {
      Keyboardmove.bosscheck = 0;
      Keyboardmove.enemycount = 0;
    }

    Battle.battlethread = 1;
    Battle.CHARACTER [1] [1] = Battle.CHARACTER [1] [0];
    Keyboardmove ex = new Keyboardmove();
    new Thread(ex).start();
    //a.drawString ("Game Over",200,200);
  }
  public static void main (String[] args) throws IOException
  {
    endscreen();
  } // main method
} // BubbleSortStrings class