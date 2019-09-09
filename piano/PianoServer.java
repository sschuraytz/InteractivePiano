package piano;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PianoServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(3773);
		BlockingQueue<PianoPacket> pianoPackets = new LinkedBlockingQueue<PianoPacket>();
		List<ObjectOutputStream> outStreams = new ArrayList<ObjectOutputStream>();
		ServerBroadcast broadcaster = new ServerBroadcast(pianoPackets, outStreams);
		broadcaster.start();

		Random gen = new Random();
		while (true)
		{
			Socket socket = serverSocket.accept();

			// You must instantiate one ObjectInputStream and one ObjectOutputStream per Socket.
			// http://stackoverflow.com/questions/15986511/java-io-streamcorruptedexception-over-sockets
			ObjectOutputStream out;
			ObjectInputStream in;
			try
			{
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				outStreams.add(out);

				// give the client its clientColor
				Color color = new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
				out.writeObject(color);
				out.flush();

				// create a receiver for the socket
				ServerReceiver receiver = new ServerReceiver(in, pianoPackets);
				receiver.start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
