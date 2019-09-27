package piano;

public interface Notes
{
	int C = 60;
	int startingC = C - (KeyStats.OCTAVES/2 * 12);
	int highC = C + 12;
	int D = 62;
	int E = 64;
	int F = 65;
	int G = 67;
	int A = 69;
	int B = 71;
	double Bflat = B - 0.5;
}
