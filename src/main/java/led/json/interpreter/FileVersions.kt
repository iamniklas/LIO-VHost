package led.json.interpreter

enum class FileVersions {
    V0_0_0,  //Full Json without any optimization Single LED State: 		{ r: 255, g: 255, b: 255 } 				(5 lines of text)
    V0_0_1,  //Optimized Json with a single line of text for one frame 	255-255-255 255-255-255 255-255-255 	(3 white LEDs) 
    V0_0_2 //Optimized Json with repetition detection 					R 3 255-255-255 | R 2 255-0-0 			(1x 3 white LEDs and 1x 2 red LEDs)
}