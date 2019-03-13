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

}