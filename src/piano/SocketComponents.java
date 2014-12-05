package piano;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketComponents {
private Socket socket;
private ObjectOutputStream out;
private ObjectInputStream in;
public SocketComponents(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
	super();
	this.socket = socket;
	this.out = out;
	this.in = in;
}
public Socket getSocket() {
	return socket;
}
public ObjectOutputStream getOut() {
	return out;
}
public ObjectInputStream getIn() {
	return in;
}

}
