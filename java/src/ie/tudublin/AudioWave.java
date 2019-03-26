package ie.tudublin;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;

public class AudioWave
{
    AudioInput ai;
    FFT fft;
    UI ui;
    AudioPlayer song;
    Minim minim;
    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public static final int BITS_PER_SAMPLE = 16;

	public AudioWave(UI ui)
    {
        this.ui = ui;
        // this.minim = minim;
        minim = new Minim(ui);
        // ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        // fft = new FFT(FRAME_SIZE, SAMPLE_RATE);        
        song = minim.loadFile(ui.dataPath("n1.mp3"));
        song.loop();
    }

    public void update()
    {
        ui.stroke(255);
        float middle = ui.height / 2;


        for(int i = 0; i < song.bufferSize() - 1; i++)
        {
            // map the color to the color range
            ui.stroke(ui.map(i, 0, song.bufferSize(), 0, 255), 255, 255);

            // map the line to the width of the screeen
            ui.line(  ui.map(i, 0, song.bufferSize(), 0, ui.width)
                    , middle
                    , ui.map(i, 0, song.bufferSize(), 0, ui.width)
                    , middle + song.left.get(i) * middle);
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