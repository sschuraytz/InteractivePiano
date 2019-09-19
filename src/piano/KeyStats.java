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

	int FRAME_WIDTH = 1600; // TODO fix error where last black get cut off (frame width 750, 2 octaves)
	int FRAME_HEIGHT = 704;

	int OCTAVES = 7;
	int OCTAVE_WIDTH = FRAME_WIDTH / OCTAVES;

	int NUM_WHITE_KEYS_IN_OCTAVE = 7;
	int NUM_BLACK_KEYS_IN_OCTAVE = 5;

	int NUM_WHITE_KEYS = OCTAVES * NUM_WHITE_KEYS_IN_OCTAVE;
	int NUM_BLACK_KEYS = OCTAVES * NUM_BLACK_KEYS_IN_OCTAVE;
	int NUM_KEYS = NUM_WHITE_KEYS + NUM_BLACK_KEYS;

	int SPACE_BETWEEN_WHITE_KEYS = (int) ((double) 2/3 * OCTAVE_WIDTH / 100); // percentage
	int WHITE_WIDTH = OCTAVE_WIDTH / NUM_WHITE_KEYS_IN_OCTAVE - SPACE_BETWEEN_WHITE_KEYS;

	int BLACK_WIDTH = (int) (13.7 * WHITE_WIDTH / 23.5); // proportion: 13.7 and 23.5 are average size of white and black keys, respectively
	int BLACK_HEIGHT = (int) (FRAME_HEIGHT / 1.75);

	int FIRST_BLACK = (WHITE_WIDTH + SPACE_BETWEEN_WHITE_KEYS) - (BLACK_WIDTH / 2);
	int SPACE_BETWEEN_BLACK_KEYS = FIRST_BLACK - (BLACK_WIDTH / 2); // + SPACE_BETWEEN_WHITE_KEYS;
	int BIG_SPACE_BETWEEN_BLACK_KEYS = FIRST_BLACK * 2; // WHITE_WIDTH + (WHITE_WIDTH / 2) - (SPACE_BETWEEN_WHITE_KEYS);
}
