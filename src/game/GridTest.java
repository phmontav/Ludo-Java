package game;
//comentario dummy
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GridTest {
	static Casa caminho[] = new Casa[68];		//Vetor com todas as casas do tabuleiro
	static Origem origens[] = new Origem[4];
	static Centro centro = new Centro();
	static int[] pertoCentro = new int[4];
	static boolean movimentoIniciado = false;	//True quando uma peça está sendo movida de uma casa para outra
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
	
	public static void main(String[] args) {
		
		JLayeredPane camadas = new JLayeredPane();	//Organiza os gráficos que estão aparecendo por camadas
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
		frame.setVisible(true);
	}
	
	public static int particular_real(int pos, int turno) {//0, amarelo -> 12
		if(pos>=0 && pos<(12*(4-turno)))
			return (pos+turno*12);
		if(pos>=(12*(4-turno)) && pos<48)
			return (pos-(12*(4-turno)));
		return (pos+turno*5);
	}
	
	public static int real_particular(int pos, int turno) {//12, amarelo -> 0
		if(pos>=turno*12 && pos<48)
			return (pos-turno*12);
		if(pos<turno*12)
			return (pos+12*(4-turno));
		return (pos-turno*5);
	}
	
	public static int avancar_casa(int pos, int n) {
		return (particular_real(real_particular(pos, Dados.turno)+n, Dados.turno));
	}
	
	public static void verificar_colisao() {
		int[][] shift = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
		for(int k=0; k<68; k++) {
			if(caminho[k].n_pecas[0]+caminho[k].n_pecas[1]+caminho[k].n_pecas[2]+caminho[k].n_pecas[3] > 1) {
				for(int i = 0; i<4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j=0; j<caminho[k].n_pecas[i]; j++) {
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x+12*shift[i][0];
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y+12*shift[i][1];
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j=0; j<caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						Pecas.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
				}
			}
			else {
				for(int i = 0; i<4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j=0; j<caminho[k].n_pecas[i]; j++) {
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x;
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y;
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j=0; j<caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						Pecas.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
					//??????????????.......
				}
			}
		}
	}
	
	
	public static void verificar_jogada() {
		switch (pertoCentro[Dados.turno]) {
		case 3:
			if(Dados.dado1 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
					(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
					(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53)))
				Dados.dado1usado = true;
			if(Dados.dado2 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
					(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
					(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53)))
				Dados.dado2usado = true;
			break;
		case 2:
			if(centro.n_pecas[Dados.turno] == 1) {
				if(Dados.dado1 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53)))
					Dados.dado1usado = true;
				if(Dados.dado2 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53)))
					Dados.dado2usado = true;
			}
			if(origens[Dados.turno].dentro == 1) {
				if((Dados.dado1 == 6 && !Dados.dado1usado) || (Dados.dado2 == 6 && !Dados.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53))
							Dados.dado1usado = true;
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53))
							Dados.dado2usado = true;
				}
			}
			break;
		case 1:
			if(centro.n_pecas[Dados.turno] == 2) {
				if(Dados.dado1 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
										(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
										(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53)))
					Dados.dado1usado = true;
				if(Dados.dado2 == 6 || ((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
										(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
										(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53)))
					Dados.dado2usado = true;
			}
			if(origens[Dados.turno].dentro == 2) {
				if((Dados.dado1 == 6 && !Dados.dado1usado) || (Dados.dado2 == 6 && !Dados.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53))
							Dados.dado1usado = true;
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53))
							Dados.dado2usado = true;
				}
			}
			if(centro.n_pecas[Dados.turno] == 1 && origens[Dados.turno].dentro == 1) {
				if((Dados.dado1 == 6 && !Dados.dado1usado) || (Dados.dado2 == 6 && !Dados.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado1 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado1 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado1 > 53))
							Dados.dado1usado = true;
					if((Pecas.posPeca[Dados.turno][0] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][0], Dados.turno) + Dados.dado2 > 53) && 
						(Pecas.posPeca[Dados.turno][1] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][1], Dados.turno) + Dados.dado2 > 53) &&
						(Pecas.posPeca[Dados.turno][2] == -1 ||  real_particular(Pecas.posPeca[Dados.turno][2], Dados.turno) + Dados.dado2 > 53))
							Dados.dado2usado = true;
				}
			}
			break;
		}
		if(Dados.dado1usado && Dados.dado2usado)
			Dados.proxTurno();
	}
	
	public static void clickCasa(int pos) {
		if(caminho[pos].nascer == true) {
			if(!Dados.dado1usado && Dados.dado1 == 6) {
				caminho[pos].nascer = false;
				caminho[pos].n_pecas[Dados.turno]++;
				caminho[pos].ColorNormal();
				caminho[pos].pecas.get(Dados.turno).push(origens[Dados.turno].pecas.pop());
				origens[Dados.turno].ColorNormal();
				origens[Dados.turno].dentro--;
				movimentoIniciado = false;
				Dados.dado1usado = true;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][0] = caminho[pos].x;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][1] = caminho[pos].y;
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				verificar_jogada();
				return;
			}
			else {
				caminho[pos].nascer = false;
				caminho[pos].n_pecas[Dados.turno]++;
				caminho[pos].ColorNormal();
				caminho[pos].pecas.get(Dados.turno).push(origens[Dados.turno].pecas.pop());
				origens[Dados.turno].ColorNormal();
				origens[Dados.turno].dentro--;
				movimentoIniciado = false;
				Dados.dado2usado = true;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][0] = caminho[pos].x;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][1] = caminho[pos].y;
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				verificar_jogada();
				return;
			}
		}
		if(caminho[pos].possivel) {
			if(avancar_casa(pos, -Dados.dado1)>=0 && !Dados.dado1usado && caminho[avancar_casa(pos, -Dados.dado1)].pecaSaindo) {
				caminho[pos].ColorNormal();
				caminho[pos].possivel = false;
				caminho[pos].n_pecas[Dados.turno]++;
				caminho[pos].pecas.get(Dados.turno).push(caminho[avancar_casa(pos, -Dados.dado1)].pecas.get(Dados.turno).pop());
				caminho[avancar_casa(pos, -Dados.dado1)].n_pecas[Dados.turno]--;
				caminho[avancar_casa(pos, -Dados.dado1)].ColorNormal();
				movimentoIniciado = false;
				caminho[avancar_casa(pos, -Dados.dado1)].pecaSaindo = false;
				
				if((real_particular(pos, Dados.turno) - Dados.dado1 + Dados.dado2) < 53) {
					caminho[avancar_casa(pos, -Dados.dado1+Dados.dado2)].possivel = false;
					caminho[avancar_casa(pos, -Dados.dado1+Dados.dado2)].ColorNormal();
				}
				if(real_particular(pos, Dados.turno) + Dados.dado2 < 53) {
					caminho[avancar_casa(pos, +Dados.dado2)].possivel = false;
					caminho[avancar_casa(pos, +Dados.dado2)].ColorNormal();
				}
				Dados.dado1usado = true;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][0] = caminho[pos].x;
				Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][1] = caminho[pos].y;
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				
				centro.Normal();
				centro.possivel = false;
				
				caminho[pos].kill();
				if(pos >= 48) {
					pertoCentro[Dados.turno] = 0;
					for(int i=48; i<53; i++) {
						pertoCentro[Dados.turno] += caminho[particular_real(i, Dados.turno)].n_pecas[Dados.turno];
					}
				}
				verificar_jogada();
				
			}
			else {
				if(avancar_casa(pos, -Dados.dado2)>=0 && !Dados.dado2usado && caminho[avancar_casa(pos, -Dados.dado2)].pecaSaindo) {
					caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[Dados.turno]++;
					caminho[pos].pecas.get(Dados.turno).push(caminho[avancar_casa(pos, -Dados.dado2)].pecas.get(Dados.turno).pop());
					caminho[avancar_casa(pos, -Dados.dado2)].n_pecas[Dados.turno]--;
					caminho[avancar_casa(pos, -Dados.dado2)].ColorNormal();
					movimentoIniciado = false;
					caminho[avancar_casa(pos, -Dados.dado2)].pecaSaindo = false;
					
					if(real_particular(pos, Dados.turno) - Dados.dado2 + Dados.dado1 < 53) {
						caminho[avancar_casa(pos, -Dados.dado2+Dados.dado1)].possivel = false;
						caminho[avancar_casa(pos, -Dados.dado2+Dados.dado1)].ColorNormal();
					}
					if(real_particular(pos, Dados.turno) + Dados.dado1 < 53) {
						caminho[avancar_casa(pos, +Dados.dado1)].possivel = false;
						caminho[avancar_casa(pos, +Dados.dado1)].ColorNormal();
					}
					Dados.dado2usado = true;
					Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][0] = caminho[pos].x;
					Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][1] = caminho[pos].y;
					Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
					
					centro.Normal();
					centro.possivel = false;
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[Dados.turno] = 0;
						for(int i=48; i<53; i++) {
							pertoCentro[Dados.turno] += caminho[particular_real(i, Dados.turno)].n_pecas[Dados.turno];
						}
					}
					verificar_jogada();
				}
				else {
					caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[Dados.turno]++;
					caminho[pos].pecas.get(Dados.turno).push(caminho[avancar_casa(pos, -Dados.dado1-Dados.dado2)].pecas.get(Dados.turno).pop());
					caminho[avancar_casa(pos, -Dados.dado1-Dados.dado2)].n_pecas[Dados.turno]--;
					caminho[avancar_casa(pos, -Dados.dado1-Dados.dado2)].ColorNormal();
					movimentoIniciado = false;
					caminho[avancar_casa(pos, -Dados.dado1-Dados.dado2)].pecaSaindo = false;
					
					caminho[avancar_casa(pos, -Dados.dado1)].possivel = caminho[avancar_casa(pos, -Dados.dado2)].possivel = false;
					caminho[avancar_casa(pos, -Dados.dado1)].ColorNormal();
					caminho[avancar_casa(pos, -Dados.dado2)].ColorNormal();
					Dados.dado1usado = Dados.dado2usado = true;
					Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][0] = caminho[pos].x;
					Pecas.coordPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()][1] = caminho[pos].y;
					Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
					
					centro.Normal();
					centro.possivel = false;
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[Dados.turno] = 0;
						for(int i=48; i<53; i++) {
							pertoCentro[Dados.turno] += caminho[particular_real(i, Dados.turno)].n_pecas[Dados.turno];
						}
					}
					Dados.proxTurno();
				}
			}
		}
		else {
			if(!movimentoIniciado && caminho[pos].n_pecas[Dados.turno] > 0) {
				if(!Dados.dado1usado && real_particular(pos, Dados.turno) + Dados.dado1 <= 53) {
					if(real_particular(pos, Dados.turno) + Dados.dado1 == 53) {
						//tratar
						caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						centro.Selected();
						centro.possivel = true;
						movimentoIniciado = true;
						
					}
					else {
						caminho[pos].ColorSelected();
						caminho[avancar_casa(pos, +Dados.dado1)].ColorPossivel();
						caminho[avancar_casa(pos, +Dados.dado1)].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;
					}
				}
				if(!Dados.dado2usado && real_particular(pos, Dados.turno) + Dados.dado2 <= 53) {
					if(real_particular(pos, Dados.turno) + Dados.dado2 == 53) {
						//tratar
						caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						centro.Selected();
						centro.possivel = true;
						movimentoIniciado = true;
					}
					else {
						caminho[pos].ColorSelected();
						caminho[avancar_casa(pos, +Dados.dado2)].ColorPossivel();
						caminho[avancar_casa(pos, +Dados.dado2)].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;
					}
				}
				if(!Dados.dado1usado && !Dados.dado2usado && real_particular(pos, Dados.turno) + Dados.dado1 + Dados.dado2 <= 53) {
					if(real_particular(pos, Dados.turno) + Dados.dado1 + Dados.dado2 == 53) {
						//tratar
						caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						centro.Selected();
						centro.possivel = true;
						movimentoIniciado = true;
					}
					else {
						caminho[pos].ColorSelected();
						caminho[avancar_casa(pos, +Dados.dado1+Dados.dado2)].ColorPossivel();
						caminho[avancar_casa(pos, +Dados.dado1+Dados.dado2)].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;
					}
				}
			}
			else {
				movimentoIniciado = false;
				for(int i=0; i<68; i++) {
					caminho[i].ColorNormal();
					caminho[i].possivel = false;
					caminho[i].pecaSaindo = false;
					caminho[i].nascer = false;
					origens[Dados.turno].ColorNormal();
					centro.Normal();
					centro.possivel = false;
				}	
			}
		}
	}
	
	
}


