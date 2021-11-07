package servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class CentroServidor extends JPanel {

	public boolean possivel;
	public int[] n_pecas = {0, 0, 0, 0};
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	public static JLayeredPane camadasRef;
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
}
