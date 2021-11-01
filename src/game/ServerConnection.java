package game;

import graficos.*;
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
	public int ID;
	
	public static GridTest tabuleiro;
	
	public ServerConnection(Socket s, String nome) throws IOException {
		server = s;
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		out = new PrintWriter(server.getOutputStream(), true);
		apelido = new String(nome);
	}
	
	@Override
	public void run() {
		try {
			String serverResponse = in.readLine();
			ID = Integer.valueOf(serverResponse);
			ClientTest.ID = Integer.valueOf(serverResponse);
			out.println(apelido);
			while(true) {
				serverResponse = in.readLine();
				interpretarMensagem(serverResponse);
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
	
	private void interpretarMensagem(String msg) {
		if(msg.startsWith("STATUS__JOGADORES")) {
			if(msg.charAt(26) == '1') {
				TelaConexao.frame.dispose();
				tabuleiro = new GridTest();
			}
			int[] i = new int[4];
			i[0] = (int)(msg.charAt(18) - '0');
			i[1] = (int)(msg.charAt(20) - '0');
			i[2] = (int)(msg.charAt(22) - '0');
			i[3] = (int)(msg.charAt(24) - '0');
			TelaConexao.atualizar(ID, i);
		}
	}
	
	public void enviarMensagem(String msg) {
		out.println(msg);
	}

}
