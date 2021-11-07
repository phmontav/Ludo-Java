package servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class CasaServidor extends JPanel {
	
	public int pos;
	public int x, y;
	public int[] n_pecas = {0, 0, 0, 0};
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	public boolean possivel;
	public boolean nascer;
	public boolean pecaSaindo;
	public JLayeredPane camadasRef;
	
	public CasaServidor(int n, int y_n, int x_n, JLayeredPane camadas){
		this.pos= n;
		possivel = false;
		nascer = false;
		pecaSaindo = false;
		x = x_n*47;
		y = y_n*47;
		camadasRef = camadas;
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
	}
	
	public void kill() {
		if(pos != 0 && pos != 12 && pos != 24 && pos != 36) {
			for(int i=0; i<4; i++) {
				if(n_pecas[i] > 0 && i != DadosServidor.turno) {
					while(n_pecas[i] > 0) {
						
						PecasServidor.coordPeca[i][pecas.get(i).peek()][0] = PecasServidor.backup[i][pecas.get(i).peek()][0];
						PecasServidor.coordPeca[i][pecas.get(i).peek()][1] = PecasServidor.backup[i][pecas.get(i).peek()][1];
						PecasServidor.posPeca[i][pecas.get(i).peek()] = -1;
						ServerLudo.mover_peca(i, pecas.get(i).peek(), PecasServidor.backup[i][pecas.get(i).peek()][0], PecasServidor.backup[i][pecas.get(i).peek()][1], 1);
						n_pecas[i]--;
						GridLogic.origens[i].dentro++;
						GridLogic.origens[i].pecas.push(pecas.get(i).peek());
						PecasServidor.p_juntas[i][pecas.get(i).pop()] = 1;
					}
				}
			}
		}
	}
}
