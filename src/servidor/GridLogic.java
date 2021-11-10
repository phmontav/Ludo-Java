package servidor;

//comentario dummy
import java.util.Stack;

public class GridLogic {
	static CasaServidor caminho[] = new CasaServidor[68];		//Vetor com todas as casas do tabuleiro
	static OrigemServidor origens[] = new OrigemServidor[4];
	static CentroServidor centro = new CentroServidor();
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
	static PecasServidor camadaPecas = new PecasServidor();
	
	public GridLogic() {
		
		for(int linha = 0; linha <15; linha++) {
			for(int col = 0; col<15; col++) {
				if((col==5 || col==7 || col==9)&&(linha<=5 || linha>=9)) {
					caminho[index[linha][col]] = new CasaServidor(index[linha][col], linha, col);
				}
				else {
					if((linha==5 || linha==7 || linha==9)&&(col<5 || col>9)) {
						caminho[index[linha][col]] = new CasaServidor(index[linha][col], linha, col);
					}
					else {
						if((col==5 || col==9)&&(linha==7)) {
							caminho[index[linha][col]] = new CasaServidor(index[linha][col], linha, col);
						}
					}
				}
			}
		}
		DadosServidor dados = new DadosServidor();
		
		origens[0] = new OrigemServidor(0);
		origens[1] = new OrigemServidor(1);
		origens[2] = new OrigemServidor(2);
		origens[3] = new OrigemServidor(3);
	}
	
	public static int particular_real(int pos, int turno) {//0, amarelo -> 12
		if(pos >= 0 && pos < (12*(4 - turno)))
			return (pos + turno*12);
		if(pos >= (12*(4 - turno)) && pos < 48)
			return (pos - (12*(4 - turno)));
		return (pos + turno*5);
	}
	
	public static int real_particular(int pos, int turno) {//12, amarelo -> 0
		if(pos >= turno*12 && pos < 48)
			return (pos - turno*12);
		if(pos < turno*12)
			return (pos + 12*(4 - turno));
		return (pos - turno*5);
	}
	
	public static int avancar_casa(int pos, int n) {
		return (particular_real(real_particular(pos, DadosServidor.turno) + n, DadosServidor.turno));
	}
	
	public static void verificar_colisao() {
		int[][] shift = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
		for(int k = 0; k < 68; k++) {
			if(caminho[k].n_pecas[0] + caminho[k].n_pecas[1] + caminho[k].n_pecas[2] + caminho[k].n_pecas[3] > 1) {
				for(int i = 0; i < 4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						PecasServidor.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x + 12*shift[i][0];
						PecasServidor.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y + 12*shift[i][1];
						ServerLudo.mover_peca(i, caminho[k].pecas.get(i).peek(), caminho[k].x + 12*shift[i][0], caminho[k].y + 12*shift[i][1], caminho[k].n_pecas[i]);
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						PecasServidor.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
				}
			}
			else {
				for(int i = 0; i < 4; i++) {
					Stack<Integer> aux = new Stack<Integer>();
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						PecasServidor.coordPeca[i][caminho[k].pecas.get(i).peek()][0] = caminho[k].x;
						PecasServidor.coordPeca[i][caminho[k].pecas.get(i).peek()][1] = caminho[k].y;
						ServerLudo.mover_peca(i, caminho[k].pecas.get(i).peek(), caminho[k].x, caminho[k].y, caminho[k].n_pecas[i]);
						aux.push(caminho[k].pecas.get(i).pop());
					}
					for(int j = 0; j < caminho[k].n_pecas[i]; j++) {
						caminho[k].pecas.get(i).push(aux.peek());
						PecasServidor.p_juntas[i][aux.pop()] = caminho[k].n_pecas[i];
					}
				}
			}
		}
	}
 	
	public static void verificar_jogada() {
		switch (pertoCentro[DadosServidor.turno]) {
		case 3:
			if(DadosServidor.dado1 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
					(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
					(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53)))
				DadosServidor.dado1usado = true;
			if(DadosServidor.dado2 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
					(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
					(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53)))
				DadosServidor.dado2usado = true;
			break;
		case 2:
			if(centro.n_pecas[DadosServidor.turno] == 1) {
				if(DadosServidor.dado1 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53)))
					DadosServidor.dado1usado = true;
				if(DadosServidor.dado2 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53)))
					DadosServidor.dado2usado = true;
			}
			if(origens[DadosServidor.turno].dentro == 1) {
				if((DadosServidor.dado1 == 6 && !DadosServidor.dado1usado) || (DadosServidor.dado2 == 6 && !DadosServidor.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53))
							DadosServidor.dado1usado = true;
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53))
							DadosServidor.dado2usado = true;
				}
			}
			break;
		case 1:
			if(centro.n_pecas[DadosServidor.turno] == 2) {
				if(DadosServidor.dado1 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
										(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
										(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53)))
					DadosServidor.dado1usado = true;
				if(DadosServidor.dado2 == 6 || ((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
										(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
										(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53)))
					DadosServidor.dado2usado = true;
			}
			if(origens[DadosServidor.turno].dentro == 2) {
				if((DadosServidor.dado1 == 6 && !DadosServidor.dado1usado) || (DadosServidor.dado2 == 6 && !DadosServidor.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53))
							DadosServidor.dado1usado = true;
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53))
							DadosServidor.dado2usado = true;
				}
			}
			if(centro.n_pecas[DadosServidor.turno] == 1 && origens[DadosServidor.turno].dentro == 1) {
				if((DadosServidor.dado1 == 6 && !DadosServidor.dado1usado) || (DadosServidor.dado2 == 6 && !DadosServidor.dado2usado)) {
					//Ele pode gastar os dois dados na peca que ta fora.
				}
				else {
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado1 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado1 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado1 > 53))
							DadosServidor.dado1usado = true;
					if((PecasServidor.posPeca[DadosServidor.turno][0] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][0], DadosServidor.turno) + DadosServidor.dado2 > 53) && 
						(PecasServidor.posPeca[DadosServidor.turno][1] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][1], DadosServidor.turno) + DadosServidor.dado2 > 53) &&
						(PecasServidor.posPeca[DadosServidor.turno][2] == -1 ||  real_particular(PecasServidor.posPeca[DadosServidor.turno][2], DadosServidor.turno) + DadosServidor.dado2 > 53))
							DadosServidor.dado2usado = true;
				}
			}
			break;
		case 0:
			if((PecasServidor.posPeca[DadosServidor.turno][0] == -1) && (PecasServidor.posPeca[DadosServidor.turno][1] == -1) && (PecasServidor.posPeca[DadosServidor.turno][2] == -1)) {
				if(DadosServidor.dado1 != 6 && DadosServidor.dado2 != 6) {
					DadosServidor.dado1usado = DadosServidor.dado2usado = true;
				}
			}
		}
		ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
		if(DadosServidor.dado1usado && DadosServidor.dado2usado)
			DadosServidor.proxTurno();
	}
	
	public static void nascerCasa(int pos) {
		caminho[pos].nascer = false;
		caminho[pos].n_pecas[DadosServidor.turno]++;
		//caminho[pos].ColorNormal();
		caminho[pos].pecas.get(DadosServidor.turno).push(origens[DadosServidor.turno].pecas.pop());
		//origens[DadosServidor.turno].ColorNormal();
		origens[DadosServidor.turno].dentro--;
		movimentoIniciado = false;
		PecasServidor.posPeca[DadosServidor.turno][caminho[pos].pecas.get(DadosServidor.turno).peek()] = pos;
		ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
		ServerLudo.colorir_casa(pos, 0);
		ServerLudo.disponivel_origem(origens[DadosServidor.turno].possivel, movimentoIniciado);
		ServerLudo.colorir_origem(0);
	}
	
	public static void avancoCasa(int avanco, boolean possivel, int colorir) {
		caminho[avanco].possivel = possivel;
		ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
		ServerLudo.colorir_casa(avanco, colorir);
	}
	
	public static void avancoCentro(boolean possivel, int colorir) {
		centro.possivel = possivel;
		//mensagem relativa ao centro
		ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
		ServerLudo.colorir_centro(colorir);
	}
	
	public static void clickCasa(int pos) {
		int avanco = 0;
		if(caminho[pos].nascer == true) {
			if(!DadosServidor.dado1usado && DadosServidor.dado1 == 6) {
				nascerCasa(pos);
				DadosServidor.dado1usado = true;
				verificar_jogada();
				verificar_colisao();
				return;
			}
			else {
				nascerCasa(pos);
				DadosServidor.dado2usado = true;
				verificar_jogada();
				verificar_colisao();
				return;
			}
		}
		if(caminho[pos].possivel) {
			//avanco -> pos
			avanco = avancar_casa(pos, -DadosServidor.dado1);
			if(avanco >= 0 && !DadosServidor.dado1usado && caminho[avanco].pecaSaindo) {
				//caminho[pos].ColorNormal();
				caminho[pos].possivel = false;
				caminho[pos].n_pecas[DadosServidor.turno]++;
				caminho[pos].pecas.get(DadosServidor.turno).push(caminho[avanco].pecas.get(DadosServidor.turno).pop());
				caminho[avanco].n_pecas[DadosServidor.turno]--;
				//caminho[avanco].ColorNormal();
				movimentoIniciado = false;
				caminho[avanco].pecaSaindo = false;
				//mensagem relativa a casa atual: pos
				ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(pos, 0);
				//mensagem relativa a casa posterior: avancar_casa(pos, -Dados.dado1)
				ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(avanco, 0);
				
				//avanco
				if((real_particular(pos, DadosServidor.turno) - DadosServidor.dado1 + DadosServidor.dado2) < 53) {
					avanco = avancar_casa(pos, -DadosServidor.dado1+DadosServidor.dado2);
					avancoCasa(avanco, false, 0);
				}
				if(real_particular(pos, DadosServidor.turno) + DadosServidor.dado2 < 53) {
					avanco = avancar_casa(pos, +DadosServidor.dado2);
					avancoCasa(avanco, false, 0);
				}
				DadosServidor.dado1usado = true;
				PecasServidor.posPeca[DadosServidor.turno][caminho[pos].pecas.get(DadosServidor.turno).peek()] = pos;
				
				avancoCentro(false, 0);
				
				caminho[pos].kill();
				if(pos >= 48) {
					pertoCentro[DadosServidor.turno] = 0;
					for(int i=48; i<53; i++) {
						pertoCentro[DadosServidor.turno] += caminho[particular_real(i, DadosServidor.turno)].n_pecas[DadosServidor.turno];
					}
				}
				verificar_jogada();
				
			}
			else {
				avanco = avancar_casa(pos, -DadosServidor.dado2);
				if(avanco >= 0 && !DadosServidor.dado2usado && caminho[avanco].pecaSaindo) {
					//caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[DadosServidor.turno]++;
					caminho[pos].pecas.get(DadosServidor.turno).push(caminho[avanco].pecas.get(DadosServidor.turno).pop());
					caminho[avanco].n_pecas[DadosServidor.turno]--;
					//caminho[avanco].ColorNormal();
					movimentoIniciado = false;
					caminho[avanco].pecaSaindo = false;
					//mensagem relativa a casa atual: pos
					ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(pos, 0);
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					if(real_particular(pos, DadosServidor.turno) - DadosServidor.dado2 + DadosServidor.dado1 < 53) {
						avanco = avancar_casa(pos, -DadosServidor.dado2+DadosServidor.dado1);
						avancoCasa(avanco, false, 0);
					}
					if(real_particular(pos, DadosServidor.turno) + DadosServidor.dado1 < 53) {
						avanco = avancar_casa(pos, +DadosServidor.dado1);
						avancoCasa(avanco, false, 0);
					}
					DadosServidor.dado2usado = true;
					PecasServidor.posPeca[DadosServidor.turno][caminho[pos].pecas.get(DadosServidor.turno).peek()] = pos;
					
					avancoCentro(false, 0);
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[DadosServidor.turno] = 0;
						for(int i = 48; i < 53; i++) {
							pertoCentro[DadosServidor.turno] += caminho[particular_real(i, DadosServidor.turno)].n_pecas[DadosServidor.turno];
						}
					}
					verificar_jogada();
				}
				else {
					avanco = avancar_casa(pos, -DadosServidor.dado1-DadosServidor.dado2);
					//caminho[pos].ColorNormal();
					caminho[pos].possivel = false;
					caminho[pos].n_pecas[DadosServidor.turno]++;
					caminho[pos].pecas.get(DadosServidor.turno).push(caminho[avanco].pecas.get(DadosServidor.turno).pop());
					caminho[avanco].n_pecas[DadosServidor.turno]--;
					//caminho[avanco].ColorNormal();
					movimentoIniciado = false;
					caminho[avanco].pecaSaindo = false;
					//mensagem relativa a casa atual: pos
					ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(pos, 0);
					//mensagem relativa a casa posterior: avanco
					ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
					ServerLudo.colorir_casa(avanco, 0);
					
					avanco = avancar_casa(pos, -DadosServidor.dado1);
					avancoCasa(avanco, false, 0);
					
					avanco = avancar_casa(pos, -DadosServidor.dado2);
					avancoCasa(avanco, false, 0);
					
					DadosServidor.dado1usado = DadosServidor.dado2usado = true;
					PecasServidor.posPeca[DadosServidor.turno][caminho[pos].pecas.get(DadosServidor.turno).peek()] = pos;
					
					avancoCentro(false, 0);
					
					caminho[pos].kill();
					if(pos >= 48) {
						pertoCentro[DadosServidor.turno] = 0;
						for(int i=48; i<53; i++) {
							pertoCentro[DadosServidor.turno] += caminho[particular_real(i, DadosServidor.turno)].n_pecas[DadosServidor.turno];
						}
					}
					ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
					DadosServidor.proxTurno();
				}
			}
		}
		else {
			if(!movimentoIniciado && caminho[pos].n_pecas[DadosServidor.turno] > 0) {
				if(!DadosServidor.dado1usado && real_particular(pos, DadosServidor.turno) + DadosServidor.dado1 <= 53) {
					if(real_particular(pos, DadosServidor.turno) + DadosServidor.dado1 == 53) {
						//tratar
						//caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						//centro.Selected();
						movimentoIniciado = true;
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa ao centro
						centro.possivel = true;
						ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
						ServerLudo.colorir_centro(1);
						
					}
					else {
						//caminho[pos].ColorSelected();
						//caminho[avanco].ColorPossivel();
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						
						avanco = avancar_casa(pos, +DadosServidor.dado1);
						avancoCasa(avanco, true, 1);
					}
				}
				if(!DadosServidor.dado2usado && real_particular(pos, DadosServidor.turno) + DadosServidor.dado2 <= 53) {
					if(real_particular(pos, DadosServidor.turno) + DadosServidor.dado2 == 53) {
						//tratar
						//caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						//centro.Selected();
						movimentoIniciado = true;
						
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);

						//mensagem relativa ao centro
						avancoCentro(true, 1);
					}
					else {
						//caminho[pos].ColorSelected();
						//caminho[avanco].ColorPossivel();
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						
						avanco = avancar_casa(pos, +DadosServidor.dado2);
						avancoCasa(avanco, true, 1);
					}
				}
				if(!DadosServidor.dado1usado && !DadosServidor.dado2usado && real_particular(pos, DadosServidor.turno) + DadosServidor.dado1 + DadosServidor.dado2 <= 53) {
					if(real_particular(pos, DadosServidor.turno) + DadosServidor.dado1 + DadosServidor.dado2 == 53) {
						//tratar
						//caminho[pos].ColorSelected();
						caminho[pos].pecaSaindo = true;
						//centro.Selected();
						movimentoIniciado = true;
						
						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						//mensagem relativa ao centro
						avancoCentro(true, 1);
					}
					else {
						//caminho[pos].ColorSelected();
						//caminho[avanco].ColorPossivel();
						movimentoIniciado = true;
						caminho[pos].pecaSaindo = true;

						//mensagem relativa a casa atual: pos
						ServerLudo.disponivel_casa(pos, caminho[pos].possivel, caminho[pos].nascer, caminho[pos].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(pos, 2);
						
						avanco = avancar_casa(pos, +DadosServidor.dado1+DadosServidor.dado2);
						avancoCasa(avanco, true, 1);
					}
				}
			}
			else {
				movimentoIniciado = false;
				for(int i = 0; i < 68; i++) {
					if(caminho[i].possivel == true || caminho[i].pecaSaindo == true || caminho[i].nascer == true) {
						avanco = i;
						//caminho[i].ColorNormal();
						caminho[i].possivel = false;
						caminho[i].pecaSaindo = false;
						caminho[i].nascer = false;
						//mensagem relativa a casa posterior: avanco
						ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
						ServerLudo.colorir_casa(avanco, 0);
					}
				}
				//origens[DadosServidor.turno].ColorNormal();
				ServerLudo.disponivel_origem(origens[DadosServidor.turno].possivel, movimentoIniciado);
				ServerLudo.colorir_origem(0);
				
				//centro.Normal();
				centro.possivel = false;	
				ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
				ServerLudo.colorir_centro(0);
			}
		}
		verificar_colisao();
	}
	
	public static void playCentro(int avanco) {
		centro.possivel = false;
		centro.n_pecas[DadosServidor.turno]++;
		centro.pecas.get(DadosServidor.turno).push(caminho[avanco].pecas.get(DadosServidor.turno).pop());
		caminho[avanco].n_pecas[DadosServidor.turno]--;
		caminho[avanco].pecaSaindo = false;
		movimentoIniciado = false;
		
		//mensagem relativa a casa avanco
		ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
		ServerLudo.colorir_casa(avanco, 0);
		//mensagem relativa ao centro
		ServerLudo.disponivel_centro(centro.possivel, movimentoIniciado);
		ServerLudo.colorir_centro(0);
		
		for(int i = 0; i < 68; i++) {
			if(caminho[i].possivel == true || caminho[i].pecaSaindo == true || caminho[i].nascer == true) {
				avanco = i;
				caminho[i].possivel = false;
				caminho[i].pecaSaindo = false;
				caminho[i].nascer = false;
				//mensagem relativa a casa posterior: avanco
				ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
				ServerLudo.colorir_casa(avanco, 0);
			}
		}
		
		PecasServidor.coordPeca[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][0] = CentroServidor.posFinal[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][0];
		PecasServidor.coordPeca[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][1] = CentroServidor.posFinal[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][1];
		PecasServidor.posPeca[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()] = -1;
		ServerLudo.mover_peca(DadosServidor.turno, centro.pecas.get(DadosServidor.turno).peek(), CentroServidor.posFinal[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][0], CentroServidor.posFinal[DadosServidor.turno][centro.pecas.get(DadosServidor.turno).peek()][1], 1);

	}
	
	public static void clickCentro() {
		int avanco = -1;
		if((53 - DadosServidor.dado1) >= 47 && !DadosServidor.dado1usado && caminho[particular_real(53 - DadosServidor.dado1, DadosServidor.turno)].pecaSaindo) {
			avanco = particular_real(53 - DadosServidor.dado1, DadosServidor.turno);
			playCentro(avanco);
			DadosServidor.dado1usado = true;
		}
		else {
			if((53 - DadosServidor.dado2) >= 47 && !DadosServidor.dado2usado && caminho[particular_real(53 - DadosServidor.dado2, DadosServidor.turno)].pecaSaindo) {
				avanco = particular_real(53 - DadosServidor.dado2, DadosServidor.turno);
				playCentro(avanco);
				DadosServidor.dado2usado = true;
			}
			else {
				avanco = particular_real(53 - DadosServidor.dado1 - DadosServidor.dado2, DadosServidor.turno);
				playCentro(avanco);
				DadosServidor.dado1usado = DadosServidor.dado2usado = true;
			}
		}
		pertoCentro[DadosServidor.turno] = 0;
		for(int i = 48; i < 53; i++) {
			pertoCentro[DadosServidor.turno] += caminho[particular_real(i, DadosServidor.turno)].n_pecas[DadosServidor.turno];
		}
		if(centro.n_pecas[DadosServidor.turno] == 3)
		{
			//Fim do Jogo
			ServerLudo.mensagem_controle(2, DadosServidor.turno);
		}
		ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
		verificar_colisao();
		verificar_jogada();
	}
	
	public static void playOrigem(int cor, int avanco) {
		caminho[avanco].nascer = true;
		movimentoIniciado = true;
		origens[cor].possivel = true;
		
		ServerLudo.disponivel_origem(origens[cor].possivel, movimentoIniciado);
		ServerLudo.colorir_origem(1);
		
		//mensagem relativa a casa avanco
		ServerLudo.disponivel_casa(avanco, caminho[avanco].possivel, caminho[avanco].nascer, caminho[avanco].pecaSaindo, movimentoIniciado);
		ServerLudo.colorir_casa(avanco, 1);
	}
	
	public static void clickOrigem(int cor) {
		int avanco = 0;
		if(DadosServidor.dado1 == 6 && !DadosServidor.dado1usado && !movimentoIniciado && origens[cor].dentro > 0 && DadosServidor.turno == cor) {
			avanco = particular_real(0, DadosServidor.turno);
			playOrigem(cor, avanco);
		}
		else if(DadosServidor.dado2 == 6 && !DadosServidor.dado2usado && !movimentoIniciado && origens[cor].dentro>0 && DadosServidor.turno == cor) {
			avanco = particular_real(0, DadosServidor.turno);
			playOrigem(cor, avanco);
		}
		origens[cor].possivel = false;
	}
}


