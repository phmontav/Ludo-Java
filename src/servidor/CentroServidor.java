package servidor;

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

public class CentroServidor extends JPanel implements MouseListener{

	public boolean possivel;
	public int[] n_pecas = {0, 0, 0, 0};
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	public static JLayeredPane camadasRef;
	Image img_atual, img_normal, img_selected;
	public static int[][][] posFinal = {{{6*47+25, 8*47-7}, {7*47, 8*47}, {8*47-25, 8*47-7}},
								{{8*47-7, 6*47+25}, {8*47, 7*47}, {8*47-7, 8*47-25}},
								{{6*47+25, 6*47+7}, {7*47, 6*47}, {8*47-25, 6*47+7}},
								{{6*47+7, 6*47+25}, {6*47, 7*47}, {6*47+7, 8*47-25}}};

	public CentroServidor(){
		possivel = false;
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
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
			GridLogic.clickCentro();
			GridLogic.verificar_colisao();
			GridLogic.verificar_jogada();
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
