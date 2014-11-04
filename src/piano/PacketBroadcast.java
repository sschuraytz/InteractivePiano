package piano;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PacketBroadcast extends Thread
{
	private List<Socket> sockets;
	private BlockingQueue<PianoPacket> packets;

	public PacketBroadcast(BlockingQueue<PianoPacket> pianoPackets, List<Socket> sockets)
	{
		this.packets = pianoPackets;
		this.sockets = sockets;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				PianoPacket packet = packets.take();

				Iterator<Socket> iter = sockets.iterator();

				while (iter.hasNext())
				{
					Socket socket = iter.next();
					try
					{
						OutputStream out = socket.getOutputStream();
						PrintWriter writer = new PrintWriter(out); // change to write object and send out entire packet
						writer.println(packet);
						writer.flush();
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
