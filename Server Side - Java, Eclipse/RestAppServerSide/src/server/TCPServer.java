package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws IOException {

		Sql sql = new Sql();
		InetAddress IP = InetAddress.getLocalHost();
		ServerSocket s = null;
		System.out.println(IP);

		try {
			s = new ServerSocket(10000, 10, IP);
			sql.ConectingToSQL();
			System.out.println("I'm connecting to sql");

		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println(s);
		while (true) {
			Socket incoming = null;

			try {

				incoming = s.accept();
				System.out.println(incoming);

			} catch (IOException e) {
				System.out.println(e);
				continue;
			}
			System.out.println("i am sockect handler");
			new SocketHandler(incoming, sql).start();

			System.out.println(incoming);

		}

	}

}
