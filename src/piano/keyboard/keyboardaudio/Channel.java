package piano.keyboard.keyboardaudio;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Channel {

    private MidiChannel midiChannel;

    public Channel() throws MidiUnavailableException {
        // setting up sound
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        midiChannel = synth.getChannels()[SoundSettings.CHANNEL];
    }

    public MidiChannel getChannel()
    {
        return midiChannel;
    }
}
