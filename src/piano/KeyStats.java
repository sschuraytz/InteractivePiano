package piano;

public interface KeyStats
{
	// public static final int NUM_KEYS = 13;

	public static final int TOP_SKINNY_WIDTH = 10;
	public static final int TOP_WHITE_WIDTH = 72;
	public static final int TOP_BLACK_WIDTH = 61;

	public static final int BOTTOM_WHITE_WIDTH = 105;
	public static final int BOTTOM_FAT_WIDTH = 114;
	public static final int BOTTOM_BLACK_WIDTH = 61;
	public static final int BOTTOM_SKINNY_WIDTH = 11;

	public static final int TOP_HEIGHT = 448;
	public static final int BOTTOM_HEIGHT = 256;


	int OCTAVES = 1;
	int OCTAVE_WIDTH = 750 / OCTAVES; // 735

	int FRAME_WIDTH = OCTAVES * OCTAVE_WIDTH + 40;
	int FRAME_HEIGHT = 704;

	int NUM_WHITE_KEYS_IN_OCTAVE = 7;
	int NUM_BLACK_KEYS_IN_OCTAVE = 5;
	int NUM_WHITE_KEYS = OCTAVES * NUM_WHITE_KEYS_IN_OCTAVE;
	int NUM_BLACK_KEYS = OCTAVES * NUM_BLACK_KEYS_IN_OCTAVE;
	int NUM_KEYS = NUM_WHITE_KEYS + NUM_BLACK_KEYS;


	int WHITE_WIDTH = OCTAVE_WIDTH / NUM_WHITE_KEYS_IN_OCTAVE;
	int BLACK_WIDTH = (int) (13.7 * WHITE_WIDTH / 23.5); // proportion: 13.7 and 23.5 are average size of white and black keys, respectively
	int BLACK_HEIGHT = (int) (FRAME_HEIGHT / 2.75);
	int SPACE_BETWEEN_BLACK_KEYS = BLACK_WIDTH / 3;
}
