package piano;

import javax.sound.midi.MidiChannel;

public class SoundSettings 
{
	private final int CHANNEL = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private final int VOLUME = 80; // between 0 et 127
	private final int DURATION = 1000; // in milliseconds
	private MidiChannel[] channels;
	
	public SoundSettings(MidiChannel[] channels) {
		this.channels = channels;
	}

	public int getCHANNEL() {
		return CHANNEL;
	}

	public int getVOLUME() {
		return VOLUME;
	}

	public int getDURATION() {
		return DURATION;
	}

	public MidiChannel[] getChannels() {
		return channels;
	}
	
	
	
	
	
}
