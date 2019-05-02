package ie.tudublin;

import java.util.Arrays;

// import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

public class AudioWave
{
    // AudioInput song;
    FFT fft;
    UI ui;
    AudioPlayer song;
    Minim minim;
    BeatDetect beat;

    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public static final int BITS_PER_SAMPLE = 16;
    private float rdDiameter;
    public float[] bands, lerpedBands, bandAverages;

	public AudioWave(UI ui)
    {
        this.ui = ui;
        minim = new Minim(ui);
        // song = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        song = minim.loadFile(ui.dataPath("n1.mp3"), 1024);
        song.loop();
        beat = new BeatDetect();
        // fft = new FFT(song.bufferSize(), song.sampleRate());   

        rdDiameter = ui.rd.diameter;

        fft = new FFT(FRAME_SIZE, SAMPLE_RATE);
        bands = new float[(int) log2(FRAME_SIZE)];
        lerpedBands = new float[bands.length];
        bandAverages = new float[bands.length];
        Arrays.fill(bandAverages, 0.0f);
    }
    
    public void update()
    {
        ui.stroke(255);
        beat.detect(song.mix);
        getBandValues();
        radarBeat();
        radarBeatLeft();
        radarBeatRight();
        backgroundBeat();
        
        
        fft.forward(song.mix);
        
        //display band bars
        // float gap = ui.width / (float) bands.length;
        // ui.noStroke();
        // float colorGap = 255 / (float) bands.length;
        // for(int i = 0 ; i < bands.length ; i ++)
        // {
        //     ui.fill(i * colorGap, 255, 255);
        //     ui.rect(i * gap, ui.height, gap, -lerpedBands[i]/2); 
        // }
              
    }

    public void radarBeatRight()
    {
        if(getFrequencyBands(7) ) {
            for (Star s : ui.sRight) {
                s.update();
                s.render();
                s.gotoCircleInverse();
                s.gotoCircleInverse();
            }
        }
        
        if(!getFrequencyBands(7) ) {
            for(int i= ui.sRight.size() - 1 ;   i >= 0 ; i--) {
                Star s = ui.sRight.get(i);
                s.update();
                s.render();
                if (i%2 == 0) s.twistRight();
                if (i%2 == 1) s.twistLeft();
                s.gotoCircleInverse();       
            }
        }
    }

    public void radarBeatLeft()
    {       
        if(getFrequencyBands(2) ) {
            for (Star s : ui.sLeft) {
                s.update();
                s.render();
                s.gotoCircleInverse();
                s.gotoCircleInverse();
            }
        }
        
        if(!getFrequencyBands(2) ) {
            for(int i= ui.sLeft.size() - 1 ;   i >= 0 ; i--) {
                Star s = ui.sLeft.get(i);
                s.update();
                s.render();
                if (i%2 == 0) s.twistRight();
                if (i%2 == 1) s.twistLeft();
                s.gotoCircleInverse();       
            }
        }
    }

    public void radarBeat()
    {
        if ( beat.isOnset()) 
        {
            ui.rd.diameter = rdDiameter + ui.rd.radius/2;
            for(int i= ui.stars.size() - 1 ;   i >= 0 ; i--)
            {
                Star s = ui.stars.get(i);
                s.update();
                // if(i%2==1) s.maxIn();
                // if(i%2==0) s.maxOut();
                if(i%2==1) s.twistRight();
                if(i%2==0) s.twistLeft();
                s.maxOut();
            }
        }
        if ( !beat.isOnset()) 
        {
            for(int i= ui.stars.size() - 1 ;   i > 0 ; i--)
            {
                Star s = ui.stars.get(i);
                s.update();
                if(i%2==0) s.twistRight();
                if(i%2==1) s.twistLeft();
              
            }
            ui.rd.diameter = rdDiameter;
        }
         
        for (Star s : ui.stars) {
            s.update();
            s.render();
            if(getFrequencyBands(0) && beat.isOnset() && getFrequencyBands(1)) s.maxOut();
            s.gotoCircle();
        }
    }

    private int bar = 2;
    private int count = bar;
    private int mov = 0;
    private float time;
    private boolean gate = false;
    
    public void backgroundBeat(){
        if (gate == false) {
            time = ui.millis();
        }

        if (ui.tmp.isFinished()) {
            if ( count > 0 ){
                
                if ( ui.millis() >= (int)(time + (ui.tmp.average *4)) ){ // checks if a tempo beat happens
                    // System.out.println("yeah>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    count--;
                    // System.out.println("c : " +count);
                    gate = false;
                }else{
                    // System.out.println( time + (ui.tmp.average *4) +" :   " + ui.millis());
                    gate = true;
                }
            }

            if (count == 0 && beat.isOnset()) {
                count = bar;
                mov = (int) ui.random(0, 7);
                // System.out.println(mov);
            }
            
            for (Star s : ui.sBackground) {
                s.update();
                s.render();
                s.controlBackground(mov);
            }
        }
    }

    // return a log2 of a number
    float log2(float f) 
    {
        return (float) (Math.log(f) / Math.log(2.0f));
    }

    public boolean getFrequencyBands(int b)
    {         
        // tracking algorithm for beat detection
        if ( (double)lerpedBands[b] <= (double)bandAverages[b] ) {
            // System.out.println( "lerp: " +  lerpedBands[i] + " &&  " + bandAverages[i]);                
            return false;
        }else { // array values are growing and bandAvg is less than lerpBands
            // System.out.println( "lerp: " +  lerpedBands[i] + " &&  " + bandAverages[i] + " True");                
            return true;
        }
    }

    public void getBandValues()
    {
        for (int i = 0; i < bands.length; i++)
        {
            bandAverages[i] = lerpedBands[i]; // set bandAvg to previous value of lerpbands
            
            int start = (int)Math.pow(2, i) - 1;
            int w = (int)Math.pow(2, i);
            int end = start + w;
            float average = 0;
            for (int j = start; j < end; j++)
            {
                average += fft.getBand(j) * (j + 1);
            }
            average /= (float) w;
            bands[i] = average * 5.0f;
            lerpedBands[i] = UI.lerp(lerpedBands[i], bands[i], 0.05f);
        }
    }

}
