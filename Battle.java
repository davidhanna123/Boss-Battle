import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;  


public class Battle {
  static String CHARACTER [][] = new String [6] [2];
  static int battlethread =0;
  static int win =0;
  static String charmove [] = new String [4];
  static String enemymoves [] = new String [12];
  
  public static void main(String[] args)  {
    //Create and set up the window.
    JFrame frame = new JFrame("Weenie");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    MapGUI (frame.getContentPane(), frame);
    
  }
  public static int getstatus ()
  {
    return battlethread;
  }
  public static void MapGUI (Container pane, final JFrame frame){
    //Remove the previous contents on the window
    pane.removeAll();
    pane.repaint();
    pane.setBackground(Color.WHITE); //Set background color
    try{
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Set GUI theme. | This sets the gui to look like the system.
    }
    catch (UnsupportedLookAndFeelException e) {
    }
    catch (ClassNotFoundException e) {
    }
    catch (InstantiationException e) {
    }
    catch (IllegalAccessException e) {
    }
    
    pane.setLayout(new FlowLayout());
    JButton trigger;
    
    //Update the window.
    frame.pack(); 
    frame.setSize(920,540); //Set size of window
    frame.setVisible(true);
    frame.setResizable(false);
    
    battlethread = 1;
    
    
    BattleMode (frame.getContentPane(), frame);
    
  }
  public static void BattleMode (Container pane, JFrame frame)
  {
    try{
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    }
    catch (UnsupportedLookAndFeelException e) {
      // handle exception
    }
    catch (ClassNotFoundException e) {
      // handle exception
    }
    catch (InstantiationException e) {
      // handle exception
    }
    catch (IllegalAccessException e) {
      // handle exception
    }
    String ENEMYget [] = new String [11];
    final String ENEMY [] = new String [11];
    int turn = 1;
    CHARACTER [1][1] = CHARACTER [1][0];
    int cooldownmove [][] = new int [5][2];
    JTextArea log = new JTextArea (12,25);//240,204 | 11,25
    BattleGUI (frame.getContentPane(), frame, turn,ENEMYget,ENEMY,log,cooldownmove); 
  }
  public static String getnewskill()
  {

    int count =0;
    String validchoices [] = new String [12];
    
    for (int j = 0; j < 12;j++)
    {
      if (enemymoves [j] != null)
      {
        if (!((enemymoves [j].equalsIgnoreCase (charmove[0])) | (enemymoves [j].equalsIgnoreCase (charmove[1]))|(enemymoves [j].equalsIgnoreCase (charmove[2]))|(enemymoves [j].equalsIgnoreCase (charmove[3])) | (enemymoves[j] == null) | enemymoves [j].equalsIgnoreCase("Ion Cannon but it missed!")))
        {
          //if it is valid
          validchoices [count] = enemymoves [j];
        }
        count++;
      }
    }
    int min = 0;
    int max = count;
    int sel;
    sel = (int) (Math.random() * count + 1);
    return validchoices [sel-1];
  }
   
  public static void winscreen (final JFrame frame, Container pane)
  {
    win =0;
    if (Keyboardmove.bosscheck == 1)
    {
      Keyboardmove.level++;
      Keyboardmove.bosscheck =0;
    }
    GridBagConstraints c = new GridBagConstraints();
    final String newskill = getnewskill();
    c.gridx = 0;
    c.gridy = 0;
    if (newskill == null)
    {
      //go back to map 
      //Call map again
      JButton button = new JButton ("Congratulations, return to map.");
      button.addActionListener(new ActionListener() //This executes when the user presses the button
                                 {
        public void actionPerformed(ActionEvent e) 
        {
          //Call map again
          frame.dispose ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      pane.add(button);
      frame.setVisible (true);
    }
    else
    {
      //pane.add (panel1,c);
      c.insets = new Insets(3,3,3,3);
      
      JTextArea newinfo = new JTextArea ();
      JLabel title = new JLabel ("You have learned a new skill from the enemy!");
      //title.setPreferredSize(new Dimension(240, 10));
      title.setFont (new Font("Monospaced", Font.BOLD, 20)); 
      pane.add (title,c);
      
      c.gridx = 0;
      c.gridy = 1;
      newinfo.setPreferredSize(new Dimension(300,240));
      newinfo.setLineWrap(true); //Automatic line breaks
      newinfo.setWrapStyleWord(true); //..That doesnt break words 
      newinfo.setFont (new Font("Monospaced", Font.PLAIN, 14));
      newinfo.setEditable(false); 
      pane.add (newinfo,c);
      displayappend (newskill, newinfo, frame, 1);
      
      c.gridx = 0;
      c.gridy = 2;
      JLabel question = new JLabel ("Select a skill you would like to replace");
      question.setFont (new Font("Monospaced", Font.BOLD, 15));
      pane.add (question,c);
      
      c.gridx = 0;
      c.gridy = 3;
      JPanel selectionpane = new JPanel (new GridLayout (2,2));
      pane.add (selectionpane,c);
      
      JButton move1, move2, move3,move4;
      move1 = new JButton (charmove [0]);
      move2 = new JButton (charmove [1]);
      move3 = new JButton (charmove [2]);
      move4 = new JButton (charmove [3]);

      move1.setFont (new Font ("monospaced", Font.PLAIN,15));
      move2.setFont (new Font ("monospaced", Font.PLAIN,15));
      move3.setFont (new Font ("monospaced", Font.PLAIN,15));
      move4.setFont (new Font ("monospaced", Font.PLAIN,15));
      //charmove determines move selection
      move1.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          charmove [0] = newskill; 
          //Call map again
          frame.dispose ();
          //frame = new JFrame ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          //clip.close();
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      move2.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          charmove [1] = newskill; 
          //Call map again
          frame.dispose ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      move3.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          charmove [2] = newskill; 
          //Call map again
          frame.dispose ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      move4.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          charmove [3] = newskill; 
          //Call map again
          frame.dispose ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      //add panels
      selectionpane.add(move1);
      selectionpane.add(move2);
      selectionpane.add(move3);
      selectionpane.add(move4);
      
      JButton nosel = new JButton ("No selection");
      nosel.setFont (new Font ("monospaced", Font.PLAIN,15));
      nosel.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          //Call map again
          frame.dispose ();
          Keyboardmove.enemycount++;
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      
      c.gridy = 4;
      pane.add(nosel,c);
      frame.setVisible(true);
    }
  }
  public static void BattleGUI (final Container pane, final JFrame frame,final int turn,String ENEMYget [], final String ENEMY [],final JTextArea log,final int cooldownmove [][]) 
  {
    //reset frame
    pane.removeAll();
    pane.revalidate();
    pane.repaint();
    
    if (win==1) //if they win
    {
      winscreen (frame,pane);
      frame.setVisible (true); 
    }    
    else //otherwise continue battle
    {
      if (turn == 1)
      {
        getenemyinfo (ENEMYget);
        for (int i = 0; i <11; i++) //transfer the enemy info to the final enemy variable 
        {
          ENEMY [i] = ENEMYget [i];  
        }
        ENEMY [8] = ENEMY [4];
        ENEMY [9] = ENEMY [5];
        ENEMY [10] = ENEMY [6];
      }
      
      pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      pane.setBackground( new Color(147, 151, 165));
      //Font font13 = new Font("Monospaced", Font.PLAIN, 13);
      
      Border blackline;
      blackline = BorderFactory.createLineBorder(Color.black);//Border color
      
      final JPanel panel1, panel2, panel3, panel4, panel5, panel6;
      
      pane.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      //natural height, maximum width
      c.fill = GridBagConstraints.HORIZONTAL;
      
      //Set up panels
      panel1 = new JPanel ();
      panel2 = new JPanel ();
      panel3 = new JPanel ();
      panel4 = new JPanel ();
      panel5 = new JPanel ();
      panel6 = new JPanel ();
      
      // c.weightx = #; //requests for space if it is stretched
      
      panel1.setBorder(blackline);
      c.fill = GridBagConstraints.HORIZONTAL;
      panel1.setLayout(new GridLayout(7,0));
      //c.ipadx = 240;
      c.ipady = 67; //total length must be 240 
      c.gridx = 0;
      c.gridy = 0;
      pane.add(panel1, c);
      //panel1.setLayout(new GridBagLayout());
      
      //Components in panel1------
      
      //display title
      JLabel displaytitle,hpstat,attackstat, defstat, speedstat,exp;
      displaytitle = new JLabel (CHARACTER [0] [0] + "|LV." + CHARACTER[0][1]);
      displaytitle.setFont (new Font("Monospaced", Font.BOLD, 14));
      displaytitle.setPreferredSize(new Dimension(240, 10));
      displaytitle.setHorizontalAlignment(JLabel.CENTER);
      panel1.add(displaytitle);
      //declare characters hp bar
      JProgressBar yourhpbar = new JProgressBar (); 
      double hpdisplay;
      JPanel hpbarpanel = new JPanel (new FlowLayout());
      //set hp bar
      yourhpbar.setStringPainted(true);
      yourhpbar.setPreferredSize(new Dimension(240,13));
      yourhpbar.setForeground(Color.green);
      yourhpbar.setStringPainted(true);
      hpdisplay = (Double.parseDouble (CHARACTER[1][1]) / Double.parseDouble (CHARACTER[1][0])) *100;
      yourhpbar.setValue ((int) Math.round (hpdisplay));
      panel1.add (hpbarpanel);
      hpbarpanel.add (yourhpbar);
      //set hp stat
      hpstat = new JLabel ("HP: " + CHARACTER [1] [1] + "/" + CHARACTER [1] [0]);
      hpstat.setHorizontalAlignment(JLabel.CENTER);
      hpstat.setPreferredSize(new Dimension(240, 10));
      hpstat.setFont (new Font("Monospaced", Font.BOLD, 14));
      panel1.add(hpstat);
      
      //The character stats have HTML code for colors.
      attackstat = new JLabel ("  ATK: " + CHARACTER [2] [1]);
      attackstat.setPreferredSize(new Dimension(240, 10));
      attackstat.setFont (new Font("Monospaced", Font.BOLD, 14)); 
      if (Integer.parseInt(CHARACTER [2][0]) < Integer.parseInt(CHARACTER [2][1]))
      {
        attackstat.setText ("<html>&nbsp;" + attackstat.getText() + "<font color='green'> (+" + (Integer.parseInt (CHARACTER[2][1]) - Integer.parseInt(CHARACTER[2][0])) +")</font></html>");
      }
      else if (Integer.parseInt(CHARACTER [2][0]) > Integer.parseInt(CHARACTER [2][1]))
      {
        attackstat.setText ("<html>&nbsp;" + attackstat.getText() + "<font color='red'> (-" + (Integer.parseInt (CHARACTER[2][0]) - Integer.parseInt(CHARACTER[2][1])) +")</font></html>");
      }
      panel1.add(attackstat);
      //set def stat
      defstat = new JLabel ("  DEF: " + CHARACTER [3] [1]);
      defstat.setPreferredSize(new Dimension(240, 10));
      defstat.setFont (new Font("Monospaced", Font.BOLD, 14));
      if (Integer.parseInt(CHARACTER [3][0]) < Integer.parseInt(CHARACTER [3][1]))
      {
        defstat.setText ("<html>&nbsp;" + defstat.getText() + "<font color='green'> (+" + (Integer.parseInt (CHARACTER[3][1]) - Integer.parseInt(CHARACTER[2][0])) +")</font></html>");
      }
      else if (Integer.parseInt(CHARACTER [3][0]) > Integer.parseInt(CHARACTER [3][1]))
      {
        defstat.setText ("<html>&nbsp;" + defstat.getText() + "<font color='red'> (-" + (Integer.parseInt (CHARACTER[3][0]) - Integer.parseInt(CHARACTER[2][1])) +")</font></html>");
      }
      panel1.add(defstat);
      
      speedstat = new JLabel ("  DEX: " + CHARACTER [4] [1]);
      speedstat.setPreferredSize(new Dimension(240, 10));
      speedstat.setFont (new Font("Monospaced", Font.BOLD, 14));
      if (Integer.parseInt(CHARACTER [4][0]) < Integer.parseInt(CHARACTER [4][1]))
      {
        speedstat.setText ("<html>&nbsp;" + speedstat.getText() + "<font color='green'> (+" + (Integer.parseInt (CHARACTER[4][1]) - Integer.parseInt(CHARACTER[4][0])) +")</font></html>");
      }
      else if (Integer.parseInt(CHARACTER [4][0]) > Integer.parseInt(CHARACTER [4][1]))
      {
        speedstat.setText ("<html>&nbsp;" + speedstat.getText() + "<font color='red'> (-" + (Integer.parseInt (CHARACTER[4][0]) - Integer.parseInt(CHARACTER[4][1])) +")</font></html>");
      }
      panel1.add(speedstat);
      
      exp = new JLabel ("  TOTAL EXP: " + CHARACTER [5] [1] + "/" + CHARACTER [5] [0]);
      exp.setPreferredSize(new Dimension(240, 10));
      exp.setFont (new Font("Monospaced", Font.PLAIN, 14));
      panel1.add(exp);
      
      //set constraints for panel 2
      panel2.setBorder(blackline);
      c.fill = GridBagConstraints.HORIZONTAL;
      panel2.setLayout(new GridBagLayout()); //**Must set proper layout. 
      //c.weightx = 0.5;
      c.ipadx = 0; //total length must be 440
      c.ipady = 0; //total width must be 300
      c.gridx = 1;
      c.gridy = 0;
      
      pane.add(panel2, c);
      
      //SubPanels inside panel 2-----------------------
      GridBagConstraints subC = new GridBagConstraints();
      JPanel subpanel2_1, subpanel2_2,subpanel2_3,subpanel2_4;
      subpanel2_1 = new JPanel (new FlowLayout());
      subpanel2_2 = new JPanel (new GridBagLayout());
      subpanel2_3 = new JPanel (new GridBagLayout());
      subpanel2_4 = new JPanel (new GridBagLayout());
      subpanel2_1.setLayout(new GridBagLayout());
      
      //Subpanel 1--Enemy info
      subC.ipadx = 0;
      subC.ipady = 0;
      subC.gridx = 0;
      subC.gridy = 0;
      //Panel for  enemy info
      JLabel enemyinfo;
      enemyinfo = new JLabel ("ENEMY: " + ENEMY[0] + " | LV."+ENEMY[1]+" | ELEMENT: "+ ENEMY [7]);
      enemyinfo.setFont (new Font("Monospaced", Font.BOLD, 13));
      subpanel2_1.add (enemyinfo);
      subC.ipadx = (int)(Math.round (440 - (enemyinfo.getPreferredSize().getWidth()))); //Scale the panel to always make it 440
      panel2.add(subpanel2_1,subC);
      
      //Subpanel 2--HP bar
      //set and calculate hp bar for enemy
      subC.ipadx = 0;
      subC.ipady = 0;
      subC.gridx = 0;
      subC.gridy = 1;
      panel2.add (subpanel2_2,subC); 
      final JProgressBar hpbar; 
      JLabel hplabel = new JLabel ("HP:");
      hplabel.setFont (new Font("Monospaced", Font.BOLD, 13));
      subpanel2_2.add (hplabel);
      hpbar = new JProgressBar ();
      hpbar.setStringPainted(true);
      hpbar.setPreferredSize(new Dimension(399,13));
      hpbar.setForeground(Color.red);
      hpbar.setStringPainted(true);
      hpdisplay = (Double.parseDouble (ENEMY [3]) / Double.parseDouble (ENEMY [2])) *100;
      hpbar.setValue ((int) Math.round (hpdisplay));
      subpanel2_2.setLayout (new FlowLayout ());
      subpanel2_2.add (hpbar);
      
      //Subpanel 3--Image
      //subC.ipadx = 440; //total image legth
      //subC.ipady = 287; //total image height
      subC.gridx = 0;
      subC.gridy = 2;
      subpanel2_3.setBackground(Color.white);
      subpanel2_3.setBorder(blackline);
      panel2.add(subpanel2_3, subC); //Panel that displays the image
      
      //Components inside subpanel2_2----------------------- | ENEMY IMAGE
      String picname = "assets/" +ENEMY[0].toLowerCase()+".jpg";
      try{
        
        BufferedImage enemypic = ImageIO.read(new File(picname)); //add enemy image
      }
      catch (IOException e)
      {
      }
      JLabel picLabel = new JLabel(new ImageIcon(picname));
      picLabel.setPreferredSize(new Dimension(440,287)); //length x width
      if ((ENEMY [0].equalsIgnoreCase ("Dragon knight")) | (ENEMY [0].equalsIgnoreCase ("mystic elk")) |(ENEMY [0].equalsIgnoreCase ("lord of the flies")) | (ENEMY [0].equalsIgnoreCase ("the demiurge")))
      {
        subpanel2_3.setBackground(Color.black);
      }
      subpanel2_3.add(picLabel); 
      
      
      //Panel 3-------------------------------------------
      panel3.setBorder(blackline);
      c.fill = GridBagConstraints.HORIZONTAL;
      panel3.setLayout(new GridBagLayout());
      c.ipadx = 0; //total length must be 240
      c.ipady = 0; //total length must eb 220
      c.gridx = 2;
      c.gridy = 0;
      pane.add(panel3, c);
      
      //Subcomponents in panel3--------------
      JPanel subpanel3_1, subpanel3_2;
      subpanel3_1 = new JPanel ();
      subpanel3_2 = new JPanel ();
      
      subC.ipadx = 0; 
      subC.ipady = 0; 
      subC.gridx = 0;
      subC.gridy = 0;
      panel3.add (subpanel3_1,subC);
      
      subC.ipadx = 0; 
      subC.ipady = 0; 
      subC.gridx = 0;
      subC.gridy = 1;
      panel3.add (subpanel3_2,subC);
      
      JLabel logtitle;
      logtitle = new JLabel ();
      logtitle.setText ("Log  |" + "  Current Turn: " + turn);
      logtitle.setFont (new Font("Monospaced", Font.PLAIN, 15));
      logtitle.setPreferredSize(new Dimension(240,17));
      subpanel3_1.add (logtitle);
      
      log.setFont (new Font("Monospaced", Font.PLAIN, 13));
      JScrollPane logdisplay = new JScrollPane (log);
      log.setEditable(false); 
      log.setLineWrap(true); //Automatic line breaks
      log.setWrapStyleWord(true); //No breaking words
      logdisplay.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
      subpanel3_2.add (logdisplay);
      
      panel4.setBorder(blackline);
      c.fill = GridBagConstraints.HORIZONTAL;
      panel4.setLayout(new GridBagLayout());
      c.ipadx = 0; //total length must be 240
      c.ipady = 0; //total length must eb 240
      c.gridx = 0;
      c.gridy = 1;
      pane.add(panel4, c);
      //Compenonents in Panel 4--------------------------------
      final JTextArea desc;
      desc = new JTextArea ();
      //desc.setFont(new Font("Serif", Font.PLAIN, 15));
      desc.setLineWrap(true); //Automatic line breaks
      desc.setWrapStyleWord(true); //..That doesnt break words 
      desc.setPreferredSize(new Dimension(240,240));
      desc.setFont (new Font("Monospaced", Font.PLAIN, 14));
      desc.setEditable(false); 
      panel4.add (desc);
      
      panel5.setBorder(blackline);
      c.fill = GridBagConstraints.HORIZONTAL;
      panel5.setLayout(new GridLayout(2,2));
      c.ipadx = 0; //total length must be 440
      c.ipady = 0; //total width must be 200
      c.gridx = 1;
      c.gridy = 1;
      pane.add(panel5, c);
      
      //Components in panel 5-----------------
      final JButton move1, move2, move3, move4;
      move1 = new JButton (charmove [0]);
      move2 = new JButton (charmove [1]);
      move3 = new JButton (charmove [2]);
      move4 = new JButton (charmove [3]);
      //Setting the cooldown if a move is supposed to be inactive
      if ((move1.getText().equals ("")) || (cooldownmove [0] [1] != 0))
      {
        move1.setText ("<html>" + move1.getText() + "<br>" + "Cooldown remaining: "+ cooldownmove[0][1] +"</html>");
        move1.setEnabled(false);
      }
      if ((move2.getText().equals ("")) || (cooldownmove [1] [1] != 0))
      {
        move2.setText ("<html>" + move2.getText() + "<br>" + "Cooldown remaining: "+ cooldownmove[1][1] +"</html>");
        move2.setEnabled(false);
      }
      if ((move3.getText().equals ("")) || (cooldownmove [2] [1] != 0))
      {
        move3.setText ("<html>" + move3.getText() + "<br>" + "Cooldown remaining: "+ cooldownmove[2][1] +"</html>");
        move3.setEnabled(false);
      }
      if ((move4.getText().equals ("")) || (cooldownmove [3] [1] != 0))
      {
        move4.setText ("<html>" + move4.getText() + "<br>" + "Cooldown remaining: "+ cooldownmove[3][1] +"</html>");
        move4.setEnabled(false);
      }
      
      move1.setPreferredSize(new Dimension(220, 100));
      move2.setPreferredSize(new Dimension(220, 100));
      move3.setPreferredSize(new Dimension(220, 100));
      move4.setPreferredSize(new Dimension(220, 100));
      move1.setFont (new Font ("monospaced", Font.PLAIN,14));
      move2.setFont (new Font ("monospaced", Font.PLAIN,14));
      move3.setFont (new Font ("monospaced", Font.PLAIN,14));
      move4.setFont (new Font ("monospaced", Font.PLAIN,14));
      
      panel5.add(move1);
      panel5.add(move2);
      panel5.add(move3);
      panel5.add(move4);
      
      panel6.setBorder(blackline);
      c.ipadx = 100; //total length must be 240
      c.ipady = 70; //total length must eb 240
      c.gridx = 2;
      c.gridy = 1;
      panel6.setLayout(new GridLayout(2,2));
      //panel6.setLayout(new GridLayout(0,1));
      c.gridwidth = 0;
      c.gridheight = 0;
      pane.add(panel6, c);
      
      final JButton attack,flee;
      attack = new JButton ();
      
      if ((move1.isEnabled()) || (move2.isEnabled()) || (move3.isEnabled()) || (move4.isEnabled()))
      {
        attack.setText ("Select a move");
        attack.setEnabled(false);
      }
      else //advanced error trap. if you locked up all your moves endevour will be an option.
      {
        moveinfo [4][0] = "Endeavor";
        cooldownmove [4][0] = 5; //What # move this is
        
        displayappend (moveinfo[4][0], desc,frame,cooldownmove [4][0]);
        
        attack.setText ("Endeavor!");  
        attacklistener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            nextturn (moveinfo[4][1], moveinfo[4][2], moveinfo[4][3], ENEMY,log,turn,frame,moveinfo[4][0],cooldownmove,cooldownmove[4][0],pane);
          }
        };
        
        attack.addActionListener (attacklistener);
        attack.setEnabled(true);
      }
      
      panel6.add (attack);
      
      flee = new JButton ("Flee");
      panel6.add (flee);
      flee.addActionListener(new ActionListener() //This executes when the user presses the button
                               {
        public void actionPerformed(ActionEvent e) 
        {
          //Call map again
          if (Keyboardmove.bosscheck ==1)
          {
            Keyboardmove.bosscheck = 0;
            Keyboardmove.enemycount = 0;
          }
          frame.dispose ();
          battlethread = 0;
          Keyboardmove ex = new Keyboardmove();
          new Thread(ex).start();
        }
      });
      
      
      move1.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {  
          
          attack.setText ("Attack!");
          //get the move information
          moveinfo [0][0] = move1.getText ();
          cooldownmove [0][0] = 1;
          //change the info panel
          displayappend (moveinfo [0][0], desc,frame,cooldownmove [0][0]);
          attack.setEnabled (true);
          
          attack.removeActionListener (attacklistener);
          //add the action listener
          attacklistener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              nextturn (moveinfo[0][1], moveinfo[0][2], moveinfo[0][3], ENEMY,log,turn,frame,moveinfo[0][0],cooldownmove,cooldownmove[0][0],pane);
            }
          };
          
          attack.addActionListener (attacklistener);
        }
      });
      move2.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {    
          attack.setText ("Attack!");
          
          moveinfo [1][0] = move2.getText ();
          cooldownmove [1][0] = 2; //MOVE # (1,2,3,4)
          
          displayappend (moveinfo [1][0], desc,frame,cooldownmove [1][0]);
          attack.setEnabled (true);
          
          attack.removeActionListener (attacklistener);
          
          attacklistener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              nextturn (moveinfo[1][1], moveinfo[1][2], moveinfo[1][3], ENEMY,log,turn,frame,moveinfo[1][0],cooldownmove,cooldownmove[1][0],pane);
            }
          };
          
          attack.addActionListener (attacklistener);
        }
        
        
      });
      move3.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        {
          attack.setText ("Attack!");
          
          moveinfo [2][0] = move3.getText ();
          cooldownmove [2][0] = 3; //MOVE # (1,2,3,4)
          
          displayappend (moveinfo [2][0], desc,frame,cooldownmove [2][0]);
          attack.setEnabled (true);
          
          attack.removeActionListener (attacklistener);
          
          attacklistener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              nextturn (moveinfo[2][1], moveinfo[2][2], moveinfo[2][3], ENEMY,log,turn,frame,moveinfo[2][0],cooldownmove,cooldownmove[2][0],pane);
            }
          };
          
          attack.addActionListener (attacklistener);
        }
      });
      move4.addActionListener(new ActionListener() //This executes when the user presses the button
                                {
        public void actionPerformed(ActionEvent e) 
        { 
          attack.setText ("Attack!");
          
          moveinfo [3][0] = move4.getText ();
          cooldownmove [3][0] = 4; //MOVE # (1,2,3,4)
          
          displayappend (moveinfo [3][0], desc,frame,cooldownmove [3][0]);
          attack.setEnabled (true);
          
          attack.removeActionListener (attacklistener);
          
          attacklistener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              nextturn (moveinfo[3][1], moveinfo[3][2], moveinfo[3][3], ENEMY,log,turn,frame,moveinfo[3][0],cooldownmove,cooldownmove[3][0],pane);
            }
          };
          
          attack.addActionListener (attacklistener);
        }
      });
      
      frame.pack(); //window must have a length of 920 and a hieght of 780
      frame.setVisible(true);
    }
  }
  static String moveinfo [] []= new String [5] [5];
  static ActionListener attacklistener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      nextturn (moveinfo[0][1], moveinfo[0][2], moveinfo[0][3], (new String [0]),(new JTextArea ()),(0),new JFrame (),moveinfo[0][0],new int [0][0],0,new JFrame ().getContentPane ()); //Place holder values to initalize the action listener.
    }
  };
  public static void getcharacterinfo () 
  {
    BufferedReader input; //Get the caharcter information from a data file and store it into a 2d array.
    try{
      input = new BufferedReader (new FileReader ("character.txt")); 
      
      for (int i = 0; i< 6; i++)
      {
        for (int j = 0; j < 2; j++)
        {
          if ((i == 0) && (j == 0))
          {
             CHARACTER [i] [j] = input.readLine ();
          }
          else
          CHARACTER [i] [j] = String.valueOf(Integer.parseInt (input.readLine ()) * 25);
        }
      }
      input.close();
    }
    catch (IOException e)
    {
    } 
  }   
  private static String allenemychance () //chances of enemy encounter
  {
    String enemylist [] = new String [20];
    String enemyname;
    
    enemylist [1] = "wizard"; 
    enemylist [2] = "soldier";
    enemylist [3] = "mercenary";
    enemylist [4] = "undead space marine"; 
    enemylist [5] = "water oracle";
    enemylist [6] = "imperator";
    enemylist [7] = "tank"; 
    enemylist [8] = "panthera soldier";
    enemylist [9] = "reaper";
    enemylist [10] = "ancient warrior";
    enemylist [11] = "lower demon";
    enemylist [12] = "cult leader";
    enemylist [13] = "dragon knight";
    enemylist [14] = "mystic elk";
    enemylist [15] = "lord of the flies";
    enemylist [16] = "the demiurge";
    
    enemyname = enemylist [(int) (Math.random() * 16 + 1)];
    return enemyname;
  }
  private static void getenemyinfo (String ENEMYget [])
  {
    String enemylist [] = new String [20];
    int levelrange = 1;
    String enemyname = "";
    if (Keyboardmove.bosscheck  == 1) //if it is a boss
    {
      System.out.println ("Boss battle");
      if (Keyboardmove.level == 1) //check which area
      {
        enemyname = "dragon knight";
        if (Integer.parseInt (CHARACTER [0][1]) > 42)
          levelrange = (Integer.parseInt ( CHARACTER [0][1]) -6);
        else
          levelrange = 42;
      }
      else if (Keyboardmove.level == 2) //check which area
      {
        enemyname = "mystic elk";
        if (Integer.parseInt (CHARACTER [0][1]) > 52)
          levelrange = (Integer.parseInt ( CHARACTER [0][1]) -6);
        else
          levelrange = 52;
      }
      else if (Keyboardmove.level == 3) //check which area
      {
        enemyname = "lord of the flies";
        if (Integer.parseInt (CHARACTER [0][1]) > 75)
          levelrange = (Integer.parseInt ( CHARACTER [0][1]) -6);
        else
          levelrange = 75;
      }
      else if (Keyboardmove.level == 4) //check which area
      {
        enemyname = "The Demiurge";
        if (Integer.parseInt (CHARACTER [0][1]) > 100)
          levelrange = (Integer.parseInt ( CHARACTER [0][1]) -6);
        else
          levelrange = 100;
      }
      else
      {
        enemyname = allenemychance ();
      }
    }
    else
    {
      if (Keyboardmove.level == 1) //check which area
      {
        enemylist [1] = "wizard"; 
        enemylist [2] = "soldier";
        enemylist [3] = "mercenary";
        
        enemyname = enemylist [(int) (Math.random() * 3 + 1)];
      }
      else if (Keyboardmove.level == 2) //check which area
      {
        enemylist [1] = "wizard"; 
        enemylist [2] = "ancient warrior";
        enemylist [3] = "lower demon";
        enemylist [4] = "cult leader";
        
        enemyname = enemylist [(int) (Math.random() * 3 + 1)];
      }
      else if (Keyboardmove.level == 3) //check which area
      {
        enemylist [1] = "tank"; 
        enemylist [2] = "panthera soldier";
        enemylist [3] = "reaper";
        
        enemyname = enemylist [(int) (Math.random() * 3 + 1)];
      }
      else if (Keyboardmove.level == 4) //check which area
      {
        enemylist [1] = "undead space marine"; 
        enemylist [2] = "water oracle";
        enemylist [3] = "imperator";
        
        enemyname = enemylist [(int) (Math.random() * 3 + 1)];
      }
      else
      {
        enemyname = allenemychance ();
        
      }
      int min,max;
      min = Integer.parseInt (CHARACTER [0][1]) -5;   
      max = Integer.parseInt (CHARACTER [0][1]) +0;
      levelrange = (min + (int)(Math.random() * (max - min + 1))) + 1;
    }
    BufferedReader input;
    try
    {
      input = new BufferedReader (new FileReader ("enemyinfo.txt")); 
      do
      {
        for (int i = 0; i <=10;i++) //begin linear search
        {
          if ((i == 0) || (i == 7))
          {
            
            ENEMYget [i] = input.readLine (); 
          }
          else
          {
            
            ENEMYget [i] = String.valueOf (Integer.parseInt (input.readLine ()) * levelrange);
          } 
        }
        
      }
      while (!(ENEMYget[0].equalsIgnoreCase(enemyname))); //linear search
    }
    catch (IOException e)
    {
    }   
    
  }
  private static void displayappend (String move, JTextArea display,JFrame frame, int moveselected) //throws IOException
  {
    BufferedReader input;
    String name, power, element, cooldown, desc;
    name = " ";
    power = " ";
    element = " ";
    cooldown = " ";
    desc = " ";
    try{
      input = new BufferedReader (new FileReader ("movelist.txt")); 
      
      name = input.readLine ();
      power = input.readLine ();
      element = input.readLine ();
      cooldown = input.readLine ();
      desc = input.readLine ();
      while (!(name.equals (move)))
      {
        name = input.readLine ();
        power = input.readLine ();
        element = input.readLine ();
        cooldown = input.readLine ();
        desc = input.readLine ();
        if (name == null)
        {   
          break;
        }
      }
      input.close();
    }
    catch (IOException e)
    {
    }
    display.setText ("\n");
    display.append ("MOVE SELECTED: " +name + "\n\n");
    display.append ("  POWER: " + power + "\n\n");
    display.append ("  ELEMENT: " + element + "\n\n");
    display.append ("  COOLDOWN: " + cooldown + " turns." + "\n\n");
    display.append (desc); 
    
    //set the move info at the same time since the program already knows the values
    moveinfo [moveselected-1] [1] = power;
    moveinfo [moveselected-1] [2] = element;
    moveinfo [moveselected-1] [3] = cooldown;
    
    frame.setVisible(true);
    return;
  }
  
  private static void nextturn (String power, String element, String cooldown,String ENEMY [], JTextArea log, int turn,JFrame frame,String movename,int cooldownmove [][],int moveselected,Container pane)
  {

    cooldownmove [moveselected -1] [1] = Integer.parseInt (cooldown);
    for (int i = 0; i <4;i++) //update cooldown
    {
      if (i != moveselected -1)
      {
        if (cooldownmove [i] [1] == 0)
        {
          //dont do anything
        }
        else 
        {
          cooldownmove [i] [1] = cooldownmove [i] [1] -1;
        }
      }
    }
    
    if (Integer.parseInt (CHARACTER [4] [1]) < Integer.parseInt (ENEMY [6])) //if enemy is faster than character
    {  
      
      enemyai (turn,ENEMY,log);
      //if character dies execute
      if (Integer.parseInt(CHARACTER [1][1]) <= 0)
      {
        loss (frame); 
      }
      log.append ("\n\n");
      charactermoves(power,element,ENEMY,log,movename);
      
      //if enemy dies execute
      if (Integer.parseInt(ENEMY [3]) <= 0)
      {
        win (ENEMY,frame,pane); 
      }
      
    }
    else
    {
      charactermoves (power,element,ENEMY,log,movename);
      //if enemy dies execute
      if (Integer.parseInt(ENEMY [3]) <= 0)
      {
        win (ENEMY,frame,pane); 
      }
      log.append ("\n\n");
      enemyai(turn,ENEMY,log);
      //if character dies execute
      
      if (Integer.parseInt(CHARACTER [1][1]) <= 0)
      {
        loss (frame); 
      }
    }
    
    //end of turn
    log.append ("\n");
    turn = turn + 1;
    String ENEMYget [] = new String [1];
    log.append ("\n    ----Turn: " + turn+"----\n");
    BattleGUI (frame.getContentPane(), frame, turn,ENEMYget,ENEMY,log,cooldownmove);
  }
  
  private static void loss (JFrame frame) 
  {
    resetcharstats ();
    frame.dispose ();
    

    try{
          //Display death screen
    //Leave battle mode
    BubbleSortGameover.endscreen();
    }
    catch (IOException e)
    {
      
    }
  }
  private static void win (String ENEMY [],JFrame frame,Container pane)
  {
  
    //Display win screen
    int level  = Integer.parseInt(CHARACTER [0][1]);
    int exp;
    
    exp = Integer.parseInt (ENEMY[8]) + Integer.parseInt (ENEMY[9]) + Integer.parseInt (ENEMY[10]);
    CHARACTER [5][1] = String.valueOf ((Integer.parseInt (CHARACTER [5][1]) + exp) * 10);  
    
    if (Integer.parseInt (CHARACTER[5][1]) > Integer.parseInt (CHARACTER [5][0]))
    {
      do 
      {
        CHARACTER [0][1] = String.valueOf (Integer.parseInt (CHARACTER [0][1]) + 1); //increase level
        
        CHARACTER [1][0] = String.valueOf (14 * Integer.parseInt(CHARACTER [0][1])); //Increase your stats
        CHARACTER [2][0] = String.valueOf (5 * Integer.parseInt(CHARACTER [0][1]));
        CHARACTER [3][0] = String.valueOf (5 * Integer.parseInt(CHARACTER [0][1]));
        CHARACTER [4][0] = String.valueOf (5 * Integer.parseInt(CHARACTER [0][1]));
        
        CHARACTER [5][0] = String.valueOf (Integer.parseInt (CHARACTER [0][1]) * 78); //update total exp     
        CHARACTER [5][1] = String.valueOf (Integer.parseInt (CHARACTER [5][1]) - Integer.parseInt (CHARACTER [5][0]));  //leftover exp      
        
      }
      while (Integer.parseInt (CHARACTER[5][1]) > Integer.parseInt (CHARACTER [5][0]));
      
    }
      resetcharstats ();
    //exp is calculated by enemy's: atk + def + dex + hp 
    pane.removeAll();
    pane.revalidate();
    pane.repaint();
    
    win = 1;

    //Leave battle mode. 
  }
  private static void resetcharstats () //resetting stats
  {
    CHARACTER [2][1] = CHARACTER [2][0]; 
    CHARACTER [3][1] = CHARACTER [3][0]; 
    CHARACTER [4][1] = CHARACTER [4][0]; 
  }
  private static void charactermoves (String power,String element,String ENEMY [], JTextArea log, String movename)
  {
    int damage;
    int statuscheck = 0;
    log.append (CHARACTER [0][0] + " used " + movename);
    try {
      Integer.parseInt(power);
    }
    catch (NumberFormatException e)
    {
      statuscheck = 1;
    }
    
    if (statuscheck == 0) //If the move does damage
    {
      //String power, String element,String enemydefense,int yourlevel,int youratk
      damage = (int) (Math.round (damagecalculator (power,element, Integer.parseInt(ENEMY [5]),Integer.parseInt(CHARACTER[0][1]),Integer.parseInt(CHARACTER[2][1])))); //Execute damage calculator  
      log.append ("\n" + ENEMY [0] + " recieved " + damage +" damage.");
      ENEMY [3] = Integer.toString (Integer.parseInt (ENEMY [3]) - damage); 
      
    }
    else //If the move buffs your character
    {
      System.out.println ("Status move activated");
      //Activate status method.
      int check = 1;
      statusmove (ENEMY,power,check,log);
      //log.append ("\n" + CHARACTER [0][0] + " gained " + power);
      damage = 0;
      statuscheck = 1;
      //buff the character
    }
    
  }
  private static void statusmove (String ENEMY [],String move,int check,JTextArea log)
  {
    
    if (check == 1) //check==1 is character doing it
    {
      //your stuff
      
      double yourhp      = Double.parseDouble (CHARACTER [1][0]);
      double yourcurhp   = Double.parseDouble (CHARACTER [1][1]);
      double youratk     = Double.parseDouble (CHARACTER [2][0]);
      double yourcuratk  = Double.parseDouble (CHARACTER [2][1]);
      double yourdef     = Double.parseDouble (CHARACTER [3][0]);
      double yourcurdef  = Double.parseDouble (CHARACTER [3][1]);
      double yourdex     = Double.parseDouble (CHARACTER [4][0]);
      double yourcurdex  = Double.parseDouble (CHARACTER [4][1]);
      double enemyhp     = Double.parseDouble (ENEMY [2]);
      double enemycurhp  = Double.parseDouble (ENEMY [3]);
      double enemyatk    = Double.parseDouble (ENEMY [8]);
      double enemycuratk = Double.parseDouble (ENEMY [4]);
      double enemydef    = Double.parseDouble (ENEMY [9]);
      double enemycurdef = Double.parseDouble (ENEMY [5]);
      double enemydex    = Double.parseDouble (ENEMY [10]);
      double enemycurdex = Double.parseDouble (ENEMY [6]);
      
      executestatchange (log,ENEMY,move,check,yourhp,yourcurhp,youratk,yourcuratk,yourdef,yourcurdef,yourdex,yourcurdex,enemyhp,enemycurhp,enemyatk,enemycuratk,enemydef,enemycurdef,enemydex,enemycurdex);
      
    }
    else
    {
      //enemy stuff 
      double yourhp      = Double.parseDouble (ENEMY [2]);
      double yourcurhp   = Double.parseDouble (ENEMY [3]);
      double youratk     = Double.parseDouble (ENEMY [8]);
      double yourcuratk  = Double.parseDouble (ENEMY [4]);
      double yourdef     = Double.parseDouble (ENEMY [9]);
      double yourcurdef  = Double.parseDouble (ENEMY [5]);
      double yourdex     = Double.parseDouble (ENEMY [10]);
      double yourcurdex  = Double.parseDouble (ENEMY [6]);
      double enemyhp     = Double.parseDouble (CHARACTER [1][0]);
      double enemycurhp  = Double.parseDouble (CHARACTER [1][1]);
      double enemyatk    = Double.parseDouble (CHARACTER [2][0]);
      double enemycuratk = Double.parseDouble (CHARACTER [2][1]);
      double enemydef    = Double.parseDouble (CHARACTER [3][0]);
      double enemycurdef = Double.parseDouble (CHARACTER [3][1]);
      double enemydex    = Double.parseDouble (CHARACTER [4][0]);
      double enemycurdex = Double.parseDouble (CHARACTER [4][1]);
      
      executestatchange (log,ENEMY,move,check,yourhp,yourcurhp,youratk,yourcuratk,yourdef,yourcurdef,yourdex,yourcurdex,enemyhp,enemycurhp,enemyatk,enemycuratk,enemydef,enemycurdef,enemydex,enemycurdex);
      
    }
  }
  private static void executestatchange (JTextArea log,String ENEMY [], String move,int check,Double yourhp,Double yourcurhp,Double youratk,Double yourcuratk,Double yourdef,Double yourcurdef,Double yourdex,Double yourcurdex,Double enemyhp,Double enemycurhp,Double enemyatk,Double enemycuratk,Double enemydef,Double enemycurdef,Double enemydex,Double enemycurdex)
  {
    int index,length,powernum;
    String stataffected;
    String power="";
    index = 0;
    
    //Character moves
    System.out.println (move + " move");
    if ((String.valueOf (move.charAt (0)).equalsIgnoreCase ("+")) ||(String.valueOf (move.charAt (0)).equalsIgnoreCase ("-")))  //BOOSTS CHARACTERS STATS
    {
      
      index = 1; //set position to beginning
      while (true)
      {
        if (Character.isLetter (move.charAt (index))) //jonah was here this is a nice keyboard if it encounters a letter - Stop
        {
          power = power.substring(0,index-2);
          
          break; 
        }
        power = power + move.charAt (index);
        index++;
      }
      //get the stat being affected
      length = move.length ();
      
      if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+")) 
        stataffected = move.substring(index, length);
      else
        stataffected = move.substring(index, index+3);
      
      if (stataffected.equalsIgnoreCase ("ATK"))
      {
        //Power: 1 point is 10% of the stat. 
        
        powernum = Integer.parseInt (power);
        
        for (int i = 0; i < powernum; i++)
        {
          if ((String.valueOf (move.charAt (0))).equalsIgnoreCase ("+"))
            yourcuratk = yourcuratk + (youratk/10.0);
          else
            enemycuratk = enemycuratk - (enemyatk/10.0);
        }
        if (check == 1)
        {
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            CHARACTER [2][1] = String.valueOf(Math.round(yourcuratk));
            log.append ("\n" + CHARACTER [0][0] + " gained " + move);
          }
          else
          {
            ENEMY [4] = String.valueOf(Math.round(enemycuratk));
            log.append ("\n" + CHARACTER [0][0] + " reduced " + ENEMY[0] + "'s attack by " + powernum + " stage."); //pass it to the log
          }
        }
        else
        {
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            ENEMY [4]  = String.valueOf(Math.round(yourcuratk));
            log.append ("\n" + ENEMY [0] + " gained " + move);
          }
          else
          {
            CHARACTER [2][1]= String.valueOf(Math.round(enemycuratk));
            log.append ("\n" + ENEMY[0] + " reduced " + CHARACTER[0][0] + "'s attack by " + powernum + " stages.");
          }
        }
      }
      else if (stataffected.equalsIgnoreCase ("DEF"))
      {
        powernum = Integer.parseInt (power);
        
        for (int i = 0; i < powernum; i++)
        {
          if ((String.valueOf (move.charAt (0))).equalsIgnoreCase ("+"))
            yourcurdef = yourcurdef + (yourdef/10);
          else
            enemycurdef = enemycurdef - (enemycurdef/10);
        } 
        
        if (check == 1)
        {
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            CHARACTER [3][1] = String.valueOf(Math.round(yourcurdef));
            log.append ("\n" + CHARACTER [0][0] + " gained " + move);
          }
          else if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("-"))
          {
            ENEMY [5] = String.valueOf(Math.round(enemycurdef));
            log.append ("\n" + CHARACTER [0][0] + " reduced " + ENEMY[0] + "'s defence by " + powernum + " stages.");
          }
        }
        else
        {
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            ENEMY [5] = String.valueOf(Math.round(yourcurdef));
            log.append ("\n" + ENEMY[0] + " gained " + move);
          }
          else if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("-"))
          {
            CHARACTER [3][1] = String.valueOf(Math.round(enemycurdef));
            log.append ("\n" + ENEMY[0] + " reduced " + CHARACTER[0][0] + "'s defence by " + powernum + " stages.");
          }
        }
      }
      else if (stataffected.equalsIgnoreCase ("DEX"))
      {
        powernum = Integer.parseInt (power);
        
        for (int i = 0; i < powernum; i++)
        {
          if ((String.valueOf (move.charAt (0))).equalsIgnoreCase ("+"))
            yourcurdex = yourcurdex + (yourdex/10);
          else
            enemycurdex = enemycurdex - (enemycurdex/10);
        }
        
        if (check == 1)
        { 
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            CHARACTER [4][1] = String.valueOf(Math.round(yourcurdex));
            log.append ("\n" + CHARACTER [0][0] + " gained " + move);
          }
          else if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("-"))
          {
            ENEMY [6] =  String.valueOf(Math.round(enemycurdex));
            log.append ("\n" + CHARACTER [0][0] + " reduced " + ENEMY[0] + "'s dexterity by " + powernum + " stages.");
          }
        }
        else
        {
          if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("+"))
          {
            ENEMY [6] = String.valueOf(Math.round(yourcurdex));
            log.append ("\n" + ENEMY[0] + " gained " + move);
          }
          else if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("-"))
          {
            CHARACTER [4][1] =  String.valueOf(Math.round(yourcurdex));
            log.append ("\n" + ENEMY[0] + " reduced " + CHARACTER[0][0] + "'s dexterity by " + powernum + " stages.");
          }
        }
      }
    }
    else if (String.valueOf (move.charAt (0)).equalsIgnoreCase ("H"))//Healing move
    {
      powernum = Integer.parseInt(move.substring (5,move.length()-1));
      
      if (yourhp <= (yourcurhp + ((powernum/100.0)*yourhp)))
        yourcurhp = yourhp;
      else
        yourcurhp = yourcurhp + (powernum/100.0)*yourhp; //Heal calculator
      
      
      if (check == 1)
      {
        CHARACTER [1][1] = String.valueOf (Math.round (yourcurhp));
        log.append ("\n" + CHARACTER [0][0] + " healed " + ((powernum/100.0)*yourhp) + " HP!");
      }
      else
      {
        ENEMY [3] = String.valueOf(Math.round(yourcurhp));
        log.append ("\n" + ENEMY[0] + " healed " + ((powernum/100.0)*yourhp) + " HP!");
      }
    }
  }
  private static void enemyai (int turn,String ENEMY [], JTextArea log)
  {
    //getenemymoves - specifically control what set of moves each individual enemy has. They slect a move based on this pool.
    if (turn == 1)
    {
      enemymoves =  new String [12];
    }
    String enemymoveselect;
    int sel= 0;
    if (ENEMY [0].equalsIgnoreCase ("Wizard"))
    {
      if ((Integer.parseInt (ENEMY [1]) > 0) & (20 >= Integer.parseInt (ENEMY [1])))   //if it is in between 1 and 10 
      {
        //Power levels: 35, 50, 75, 100, 125+   
        //               *  ** *** ****   -
        //Level 1-10:*
        enemymoves [1] = "Barrier";
        enemymoves [2] = "Shock Wave"; 
        enemymoves [3] = "Life Pool";
        
        sel = (int) (Math.random() * 3 +1);
        enemymoveselect (enemymoves [sel],log,ENEMY);
      }
      else if ((Integer.parseInt (ENEMY [1]) > 21) & (23 >= Integer.parseInt (ENEMY [1])))
      {
        enemymoves [1] = "Fire Ball";
        enemymoves [2] = "Cloak of Midnight";
        enemymoves [3] = "Restoration";
        // enemymoves [4] = "Fire Ball";
        
        enemymoveselect (enemymoves [(int) (Math.random() * 3 + 1)],log,ENEMY);
      } 
      else if ((Integer.parseInt (ENEMY [1]) > 24) & (30 >= Integer.parseInt (ENEMY [1])))
      {
        enemymoves [1] = "Arcane Bolt"; 
        enemymoves [2] = "Barrier";
        enemymoves [3] = "Restoration";
        enemymoveselect (enemymoves [(int) (Math.random() * 3 + 1)],log,ENEMY);
      }
      else if ((Integer.parseInt (ENEMY [1]) > 31) & (49 >= Integer.parseInt (ENEMY [1])))
      {
        enemymoves [1] = "Arcane Bolt";
        enemymoves [2] = "Barrier";
        enemymoves [3] = "Cloudkill";
        enemymoves [4] = "Fire Ball";
        
        enemymoveselect (enemymoves [(int) (Math.random() * 4 + 1)],log,ENEMY);
      }
      else
      {
        enemymoves [1] = "Arcane Bolt";
        enemymoves [2] = "Restoration";
        enemymoves [3] = "Holy Ray";
        enemymoves [4] = "Cloudkill";
        enemymoves [5] = "Cloak of Midnight";
        enemymoves [6] = "Barrier";
        
        enemymoveselect (enemymoves [(int) (Math.random() * 6 + 1)],log,ENEMY);
      }
    }
    else if (ENEMY[0].equalsIgnoreCase ("Soldier"))
    {
      enemymoves [1] = "Shot Gun";
      enemymoves [2] = "Pistol Shot";
      enemymoves [3] = "Chemtrail Smog";
      enemymoves [4] = "Semi-Automatic Rifle";
      enemymoves [5] = "Take Cover";
      enemymoves [6] = "Step Back";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 6 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Mercenary"))
    {
      enemymoves [1] = "Shot Gun";
      enemymoves [2] = "First Aid";
      enemymoves [3] = "Cloak of Midnight";
      enemymoves [4] = "Slash";
      enemymoves [5] = "Step Back";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 5 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Panthera Soldier"))
    {
      enemymoves [1] = "Shot Gun";
      enemymoves [2] = "Pistol Shot";
      enemymoves [3] = "Forward Stance";
      enemymoves [3] = "Slash";
      enemymoves [4] = "Semi-Automatic Rifle";
      enemymoves [5] = "Roar";
      enemymoves [6] = "Disembowl";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 6 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Tank"))
    {
      enemymoves [1] = "Ion Cannon but it missed!";
      enemymoves [2] = "Ion Cannon but it missed!";
      enemymoves [3] = "Ion Cannon";
      enemymoves [4] = "Ion Cannon but it missed!";
      enemymoves [5] = "Machine Gun";
      enemymoves [6] = "Ion Cannon but it missed!";
      enemymoves [7] = "Ion Cannon but it missed!";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 7 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Ancient Warrior"))
    {
      enemymoves [1] = "Life Pool";
      enemymoves [2] = "Slash";
      enemymoves [3] = "Wrath Blow";
      enemymoves [4] = "Low Blow";
      enemymoves [5] = "Disembowl";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 5 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Cult Leader"))
    {
      enemymoves [1] = "Dark Aura";
      enemymoves [2] = "Low Blow";
      enemymoves [3] = "Corruption";
      enemymoves [4] = "Chemtrail Smog";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 4 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Lower Demon"))
    {
      enemymoves [1] = "Barrier";
      enemymoves [2] = "Cloak of Midnight";
      enemymoves [3] = "Dark Aura";
      enemymoves [4] = "Deathscythe";
      enemymoves [5] = "Corruption";
      
      
      enemymoveselect (enemymoves [(int) (Math.random() * 5 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Reaper"))
    {
      enemymoves [1] = "Barrier";
      enemymoves [2] = "Cloak of Midnight";
      enemymoves [3] = "Restoration";
      enemymoves [4] = "Deathscythe";
      enemymoves [5] = "Corruption";
      enemymoves [6] = "Dark Aura";
      enemymoves [7] = "Disembowl";
      enemymoves [8] = "Fire Ball";
      enemymoves [9] = "Life Pool";
      enemymoves [10] = "Cloudkill";
      
      
      enemymoveselect (enemymoves [(int) (Math.random() * 10 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("undead space marine"))
    {
      enemymoves [1] = "Mana Surge";
      enemymoves [2] = "Slash";
      enemymoves [3] = "Wrath Blow";
      enemymoves [4] = "Deathscythe";
      enemymoves [5] = "Corruption";
      enemymoves [6] = "Dark Aura";
      enemymoves [7] = "High Blow";
      enemymoves [8] = "Forward Stance";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 8 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Water Oracle"))
    {
      enemymoves [1] = "Barrier";
      enemymoves [2] = "Arcane Bolt";
      enemymoves [3] = "Restoration";
      enemymoves [4] = "Holy Ray";
      enemymoves [5] = "Judgement Storm";
      enemymoves [6] = "Aqua Veil";
      enemymoves [7] = "Acid Rain";
      enemymoves [8] = "Blood Rain";
      enemymoves [9] = "Cloudkill";
      
      
      enemymoveselect (enemymoves [(int) (Math.random() * 9 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Imperator"))
    {
      enemymoves [1] = "Arcane Bolt";
      enemymoves [2] = "Dragon Blade";
      enemymoves [3] = "Low Kick";
      enemymoves [4] = "Forward Stance";
      enemymoves [5] = "High Blow";
      enemymoves [5] = "Restoration";
      
      enemymoveselect (enemymoves [(int) (Math.random() * 5 + 1)],log,ENEMY);
    }
    else if (ENEMY[0].equalsIgnoreCase ("Dragon Knight"))
    {
      
      enemymoves [1] = "Middle Blow";
      enemymoves [2] = "Forward Stance";
      enemymoves [3] = "High Blow";
      enemymoves [4] = "Low Kick";
      enemymoves [5] = "Dragon Blade";
      enemymoves [6] = "Slash";
      enemymoves [7] = "Mana Surge"; 
      enemymoves [8] = "Flare Brass";
      
      if ((Integer.parseInt (ENEMY [4]) < 100)) //if his attack is less than 100
      { 
        enemymoveselect (enemymoves [2],log,ENEMY);
      }
      
      else if ((Integer.parseInt (ENEMY [3]) < 60 ) && (healcount == 0)) 
      {
        enemymoveselect (enemymoves [3],log,ENEMY);
        healcount++;
      }
      else if ((Integer.parseInt (ENEMY [3]) <= 225 ) && (brasscount == 0)) //Ultimate move
      {
        enemymoveselect (enemymoves [8],log,ENEMY);
        brasscount++;
      }
      else
      {
        enemymoveselect (enemymoves [(int) (Math.random() * 6 + 1)],log,ENEMY);
      }
      
    }
    else if (ENEMY[0].equalsIgnoreCase ("Mystic Elk"))
    {
      enemymoves [1] = "Barrier";
      enemymoves [2] = "Arcane Bolt";
      enemymoves [3] = "Restoration";
      enemymoves [4] = "Holy Ray";
      enemymoves [5] = "Judgement Storm";
      enemymoves [6] = "Cloudkill";
      enemymoves [7] = "Mana Surge";
      enemymoves [8] = "Catastrophe";
        if ((Integer.parseInt (ENEMY [3]) < 225 ) && (healcount == 0)) //heal threshold
      {
        enemymoveselect (enemymoves [7],log,ENEMY);
        healcount++;
      }
      else if ((Integer.parseInt (ENEMY [3]) <= 400 ) && (catastrophecount == 0)) //Ultimate move
      {
        enemymoveselect (enemymoves [8],log,ENEMY);
        catastrophecount++;
      }
      else
      {
        enemymoveselect (enemymoves [(int) (Math.random() * 6 + 1)],log,ENEMY);
      }
    }
    else if (ENEMY[0].equalsIgnoreCase ("Lord of The Flies"))
    {
      enemymoves [1] = "Proliferation";
      enemymoves [2] = "Dark Bolt";
      enemymoves [3] = "Imperial Wrath";
      enemymoves [4] = "Life Pool";
      enemymoves [5] = "Summon Nightmare";
      enemymoves [6] = "Oblivion Contract"; 
      enemymoves [7] = "Brilliance of Life";
      enemymoves [8] = "Divine Heal";
      
      if ((Integer.parseInt (ENEMY [4]) < 100)) //if his attack is less than 100
      { 
        enemymoveselect (enemymoves [6],log,ENEMY);
      }
      else if ((Integer.parseInt (ENEMY [3]) < 225 ) && (healcount == 0)) //when to heal
      {
        enemymoveselect (enemymoves [8],log,ENEMY);
        healcount++;
      }
      else if ((Integer.parseInt (ENEMY [3]) <= 400 ) && (brilliance == 0)) //Ultimate move
      {
        enemymoveselect (enemymoves [7],log,ENEMY);
        brilliance++;
      }
      else
      {
        enemymoveselect (enemymoves [(int) (Math.random() * 3 + 1)],log,ENEMY);
      }
    }
    else if (ENEMY[0].equalsIgnoreCase ("The Demiurge"))
    {
      enemymoves [1] = "Heaven's Ray";
      enemymoves [2] = "Grandcross";
      enemymoves [3] = "Grandcross";
      enemymoves [4] = "Meteor Strike";
      enemymoves [5] = "Angel Wings";
      enemymoves [6] = "All-Seeing Force";
      enemymoves [7] = "Imperial Wrath";
      enemymoves [8] = "Imperial Wrath";
      enemymoves [9] = "Divine Heal";
      enemymoves [10] = "Fusion Supernova";
      enemymoves [11] = "Gaia's Reawakening";
      
      if ((Integer.parseInt (ENEMY [4]) < 100)) //if his attack is less than 100
      { 
        enemymoveselect (enemymoves [11],log,ENEMY);
      }
      else if ((Integer.parseInt (ENEMY [3]) < 225 ) && (healcount == 0)) //when to heal
      {
        enemymoveselect (enemymoves [9],log,ENEMY);
        healcount++;
      }
      else if ((Integer.parseInt (ENEMY [3]) <= 400 ) && (supernova == 0)) //Ultimate move
      {
        enemymoveselect (enemymoves [10],log,ENEMY);
        supernova++;
      }
      else
      {
        enemymoveselect (enemymoves [(int) (Math.random() * 8 + 1)],log,ENEMY);
      }
    }
  }
  //class wide variables to ensure proper execution
  static int catastrophecount = 0;
  static  int healcount = 0;
  static int brasscount = 0;
  static int brilliance = 0;
  static int supernova = 0;
  private static void enemymoveselect (String move,JTextArea log,String ENEMY []) //This will make the enemy attack 
  {
    BufferedReader input;
    String name, power, element, cooldown, desc;
    int check =0;
    name = " ";
    power = " ";
    element = " ";
    cooldown = " ";
    desc = " ";
    //double damage;
    try{
      input = new BufferedReader (new FileReader ("movelist.txt")); //linear search to read the move list
      
      name = input.readLine ();
      power = input.readLine ();
      element = input.readLine ();
      cooldown = input.readLine ();
      desc = input.readLine ();
      while (!(name.equals (move)))
      {
        name = input.readLine ();
        power = input.readLine ();
        element = input.readLine ();
        cooldown = input.readLine ();
        desc = input.readLine ();
        if (name == null)
        {   
          break;
        }
      }
      input.close();
    }
    catch (IOException e)
    {
    } 
    
    int damage;
    int statuscheck = 0;
    log.append (ENEMY [0] + " used " + move);
    try {
      Integer.parseInt(power);
    }
    catch (NumberFormatException e)
    {
      statuscheck = 1;
    }
    
    if (statuscheck == 0) //If the move does damage
    {
      damage = (int) (Math.round (damagecalculator (power,element, Integer.parseInt(CHARACTER [3][1]),Integer.parseInt(ENEMY[1]),Integer.parseInt(ENEMY[4])))); //Execute damage calculator  
      log.append ("\nYou recieved " + damage +" damage!");
      CHARACTER [1] [1] = Integer.toString (Integer.parseInt (CHARACTER [1] [1]) - damage); 
      
    }
    else //If the move buffs your character
    {
      statusmove (ENEMY,power,check,log);
      damage = 0;
      statuscheck = 1;
      //buff the character
    }
    
  }
  private static double damagecalculator (String power, String element,double enemydefense,double yourlevel,double youratk)
  {
    double damage = 0;
    damage = (youratk)/(enemydefense) * (Double.parseDouble(power)) +1 ;
    if (damage < 0) //if damage is negative. set to 1 instead.
      damage = 1.0;
    return damage;
  }
}