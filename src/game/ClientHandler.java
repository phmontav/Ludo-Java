package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

	private Socket client;
	private BufferedReader in;
	public PrintWriter out;
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String clientRequest;
		try {
			while(true) {
				clientRequest = in.readLine();
				interpretarMensagem(clientRequest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ServerTest.outToAll( x );
		
		//out.close();
		//sc.close();
	}
	
	private void interpretarMensagem(String msg) throws IOException {
		if(msg.startsWith("INICIAR_JOGO")) {
			int id = (int)(msg.charAt(13) - '0');
			if(msg.charAt(15) == '0')
				ServerTest.statusJogadores[id] = 1;
			else
				ServerTest.statusJogadores[id] = 2;
		}
		ServerTest.outToAll("STATUS__JOGADORES " + ServerTest.statusJogadores[0] + " " + ServerTest.statusJogadores[1] + " " + ServerTest.statusJogadores[2] + " " + ServerTest.statusJogadores[3] + " 0");
		int terminar = 2;
		for(int i=0; i<ServerTest.qtConectada; i++)
		{
			if(ServerTest.statusJogadores[i] <= terminar)
				terminar = ServerTest.statusJogadores[i] ;
		}
		if(terminar == 2 && ServerTest.qtConectada>1) {
			ServerTest.esperarConexao = false;
			if(ServerTest.qtConectada<4) {
				Socket dummy = new Socket ("127.0.0.1", 9090);
				dummy.close();
			}
		}
			
	}
	
}
