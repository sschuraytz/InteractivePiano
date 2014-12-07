package piano;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ServerBroadcast extends Thread
{
	private List<ObjectOutputStream> outStreams;
	private BlockingQueue<PianoPacket> packets;

	public ServerBroadcast(BlockingQueue<PianoPacket> pianoPackets, List<ObjectOutputStream> outStreams)
	{
		this.packets = pianoPackets;
		this.outStreams = outStreams;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				PianoPacket packet = packets.take();

				Iterator<ObjectOutputStream> iter = outStreams.iterator();

				while (iter.hasNext())
				{
					ObjectOutputStream out = iter.next();
					try
					{
						out.writeObject(packet);
						out.flush();
					}
					catch (IOException e)
					{
						e.printStackTrace();
						iter.remove();
					}
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
