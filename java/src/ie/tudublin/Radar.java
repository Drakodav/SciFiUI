package ie.tudublin;

import processing.core.PApplet;
import processing.core.PVector;

public class Radar
{
<<<<<<< HEAD
    private float radius;
    private PVector pos;
    private float frequency;
    private UI ui;
    private float theta = 0;
=======
    public float x;
    public float y;
    public float diameter;
    public float radius;
    private float rotation;
    UI ui;
>>>>>>> working

    public Radar(UI ui, float frequency, float x, float y, float radius)
    {
        this.ui = ui;
<<<<<<< HEAD
        this.frequency = frequency;
        pos = new PVector(x, y);
        this.radius = radius;
=======
        this.x = x;
        this.y = y;
        this.diameter = diameter*1.5f;
        radius = (this.diameter)/2;
>>>>>>> working
    }

    public void render()
    {
<<<<<<< HEAD
        /*ui.pushMatrix();        
        ui.noFill();
        ui.stroke(0, 200, 0);
        ui.translate(pos.x, pos.y);
        ui.rotate(theta);
        ui.ellipse(0, 0, radius * 2, radius * 2);
        ui.line(0,0,0,-radius);
=======
        ui.stroke(255);
        ui.fill(10, 200, 50);
        
        ui.pushMatrix();
        ui.translate(x, y);
        ui.ellipse(0, 0, diameter, diameter);
        
        ui.rotate(rotation);
        // for (int i = 0; i < 100; i++) {
        //     ui.stroke(255, ui.frameCount%100+155, 255);
        //     ui.line(0, 0, (float) (radius*Math.sin(i*0.001f)), (float) (radius*Math.cos(i*0.001f)));
        // }
>>>>>>> working
        ui.popMatrix();
        */

        ui.noFill();
        ui.ellipse(pos.x, pos.y, radius * 2, radius * 2);
        float x2 = pos.x + (float) Math.sin(theta) * radius;
        float y2 = pos.y - (float) Math.cos(theta) * radius;
        ui.line(pos.x, pos.y, x2, y2);
    }

    float timeDelta = 1.0f / 60.0f;

    public void update()
    {
<<<<<<< HEAD
        theta += PApplet.TWO_PI * timeDelta * frequency;
    }

    /**
     * @return the radius
     */
    public float getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * @return the pos
     */
    public PVector getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(PVector pos) {
        this.pos = pos;
    }

    /**
     * @return the frequency
     */
    public float getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(float frequency) {
        this.frequency = frequency;
=======
        rotation += 0.03f;
        // diameter += Math.sin(rotation*3)*3;
        radius = diameter/2;
    }

    void mouseDragged()
    {
        if ( UI.between(ui.mouseX, x-radius, x+radius) && UI.between(ui.mouseY, y-radius, y+radius) )
        {
            x = ui.mouseX;
            y = ui.mouseY;
        }
>>>>>>> working
    }

    
}