# SciFi UI Project

Name: Vlad-Anton Medves

Student Number: c17719031

Fork this repository and use it a starter project for your assignment

# Description of the assignment
This is an automatic Music visualizer which uses inspiring visuals
using math. My aim for this was to create something visually stunning and 
entertaining at the same time. 

# Instructions
simply tap the spacebar to the beat for automatic visuals.
press r to reset the tempo count
key : 	a, s
		z, x, c
		j, k, l
	manual controls for backgroung stars

# How it works
A thousands of stars are created which all have a constant random movement.
They align themselves up to a radar and from there proceed to move in rythm
with the beat. Everything is done so that it can scale to any canvas size.

The music is played from a data set.
FFT is performed on the song and from there it is split up into multiple bands, 
these can then be tracked individually and depending on if the overall band value is coming
to a rise and a drop it will detect it as a beat.
Furthermore tempo is obtained by getting an average of 4 spacebar taps to the beat
and integrated by calculating the small time difference between the expected beat time and the actual time 
within the program.

The stars have a nameCheck on them in order to assign them to different radars and give them their correct qualities.
Their stunning movements are a reflection of how particle sytems work. For my usage I want gradual movement instead of 
instantly appearing in a location. This is done by subtracting the current position from the final position and adding it
to the current position.

```Java
public void gotoCircle()
    {                
        v2 = new PVector( (rdX + (rdRadius * (float) Math.sin(i))), (rdY + (rdRadius * (float) Math.cos(i))) ) ;
        v1 = new PVector(x, y);
        v2.sub(v1);
        x += v2.x/speed;
        y += v2.y/speed;
    }
```


# What I am most proud of in the assignment
I am most proud of how I handled the music beat detection, although quite simple when looking back, 
the instructions weren't really clear on how to go about it, online resources were hard to find.
Second are the movements of the stars, it really amazes me how simple maths can create such unique 
and astonishing patterns.


# Markdown Tutorial

Youtube video:

[![YouTube](https://img.youtube.com/vi/hwPyoFMUxs0/0.jpg)](https://youtu.be/hwPyoFMUxs0)

