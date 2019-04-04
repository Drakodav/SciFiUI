package ie.tudublin;

import java.util.Arrays;

import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

public class AudioWave
{
    AudioInput song;
    FFT fft;
    UI ui;
    // AudioPlayer song;
    Minim minim;
    BeatDetect beat;

    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public static final int BITS_PER_SAMPLE = 16;
    private float rdDiameter;

	public AudioWave(UI ui)
    {
        this.ui = ui;
        minim = new Minim(ui);
        song = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        // song = minim.loadFile(ui.dataPath("n2.mp3"), 1024);
        // song.loop();
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
        radarBeat();
        radarBeatLeft();
        
        // float middle = ui.height/6*5;
        
        fft.forward(song.mix);
        
        float gap = ui.width / (float) bands.length;
        ui.noStroke();
        float colorGap = 255 / (float) bands.length;
        for(int i = 0 ; i < bands.length ; i ++)
        {
            ui.fill(i * colorGap, 255, 255);
            ui.rect(i * gap, ui.height, gap, -lerpedBands[i]*5); 
        }

        // for(int i = 0; i < fft.specSize() - 1; i++)
        // {
        //     // map the color to the color range
        //     ui.stroke(UI.map(i, 0, fft.specSize(), 0, 255), 255, 255);

        //     // map the line to the width of the screeen
        //     ui.line(  UI.map(i, 0, fft.specSize(), 0, ui.width)
        //             , middle
        //             , UI.map(i, 0, fft.specSize(), 0, ui.width)
        //             , middle + song.left.get(i) * middle/2);
        // }        
    }
    float[] bands;
    float[] lerpedBands;
    float[] bandAverages;
    public double threshold;

    public boolean getFrequencyBands(int i)
    {         
    // for (int i = 0; i < bands.length; i++)
    // {
            bandAverages[i] = lerpedBands[i];
            
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
           
            // if (lerpedBands[i] >= bandAverages[i] ) {
            //     bandAverages[i] = (bandAverages[i] + lerpedBands[i]) / 2 ;   
            // }else{
            //     bandAverages[i] = 0; //(bandAverages[i] - lerpedBands[i]) / 2 ;
            // }
            float check = 0;
            threshold = (double) Math.abs(lerpedBands[i] - bandAverages[i]);

            if ( (int)lerpedBands[i] <= (double)bandAverages[i] && threshold < 5.5) {
                check = 0;
                System.out.println( "lerp: " +  lerpedBands[i] + " &&  " + bandAverages[i] + " diff: " + threshold);                
                return false;
            }else { // array values are growing and bandAvg is less than lerpBands
                check = lerpedBands[i];
                System.out.println( "lerp: " +  lerpedBands[i] + " &&  " + bandAverages[i] + " diff: " + threshold);                
                return true;
            }
            
        
            
            // bandAverages[i] = lerpedBands[i];
        // }
    }
    
    public void radarBeatLeft()
    {
        if(getFrequencyBands(0)) {
            for (Star s : ui.sLeft) {
                s.update();
                s.render();
                
                s.maxIn();
            }
        }
        
        for (Star s : ui.sLeft) {
            s.update();
            s.render();
            s.gotoCircle();
        }
    }

    public void radarBeat()
    {
        if ( beat.isOnset()) 
        {
            ui.rd.diameter = rdDiameter + ui.rd.radius;
            for(int i= ui.stars.size() - 1 ;   i >= 0 ; i--)
            {
                Star s = ui.stars.get(i);
                s.update();
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
            if(ui.frameCount%50==0 && beat.isOnset()) s.gotoLine();
            // if(fft.getBand(2) > 0 && fft.getBand(2) < 1) s.gotoLine();
            s.gotoCircle();
        }
    }

    float log2(float f) 
    {
        return (float) (Math.log(f) / Math.log(2.0f));
    }

}
