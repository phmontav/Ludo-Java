package cliente;
import graficos.*;
//comentario dummy
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Grid {
	static Casa caminho[] = new Casa[68];		//Vetor com todas as casas do tabuleiro
	static Origem origens[] = new Origem[4];
	static Centro centro = new Centro();
	static int[] pertoCentro = new int[4];
	static boolean movimentoIniciado = false;	//True quando uma pe�a est� sendo movida de uma casa para outra
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
	static Pecas camadaPecas = new Pecas();
	
	public Grid() {
		
		JLayeredPane camadas = new JLayeredPane();	//Organiza os gr�ficos que est�o aparecendo por camadas
		camadas.setBounds(0, 0, 1000, 705);
		
		JFrame frame = new JFrame();
		frame.add(camadas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 745);
		//frame.setLayout(new GridLayout(15, 15, 0, 0));
		//frame.getContentPane().setBackground(Color.white);
		
		JPanel canvas1 = new JPanel();				//|Utilizado para limitar o tamanho que o tabuleiro ocupa
		canvas1.setBounds(0, -5, 705, 710);
		
		
		JPanel tabuleiro = new JPanel();
		tabuleiro.setLayout(new GridLayout(15, 15, 0, 0));
		tabuleiro.setBackground(Color.white);
		//tabuleiro.setBounds(0, 0, 705, 705);
		
		for(int linha = 0; linha <15; linha++) {
			for(int col = 0; col<15; col++) {
				if((col==5 || col==7 || col==9)&&(linha<=5 || linha>=9)) {
					tabuleiro.add(caminho[index[linha][col]] = new Casa(index[linha][col], linha, col, camadas));
				}
				else {
					if((linha==5 || linha==7 || linha==9)&&(col<5 || col>9)) {
						tabuleiro.add(caminho[index[linha][col]] = new Casa(index[linha][col], linha, col, camadas));
					}
					else {
						if((col==5 || col==9)&&(linha==7)) {
							tabuleiro.add(caminho[index[linha][col]] = new Casa(index[linha][col], linha, col, camadas));
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
		camadas.add(new Dados());
		
		
		camadas.add(origens[0] = new Origem(0));
		camadas.add(origens[1] = new Origem(1));
		camadas.add(origens[2] = new Origem(2));
		camadas.add(origens[3] = new Origem(3));
		
		camadas.add(centro);
		Origem.camadasRef = camadas;
		Centro.camadasRef = camadas;
		
		canvas1.add(tabuleiro);
		camadas.add(canvas1);
		
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}


