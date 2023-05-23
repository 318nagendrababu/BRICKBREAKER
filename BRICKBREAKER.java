package com.mycompany.brickbreaker;
import javax.swing.JFrame;

public class BRICKBREAKER {

    public static void main(String[] args) {
        // creating JFrame object
         JFrame obj=new JFrame();
         
         //creating gameplay object from GamePlay class
       GamePlay gamePlay=new GamePlay();
       
       //setting bounds f0r window
        obj.setBounds(10,10, 700, 600);
        
        //setting window visiible after execution
        obj.setVisible(true);
        
        //setting titile for window
        obj.setTitle("Brick Breaker");
        
        //setting resisizable option to not work
        obj.setResizable(false );
        
        //setting application close after clicking close button 
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //adding gameplay object on JFrame 
        obj.add(gamePlay);
    }
}
