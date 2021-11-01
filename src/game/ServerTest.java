package game;

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

public class ServerTest {
	private static final int PORT = 9090;
	
	static private ArrayList<ClientHandler> clients = new ArrayList<>();
	static public int qtConectada = 0;
	static public int[] statusJogadores = {0, 0, 0, 0};
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	public static volatile boolean esperarConexao = true;
	
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
		            System.out.println("[SERVER] O IP para conexão é: " + ip);
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
		outToAll("STATUS__JOGADORES " + ServerTest.statusJogadores[0] + " " + ServerTest.statusJogadores[1] + " " + ServerTest.statusJogadores[2] + " " + ServerTest.statusJogadores[3] + " 1");
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
	
}
