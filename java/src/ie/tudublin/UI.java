package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class UI extends PApplet
{
    private float DUnit;
    private float centerX;
    private float centerY;
    private int lots;
    private int sec;

    Button b;
    MovingCircle mc;
    Radar rd;
    Radar rdLeft;
    Radar rdRight;
    AudioWave aw;
    Tempo tmp;
    
    public  ArrayList<Star> stars = new  ArrayList<Star>();
    public  ArrayList<Star> sLeft = new  ArrayList<Star>();
    public  ArrayList<Star> sRight = new  ArrayList<Star>();
    public  ArrayList<Star> sBackground = new  ArrayList<Star>();

    public void settings() {
        // size(800, 800);
        // Use fullscreen instead of size to make your interface fullscreen
        // fullScreen(P3D,SPAN); 
        fullScreen();
    }

    public void setup() {
        // set a colormode
        colorMode(HSB, 255, 255, 255);
        smooth();

        // set screen center
        centerX = width/2.0f;
        centerY = height/2.0f;
        // set normal unit circle diameter
        DUnit = (height + width) / 10;
        lots = 700;

        sec = second();

        // instantiate objects
        instatiate();
    }
    
    public void instatiate()
    {
        // b = new Button(this, 50, 50, 100, 50, "I am a button");
        // mc = new MovingCircle(this, centerX, centerY, DUnit*2);
        rd = new Radar(this, centerX, centerY, DUnit/5 * 4);
        rdLeft = new Radar(this, centerX/3, centerY, DUnit/3);
        rdRight = new Radar(this, centerX/3 * 5, centerY, DUnit/3);
        aw = new AudioWave(this);
        tmp = new Tempo(this);

        // initialise arraylists
        for (int i = 0; i < lots*2; i++) {
            if (i<lots/2) {
                stars.add(new Star(this, (DUnit/3) * 2, "stars"));
            }
            if (i>lots/2 && i < lots/2+lots/4) {
                sLeft.add(new Star(this, DUnit/2, "sLeft"));
            }
            if (i>lots/2+lots/4 && i<lots) {
                sRight.add(new Star(this, DUnit/2, "sRight"));
            }
            if (i>lots && i<lots*2) {
                sBackground.add(new Star(this, DUnit/4, " "));
            }
        }
    }

    public void draw()
    {
        background(0);

        tmp.render();
        tmp.update();

        aw.update();
        
        // show and update radar objects
        rd.update();
        rd.render();
        rdLeft.update();
        rdLeft.render();
        rdRight.update();
        rdRight.render();

        // foreach loop which makes eases the arraylist way of doing things
        for (Star s : stars) {
            s.update();
            s.render();
        }
        for (Star s : sLeft) {
            s.update();
            s.render();
        }
        for (Star s : sRight) {
            s.update();
            s.render();
        }
        for (Star s : sBackground) {
            s.update();
            s.render();
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

    final static double EPSILON = 1e-12;

    public static double map(double valueCoord1,
            double startCoord1, double endCoord1,
            double startCoord2, double endCoord2) {

        if (Math.abs(endCoord1 - startCoord1) < EPSILON) {
            throw new ArithmeticException("/ 0");
        }

        double offset = startCoord2;
        double ratio = (endCoord2 - startCoord2) / (endCoord1 - startCoord1);
        return ratio * (valueCoord1 - startCoord1) + offset;
    }

    public void mouseDragged()
    {
        rd.mouseDragged();
        rdLeft.mouseDragged();
        rdRight.mouseDragged();
    }

    public int secCount(){
        if ( second() % 1 == 0 ) {
            if ( second() > sec) {
                // System.out.println(sec);
                sec++;
                return sec;
            }
            sec = second();
        }
        return sec;
    }

    public boolean oneSec() {
        if ( second() % 1 == 0 ) {
            if ( second() > sec) {
                // System.out.println(sec);
                sec++;
                return true;
            }
            sec = second();
        }
        return false;
        
    }
}

