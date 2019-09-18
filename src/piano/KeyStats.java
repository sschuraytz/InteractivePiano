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
	int NUM_WHITE_KEYS = OCTAVES * 7;
	int NUM_BLACK_KEYS = OCTAVES * 5;
	int NUM_KEYS = NUM_WHITE_KEYS + NUM_BLACK_KEYS;

	int WHITE_WIDTH = 105;
	int BLACK_WIDTH = 61;
	int WIDTH = WHITE_WIDTH * NUM_WHITE_KEYS + 40;
	int HEIGHT = 704;
}
