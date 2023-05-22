package com.mycompany.brickbreaker;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.SocketOption;
import java.awt.Graphics2D;

public  class GamePlay extends JPanel implements ActionListener,KeyListener {
    private boolean play=false;
    private int score=0;
    private int totalbricks=21;
    private Timer Timer;
    private int delay=8;
    private int playerx=350;
    private int ballpositionx=120;
    private int ballpositiony=350;
    private int balldirx=-1;
    private int balldiry=-2;
    private MapGenerator map;
    public GamePlay()
    {
        map=new MapGenerator(3, 7);
  addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       Timer = new Timer(delay,this);
        Timer.start();
        
        
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692    , 592);
        
        map.Draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0,0 , 3, 592);
        g.fillRect(691, 0, 3, 592);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score,590,30);
        g.setColor(Color.yellow);
        g.fillRect(playerx,550,100,8);
        
        
        g.setColor(Color.GREEN);
        g.fillOval(ballpositionx, ballpositiony,20,20);
        
        if(ballpositiony>570)
        {
            play=false;
            balldirx=0;
            balldiry=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    GAME OVER  : "+score, 190,300);
            
           // g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter To Restart", 190,340);
            
            
        }
        if(totalbricks==0)
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
                          int brickX=(j*map.brickwidth)+80;
                          int brickY=(i*map.brickheight)+50;
                     
                          int brickWidth= map.brickwidth;
                          int brickHeight=map.brickheight;
                          Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                          Rectangle ballrect=new Rectangle(ballpositionx,ballpositiony,20,20);
                          Rectangle brickrect=rect; 
                     
                     
                     if(ballrect.intersects(brickrect))
                     {
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
             if(ballpositionx<0)
             {
                 balldirx =-balldirx;
                 
             }
             if(ballpositiony<0)
             {
                 balldiry=-balldiry;
             }
             if(ballpositionx>670)
             {
                 balldirx=- balldirx;
             }
         }
        repaint();
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
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        { 
            if(playerx>=600)
            {
                playerx=600;
            }
            else
            {
                moveright();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerx<=0)
            {
                playerx=0;
            }
            else
            
            {
             moveleft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(play==false)
            {
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
    public  void moveleft()
    {
        play=true;
        playerx-=30;
    }
    
        public  void moveright()
    {
        play = true;
        playerx+=30;
    }
    public static void main(String[] args)
    {
       
    }
}