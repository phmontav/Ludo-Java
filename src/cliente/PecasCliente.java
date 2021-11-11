package cliente;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PecasCliente extends JPanel {
	
	static public int[][][] backup =  {{{11*47+27, 12*47+10}, {12*47, 12*47-20}, {13*47-27, 12*47+10}},
			  					{{11*47+27, 2*47+10}, {12*47, 2*47-20}, {13*47-27, 2*47+10}},
			  					{{1*47+27, 2*47+10}, {2*47, 2*47-20}, {3*47-27, 2*47+10}},
			  					{{1*47+27, 12*47+10}, {2*47, 12*47-20}, {3*47-27, 12*47+10}}};
	static public int[][][] coordPeca = {{{11*47+27, 12*47+10}, {12*47, 12*47-20}, {13*47-27, 12*47+10}},
								  {{11*47+27, 2*47+10}, {12*47, 2*47-20}, {13*47-27, 2*47+10}},
								  {{1*47+27, 2*47+10}, {2*47, 2*47-20}, {3*47-27, 2*47+10}},
								  {{1*47+27, 12*47+10}, {2*47, 12*47-20}, {3*47-27, 12*47+10}}};
	static public int[][] posPeca = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
	static public int[][] p_juntas = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
	URL url = getClass().getResource("/resource/PecaAzul.png");
	static Image[] img_az = new Image[3], img_am = new Image[3], img_vm = new Image[3], img_vd = new Image[3];
	
	public PecasCliente() {
		this.setBounds(0, 0, 705, 705);
		this.setOpaque(false);
		URL url = getClass().getResource("/resource/PecaAzul.png");
		img_az[0] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaAzul2.png");
		img_az[1] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaAzul3.png");
		img_az[2] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaAmarela.png");
		img_am[0] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaAmarela2.png");
		img_am[1] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaAmarela3.png");
		img_am[2] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVermelha.png");
		img_vm[0] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVermelha2.png");
		img_vm[1] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVermelha3.png");
		img_vm[2] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVerde.png");
		img_vd[0] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVerde2.png");
		img_vd[1] = new ImageIcon(url).getImage();
		url = getClass().getResource("/resource/PecaVerde3.png");
		img_vd[2] = new ImageIcon(url).getImage();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_az[p_juntas[0][0]-1], coordPeca[0][0][0], coordPeca[0][0][1], null);
		g.drawImage(img_az[p_juntas[0][1]-1], coordPeca[0][1][0], coordPeca[0][1][1], null);
		g.drawImage(img_az[p_juntas[0][2]-1], coordPeca[0][2][0], coordPeca[0][2][1], null);
		
		g.drawImage(img_am[p_juntas[1][0]-1], coordPeca[1][0][0], coordPeca[1][0][1], null);
		g.drawImage(img_am[p_juntas[1][1]-1], coordPeca[1][1][0], coordPeca[1][1][1], null);
		g.drawImage(img_am[p_juntas[1][2]-1], coordPeca[1][2][0], coordPeca[1][2][1], null);
		
		g.drawImage(img_vm[p_juntas[2][0]-1], coordPeca[2][0][0], coordPeca[2][0][1], null);
		g.drawImage(img_vm[p_juntas[2][1]-1], coordPeca[2][1][0], coordPeca[2][1][1], null);
		g.drawImage(img_vm[p_juntas[2][2]-1], coordPeca[2][2][0], coordPeca[2][2][1], null);
		
		g.drawImage(img_vd[p_juntas[3][0]-1], coordPeca[3][0][0], coordPeca[3][0][1], null);
		g.drawImage(img_vd[p_juntas[3][1]-1], coordPeca[3][1][0], coordPeca[3][1][1], null);
		g.drawImage(img_vd[p_juntas[3][2]-1], coordPeca[3][2][0], coordPeca[3][2][1], null);
	}
}
