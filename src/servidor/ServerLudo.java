package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerLudo {
	private static final int PORT = 9090;
	
	static private ArrayList<ClientHandler> clients = new ArrayList<>();
	static public int turnoAtual = 0;
	static public int qtConectada = 0;
	static public int[] statusJogadores = {0, 0, 0, 0};
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	public static volatile boolean esperarConexao = true;
	static GridLogic tabuleiro;
	
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(PORT);
		
		String ip;
		try {
		    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		    while (interfaces.hasMoreElements()) {
		        NetworkInterface iface = interfaces.nextElement();
		        // filters out 127.0.0.1 and inactive interfaces
		        if (iface.isLoopback() || !iface.isUp())
		            continue;

		        Enumeration<InetAddress> addresses = iface.getInetAddresses();
		        while(addresses.hasMoreElements()) {
		            InetAddress addr = addresses.nextElement();

		            // *EDIT*
		            if (addr instanceof Inet6Address) continue;

		            ip = addr.getHostAddress();
		            System.out.println("[SERVER] O IP para conex�o �: " + ip);
		        }
		    }
		} catch (SocketException e) {
			System.out.println("[SERVER] Erro na identificacao do IP.");
		}
		
		while(esperarConexao && qtConectada < 4) {
			System.out.println("[SERVER] Esperando conexao com cliente...");
			Socket client = listener.accept();
			if(!esperarConexao) {
				break;
			}
			ClientHandler clientThread = new ClientHandler(client);
			clients.add(clientThread);
			pool.execute(clientThread);
			clients.get(qtConectada).out.println(qtConectada);
			statusJogadores[qtConectada] = 1;
			outToAll("STATUS__JOGADORES " + statusJogadores[0] + " " + statusJogadores[1] + " " + statusJogadores[2] + " " + statusJogadores[3] + " 0");
			/*String clientResponse = in.readLine();
			outToAll("Conexao estabelecida com cliente: " + clientResponse);*/
			qtConectada++;
		}
		while(qtConectada == 4 && esperarConexao) {}
		outToAll("STATUS__JOGADORES " + statusJogadores[0] + " " + statusJogadores[1] + " " + statusJogadores[2] + " " + statusJogadores[3] + " 1");
		tabuleiro = new GridLogic();
		while(true) {
			
		}
		//System.out.println("[SERVER] Dados Enviados. Encerrando...");
		//client.close();
		//listener.close();
	}
	
	public static void outToAll(String msg) {
		for(ClientHandler aClient : clients) {
			aClient.out.println(msg);
		}
	}	
	
	public static void atualizar_dados(int dado1, boolean dado1usado, int dado2, boolean dado2usado) {
		int usado1 = 0, usado2 = 0;
		if(dado1usado) usado1 = 1;
		if(dado2usado) usado2 = 1;
		outToAll("ATUALIZAR___DADOS " + turnoAtual + " " + dado1 + " " + usado1 + " " + dado2 + " " + usado2);
	}
	
	public static void mover_peca(int cor, int peca_id, int peca_x, int peca_y, int peca_qtd) {
		String x = String.format("%03d", peca_x);
		String y = String.format("%03d", peca_y);
		outToAll("MOVER________PECA " + turnoAtual + " " + cor + " " + peca_id + " " + x + " " + y + " " + peca_qtd);
	}

	public static void colorir_casa(int pos, int cor) {
		String uni = String.format("%02d", pos);
		outToAll("COLORIR______CASA " + turnoAtual + " " + uni + " " + cor);
	}

	public static void disponivel_casa(int pos, boolean possivel, boolean nascer, boolean pecaSaindo, boolean movimentoIniciado) {
		int possivelInt = 0, nascerInt = 0, pecaSaindoInt = 0, movimentoIniciadoInt = 0;
		if(possivel) possivelInt = 1;
		if(nascer) nascerInt = 1;
		if(pecaSaindo) pecaSaindoInt = 1;
		if(movimentoIniciado) movimentoIniciadoInt = 1;
		String uni = String.format("%02d", pos);
		outToAll("DISPONIVEL___CASA " + turnoAtual + " " + uni + " " + possivelInt + " " + nascerInt + " " + pecaSaindoInt + " " + movimentoIniciadoInt);
	}
	
	public static void colorir_origem(int cor) {
		outToAll("COLORIR____ORIGEM " + turnoAtual + " " + cor);
	}
	
	public static void disponivel_origem(boolean possivel, boolean movimentoIniciado) {
		int possivelInt = 0, movimentoIniciadoInt = 0;
		if(possivel) possivelInt = 1;
		if(movimentoIniciado) movimentoIniciadoInt = 1;
		outToAll("DISPONIVEL_ORIGEM " + turnoAtual + " " + possivelInt + " " + movimentoIniciadoInt);
	}
	
	public static void colorir_centro(int cor) {
		outToAll("COLORIR____CENTRO " + turnoAtual + " " + cor);
	}
	
	public static void disponivel_centro(boolean possivel, boolean movimentoIniciado) {
		int possivelInt = 0, movimentoIniciadoInt = 0;
		if(possivel) possivelInt = 1;
		if(movimentoIniciado) movimentoIniciadoInt = 1;
		outToAll("DISPONIVEL_CENTRO " + turnoAtual + " " + possivelInt + " " + movimentoIniciadoInt);
	}
}
