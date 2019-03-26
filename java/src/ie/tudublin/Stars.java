package ie.tudublin;

public class Stars
{
    UI ui;
    private float unit;
    public float x;
    public float y;

    public Stars(UI ui, float unit)
    {
        this.ui = ui;
        this.unit = unit/64; // 1 size unit

        // set star location to spawn in a random location
        x = (float) (Math.random() * (ui.width - 0) + 1);
        y = (float) (Math.random() * (ui.height - 0)+ 1);
    }

    public void render()
    {
        ui.stroke(0);
        ui.noFill();
        // for (int i = 0; i < ui.frameCount%255; i++) {
            // for (int j = 0; j < ui.frameCount%255; j++) {
                ui.stroke(ui.frameCount%255, 40, 150);
            // }
        // }

        ui.pushMatrix();
        ui.translate(x, y);

        ui.line(-unit, 0, unit, 0); // horizontal
        ui.line(0, unit, 0, -unit); // vertical
        ui.line(-unit/6*4, unit/6*4, unit/6*4, -unit/6*4 ); // slash downwards
        ui.line(-unit/6*4, -unit/6*4, unit/6*4, unit/6*4 ); // slash upwards
        
        ui.popMatrix();
    }

    public void update()
    {
        x = (float) (x + (Math.random() * (1) + 1));
        x = (float) (x - (Math.random() * (1) + 1));
        y = (float) (y + (Math.random() * (1) + 1));
        y = (float) (y - (Math.random() * (1) + 1));
    }

}