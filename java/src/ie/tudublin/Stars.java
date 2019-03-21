package ie.tudublin;

public class Stars
{
    UI ui;
    private float unit;
    private float x;
    private float y;

    public Stars(UI ui, float unit)
    {
        this.ui = ui;
        this.unit = unit/20; // 1 size unit

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
        ui.line(x, y, x+unit , y); // horizontal
        ui.line(x+unit/2, y-unit/2, x+unit/2, y+unit/2); // vertical
        ui.line(x+unit/6, y-unit/3, x+unit-unit/6, y+unit/3); // slash downwards
        ui.line(x+unit/6, y+unit/3, x+unit-unit/6, y-unit/3); // slash upwards
        
    }

    public void update()
    {
        

    }

}