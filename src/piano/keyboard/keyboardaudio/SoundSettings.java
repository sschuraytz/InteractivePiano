package piano.keyboard.keyboardaudio;

public interface SoundSettings
{
	int CHANNEL = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	int VOLUME = 80; // between 0 to 127
	int DURATION = 1000; // in milliseconds
}
