package piano;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;

public class ControlPanel extends JPanel {

    private MidiChannel channel;
    private PlaybackFunctionality storedSongs;
    private JComboBox<Object> songList;
    private HashMap<String, double[]> songMap = new HashMap<>();
    int startingNote = Notes.C;

    private HashMap<Integer, PianoLabel> notesKeyHashMap = new HashMap<>();

    public HashMap<Integer, PianoLabel> getNotesKeyHashMap() {
        return notesKeyHashMap;
    }

    public ControlPanel() {
        setBackground(Color.BLACK);
        setSize(KeyStats.FRAME_WIDTH, PianoGUI.INSTRUCTION_PANEL_HEIGHT);
        fillSongMap();
        //this is copied from PianoGUI. Shouldn't have it in both places, so remove after finish e/t else.
        Synthesizer synth = null;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();

        channel = synth.getChannels()[SoundSettings.CHANNEL];
        addSongDropDownMenu();
        addPlayButton();
        addTeachButton();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void fillSongMap() {
        songMap.put("Happy Birthday", new double[] {
                Notes.C, Notes.C, Notes.D, Notes.C, Notes.F, Notes.E,
                Notes.C, Notes.C, Notes.D, Notes.C, Notes.G, Notes.F,
                Notes.C, Notes.C, Notes.highC, Notes.A, Notes.F, Notes.E, Notes.D,
                Notes.Bflat, Notes.Bflat, Notes.A, Notes.F, Notes.G, Notes.F});
        songMap.put("Mary Had A Little Lamb", new double[] {
                Notes.E, Notes.D, Notes.C, Notes.D, Notes.E, Notes.E, Notes.E,
                Notes.D, Notes.D, Notes.D, Notes.E, Notes.E, Notes.E,
                Notes.E, Notes.D, Notes.C, Notes.D, Notes.E, Notes.E, Notes.E,
                Notes.E, Notes.D, Notes.D, Notes.E, Notes.D, Notes.C}
                );
        songMap.put("Twinkle Twinkle Little Star", new double[] {
                Notes.C, Notes.C, Notes.G, Notes.G, Notes.A, Notes.A, Notes.G,
                Notes.F, Notes.F, Notes.E, Notes.E, Notes.D, Notes.D, Notes.C,
                Notes.G, Notes.G, Notes.F, Notes.F, Notes.E, Notes.E, Notes.D,
                Notes.C, Notes.C, Notes.G, Notes.G, Notes.A, Notes.A, Notes.G,
                Notes.F, Notes.F, Notes.E, Notes.E, Notes.D, Notes.D, Notes.C
        });
    }
    public void addInstructions() {
        JLabel instructionLabel = new JLabel("Select a song from the drop down menu and listen to it played or play it yourself.");
        add(instructionLabel, BorderLayout.NORTH);
    }

    public void addSongDropDownMenu() {
        songList = new JComboBox<>(songMap.keySet().toArray());
        add(songList, BorderLayout.SOUTH);
    }

    public void addPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.setOpaque(true);
        playButton.setSize(100, 30);
        add(playButton);
        playButton.addActionListener(e -> {
            try {
                storedSongs = new PlaybackFunctionality(channel);
            } catch (MidiUnavailableException ex) {
                ex.printStackTrace();
            }
            storedSongs.playSong(songMap.get(songList.getSelectedItem()));
        });
    }

    public void addTeachButton() {
        JButton teachButton = new JButton("Teach Me");
        teachButton.setOpaque(true);
        teachButton.setSize(100, 30);
        add(teachButton);
        teachButton.addActionListener(e -> {
            try {
                storedSongs = new PlaybackFunctionality(channel);
            } catch (MidiUnavailableException ex) {
                ex.printStackTrace();
            }
           storedSongs.teachSong(songMap.get(songList.getSelectedItem()));

        });
    }

    public void linkKeyToNote(int note, PianoLabel pianoLabel) {
        notesKeyHashMap.put(note, pianoLabel);
        startingNote++;
    }
}


//pianoLabel.setBorder(BorderFactory.createLineBorder(pianoLabel.getPreferredColor()));
//pianoLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), 3));