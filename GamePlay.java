package com.mycompany.brickbreaker;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;               //importing necessary awt and swing classes
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.SocketOption;
import java.awt.Graphics2D;

public  class GamePlay extends JPanel implements ActionListener,KeyListener {
    //this variables are used in games
    private boolean play=false;
    private int score=0;
    private int totalbricks=21;
    private Timer Timer;
    private int delay=8;            //declaring and initilise some variables in game
    private int playerx=350;
    private int ballpositionx=180;
    private int ballpositiony=250;
    private int balldirx=-1;
    private int balldiry=-2;
    private MapGenerator map;
    public GamePlay()
    {
        
        map=new MapGenerator(3, 7);   //create map object
  addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       Timer = new Timer(delay,this);
        Timer.start();
        
        
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.black);         
        g.fillRect(1, 1, 692    , 592);   //setting black panel
        
        map.Draw((Graphics2D) g);   //drawing bricks and black stroke
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 3); //top border for reflecting
        g.fillRect(0,0 , 3, 592);  //left border for reflecting
        g.fillRect(691, 0, 3, 592); //right border for reflecting
        
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score,590,30);   //to show score
        g.setColor(Color.yellow);
        g.fillRect(playerx,550,100,8); //  to draw playerx which is moving left to right while pressing right,left keys
        
        
        g.setColor(Color.GREEN);
        g.fillOval(ballpositionx, ballpositiony,20,20); // to draw ball
        
        if(ballpositiony>570)    //condition for bottom edge if not intersect with playerx game 
        {
            play=true;
            balldirx=0;
            balldiry=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    GAME OVER  : "+score, 190,300);
            
           // g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter To Restart", 190,340);
            
            
        }
        if(totalbricks==0)  //if allbricks over for showing score and to stop game
        {
            play=false;
            balldirx=-1;
            balldiry=-2;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(" GAME OVER  : "+score, 190,300);
            
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter To Restart", 190,340);
        }   
        g.dispose();
    }
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
       Timer.start();
         if(play)
         {
             // condition for reflect back if ball hits playerx
             if(new Rectangle(ballpositionx,ballpositiony,20,20).intersects(new Rectangle(playerx,550,100,8)))
             {
                 balldiry=-balldiry;
             }
             A:
             for(int i=0;i<map.map.length;i++)
             {
                 for(int j=0;j<map.map[0].length;j++)
                 {
                     if(map.map[i][j]>0)
                     {
                        // declaring rectangles to make  conditions for rectangles
                          int brickX=(j*map.brickwidth)+80;
                          int brickY=(i*map.brickheight)+50;
                     
                          int brickWidth= map.brickwidth;
                          int brickHeight=map.brickheight;
                          Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                          Rectangle ballrect=new Rectangle(ballpositionx,ballpositiony,20,20);
                          Rectangle brickrect=rect; 
                     
                     
                     if(ballrect.intersects(brickrect)) //if ball intersect with brick
                     {
                         // code for if ball hits bricks set value 0 for getting black
                         
                         map.setBrickValue(0, i, j);
                         totalbricks--;
                         score+=5;
                         if(ballpositionx+19<brickrect.x||ballpositionx+1>=brickrect.x+brickWidth){
                             
                         balldirx=-balldirx;
                         }
                         else
                         {
                             balldiry=-balldiry;
                         }
                         break A;
                     }
                     }
                     
                    
                 }
             }
             ballpositionx+=balldirx;
             ballpositiony+=balldiry; 
             if(ballpositionx<0)  //reflecting condition for left edge
             {
                 balldirx =-balldirx;
                 
             }
             if(ballpositiony<0)
             {
                 balldiry=-balldiry; // reflecting condition for top edge
             }
             if(ballpositionx>670)
             {
                 balldirx=- balldirx;     //reflecting for right edge
             }
         }
        repaint();    //to paint again
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)   //while pressing right key
        { 
            if(playerx>=600)  //checking playerx movement w.r.t window
            {
                playerx=600;  //setting right edge if playerx moves to extreme right
            }
            else
            {
                moveright();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)  //after pressing left key
        {
            if(playerx<=0)
            {
                playerx=0; //setting leftedge
            }
            else
            
            {
             moveleft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER) // after pressing enter
        {
            if(play==false) //condition for checking game is false or true
            {
                //initilise all values like new game
             play=false;
            balldirx=-1;
            balldiry=-2;
           ballpositionx=120;
           ballpositiony=350;
           score=0;
           totalbricks=21;
            playerx=350;
             map = new MapGenerator(3,7);
             repaint();
            }
        }
    }
    public  void moveleft() //playerx changing direction to left
    {
        play=true;
        playerx-=30;
    }
    
        public  void moveright() //playerx changing direction to right
    {
        play = true;
        playerx+=30;
    }
    public static void main(String[] args)
    {
       
    }
}