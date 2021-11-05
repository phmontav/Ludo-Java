package servidor;

import graficos.*;
//comentario dummy
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GridLogic {
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
	
	public GridLogic() {
		
		JLayeredPane camadas = new JLayeredPane();	//Organiza os gr�ficos que est�o aparecendo por camadas
		
		JFrame frame = new JFrame();
		//frame.setLayout(new GridLayout(15, 15, 0, 0));
		//frame.getContentPane().setBackground(Color.white);
		
		JPanel canvas1 = new JPanel();		
		
		JPanel tabuleiro = new JPanel();
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
		
	}
	
	public static int particular_real(int pos, int turno) {//0, amarelo -> 12
		if(pos >= 0 && pos < (12 * (4 - turno)))
			return (pos + turno*12);
		if(pos >= (12*(4-turno)) && pos<48)
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
		for(int k = 0; k < 68; k++) {
			if(caminho[k].n_pecas[0] + caminho[k].n_pecas[1] + caminho[k].n_pecas[2] + caminho[k].n_pecas[3] > 1) {
				for(int i = 0; i < 4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x + 12*shift[i][0];
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y + 12*shift[i][1];
						ServerLudo.mover_peca(i, caminho[k].pecas.get(i).peek(), caminho[k].x + 12*shift[i][0], caminho[k].y + 12*shift[i][1], caminho[k].n_pecas[i]);
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						Pecas.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
				}
			}
			else {
				for(int i = 0; i < 4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x;
						Pecas.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y;
						ServerLudo.mover_peca(i, caminho[k].pecas.get(i).peek(), caminho[k].x, caminho[k].y, caminho[k].n_pecas[i]);
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						Pecas.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
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
		ServerLudo.atualizar_dados(Dados.dado1, Dados.dado1usado, Dados.dado2, Dados.dado2usado);
		if(Dados.dado1usado && Dados.dado2usado)
			Dados.proxTurno();
	}
	
	public static void clickCasa(int pos) {
		int avanco = 0;
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
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(pos, 0);
				ServerLudo.disponivel_origem(origens[Dados.turno].possivel, movimentoIniciado);
				ServerLudo.colorir_origem(0);
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
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(pos, 0);
				ServerLudo.disponivel_origem(origens[Dados.turno].possivel, movimentoIniciado);
				ServerLudo.colorir_origem(0);
				verificar_jogada();
				return;
			}
		}
		if(caminho[pos].possivel) {
			avanco = avancar_casa(pos, -Dados.dado1);
			if(avanco >= 0 && !Dados.dado1usado && caminho[avanco].pecaSaindo) {
				caminho[pos].ColorNormal();
				caminho[pos].possivel = false;
				caminho[pos].n_pecas[Dados.turno]++;
				caminho[pos].pecas.get(Dados.turno).push(caminho[avanco].pecas.get(Dados.turno).pop());
				caminho[avanco].n_pecas[Dados.turno]--;
				caminho[avanco].ColorNormal();
				movimentoIniciado = false;
				caminho[avanco].pecaSaindo = false;
				//mensagem relativa a casa atual: pos
				ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(pos, 0);
				//mensagem relativa a casa posterior: avancar_casa(pos, -Dados.dado1)
				ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(avanco, 0);
				
				
				if((real_particular(pos, Dados.turno) - Dados.dado1 + Dados.dado2) < 53) {
					avanco = avancar_casa(pos, -Dados.dado1+Dados.dado2);
					caminho[avanco].possivel = false;
					caminho[avanco].ColorNormal();
					//mensagem relativa a casa posterior: avancar_casa(pos, -Dados.dado1+Dados.dado2)
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
				}
				if(real_particular(pos, Dados.turno) + Dados.dado2 < 53) {
					avanco = avancar_casa(pos, +Dados.dado2);
					caminho[avanco].possivel = false;
					caminho[avanco].ColorNormal();
					//mensagem relativa a casa posterior: avancar_casa(pos, +Dados.dado2)
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
				}
				Dados.dado1usado = true;
				Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
				
				centro.Normal();
				centro.possivel = false;
				//mensagem relativa ao centro
				ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
				ServerLudo.colorir_centro(0);
				
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
				avanco = avancar_casa(pos, -Dados.dado2);
				if(avanco >= 0 && !Dados.dado2usado && caminho[avanco].pecaSaindo) {
					caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[Dados.turno]++;
					caminho[pos].pecas.get(Dados.turno).push(caminho[avanco].pecas.get(Dados.turno).pop());
					caminho[avanco].n_pecas[Dados.turno]--;
					caminho[avanco].ColorNormal();
					movimentoIniciado = false;
					caminho[avanco].pecaSaindo = false;
					//mensagem relativa a casa atual: pos
					ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(pos, 0);
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					if(real_particular(pos, Dados.turno) - Dados.dado2 + Dados.dado1 < 53) {
						avanco = avancar_casa(pos, -Dados.dado2+Dados.dado1);
						caminho[avanco].possivel = false;
						caminho[avanco].ColorNormal();
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 0);
					}
					if(real_particular(pos, Dados.turno) + Dados.dado1 < 53) {
						avanco = avancar_casa(pos, +Dados.dado1);
						caminho[avanco].possivel = false;
						caminho[avanco].ColorNormal();
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 0);
					}
					Dados.dado2usado = true;
					Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
					
					centro.Normal();
					centro.possivel = false;
					//mensagem relativa ao centro
					ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
					ServerLudo.colorir_centro(0);
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[Dados.turno] = 0;
						for(int i = 48; i < 53; i++) {
							pertoCentro[Dados.turno] += caminho[particular_real(i, Dados.turno)].n_pecas[Dados.turno];
						}
					}
					verificar_jogada();
				}
				else {
					avanco = avancar_casa(pos, -Dados.dado1-Dados.dado2);
					caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[Dados.turno]++;
					caminho[pos].pecas.get(Dados.turno).push(caminho[avanco].pecas.get(Dados.turno).pop());
					caminho[avanco].n_pecas[Dados.turno]--;
					caminho[avanco].ColorNormal();
					movimentoIniciado = false;
					caminho[avanco].pecaSaindo = false;
					//mensagem relativa a casa atual: pos
					ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(pos, 0);
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					avanco = avancar_casa(pos, -Dados.dado1);
					caminho[avanco].possivel = false;
					caminho[avanco].ColorNormal();
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					avanco = avancar_casa(pos, -Dados.dado2);
					caminho[avanco].possivel = false;
					caminho[avanco].ColorNormal();
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					
					Dados.dado1usado = Dados.dado2usado = true;
					Pecas.posPeca[Dados.turno][caminho[pos].pecas.get(Dados.turno).peek()] = pos;
					
					centro.Normal();
					centro.possivel = false;
					//mensagem relativa ao centro
					ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
					ServerLudo.colorir_centro(0);
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[Dados.turno] = 0;
						for(int i=48; i<53; i++) {
							pertoCentro[Dados.turno] += caminho[particular_real(i, Dados.turno)].n_pecas[Dados.turno];
						}
					}
					ServerLudo.atualizar_dados(Dados.dado1, Dados.dado1usado, Dados.dado2, Dados.dado2usado);
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
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa ao centro
						ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
						ServerLudo.colorir_centro(1);
						
					}
					else {
						avanco = avancar_casa(pos, +Dados.dado1);
						caminho[pos].ColorSelected();
						caminho[avanco].ColorPossivel();
						caminho[avanco].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 1);
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
						
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa ao centro
						ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
						ServerLudo.colorir_centro(1);
					}
					else {
						avanco = avancar_casa(pos, +Dados.dado2);
						caminho[pos].ColorSelected();
						caminho[avanco].ColorPossivel();
						caminho[avanco].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 1);
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
						
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa ao centro
						ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
						ServerLudo.colorir_centro(1);
					}
					else {
						avanco = avancar_casa(pos, +Dados.dado1+Dados.dado2);
						caminho[pos].ColorSelected();
						caminho[avanco].ColorPossivel();
						caminho[avanco].possivel = true;
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 1);
					}
				}
			}
			else {
				movimentoIniciado = false;
				for(int i = 0; i < 68; i++) {
					if(caminho[i].possivel == true || caminho[i].pecaSaindo == true || caminho[i].nascer == true) {
						avanco = i;
						caminho[i].ColorNormal();
						caminho[i].possivel = false;
						caminho[i].pecaSaindo = false;
						caminho[i].nascer = false;
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 0);
					}
				}
				origens[Dados.turno].ColorNormal();
				ServerLudo.disponivel_origem(origens[Dados.turno].possivel, movimentoIniciado);
				ServerLudo.colorir_origem(0);
				
				centro.Normal();
				centro.possivel = false;	
				ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
				ServerLudo.colorir_centro(0);
			}
		}
	}
	
	
}


