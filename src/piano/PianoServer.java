package piano;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PianoServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(3773);
		BlockingQueue<PianoPacket> pianoPackets = new LinkedBlockingQueue<PianoPacket>();
		List<Socket> sockets = new ArrayList<Socket>();
		PacketBroadcast broadcaster = new PacketBroadcast(pianoPackets, sockets);
		broadcaster.start();

		while (true)
		{
			Socket socket = serverSocket.accept();
			sockets.add(socket);
			PacketReciever reciever = new PacketReciever(socket, pianoPackets);
			reciever.start();
		}
	}
}
