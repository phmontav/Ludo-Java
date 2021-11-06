package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import graficos.Dados;
import servidor.PecasServidor;

public class ServerConnection implements Runnable{

	private Socket server;
	private BufferedReader in;
	public PrintWriter out;
	
	public String apelido;
	public static int ID;
	
	public static Grid tabuleiro;
	public static int turnoAtual;
	
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
			ClientLudo.ID = Integer.valueOf(serverResponse);
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
				tabuleiro = new Grid();
			}
			int[] i = new int[4];
			i[0] = (int)(msg.charAt(18) - '0');
			i[1] = (int)(msg.charAt(20) - '0');
			i[2] = (int)(msg.charAt(22) - '0');
			i[3] = (int)(msg.charAt(24) - '0');
			TelaConexao.atualizar(ID, i);
			return;
		}
		if(msg.startsWith("ATUALIZAR___DADOS")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			DadosCliente.dado1 = (int)(msg.charAt(20) - '0');
			DadosCliente.dado1usado = false;
			if(msg.charAt(22) == '1')
				DadosCliente.dado1usado = true;
			DadosCliente.dado2usado = false;
			DadosCliente.dado2 = (int)(msg.charAt(24) - '0');
			if(msg.charAt(26) == '1')
				DadosCliente.dado2usado = true;
			Grid.dados.label1.setIcon(Grid.dados.DadoImg[DadosCliente.dado1]);
			Grid.dados.label2.setIcon(Grid.dados.DadoImg[DadosCliente.dado2]);
			Grid.camadas.repaint();
			return;
		}
		if(msg.startsWith("COLORIR______CASA")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int pos = (int)(msg.charAt(20) - '0') * 10;
			pos += (int)(msg.charAt(21) - '0');
			int cor = (int)(msg.charAt(23) - '0');
			switch (cor) {
				case 0:
					Grid.caminho[pos].ColorNormal();
					break;
				case 1:
					Grid.caminho[pos].ColorPossivel();
					break;
				case 2:
					Grid.caminho[pos].ColorSelected();
					break;
			}
			Grid.camadas.repaint();
			return;
		}
		if(msg.startsWith("DISPONIVEL___CASA")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int pos = (int)(msg.charAt(20) - '0') * 10;
			pos += (int)(msg.charAt(21) - '0');
			int possivel = (int)(msg.charAt(23) - '0');
			int nascer = (int)(msg.charAt(25) - '0');
			int pecaSaindo = (int)(msg.charAt(27) - '0');
			int movimentoIniciado  = (int)(msg.charAt(29) - '0');
			Grid.caminho[pos].possivel = false;
			if(possivel == 1)
				Grid.caminho[pos].possivel = true;
			Grid.caminho[pos].nascer = false;
			if(nascer == 1)
				Grid.caminho[pos].nascer = true;
			Grid.caminho[pos].pecaSaindo = false;
			if(pecaSaindo == 1)
				Grid.caminho[pos].pecaSaindo = true;
			Grid.movimentoIniciado = false;
			if(movimentoIniciado == 1)
				Grid.movimentoIniciado = true;
			return;
		}
		if(msg.startsWith("COLORIR____ORIGEM")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int cor = (int)(msg.charAt(20) - '0');
			switch (cor) {
				case 0:
					Grid.origens[turnoAtual].ColorNormal();
					break;
				case 1:
					Grid.origens[turnoAtual].ColorSelected();
					break;
			}
			Grid.camadas.repaint();
			return;
		}
		if(msg.startsWith("DISPONIVEL_ORIGEM")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int possivel = (int)(msg.charAt(20) - '0');
			int movimentoIniciado  = (int)(msg.charAt(22) - '0');
			Grid.origens[turnoAtual].possivel = false;
			if(possivel == 1)
				Grid.origens[turnoAtual].possivel = true;
			Grid.movimentoIniciado = false;
			if(movimentoIniciado == 1)
				Grid.movimentoIniciado = true;
			return;
		}
		if(msg.startsWith("COLORIR____CENTRO")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int cor = (int)(msg.charAt(20) - '0');
			switch (cor) {
				case 0:
					Grid.centro.Normal();
					break;
				case 1:
					Grid.centro.Selected();
					break;
			}
			Grid.camadas.repaint();
			return;
		}
		if(msg.startsWith("DISPONIVEL_CENTRO")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int possivel = (int)(msg.charAt(20) - '0');
			int movimentoIniciado  = (int)(msg.charAt(22) - '0');
			Grid.centro.possivel = false;
			if(possivel == 1)
				Grid.centro.possivel = true;
			Grid.movimentoIniciado = false;
			if(movimentoIniciado == 1)
				Grid.movimentoIniciado = true;
			return;
		}
		if(msg.startsWith("MOVER________PECA")) {
			turnoAtual = (int)(msg.charAt(18) - '0');
			int id_cor = (int)(msg.charAt(20) - '0');
			int id_peca = (int)(msg.charAt(22) - '0');
			char sinal = msg.charAt(24);
			int x = (int)(msg.charAt(25) - '0') * 10;
			x += (int)(msg.charAt(26) - '0');
			if(sinal == '-')
				x *= -1;
			else
				x += (int)(msg.charAt(24) - '0') * 100;
			
			sinal = msg.charAt(28);
			int y = (int)(msg.charAt(29) - '0') * 10;
			y += (int)(msg.charAt(30) - '0');
			if(sinal == '-')
				y *= -1;
			else
				y += (int)(msg.charAt(28) - '0') * 100;
			int img = (int)(msg.charAt(32) - '0');
			PecasCliente.coordPeca[id_cor][id_peca][0] = x;
			PecasCliente.coordPeca[id_cor][id_peca][1] = y;
			PecasCliente.p_juntas[id_cor][id_peca] = img;
			Grid.camadas.repaint();
			return;
		}
	}
	
	public void enviarMensagem(String msg) {
		out.println(msg);
	}
	
}
