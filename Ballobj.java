import java.awt.*;
import java.applet.*;
import java.util.*;

import javax.swing.*;


public class Ballobj extends Applet implements Runnable
{   
   ImageIcon emptyIcon = new ImageIcon(new byte[0]);
    
   Cursor invisibleCursor = getToolkit().createCustomCursor(
   emptyIcon.getImage(), new Point(0,0), "Invisible");
    
    Thread runner;
    Image Buffer;
    Graphics gBuffer;
    Ball A;
    Ball B;
    Ball temp;
    Image fly, spider;
    int i;
    boolean m;
    ArrayList z;
    boolean gameOver = false;
    int count = 0;
    int score = 0;
    AudioClip click_x;
    AudioClip game_over;
    Random D = new Random();
     
    public void init()
    {       
    this.setCursor(invisibleCursor);
    
 z = new ArrayList();
    for(int i = 0; i< 100; i++)
    {
        temp = new Ball(D.nextInt(600)+5, D.nextInt(600)-5,D.nextInt(40)+5);
        z.add(temp);
    }
 
        fly=getImage(getCodeBase(),"fly.gif"); 
        spider=getImage(getCodeBase(),"spider.gif");
    
        Buffer  = createImage(size().width,size().height);
        gBuffer = Buffer.getGraphics();
        A = new Ball(15,450,10);
       
       
        B = new Ball( 250,250,30);
        click_x = getAudioClip(getCodeBase(), "click_x.wav");
        game_over = getAudioClip(getCodeBase(), "game_over.wav");
    }
   
    public void kill()
{
        gBuffer.setColor(Color.red);
         gBuffer.fillRect(0,0,size().width,size().height);
         count++;
         if(count == 3)
         {
             gBuffer.setColor(Color.red);
             gBuffer.fillRect(0,0,size().width,size().height);
             gBuffer.setColor(Color.white);
             gBuffer.fillRect(0,0,size().width,size().height);
             gBuffer.setColor(Color.blue);
             gBuffer.fillRect(0,0,size().width,size().height);
             score = score - 1;
             count = 0;
         }
}
    
   public void start()
    { if (runner == null)
        {
            runner = new Thread (this);
            runner.start();
        }
    }
  
  public void stop()
    {  if (runner != null)
        {
            runner.stop();
            runner = null;
        }
    }
 
 public boolean mouseMove(Event evt,int x,int y)
 {
  A.MouseMove(x,y);
  return true;  
 }
 
   public void run()
    {   
       while(!gameOver)
       {
        
          try {runner.sleep(15);}
            catch (Exception e) { }
               
         gBuffer.setColor(Color.black);
         gBuffer.fillRect(0,0,size().width,size().height);
         
            B.Tracker(A);
            //B.moveBall();
            A.paint(gBuffer);
            
            
            B.paint(gBuffer);
            
            
         
            for(int i = 0; i<z.size();i++)
            {
                temp=(Ball)z.get(i);
                temp.paint(gBuffer);
                temp.moveBall();
                
                if(temp.getX()>600)
                {
                    temp.setX(-5);
                }
            }
            for(int i = 0; i<z.size();i++)
            {
                temp=(Ball)z.get(i);
                if(collision(A,temp))
                {
                    if(temp.getD()>A.getD())
                    { 
                       kill();
                    }else{
                        z.remove(i);
                        z.add(new Ball(D.nextInt(600)+5, D.nextInt(600)-5,D.nextInt(45)+5));
                        A.setDiameter(A.getDiameter()+1);
                        click_x.play();
                        score++;
                       
                    }
                }
            }
            gBuffer.setFont(new Font("Courier new",700,40));
            gBuffer.setColor(Color.orange);
            gBuffer.drawString("Score: " + score,50,50);
            gameOver = collision(A,B);
            game_over.play();
            repaint(); 
    }
    }  

   
  public void update(Graphics g)
    {
        paint(g);
    }
    public void paint(Graphics g)
    {
        g.drawImage (Buffer,0,0, this);
    }
    
    public boolean collision(Ball x, Ball y)
    { 
      float a = x.getcX();        
      float b = x.getcY();
      float c = y.getcX();        
      float d = y.getcY();
    
      float e = x.getRadius()+ y.getRadius();   
      
      double f = Math.sqrt( (a - c)* (a - c) + (b - d)*( b - d));  
    
      
      if ( f <= e)
      return true;//  collision
      return false;// no collision
    }

}


