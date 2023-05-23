
package com.mycompany.brickbreaker;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;



public class MapGenerator {
    // creating 2D array
    public int[][] map;     
     //declare brickwidth
    public int brickwidth; 
    //declaring brick height
    public int brickheight;
    public MapGenerator(int row,int col)
    {
        //initialising 2d array
        map=new int[row][col];
        //loop for etting value for 2d array to 1
       for(int i=0;i<map.length;i++)
       {
           for(int j=0;j<map[0].length;j++)
           {
               map[i][j]=1;
           }
       }
       // initilising brickwidth consideration taken with respect to window size
        brickwidth=540/col;
         // initilising brickwidth consideration taken with respect to window size
        brickheight=150/row;
        //drawing bricks
    }
    public void Draw(Graphics2D g)
    {
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                 g.setColor(Color.red);
                 g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);//redcolor bricks
//                 
                 g.setStroke(new BasicStroke(3));//for stroke width
                 g.setColor(Color.black);
                 g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);//black strokes
                 
                 
                }
            }
        }
    }
    
    public void setBrickValue(int value,int row,int col)//to change value 1 to 0 after touching bricks ;
    {
        map[row][col]=value;
    }
    public static void main(String[] args) {
        
    }
    
}
