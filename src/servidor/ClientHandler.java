package servidor;

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
		//ServerLudo.outToAll( x );
		
		//out.close();
		//sc.close();
	}
	
	private void interpretarMensagem(String msg) throws IOException {
		if(msg.startsWith("INICIAR_JOGO")) {
			int id = (int)(msg.charAt(13) - '0');
			if(msg.charAt(15) == '0')
				ServerLudo.statusJogadores[id] = 1;
			else
				ServerLudo.statusJogadores[id] = 2;
		}
		ServerLudo.outToAll("STATUS__JOGADORES " + ServerLudo.statusJogadores[0] + " " + ServerLudo.statusJogadores[1] + " " + ServerLudo.statusJogadores[2] + " " + ServerLudo.statusJogadores[3] + " 0");
		int terminar = 2;
		for(int i = 0; i < ServerLudo.qtConectada; i++)
		{
			if(ServerLudo.statusJogadores[i] <= terminar)
				terminar = ServerLudo.statusJogadores[i] ;
		}
		if(terminar == 2 && ServerLudo.qtConectada > 1) {
			ServerLudo.esperarConexao = false;
			if(ServerLudo.qtConectada<4) {
				Socket dummy = new Socket ("127.0.0.1", 9090);
				dummy.close();
			}
		}
			
	}
	
}
