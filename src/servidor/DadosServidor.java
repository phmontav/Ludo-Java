package servidor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DadosServidor extends JPanel implements MouseListener{

	static public int dado1, dado2, turno;						//Valor do dado 1, valor do dado 2 e valor do jogador que estï¿½ jogando agora(1, 2, 3 ou 4 para azul, amarelo, vermelho e verde)
	static public boolean dado1usado = true, dado2usado = true;	//Indica se determinado dado ja foi utilizado na jogada atual
	static Random random = new Random();;
	static JLabel label1, label2;								//Icone clicavel dos dados 1 e 2
	static ImageIcon[] DadoImg;								//Vetor que possui as imagens dos dados
	
	public DadosServidor() {
		random = new Random();
		turno = 0;
		this.setVisible(false);
	}
	
	static public void proxTurno() {
		if(DadosServidor.dado1 == 6 && DadosServidor.dado2 == 6) {
			//repete o turno
		}
		else {
			if(++turno >= ServerLudo.qtConectada) {
				turno = 0;
			}
		}
		ServerLudo.turnoAtual = turno;
		ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
		System.out.println("Jogador atual: " + turno);	//--------------------------------------Enviar mensagem para a tabela de informações.
	}

	static public void rolarDados() {
		if((dado1usado && dado2usado) || (GridLogic.origens[turno].dentro + GridLogic.centro.n_pecas[turno] == 3 && dado1 != 6 && dado2 != 6)) {
			dado1 = random.nextInt(6) + 1; //Sorteia um numero inteiro entre 0 e 5, em seguida soma 1;
			dado2 = random.nextInt(6) + 1;
			//label1.setIcon(DadoImg[dado1]);
			//label2.setIcon(DadoImg[dado2]);
			dado1usado = false;
			dado2usado = false;
			ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
			System.out.println("       Jogador " + turno + " tirou " + dado1 + " e " + dado2);
			GridLogic.verificar_jogada();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
