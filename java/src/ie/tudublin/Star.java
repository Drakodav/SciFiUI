package ie.tudublin;

import processing.core.PVector;

public class Star extends Stars
{
    public float x;
    public float y;
    public PVector v1;
    public PVector v2;
    
    private float width;
    private float height;
    private float speed;

    public Star(UI ui, float unit)
    {
        super(ui, unit);

        width = ui.width;
        height = ui.height;
        v1 = new PVector(0,0);
        v2 = new PVector(0,0);

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
        randomMovement();
        if (ui.checkKey('l')) gotoLine();
        if (ui.checkKey(' ')) gotoCircle();
        if (ui.checkKey('z')) twistLeft();
        if (ui.checkKey('x')) twistRight();
        if (ui.checkKey('a')) maxIn();
        if (ui.checkKey('s')) maxOut();

        // speed = (float) (Math.random() * (30) + 1);
        speed = 10;
    }

    public void randomMovement()
    {
        x = (float) (x + (Math.random() * (1) + 1));
        x = (float) (x - (Math.random() * (1) + 1));
        y = (float) (y + (Math.random() * (1) + 1));
        y = (float) (y - (Math.random() * (1) + 1));
    }
    
    public void gotoLine()
    {
        // int i = ui.stars.indexOf(this);
        // Star s = ui.stars.get(i);
        // s.x = (float) (ui.map(i, 0, ui.stars.size(), 0, width));
        // s.y = height/2;

        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        v2 = new PVector( ((float) (UI.map(i, 0, ui.stars.size(), 0, width))) , (height/2) );
        v1 = new PVector(x, y);
        v2.sub(v1);
        s.x += v2.x/speed;
        s.y += v2.y/speed;
    }

    public void gotoCircle()
    {
        // int i = ui.stars.indexOf(this);
        // Star s = ui.stars.get(i);
        // s.x = ui.rd.x + (ui.rd.radius * (float) Math.sin(i));
        // s.y = ui.rd.y + (ui.rd.radius * (float) Math.cos(i));

        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        v2 = new PVector( (ui.rd.x + (ui.rd.radius * (float) Math.sin(i))), (ui.rd.y + (ui.rd.radius * (float) Math.cos(i))) ) ;
        v1 = new PVector(x, y);
        v2.sub(v1);
        s.x += v2.x/speed;
        s.y += v2.y/speed;

    }

    public void twistLeft()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x += (float) (Math.cos(i)) *4;
        s.y += (float) (Math.sin(i)) *4;  
    }

    public void  twistRight()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x -= (float) (Math.cos(i)) *4;
        s.y -= (float) (Math.sin(i)) *4;
    }

    public void maxIn()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x -= (float) (( Math.cos(i*width)*8) %Math.cos(width)) *8;
        s.y -= (float) (( Math.sin(i*height)*8) %Math.sin(height)) *8;
    }

    public void maxOut()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x += (float) (( Math.cos(i*width)*8) );//%Math.cos(width);
        s.y += (float) (( Math.sin(i*height)*8) );//Math.sin(height);
    }

}
