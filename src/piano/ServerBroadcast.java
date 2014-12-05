package piano;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ServerBroadcast extends Thread
{
	private List<SocketComponents> socketComponents;
	private BlockingQueue<PianoPacket> packets;

	public ServerBroadcast(BlockingQueue<PianoPacket> pianoPackets, List<SocketComponents> socketComponents)
	{
		this.packets = pianoPackets;
		this.socketComponents = socketComponents;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				PianoPacket packet = packets.take();

				Iterator<Socket> iter = socketComponents.iterator();

				while (iter.hasNext())
				{
					Socket socket = iter.next();
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(packet);
						oos.flush(); //do we need to flush?				
			    		//oos.close();
					} catch (IOException e) {
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
