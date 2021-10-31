package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientTest {

	private static String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	static Socket receptor;
	
	public static void main(String[] args) throws IOException {
		
		TelaInicial titulo = new TelaInicial();
		
	}
	
	public static boolean Conectar(String insertIP, String apelido) throws UnknownHostException, IOException {
		
		receptor = new Socket (insertIP, SERVER_PORT);
		ServerConnection serverConn = new ServerConnection(receptor, apelido);
		SERVER_IP = insertIP;
		new Thread(serverConn).start();
		TelaInicial.frame.dispose();
		TelaConexao loading = new TelaConexao();
		
		while(true) {
			int i = 3;
			if(i == 2)
				break;
		}
		receptor.close();
		System.exit(0);
		
		return true;
	}
	
}
