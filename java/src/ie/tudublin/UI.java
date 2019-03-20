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

    boolean[] keys = new boolean[1024];

    public void keyPressed()
    {
        keys[keyCode] = true;
    }
    
    public void keyReleased()
    {
        keys[keyCode] = true;
    }

    public boolean checkKey(int c)
    {
        return keys[c] || keys [Character.toUpperCase(c)];
    }
    

    public void settings()
    {
        
        // size(800, 800);
        // Use fullscreen instead of size to make your interface fullscreen
        fullScreen(); 
    }

    public void setup()
    {
        // set a colormode
        colorMode(HSB);
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
        rd = new Radar(this, centerX/2, centerY/2, DUnit);
        aw = new AudioWave(this);
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

        if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
            System.out.println(centerX + " vs " + width/2);
        }
    }
}

