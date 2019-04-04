package ie.tudublin;

import processing.core.PVector;

public class Star extends ArtForms
{
    public PVector v1;
    public PVector v2;
    
    public float x;
    public float y;
    private float dx = 1;
    private float dy = 1;
    private float width;
    private float height;
    private float speed;
    private float rdX;
    private float rdY;
    private float rdRadius;
    private String name;
    private int i;
    private Star s;
    private int color;

    public Star(UI ui, float unit, String name)
    {
        super(ui, unit);

        width = ui.width;
        height = ui.height;
        this.name = name;
 
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
        
        ui.stroke(color, 100, 250);
        

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
        nameCheck();
        randomMovement();
        
        speed = (float) (Math.random() * (40) + 1);
        // speed = 15;

        // keep star onscreen
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
        if (ui.checkKey('j')) gotoCircleInverse();
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
        // s.x = (float) (ui.map(i, 0, ui.stars.size(), 0, width));
        // s.y = height/2;

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
        v2 = new PVector( (rdX + (rdRadius * (float) Math.sin(i))), (rdY + (rdRadius * (float) Math.cos(i))) ) ;
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }

    public void twistLeft()
    {
        s.x += (float) (Math.cos(i)) *4 *dx;
        s.y += (float) (Math.sin(i)) *4 *dy;  
    }

    public void  twistRight()
    {
        s.x -= (float) (Math.cos(i)) *4*dx;
        s.y -= (float) (Math.sin(i)) *4*dy;
    }

    public void maxIn()
    {
        s.x -= (float) (( Math.cos(i*width)*8) %Math.cos(width)) *8 *dx;
        s.y -= (float) (( Math.sin(i*height)*8) %Math.sin(height)) *8 *dy;
    }

    public void maxOut()
    {
        s.x += (float) (( Math.cos(i*width)*8) *dx );//%Math.cos(width);
        s.y += (float) (( Math.sin(i*height)*8) *dy);//Math.sin(height);
    }

    public void gotoCircleInverse()
    {           
        v2 = new PVector( (rdX + (rdRadius * (float) Math.cos(i))), (rdY + (rdRadius * (float) Math.sin(i))) ) ;
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }

    public void gotoRectangle()
    {
        // v2 = new PVector( ((float) (UI.map(i, 0, ui.stars.size(), 0, width))) , (height/2) );
        // v1 = new PVector(x, y);
        // v2.sub(v1);
        // x += v2.x/speed;
        // y += v2.y/speed;

        
        // make an animation function and keep it in the update for smooth movement 
        // add flags to set on and off
        

    }

    public void nameCheck()
    {
        if(name == "stars") 
        {
            color = ui.frameCount*i%255;
            // keyMovement();
            i = ui.stars.indexOf(this);
            s = ui.stars.get(i);
            rdX = ui.rd.x;
            rdY = ui.rd.y;
            rdRadius = ui.rd.radius;
        }
        if(name == "sLeft") 
        {
            color = ui.frameCount*i%255;
            // keyMovement();
            i = ui.sLeft.indexOf(this);
            s = ui.sLeft.get(i);
            rdX = ui.rdLeft.x;
            rdY = ui.rdLeft.y;
            rdRadius = ui.rdLeft.radius;
        }
        if(name == "sRight") 
        {
            color = ui.frameCount*i%255;
            // keyMovement();
            i = ui.sRight.indexOf(this);
            s = ui.sRight.get(i);
            rdX = ui.rdRight.x;
            rdY = ui.rdRight.y;
            rdRadius = ui.rdRight.radius;
        }
        if(name == " ")
        {
            color = ui.frameCount%255;
            i = ui.sBackground.indexOf(this);
            s = ui.sBackground.get(i);
            rdX = width/2;
            rdY = height/2;
            rdRadius = ui.rd.radius/3;
            keyMovement();
            if (ui.checkKey('q')) {
                s.gotoRectangle();
            }
        }
    }

}
