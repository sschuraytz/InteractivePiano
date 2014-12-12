package piano;

public class SoundThread extends Thread {

	private int pitch;
	private SoundSettings settings;

	public SoundThread(int pitch, SoundSettings soundSettings) {
		this.pitch = pitch;
		settings = soundSettings;
	}

	@Override
	public void run() {
		super.run();
		settings.getChannels()[settings.getCHANNEL()].noteOn( pitch, settings.getVOLUME() );
		try {
			Thread.sleep( settings.getDURATION() );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		settings.getChannels()[settings.getCHANNEL()].noteOff( pitch );
	}

}
