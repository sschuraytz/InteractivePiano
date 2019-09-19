package piano;

public interface KeyStats
{

	public static final int NUM_KEYS_PER_OCTAVE = 13;
	public static final int NUM_OF_OCTAVES = 3;
	public static final int NUM_KEYS = NUM_KEYS_PER_OCTAVE * NUM_OF_OCTAVES + NUM_OF_OCTAVES - 1;

	public static final int TOP_SKINNY_WIDTH = 5;
	public static final int TOP_WHITE_WIDTH = 36;
	public static final int TOP_BLACK_WIDTH = 31;

	public static final int BOTTOM_WHITE_WIDTH = 53;
	public static final int BOTTOM_EXTRA_WIDE_WIDTH = 58;
	public static final int BOTTOM_WIDE_WIDTH = 54;
	public static final int BOTTOM_SKINNY_WIDTH = 5;

	public static final int TOP_HEIGHT = 308;
	public static final int BOTTOM_HEIGHT = 176;

	public static final int FIRST_KEY_REQUIRING_DIFFERENT_WIDTH = 4;
	public static final int NUM_KEYS_TO_INCREMENT = 14;
}
