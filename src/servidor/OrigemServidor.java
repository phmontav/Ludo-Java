package servidor;

import java.util.Stack;
import javax.swing.JLayeredPane;

public class OrigemServidor {

	public int dentro;
	public int cor;
	public boolean possivel;
	public Stack<Integer> pecas;
	public static JLayeredPane camadasRef;

	public OrigemServidor(int n){
		cor = n;
		possivel = false;
		this.dentro= 3;
		pecas = new Stack<Integer>();
		pecas.push(0);
		pecas.push(1);
		pecas.push(2);	
	}
}
