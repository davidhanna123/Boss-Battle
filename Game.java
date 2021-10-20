
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.*;  

public class Game
{
  
  
  final JFrame window;
  Container con;
  JPanel titleNamePanel, startButtonPanel;
  JLabel titleNameLabel;
  Font titleFont = new Font("Times New Roman",Font.PLAIN,90);
  Font normalFont = new Font("Times New Roman", Font.PLAIN,30);
  JButton startButton;
  
  public static void main(String[] args){
    new Game ();
  }
  public Game (){
    window = new JFrame();

    con = window.getContentPane();
    
    titleNamePanel = new JPanel();
    titleNamePanel.setBounds(100,100,600,150);
    titleNamePanel.setBackground(Color.blue);
    titleNameLabel =  new JLabel("RPGGAME");
    titleNameLabel.setForeground(Color.white);
    titleNameLabel.setFont(titleFont);
    
    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(300,400,200,100);
    startButtonPanel.setBackground(Color.blue);
    
    
    
    startButton = new JButton("START");
    startButton.setBackground(Color.black);
    startButton.setForeground(Color.white);
    startButton.setFont(normalFont);
    
    startButton.addActionListener(new ActionListener() //This executes when the user presses the button
                                    {
      public void actionPerformed(ActionEvent e) 
      {
        String args [] = {};
        window.dispose();
        Keyboardmove.main (args);
      }
    });
 
    titleNamePanel.add(titleNameLabel);
    startButtonPanel.add(startButton);
    con.add(titleNamePanel);
    con.add(startButtonPanel);
    
    window.setSize(800,600);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.black);
    window.setLayout(null);
    window.setVisible(true);
    
  }
}