package piano;

public class Key
{
	private int pitch;
	private PianoGUI gui;

	public Key(int position, PianoGUI gui)
	{
		pitch = position + Notes.startingC;
		this.gui = gui;
	}

	public void play() {
		// change background for as as long as note plays
		SoundThread s = new SoundThread(pitch, gui.getChannel()); // pass in pitch to play
		s.start();
	}
}
