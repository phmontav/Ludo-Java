package servidor;

public class JogadorMaquina {
	
	public static void avancarCentro(int meuTurno) throws InterruptedException {
		for(int i = 0; i < 3; i++) {
			if(PecasServidor.posPeca[meuTurno][i] != -1) {
				GridLogic.clickCasa(PecasServidor.posPeca[meuTurno][i]);
				if(GridLogic.centro.possivel && GridLogic.movimentoIniciado) {
					Thread.sleep(1000);
					GridLogic.clickCentro();
					Thread.sleep(500);
				}
			}
		}
	}
	
	public static void sairOrigem(int meuTurno) throws InterruptedException {
		GridLogic.clickOrigem(meuTurno);
		int avanco = GridLogic.particular_real(0, DadosServidor.turno);
		if(GridLogic.movimentoIniciado && GridLogic.caminho[avanco].nascer == true) {
			Thread.sleep(1000);
			GridLogic.clickCasa(avanco);
			Thread.sleep(500);
		}
		
		GridLogic.clickOrigem(meuTurno);
		avanco = GridLogic.particular_real(0, DadosServidor.turno);
		if(GridLogic.movimentoIniciado && GridLogic.caminho[avanco].nascer == true) {
			Thread.sleep(1000);
			GridLogic.clickCasa(avanco);
			Thread.sleep(500);
		}
	}
	
	public static void avancarCasa(int meuTurno) throws InterruptedException {
		for(int k = 0; k < 2; k++) {
			for(int i = 0; i < 3; i++) {
				int pos = PecasServidor.posPeca[meuTurno][i];
				if(pos != -1) {
					GridLogic.clickCasa(pos);
					for(int j = 67; j >= 0; j--) {
						if(GridLogic.caminho[j].possivel) {
							int avanco = j;
							Thread.sleep(1000);
							GridLogic.clickCasa(avanco);
							Thread.sleep(500);
						}
					}
				}
			}
		}
	}
	
	public static void jogarMaquina(int meuTurno) {
		//Rola dados para iniciar a jogada
		try {
			while(meuTurno == ServerLudo.turnoAtual) {
				DadosServidor.rolarDados();
				Thread.sleep(2000);
				avancarCentro(meuTurno);

				sairOrigem(meuTurno);

				avancarCasa(meuTurno);
			}
		}
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}
