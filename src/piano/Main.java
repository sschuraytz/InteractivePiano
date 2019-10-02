package piano;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiChannel midiChannel = new Channel().getChannel();
            Recorder recorder = new Recorder();
            PianoGUI frame = new PianoGUI(midiChannel, recorder);
            frame.setVisible(true);
            new Intro().playIntro(midiChannel);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
