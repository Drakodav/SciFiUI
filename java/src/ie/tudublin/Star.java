package ie.tudublin;

import processing.core.PVector;

public class Star extends ArtForms
{
    public PVector v1, v2;
    public float x, y;
    public float rdX, rdY, rdRadius;

    private float dx = 1;
    private float dy = 1;
    private float width;
    private float height;
    private float speed;
    private String name;
    private int i;
    private Star s;
    private float color1, color2, color3;
    public AudioWave aw;

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

        aw = ui.aw;

    color1 = 0; color2 = 100; color3 = 250;
    }

    public void render()
    {
        ui.stroke(0);
        ui.noFill();
        
        ui.stroke(color1, color2, color3);
        

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
        
        speed =(float) (Math.random() * (40) + 1);
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
        if (ui.checkKey('c')) clover();
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
        v2 = new PVector( ((float) (UI.map(i, 0, ui.stars.size(), 0, width))) , (height/2) );
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }
    
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

    public void clover(){
        float circle;
        float freq = 5; 
        float sz = height/5;
        float gap = width/50;

        circle = (float) (sz + gap * Math.sin(ui.millis() * freq/1000000 * i));
        float r = UI.map(circle,sz,sz+gap,5,2);
        v2 = new PVector( width/2 + r + (float) (circle*Math.cos(i)) * 2, height/2 + r + (float) (circle*Math.sin(i)) * 2 );
        v1 = new PVector(x, y);

        color1 = UI.map(circle, sz, sz + gap, 100, 255);
        r = UI.map(circle,sz,sz+gap,150,225);
        color2 = r; color3 = r;
        // ui.fill( (UI.map(circle,sz,sz+gap,60,255)), r, r);
        v2.sub(v1);
        x += v2.x/speed;  
        y += v2.y/speed;
    }

    private int scl = 0;
    private boolean busy = false;
    public void controlBackground(){
        // take care of color
        if (aw.getFrequencyBands(0))
            scl = (scl + 1 )%200;

        color1 = (float) UI.map(scl + 25 * Math.sin(UI.second() * 5* i), 0, 255, 255, 0);

        // take care of movement
    }

    public void nameCheck()
    {
        if(name == "stars") 
        {
            color1 = ui.frameCount*i%255;
            // keyMovement();
            i = ui.stars.indexOf(this);
            s = ui.stars.get(i);
            rdX = ui.rd.x;
            rdY = ui.rd.y;
            rdRadius = ui.rd.radius;
        }
        if(name == "sLeft") 
        {
            color1 = ui.frameCount*i%255;
            // keyMovement();
            i = ui.sLeft.indexOf(this);
            s = ui.sLeft.get(i);
            rdX = ui.rdLeft.x;
            rdY = ui.rdLeft.y;
            rdRadius = ui.rdLeft.radius;
        }
        if(name == "sRight") 
        {
            color1 = ui.frameCount*i%255;
            // keyMovement();
            i = ui.sRight.indexOf(this);
            s = ui.sRight.get(i);
            rdX = ui.rdRight.x;
            rdY = ui.rdRight.y;
            rdRadius = ui.rdRight.radius;
        }
        if(name == " ")
        {
            i = ui.sBackground.indexOf(this);
            s = ui.sBackground.get(i);
            rdX = width/2;
            rdY = height/2;
            rdRadius = ui.rd.radius/3;
            keyMovement();
            controlBackground();
            if (ui.checkKey('q')) {
                System.out.println("q pressed");
            }
        }
    }

}