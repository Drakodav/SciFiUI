package ie.tudublin;

// import processing.core.PVector;

public class Radar
{
    private float x;
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
        this.diameter = diameter*2;
        radius = (this.diameter)/2;
    }

    public void render()
    {
        ui.stroke(255);
        ui.noFill();
        
        ui.pushMatrix();
        ui.translate(x, y);
        ui.rotate(rotation);
        
        ui.ellipse(0, 0, diameter, diameter);
        ui.stroke(ui.frameCount%255+200, ui.frameCount%255+200, 255);
        ui.line(0, 0, radius, 0);
        for (int i = 0; i < 300; i+=4) {
            ui.line(0, 0, radius, (float) (-i*Math.cos(radius)));
        }
        ui.popMatrix();
    }

    public void update()
    {
        rotation += 0.01f;
    }

}