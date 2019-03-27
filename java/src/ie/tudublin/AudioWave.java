package ie.tudublin;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

public class AudioWave
{
    AudioInput ai;
    FFT fft;
    UI ui;
    AudioPlayer song;
    Minim minim;
    BeatDetect beat;

    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public static final int BITS_PER_SAMPLE = 16;
    // private float width;
    // private float height;

	public AudioWave(UI ui)
    {
        this.ui = ui;
        // this.minim = minim;
        minim = new Minim(ui);
        // ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        // fft = new FFT(FRAME_SIZE, SAMPLE_RATE);        
        song = minim.loadFile(ui.dataPath("n2.mp3"), 2048);
        song.play();
        beat = new BeatDetect();

        // width = ui.width;
        // height = ui.height;
        
        
    }
    
    public void update()
    {
        ui.stroke(255);
        // float middle = height / 2;


        // for(int i = 0; i < song.bufferSize() - 1; i++)
        // {
        //     // map the color to the color range
        //     ui.stroke(ui.map(i, 0, song.bufferSize(), 0, 255), 255, 255);

        //     // map the line to the width of the screeen
        //     ui.line(  ui.map(i, 0, song.bufferSize(), 0, ui.width)
        //             , middle
        //             , ui.map(i, 0, song.bufferSize(), 0, ui.width)
        //             , middle + song.left.get(i) * middle);
        // }

        
        beat.detect(song.right);
        if ( beat.isOnset()) 
        {
            for(int i= ui.stars.size() - 1 ;   i >= 0 ;    i--)
            {
                Star s = ui.stars.get(i);
                s.gotoCircle();
            }
        }
        if ( !beat.isOnset()) 
        {
            for(int i= ui.stars.size() - 1 ;   i >= 0 ;    i--)
            {
                Star s = ui.stars.get(i);
                if(i%2==0) s.twistRight();
                if(i%2==1) s.twistLeft();
                // else s.maxOut();
            }
        }

        





        // for(int i = 0 ; i < ai.bufferSize(); i ++)
        // {
        //     // map the color to the color range
        //     ui.stroke(ui.map(i, 0, ai.bufferSize(), 0, 255), 255, 255);

        //     // map the line to the width of the screeen
        //     ui.line( ui.map(i, 0, ai.bufferSize(), 0, ui.width)
        //             , middle
        //             , ui.map(i, 0, ai.bufferSize(), 0, ui.width)
        //             , middle + ai.left.get(i) * middle);
        // }
        // fft.forward(ai.left);

        // for(int i = 0 ; i < fft.specSize() ; i ++)
        // {
        //     ui.stroke(ui.map(i, 0, ai.bufferSize(), 0, 255), 255, 255);            
        //     ui.ellipse(i, middle/2, i, fft.getBand(i) * 20);
        // }
    }

}