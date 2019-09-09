package piano;

public interface KeyStats
{
	public static final int NUM_KEYS_PER_OCTAVE = 13;
	public static final int NUM_OF_OCTAVES = 3;
	public static final int NUM_KEYS = NUM_KEYS_PER_OCTAVE * NUM_OF_OCTAVES + (1 * NUM_OF_OCTAVES) - 1; //not sure why need this, but seems necessary

	public static final int TOP_SKINNY_WIDTH = 5;
	public static final int TOP_WHITE_WIDTH = 36;
	public static final int TOP_BLACK_WIDTH = 31;

	public static final int BOTTOM_WHITE_WIDTH = 53; //53
	public static final int BOTTOM_FAT_WIDTH = 58;	 //58 - if no other fat_width
	public static final int BOTTOM_FAT_WIDTH_2 = 54;
	public static final int BOTTOM_BLACK_WIDTH = 30;
	public static final int BOTTOM_SKINNY_WIDTH = 5;

	public static final int TOP_HEIGHT = 308;
	public static final int BOTTOM_HEIGHT = 176;

	//need to modify widths to see expanded keyboard
/*
	public static final int TOP_SKINNY_WIDTH = 10;
	public static final int TOP_WHITE_WIDTH = 72;
	public static final int TOP_BLACK_WIDTH = 61;

	public static final int BOTTOM_WHITE_WIDTH = 105;
	public static final int BOTTOM_FAT_WIDTH = 114;
	public static final int BOTTOM_BLACK_WIDTH = 61;
	public static final int BOTTOM_SKINNY_WIDTH = 11;

	public static final int TOP_HEIGHT = 448;
	public static final int BOTTOM_HEIGHT = 256;
*/

}
