import java.awt.*;
import java.applet.*;
import java.util.*;
public class Ball //extends Applet 
{
 int x,y,ystep,xstep,diameter,cX,cY,radius, angle;
 
 Graphics g;
Random D = new Random();
public Ball(int c, int r, int d)
{

x = c;
y = r;
xstep = 1;
ystep = 1;
diameter = d;
radius=diameter/2;
cX = c + radius;
cY = r + radius;
}

public void Tracker( Ball A )
{

float Ax = A.getcX();
int   Ay = A.getcY();
int   Bx = getcX();
int   By = getcY();

if(Bx > Ax)
{
x-=xstep; cX = x+radius;
}else{
x+=xstep; cX = x+radius;
}

if(By > Ay)
{
y-=ystep; cY = y+radius;
}else{
y+=ystep; cY = y+radius;
}




}

public void reverseX()
{
xstep*=-1;
}

public int getDiameter()
{
    return diameter;
}

public void reverseY()
{
ystep*=-1;
}

public void bumpX()
{
x+=xstep*2;

}

public void bumpY()
{
y+=ystep*2;
}

public int getRadius()
{
return diameter/2;
}

public int getX()
{
return x;
}

public int getY()
{
return y;
}

public int getcX()
{
return cX;
}

public int getcY()
{
return cY;
}

public int getD()
{
return diameter;
}
public void setX(int X)
{
x = X;
}
public void setY(int Y)
{
y = Y;
}
public int getXstep()
{
return xstep;
}
public void setDiameter(int d)
{
    diameter=d;
}

public int getYstep()
{
return ystep;
}

public void drawBall()
{
g.setColor(Color.white);
g.fillOval(x,y,diameter,diameter);
}

public void MouseMove(int X, int Y)
{
 x = X;
 y =Y;
  cX = x + radius;
 cY = y + radius;
}

public void moveBall()
{
 x = x + xstep;
 //y = y + ystep;
 cX = x + radius;
 cY = y + radius;



}


public void paint(Graphics gr)
{
g = gr;
drawBall();
}

}// class Ball

