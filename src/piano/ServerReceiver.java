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
				// System.out.println("ServerReceiver: key pos = " + packet.getKeyPosition() + " color = " + packet.getClientColor());
				packets.add(packet);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
