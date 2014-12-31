package piano;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;

public class ServerReceiver extends Thread
{
	private ObjectInputStream in;
	private BlockingQueue<PianoPacket> packets;

	public ServerReceiver(ObjectInputStream in, BlockingQueue<PianoPacket> pianoPackets)
	{
		this.in = in;
		this.packets = pianoPackets;
	}

	public void run()
	{
		try
		{
			while (true)
			{
				PianoPacket packet = (PianoPacket) in.readObject();
				packets.add(packet);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
