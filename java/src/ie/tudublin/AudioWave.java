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
    public float fr;
    private float height;
    private float rdDiameter;

	public AudioWave(UI ui)
    {
        this.ui = ui;
        // this.minim = minim;
        minim = new Minim(ui);
        // ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        song = minim.loadFile(ui.dataPath("n2.mp3"), 2048);
        song.loop();
        beat = new BeatDetect();
        fft = new FFT(song.bufferSize(), song.sampleRate());       

        height = ui.height;
        
        rdDiameter = ui.rd.diameter;
        
    }
    
    public void update()
    {
        // ui.stroke(255);
        radarBeat();
        radarBeatLeft();

        float middle = height / 5 * 4;

        fft.forward(song.left); // need in order for get band to work

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

    public void radarBeatLeft()
    {
        for (Star s : ui.sLeft) {
            s.update();
            s.render();
            // if(ui.frameCount%50==0 && beat.isOnset()) s.gotoLine();
            if(fft.getBand(2) > 100 && fft.getBand(2) < 150) s.gotoLine();
            if(fft.getBand(2) > 200 && fft.getBand(2) < 300)s.gotoCircleInverse();
            s.gotoCircle();
        }
    }

    public void radarBeat()
    {
        beat.detect(song.mix);
        if ( beat.isOnset()) 
        {
            ui.rd.diameter = rdDiameter + ui.rd.radius;
            for(int i= ui.stars.size() - 1 ;   i >= 0 ;    i--)
            {
                Star s = ui.stars.get(i);
                s.maxOut();
            }
        }
        if ( !beat.isOnset()) 
        {
            for(int i= ui.stars.size() - 1 ;   i > 0 ;    i--)
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
            // if(ui.frameCount%50==0 && beat.isOnset()) s.gotoLine();
            if(fft.getBand(2) > 0 && fft.getBand(2) < 1) s.gotoLine();
            s.gotoCircle();
        }
    }

}
