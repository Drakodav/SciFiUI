package ie.tudublin;

import processing.core.PVector;

public class Star extends ArtForms
{
    public PVector v1;
    public PVector v2;
    
    private float x;
    private float y;
    private float dx = 1;
    private float dy = 1;
    private float width;
    private float height;
    private float speed;
    private float rdX;
    private float rdY;
    private float rdRadius;

    public Star(UI ui, float unit)
    {
        super(ui, unit);

        width = ui.width;
        height = ui.height;
        
        // set star location to spawn in a random location
        x = (float) (Math.random() * (ui.width - 0) + 1);
        y = (float) (Math.random() * (ui.height - 0)+ 1);
        
        v1 = new PVector(x,y);
        v2 = new PVector(0,0);

        rdX = ui.rd.x;
        rdY = ui.rd.y;
        rdRadius = ui.rd.radius;
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
        keyMovement();
        
        speed = (float) (Math.random() * (30) + 1);
        // speed = 10;

        // if ( (x > width || x < 0) || (y > height || y < 0)) {
        //     // gotoCircle();gotoCircle();gotoCircle();gotoCircle();gotoCircle();
        //     dx *= -1;
        //     dy *= -1;            
        // }
        if (x <= 0 || x >= width)
        {
            dx *= -1;
        }
        if (y <= 0 || y >= height)
        {
            dy *= -1;            
        }
    }

    public void keyMovement()
    {
        if (ui.checkKey('l')) gotoLine();
        if (ui.checkKey('k')) gotoCircle();
        if (ui.checkKey('z')) twistLeft();
        if (ui.checkKey('x')) twistRight();
        if (ui.checkKey('a')) maxIn();
        if (ui.checkKey('s')) maxOut();
    }

    public void randomMovement()
    {
        x = (float) (x + (Math.random() * (dx) + -dx));
        x = (float) (x - (Math.random() * (dx) + -dx));
        y = (float) (y + (Math.random() * (dx) + -dx));
        y = (float) (y - (Math.random() * (dx) + -dx));
    }
    
    public void gotoLine()
    {
        // int i = ui.stars.indexOf(this);
        // Star s = ui.stars.get(i);
        // s.x = (float) (ui.map(i, 0, ui.stars.size(), 0, width));
        // s.y = height/2;

        int i = ui.stars.indexOf(this);
        v2 = new PVector( ((float) (UI.map(i, 0, ui.stars.size(), 0, width))) , (height/2) );
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }
    // code below makes an eye shape
    // v2 = new PVector( (rdX + (rdRadius * (float) Math.cos(i))), (rdY + (rdRadius * (float) Math.sin(i))) ) ;
    public void gotoCircle()
    {
        // int i = ui.stars.indexOf(this);
        // Star s = ui.stars.get(i);
        // s.x = ui.rd.x + (ui.rd.radius * (float) Math.sin(i));
        // s.y = ui.rd.y + (ui.rd.radius * (float) Math.cos(i));
        
        rdX = ui.rd.x;
        rdY = ui.rd.y;
        rdRadius = ui.rd.radius;
        
        int i = ui.stars.indexOf(this);
        // Star s = ui.stars.get(i);
        v2 = new PVector( (rdX + (rdRadius * (float) Math.sin(i))), (rdY + (rdRadius * (float) Math.cos(i))) ) ;
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }

    public void twistLeft()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x += (float) (Math.cos(i)) *4 *dx;
        s.y += (float) (Math.sin(i)) *4 *dy;  
    }

    public void  twistRight()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x -= (float) (Math.cos(i)) *4*dx;
        s.y -= (float) (Math.sin(i)) *4*dy;
    }

    public void maxIn()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x -= (float) (( Math.cos(i*width)*8) %Math.cos(width)) *8 *dx;
        s.y -= (float) (( Math.sin(i*height)*8) %Math.sin(height)) *8 *dy;
    }

    public void maxOut()
    {
        int i = ui.stars.indexOf(this);
        Star s = ui.stars.get(i);
        s.x += (float) (( Math.cos(i*width)*8) *dx );//%Math.cos(width);
        s.y += (float) (( Math.sin(i*height)*8) *dy);//Math.sin(height);
    }

}
