package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Grid {
	static CasaCliente caminho[] = new CasaCliente[68];		//Vetor com todas as casas do tabuleiro
	static OrigemCliente origens[] = new OrigemCliente[4];
	static CentroCliente centro = new CentroCliente();
	static JLayeredPane camadas;
	static JLabel vencedor;
	static DadosCliente dados;
	static JFrame frame;
	static int[] pertoCentro = new int[4];
	static boolean movimentoIniciado = false;	//True quando uma peca esta sendo movida de uma casa para outra
	static int[][] index = {{-1, -1,	-1,	-1,	-1,	24,	-1,	23,	-1,	22,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	25,	-1,	58,	-1,	21,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	26,	-1,	59,	-1,	20,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	27,	-1,	60,	-1,	19,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1, -1,	-1,	28,	-1,	61,	-1,	18,	-1,	-1,	-1,	-1,	-1},
					 		{34, 33,	32,	31,	30,	29,	-1,	62,	-1,	17,	16,	15,	14,	13,	12},
					 		{-1, -1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
					 		{35, 63,	64,	65,	66,	67,	-1,	-1,	-1,	57,	56,	55,	54,	53,	11},
					 		{-1, -1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
					 		{36, 37,	38,	39,	40,	41,	-1,	52,	-1,	5,	6,	7,	8,	9,	10},
					 		{-1, -1,	-1,	-1,	-1,	42,	-1,	51,	-1,	4,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	43,	-1,	50,	-1,	3,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	44,	-1,	49,	-1,	2,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	45,	-1,	48,	-1,	1,	-1,	-1,	-1,	-1,	-1},
					 		{-1, -1,	-1,	-1,	-1,	46,	-1,	47,	-1,	0,	-1,	-1,	-1,	-1,	-1}};
	static PecasCliente camadaPecas = new PecasCliente();
	
	public Grid() {
		
		vencedor = new JLabel("<html><p align=center style=\"width:400px\">" + "Jogador " + "Aguiar" + " venceu!" + "</p></html>");
		vencedor.setBackground(new Color(0, 0, 0, 90));
		vencedor.setForeground(Color.white);
		vencedor.setOpaque(true);
		vencedor.setVisible(false);
		vencedor.setBounds(0, 0, 705, 705);
		vencedor.setFont(new Font("Arial", Font.BOLD, 70));
		vencedor.setHorizontalAlignment(JLabel.CENTER);
		vencedor.setVerticalAlignment(JLabel.CENTER);
		
		camadas = new JLayeredPane();	//Organiza os graficos que estao aparecendo por camadas
		camadas.setBounds(0, 0, 1000, 705);
		camadas.add(vencedor);
		
		frame = new JFrame();
		frame.add(camadas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 745);
		
		JPanel canvas1 = new JPanel();				//|Utilizado para limitar o tamanho que o tabuleiro ocupa
		canvas1.setBounds(0, -5, 705, 710);
		
		
		JPanel tabuleiro = new JPanel();
		tabuleiro.setLayout(new GridLayout(15, 15, 0, 0));
		tabuleiro.setBackground(Color.white);
		
		for(int linha = 0; linha <15; linha++) {
			for(int col = 0; col<15; col++) {
				if((col==5 || col==7 || col==9)&&(linha<=5 || linha>=9)) {
					tabuleiro.add(caminho[index[linha][col]] = new CasaCliente(index[linha][col], linha, col, camadas));
				}
				else {
					if((linha==5 || linha==7 || linha==9)&&(col<5 || col>9)) {
						tabuleiro.add(caminho[index[linha][col]] = new CasaCliente(index[linha][col], linha, col, camadas));
					}
					else {
						if((col==5 || col==9)&&(linha==7)) {
							tabuleiro.add(caminho[index[linha][col]] = new CasaCliente(index[linha][col], linha, col, camadas));
						}
						else {
							JPanel empty = new JPanel();
							empty.setBackground(Color.white);
							empty.setBounds(0, 0, 47, 47);
							tabuleiro.add(empty);
						}
					}
				}
			}
		}
		
		camadas.add(camadaPecas);
		dados = new DadosCliente();
		camadas.add(dados);
		
		camadas.add(origens[0] = new OrigemCliente(0));
		camadas.add(origens[1] = new OrigemCliente(1));
		camadas.add(origens[2] = new OrigemCliente(2));
		camadas.add(origens[3] = new OrigemCliente(3));
		
		camadas.add(centro);
		OrigemCliente.camadasRef = camadas;
		CentroCliente.camadasRef = camadas;
		
		canvas1.add(tabuleiro);
		camadas.add(canvas1);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}


