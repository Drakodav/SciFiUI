package ie.tudublin;

import java.util.Arrays;

public class Tempo
{
    UI ui;

    private float[] values;
    public float p1, p2, average, tempo, time;
    private Boolean finished, s1, s2;
    private int count, tap;
    
    public Tempo(UI ui){
        this.ui = ui;

        finished = false;
        average = 0;
        s1 = false; s2 = false;
        p1 = 0; p2 = 0;
        count = 0;
        tempo = 0;
        tap = 4;

        values = new float[tap];
        Arrays.fill(values, 0);
    }

    public void render(){
        ui.textSize(22);
        if (!isFinished()) {
            ui.fill(0, 102, 153);
            ui.text("tap 'space-bar' 8 times on the beat to the identify the tempo : " + count, 10, 30);
        }else{
            ui.text("Press r to re-enter tempo", 10, 30);
        }
        ui.fill(0, 102, 153);
            if (isFinished()) {
                ui.text("bpm : " + tempo, 10, 60);
            }
    }

    
    public void update(){
        time = ui.millis();

        if (count < tap && !isFinished()) {
            getTempo();
        }
        if (count == tap){
            average = 0;
            for (int i = 0; i < values.length; i++) {
                average += values[i];
                // System.out.println(values[i]);
            }
            average = average / values.length;
            tempo = average;
            tempo = 60/(tempo/1000);
            // System.out.println("tempo : " + tempo);
            finished = true;
            count = 0;
        }
        if (ui.checkKey('r')) {
            count = 0;
            finished = false;
        }

    }

    public void getTempo(){
        
        if (ui.checkKey(' ') && s1 == false) {
            p1 = ui.millis();
            ui.keys[' '] = false; // reset the space pressed
            s1 = true;
            return;
        }
        if (ui.checkKey(' ') && s2 == false && s1 == true) {
            p2 = ui.millis();
            ui.keys[' '] = false; // reset the space pressed            
            s2 = true;
        }
        if (s1 == true && s2 == true) {
            average = p2-p1;
            values[count] = average;
            count++;
            System.out.println(p1 + " : " + p2 + " -> " + (p2-p1));
            s1 = false; s2 = false;
            p1 = 0; p2 = 0;
        }
    }

    public Boolean isFinished(){
        if (finished == true) return true;
        else return false;
    } 
}