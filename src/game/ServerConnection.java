package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable{

	private Socket server;
	private BufferedReader in;
	public PrintWriter out;
	
	public String apelido;
	
	public ServerConnection(Socket s, String nome) throws IOException {
		server = s;
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		out = new PrintWriter(server.getOutputStream(), true);
		apelido = new String(nome);
	}
	
	@Override
	public void run() {
		try {
			out.println(apelido);
			while(true) {
				String serverResponse = in.readLine();
				
				if(serverResponse == null) break;
				System.out.println(serverResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
