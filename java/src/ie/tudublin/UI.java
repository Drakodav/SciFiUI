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
        // fullScreen(P3D); 
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
        // b = new Button(this, 50, 50, 100, 50, "I am a button");
        // mc = new MovingCircle(this, centerX, centerY, DUnit*2);
        // rd = new Radar(this, 2, centerX/2, centerY/2, DUnit);
        // aw = new AudioWave(this);

        // for (int i = 0; i < stars.length; i++)
        // {
        //     stars[i] = new Stars(this, DUnit);
        // }

        mc = new MovingCircle(this, width / 2, height * .75f, 50);
        radar = new Radar(this, 1, width / 2, height / 2, 100);
    }

    Radar radar;

    public void draw()
    {
        background(0);
        // aw.update();

        //b.render();

        // mc.update();
        // mc.render();
        

        // rd.update();
        // rd.render();

        // for (int i = 0; i < stars.length; i++)
        // {    
        //     stars[i].render();
        //     stars[i].update();
        // }
        
        radar.update();
        radar.render();

        if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
            System.out.println(centerX + " vs " + width/2);
        }
    }
}

