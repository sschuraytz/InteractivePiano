package miriamTest;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

// The below table gives a few values for the "MIDI note number".
// # means sharp, b means flat
//
// When two notes have consecutive "MIDI note numbers",
// they are separated by a semi-tone (i.e. they are next to each other on a piano keyboard)
// and the ratio of their frequencies is the 12th root of 2.0.
// Two notes separated by an octave (8 white keys on a piano keyboard, or 12 semi-tones)
// have frequencies whose ratio is equal to 2.0.
//
//
// MIDI_note_number    Name in music    Notes
// ----------------    -------------    --------------------------
//  21                 A0               lowest note on an 88-key piano
//   .                 .                .
//   .                 .                .
//   .                 .                .
//  57                 A3               has a frequency of 220 Hertz
//  58                 A3# = B3b
//  59                 B3
//  60                 C4               "middle C"; start of 4th octave
//  61                 C4# = D4b
//  62                 D4
//  63                 D4# = E4b
//  64                 E4
//  65                 F4
//  66                 F4# = G4b
//  67                 G4
//  68                 G4# = A4b
//  69                 A4               "concert A"; has a frequency of 440 Hertz
//  70                 A4# = B4b
//  71                 B4
//  72                 C5               "concert C"; start of 5th octave
//  73                 C5# = D5b
//   .                 .                .
//   .                 .                .
//   .                 .                .
// 108                 C8               highest note on an 88-key piano

public class SynthesizerTest2 {

	public static void main( String[] args ) {

		int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

		int volume = 80; // between 0 et 127
		int duration = 200; // in milliseconds

		int[] notes = new int[ 13 ];

		boolean playMajorScale = true;
		boolean playMinorScale = true;
		boolean playChromaticScale = true;

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();

			// --------------------------------------
			// Play a few notes.
			// The two arguments to the noteOn() method are:
			// "MIDI note number" (pitch of the note),
			// and "velocity" (i.e., volume, or intensity).
			// Each of these arguments is between 0 and 127.
			channels[channel].noteOn( 60, volume ); // C note
			Thread.sleep( duration );
			channels[channel].noteOff( 60 );
			channels[channel].noteOn( 62, volume ); // D note
			Thread.sleep( duration );
			channels[channel].noteOff( 62 );
			channels[channel].noteOn( 64, volume ); // E note
			Thread.sleep( duration );
			channels[channel].noteOff( 64 );

			Thread.sleep( 500 );

			// --------------------------------------
			if ( playMajorScale ) {
				// Play a major C scale.
				notes[0] = 60; // C
				notes[1] = 62; // D
				notes[2] = 64; // E
				notes[3] = 65; // F
				notes[4] = 67; // G
				notes[5] = 69; // A
				notes[6] = 71; // B
				notes[7] = 72; // C

				for ( int i = 0; i <= 7; ++i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}
				for ( int i = 7; i >= 0; --i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}

				Thread.sleep( 500 );
			}

			// --------------------------------------
			if ( playMinorScale ) {
				// Play a minor C scale.
				notes[0] = 60; // C
				notes[1] = 62; // D
				notes[2] = 63; // D#
				notes[3] = 65; // F
				notes[4] = 67; // G
				notes[5] = 68; // G#
				notes[6] = 70; // A#
				notes[7] = 72; // C

				for ( int i = 0; i <= 7; ++i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}
				for ( int i = 7; i >= 0; --i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}

				Thread.sleep( 500 );
			}

			// --------------------------------------
			if ( playChromaticScale ) {
				// Play a chromatic C scale.
				notes[ 0] = 60; // C
				notes[ 1] = 61; // C#
				notes[ 2] = 62; // D
				notes[ 3] = 63; // D#
				notes[ 4] = 64; // E
				notes[ 5] = 65; // F
				notes[ 6] = 66; // F#
				notes[ 7] = 67; // G
				notes[ 8] = 68; // G#
				notes[ 9] = 69; // A
				notes[10] = 70; // A#
				notes[11] = 71; // B
				notes[12] = 72; // C

				for ( int i = 0; i <= 12; ++i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}
				for ( int i = 12; i >= 0; --i ) {
					channels[channel].noteOn( notes[i], volume );
					Thread.sleep( duration );
					channels[channel].noteOff( notes[i] );
				}

				Thread.sleep( 500 );
			}

			// --------------------------------------
			// Play a C major chord.
			channels[channel].noteOn( 60, volume ); // C
			channels[channel].noteOn( 64, volume ); // E
			channels[channel].noteOn( 67, volume ); // G
			Thread.sleep( 3000 );
			channels[channel].allNotesOff();
			Thread.sleep( 500 );

			// --------------------------------------
			// Try some percussion.
			// To find a list of percussive instruments,
			// do a web search for "General Midi PERCUSSION Key Map",
			// and you might find
			//    http://computermusicresource.com/GM.Percussion.KeyMap.html
			channels[channel].allNotesOff();
			channel = 9; // percussion
			for ( int i = 35; i <= 81; ++i ) {
				channels[channel].noteOn( i, volume );
				Thread.sleep( duration );
				channels[channel].noteOff( i );
			}
			Thread.sleep( 500 );



			synth.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}



