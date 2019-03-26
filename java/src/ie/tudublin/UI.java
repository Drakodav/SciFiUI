package ie.tudublin;

import processing.core.PApplet;

public class UI extends PApplet
{
    private float DUnit;
    private float centerX;
    private float centerY;

    Button b;
    MovingCircle mc;
    Radar rd;
    AudioWave aw;
    Stars[] stars = new Stars[1000];

    public void settings()
    {
        
        // size(800, 800);
        // Use fullscreen instead of size to make your interface fullscreen
        fullScreen(P3D,SPAN); 
    }

    public void setup()
    {
        // set a colormode
        colorMode(HSB, 255, 255, 255);
        smooth();

        // set screen center
        centerX = width/2.0f;
        centerY = height/2.0f;

        // set normal unit circle diameter
        DUnit = (height + width) / 16;

        // instantiate objects
        instatiate();
    }

    public void instatiate()
    {
        b = new Button(this, 50, 50, 100, 50, "I am a button");
        mc = new MovingCircle(this, centerX, centerY, DUnit*2);
        rd = new Radar(this, centerX, centerY, DUnit);
        aw = new AudioWave(this);

        for (int i = 0; i < stars.length; i++)
        {
            stars[i] = new Stars(this, DUnit);
        }
    }
    public void draw()
    {
        background(0);
        aw.update();

        //b.render();

        // mc.update();
        // mc.render();
        

        rd.update();
        rd.render();

        for (int i = 0; i < stars.length; i++)
        {    
            stars[i].render();
            stars[i].update();
        }

        // control for stars[]
        if (checkKey(' ')) 
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x = rd.x + (rd.radius * (float) Math.sin(i));
                stars[i].y = rd.y + (rd.radius * (float) Math.cos(i));
            }  
        }
        if (checkKey('z'))
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x += (float) (Math.cos(i)) *4;
                stars[i].y += (float) (Math.sin(i)) *4;
            }
        }
        if (checkKey('x'))
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x -= (float) (Math.cos(i)) *4;
                stars[i].y -= (float) (Math.sin(i)) *4;
            }
        }
        if (checkKey('a'))
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x -= (float) (( Math.cos(i*width)*8)*8 %Math.cos(width));
                stars[i].y -= (float) (( Math.sin(i*height)*8)*8 %Math.sin(height));
            }
        }
        if (checkKey('s'))
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x += (float) (( Math.cos(i*width)*8) );//%Math.cos(width);
                stars[i].y += (float) (( Math.sin(i*height)*8) );//Math.sin(height);
            }
        }
        if (checkKey('l'))
        {
            for (int i = 0; i < stars.length; i++)
            {
                stars[i].x = (float) (map(i, 0, stars.length, 0, width));
                stars[i].y = height/2;
            }
        }

        if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
            System.out.println(centerX + " vs " + width/2);
        }
    }

    
    boolean[] keys = new boolean[1024];

    public void keyPressed()
    {
        keys[keyCode] = true;
    }
    
    public void keyReleased()
    {
        keys[keyCode] = false;
    }

    public boolean checkKey(int c)
    {
        return keys[c] || keys [Character.toUpperCase(c)];
    }
    
}

