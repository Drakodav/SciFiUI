package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class UI extends PApplet
{
    private float DUnit;
    private float centerX;
    private float centerY;
    private int lots;

    Button b;
    MovingCircle mc;
    Radar rd;
    Radar rdLeft;
    AudioWave aw;
    public  ArrayList<Star> stars = new  ArrayList<Star>();
    public  ArrayList<Star> sLeft = new  ArrayList<Star>();
    public  ArrayList<Star> sBackground = new  ArrayList<Star>();

    public void settings() {
        // size(800, 800);
        // Use fullscreen instead of size to make your interface fullscreen
        fullScreen(P3D,SPAN); 
        // fullScreen();
    }

    public void setup() {
        // set a colormode
        colorMode(HSB, 255, 255, 255);
        smooth();

        // set screen center
        centerX = width/2.0f;
        centerY = height/2.0f;
        // set normal unit circle diameter
        DUnit = (height + width) / 16;
        lots = 1000;

        // instantiate objects
        instatiate();
    }
    
    public void instatiate()
    {
        b = new Button(this, 50, 50, 100, 50, "I am a button");
        mc = new MovingCircle(this, centerX, centerY, DUnit*2);
        rd = new Radar(this, centerX, centerY, DUnit);
        rdLeft = new Radar(this, centerX/4, centerY/4, DUnit/2);
        aw = new AudioWave(this);

        for (int i = 0; i < lots; i++) {
            stars.add(new Star(this, DUnit, "stars"));
        }
        for (int i = 0; i < lots/2; i++) {
            sLeft.add(new Star(this, DUnit/2, "sLeft"));
        }
        for (int i = 0; i < lots; i++) {
            sBackground.add(new Star(this, DUnit/2, " "));
        }
        
    }

    Radar radar;

    public void draw()
    {
        background(0);
        aw.update();

        //b.render();

        // mc.update();
        // mc.render();
        
        // show and update radar objects
        rd.update();
        rd.render();
        rdLeft.update();
        rdLeft.render();

        // foreach loop which makes eases the arraylist way of doing things
        for (Star s : stars) {
            s.update();
            s.render();
        }
        for (Star s : sLeft) {
            s.update();
            s.render();
        }
        for (Star s : sBackground) {
            s.update();
            s.render();
        }

        if (checkKey(LEFT))
        {
            System.out.println("Check : ");
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

    // a range between function
    public static boolean between(float i, float minValueInclusive, float maxValueInclusive) {
        if (i >= minValueInclusive && i <= maxValueInclusive)
            return true;
        else
            return false;
    }

    public void mouseDragged()
    {
        rd.mouseDragged();
        rdLeft.mouseDragged();
    }
}

