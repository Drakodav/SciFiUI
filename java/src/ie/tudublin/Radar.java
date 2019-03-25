<<<<<<< HEAD
package ie.tudublin;

// import processing.core.PVector;

public class Radar
{
    private float x;
    private float dx = 2;
    private float y;
    private float diameter;
    private float radius;
    private float rotation;
    UI ui;

    public Radar(UI ui, float x, float y, float diameter)
    {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        radius = (diameter / 2.00f);
    }

    public void render()
    {
        ui.stroke(255);
        ui.noFill();
        ui.ellipse(x, y, diameter, diameter);

        ui.pushMatrix();
        ui.translate(x, y);
        ui.rotate(rotation);

        ui.fill(255);
        ui.line(0, 0, radius, 0);

        ui.popMatrix();
    }

    public void update()
    {
        rotation -= 0.1f;

        x += dx;
        if ((x > ui.width - radius) || (x < radius))
        {
            dx *= -1;
        }
    }

=======
package ie.tudublin;

import processing.core.PApplet;
import processing.core.PVector;

public class Radar
{
    private float radius;
    private PVector pos;
    private float frequency;
    private UI ui;
    private float theta = 0;

    public Radar(UI ui, float frequency, float x, float y, float radius)
    {
        this.ui = ui;
        this.frequency = frequency;
        pos = new PVector(x, y);
        this.radius = radius;
    }

    public void render()
    {
        /*ui.pushMatrix();        
        ui.noFill();
        ui.stroke(0, 200, 0);
        ui.translate(pos.x, pos.y);
        ui.rotate(theta);
        ui.ellipse(0, 0, radius * 2, radius * 2);
        ui.line(0,0,0,-radius);
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
    }

    
>>>>>>> 8f5fee54af6d6253030d429f4765a4e9bfe12053
}