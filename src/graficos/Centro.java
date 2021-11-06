package graficos;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Centro extends JPanel implements MouseListener{

	public boolean possivel;
	public int[] n_pecas = {0, 0, 0, 0};
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	public static JLayeredPane camadasRef;
	Image img_atual, img_normal, img_selected;
	public static int[][][] posFinal = {{{6*47+25, 8*47-7}, {7*47, 8*47}, {8*47-25, 8*47-7}},
								{{8*47-7, 6*47+25}, {8*47, 7*47}, {8*47-7, 8*47-25}},
								{{6*47+25, 6*47+7}, {7*47, 6*47}, {8*47-25, 6*47+7}},
								{{6*47+7, 6*47+25}, {6*47, 7*47}, {6*47+7, 8*47-25}}};

	public Centro(){
		this.setBounds(6*47, 6*47, 141, 141);
		this.setOpaque(false);
		possivel = false;
		addMouseListener(this);
		img_atual = img_normal = new ImageIcon("Centro.png").getImage();
		img_selected = new ImageIcon("CentroSelected.png").getImage();
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_atual, 0, 0, null);
	}
	
	public void Selected() {
		img_atual = img_selected;
		repaint();
	}
	
	public void Normal() {
		img_atual = img_normal;
		repaint();
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
		if(possivel) {
			if((53 - Dados.dado1) >= 47 && !Dados.dado1usado && GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].pecaSaindo) {
				Normal();
				possivel = false;
				n_pecas[Dados.turno]++;
				pecas.get(Dados.turno).push(GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].pecas.get(Dados.turno).pop());
				GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].n_pecas[Dados.turno]--;
				GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].ColorNormal();
				GridTest.movimentoIniciado = false;
				GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].pecaSaindo = false;
				
				GridTest.movimentoIniciado = false;
				for(int i=0; i<68; i++) {
					GridTest.caminho[i].ColorNormal();
					GridTest.caminho[i].possivel = false;
					GridTest.caminho[i].pecaSaindo = false;
					GridTest.caminho[i].nascer = false;
				}
				
				Dados.dado1usado = true;
				Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][0] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][0];
				Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][1] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][1];
				Pecas.posPeca[Dados.turno][pecas.get(Dados.turno).peek()] = -1;
				
			}
			else {
				if((53 - Dados.dado2) >= 47 && !Dados.dado2usado && GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].pecaSaindo) {
					Normal();
					possivel = false;
					n_pecas[Dados.turno]++;
					pecas.get(Dados.turno).push(GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].pecas.get(Dados.turno).pop());
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].n_pecas[Dados.turno]--;
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].ColorNormal();
					GridTest.movimentoIniciado = false;
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].pecaSaindo = false;
					
					GridTest.movimentoIniciado = false;
					for(int i=0; i<68; i++) {
						GridTest.caminho[i].ColorNormal();
						GridTest.caminho[i].possivel = false;
						GridTest.caminho[i].pecaSaindo = false;
						GridTest.caminho[i].nascer = false;
					}
					
					Dados.dado2usado = true;
					Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][0] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][0];
					Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][1] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][1];
					Pecas.posPeca[Dados.turno][pecas.get(Dados.turno).peek()] = -1;
				}
				else {
					Normal();
					possivel = false;
					n_pecas[Dados.turno]++;
					pecas.get(Dados.turno).push(GridTest.caminho[GridTest.particular_real(53 - Dados.dado1 - Dados.dado2, Dados.turno)].pecas.get(Dados.turno).pop());
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado1 - Dados.dado2, Dados.turno)].n_pecas[Dados.turno]--;
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado1 - Dados.dado2, Dados.turno)].ColorNormal();
					GridTest.movimentoIniciado = false;
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado1 - Dados.dado2, Dados.turno)].pecaSaindo = false;
					
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].possivel = GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].possivel = false;
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado1, Dados.turno)].ColorNormal();
					GridTest.caminho[GridTest.particular_real(53 - Dados.dado2, Dados.turno)].ColorNormal();
					Dados.dado1usado = Dados.dado2usado = true;
					Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][0] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][0];
					Pecas.coordPeca[Dados.turno][pecas.get(Dados.turno).peek()][1] = posFinal[Dados.turno][pecas.get(Dados.turno).peek()][1];
					Pecas.posPeca[Dados.turno][pecas.get(Dados.turno).peek()] = -1;
				}
			}
			GridTest.pertoCentro[Dados.turno] = 0;
			for(int i = 48; i < 53; i++) {
				GridTest.pertoCentro[Dados.turno] += GridTest.caminho[GridTest.particular_real(i, Dados.turno)].n_pecas[Dados.turno];
			}
			GridTest.verificar_colisao();
			camadasRef.repaint();
			GridTest.verificar_jogada();
		}
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
