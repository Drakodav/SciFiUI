package ie.tudublin;

public class Stars
{
    UI ui;
    private float unit;
    private float x;
    private float y;
    private float jitter;

    public Stars(UI ui, float unit)
    {
        this.ui = ui;
        this.unit = unit/24; // 1 size unit

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
        if (x>ui.width/2 && y<ui.height/2) {
            ui.rotate((float)Math.cos(jitter));
        }else{
            ui.rotate((float)Math.sin(jitter));
        }

        ui.line(0, 0, +unit , 0); // horizontal
        ui.line(+unit/2, -unit/2, +unit/2, +unit/2); // vertical
        ui.line(+unit/6, -unit/3, +unit-unit/6, +unit/3); // slash downwards
        ui.line(+unit/6, +unit/3, +unit-unit/6, -unit/3); // slash upwards
        
        ui.popMatrix();
    }

    public void update()
    {
        jitter += 0.02f;
    }

}