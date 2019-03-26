package ie.tudublin;

// import processing.core.PVector;

public class Radar
{
    public float x;
    public float y;
    public float diameter;
    public float radius;
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
        for (int i = 0; i < 100; i++) {
            ui.stroke(255, ui.frameCount%100+155, 255);
            ui.line(0, 0, (float) (radius*Math.sin(i*0.001)), (float) (radius*Math.cos(i*0.001)));
        }
        ui.popMatrix();
    }

    public void update()
    {
        rotation += 0.03f;
        diameter += Math.sin(rotation*3)*2.5;
        radius = diameter/2;
    }

}