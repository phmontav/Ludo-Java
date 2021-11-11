package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import javax.swing.*;

public class TelaInicial implements ActionListener{

	static JFrame frame = new JFrame();
	JButton botao = new JButton("Conectar");
	URL url = getClass().getResource("/resource/TelaInicialLudo.png");
	ImageIcon fundo = new ImageIcon(url);
	JLabel fundoLabel = new JLabel();
	
	private static JTextField apelido;
	private static JTextField enderecoIP;
	
	TelaInicial() {
		
		frame.setTitle("LUDO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 745);
		frame.setLocationRelativeTo(null);
		
		JLayeredPane camadas = new JLayeredPane();
		camadas.setBounds(0, 0, 1000, 745);
		frame.add(camadas);
		
		fundoLabel.setIcon(fundo);
		fundoLabel.setBounds(0, -40, 1000, 745);
		
		botao.setBounds(17, 560, 254, 39);
		botao.setFocusable(false);
		botao.addActionListener(inserir);
		botao.setBackground(new Color(0xea374d));
		botao.setForeground(Color.white);
		botao.setFont(new Font("Arial", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		apelido = new JTextField();
		apelido.setBounds(17, 381, 254, 39);
		apelido.setBackground(Color.white);
		apelido.setBorder(null);
		apelido.setFont(new Font("Arial", Font.PLAIN, 20));
		
		enderecoIP = new JTextField();
		enderecoIP.setBounds(17, 475, 254, 39);
		enderecoIP.addActionListener(inserir);
		enderecoIP.setBackground(Color.white);
		enderecoIP.setBorder(null);
		enderecoIP.setFont(new Font("Arial", Font.PLAIN, 20));
		
		panel.add(apelido);
		panel.add(enderecoIP);
		panel.setOpaque(false);
		panel.setBounds(350, 0, 300, 745);
		panel.add(botao);
		
		camadas.add(panel);
		camadas.add(fundoLabel);
		
		frame.setVisible(true);
	}

	
	Action inserir = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	String nick = apelido.getText();
	    	if(nick.isBlank())
	    		nick = "SemNome";
	    	try {
	    		ClientLudo.Conectar(enderecoIP.getText(), nick);
			} catch (UnknownHostException e1) {
				System.out.println("Insira IP válido e tente novamente.");
			} catch (IOException e1) {
				System.out.println("Problema de Conexão. Tente novamente.");
			}
	    }
	};

	@Override
	public void actionPerformed(ActionEvent e) {}
	
}
