package cliente;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientLudo {

	private static final int SERVER_PORT = 9090;
	public static int ID;
	static boolean fimJogo = false;
	static Socket receptor;
	public static ExecutorService pool = Executors.newFixedThreadPool(2);
	static public String[] apelidos = {"Azul", "Amarelo", "Vermelho", "Verde"};
	static Color[] cores = {new Color(0x00e8fc), new Color(0xf9c846), new Color(0xf96e46), new Color(0x0fff95)};
	
	public static PrintWriter out;
	
	public static void main(String[] args) throws IOException {
		
		new TelaInicial();
		
	}
	
	public static boolean Conectar(String insertIP, String apelido) throws UnknownHostException, IOException {
		
		receptor = new Socket (insertIP, SERVER_PORT);
		ServerConnection serverConn = new ServerConnection(receptor, apelido);
		out = new PrintWriter(receptor.getOutputStream(), true);
		pool.execute(serverConn);
		TelaInicial.frame.dispose();
		TelaConexao loading = new TelaConexao(serverConn);
		pool.execute(loading);

		return true;
	}
	
	public static void enviarMensagem(String msg) {
		out.println(msg);
	}
	
	public static void rolar_dados() {
		enviarMensagem("ROLAR__DADOS " + ID);
	}
	public static void clicar_casa(int pos) {
		String uni = String.format("%02d", pos);
		enviarMensagem("CLICAR__CASA " + ID + " " + uni);
	}
	public static void clica_origem() {
		enviarMensagem("CLICA_ORIGEM " + ID);
	}
	public static void clica_centro() {
		enviarMensagem("CLICA_CENTRO " + ID);
	}
	public static void iniciar_jogo(int s) {
		enviarMensagem("INICIAR_JOGO " + ID + " " + s);
	}
	public static void enviar__nome(String nome) {
		enviarMensagem("ENVIAR__NOME " + ID + " " + nome);
	}
}
