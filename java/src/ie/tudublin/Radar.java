package ie.tudublin;

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
        this.diameter = diameter*1.5f;
        radius = (this.diameter)/2;
    }

    public void render()
    {
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
        ui.popMatrix();
    
    }

    public void update()
    {
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
    }

    
}