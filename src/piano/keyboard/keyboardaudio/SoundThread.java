package piano.keyboard.keyboardaudio;

import javax.sound.midi.MidiChannel;

public class SoundThread extends Thread
{
	private int pitch;
	private MidiChannel channel;

	public SoundThread(int pitch, MidiChannel channel)
	{
		this.pitch = pitch;
		this.channel = channel;
	}

	@Override
	public void run()
	{
		super.run();
		channel.noteOn(pitch, SoundSettings.VOLUME);
		try
		{
			Thread.sleep(SoundSettings.DURATION);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		channel.noteOff(pitch);
	}
}
