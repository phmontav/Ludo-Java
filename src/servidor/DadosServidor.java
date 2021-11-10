package servidor;

import java.util.Random;

public class DadosServidor {

	static public int dado1, dado2, turno;						//Valor do dado 1, valor do dado 2 e valor do jogador que est� jogando agora(1, 2, 3 ou 4 para azul, amarelo, vermelho e verde)
	static public boolean dado1usado = true, dado2usado = true;	//Indica se determinado dado ja foi utilizado na jogada atual
	static Random random = new Random();
	static int chance = 2;
	
	public DadosServidor() {
		random = new Random();
		turno = 0;
	}
	
	static public void proxTurno() {
		if(DadosServidor.dado1 == 6 && DadosServidor.dado2 == 6) {
			//repete o turno
		}
		else {
//			if(++turno >= ServerLudo.qtConectada) {
//				turno = 0;
//			}
			turno = (turno + 1)%4;
		}
		ServerLudo.turnoAtual = turno;
		ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
		System.out.println("Jogador atual: " + turno);	//--------------------------------------Enviar mensagem para a tabela de informa��es.
	}

	static public void rolarDados() {
		if(dado1usado && dado2usado) {
			dado1 = random.nextInt(6) + 1; //Sorteia um numero inteiro entre 0 e 5, em seguida soma 1;
			dado2 = random.nextInt(6) + 1;
			dado1usado = false;
			dado2usado = false;
			ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
			ServerLudo.animar_dados();
			System.out.println("       Jogador " + turno + " tirou " + dado1 + " e " + dado2);
			if(GridLogic.origens[turno].dentro + GridLogic.centro.n_pecas[turno] == 3 && dado1 != 6 && dado2 != 6) {
				if(chance == 0) {
					System.out.println("       Jogador " + turno + " perdeu a vez.");
					chance = 2;
					dado1usado = true;
					dado2usado = true;
					proxTurno();
					return;
				}
				else {
					System.out.println("       Jogador " + turno + " tem mais " + chance + " chance(s) de rolar os dados.");
					chance--;
					dado1usado = true;
					dado2usado = true;
					ServerLudo.atualizar_dados(DadosServidor.dado1, DadosServidor.dado1usado, DadosServidor.dado2, DadosServidor.dado2usado);
					return;
				}
			}
			chance = 2;
			GridLogic.verificar_jogada();
		}
	}
}
