import java.awt.Color;
import java.awt.Font;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class listaDeEspera {

	static JFrame frame;
	static JPanel painel;
	static JLabel label;
	static MaskFormatter data;
	static MaskFormatter hora;
	static JTextField lista;
	static JTextField texto;
	static JTextField acompanhantes;
	static JButton botao;
	static JButton saiu;
	static JButton desistiu;
	static JTable table;
	static JScrollBar barra;
	static JScrollPane roll;

	static int senha = 0;

	static lista cliente;

	static String[] campos = new String[] { "Lista", "Nome", "Acompanhantes" };

	static DefaultTableModel tabela2;

	public static void main(String[] args) {

		menu();

	}

	static void menu() {

		frame = new JFrame();
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		painel = new JPanel();
		painel.setLayout(null);
		painel.setBackground(Color.GREEN);
		painel.setSize(1000, 600);
		painel.setVisible(true);
		frame.add(painel);

		label = new JLabel("Lista de Espera");
		label.setFont(new Font("Dialog", Font.ITALIC, 32));
		label.setSize(500, 200);
		label.setLocation(350, -40);
		label.setVisible(true);
		painel.add(label);

		label = new JLabel("Nome:");
		label.setFont(new Font("Dialog", Font.HANGING_BASELINE, 15));
		label.setSize(100, 300);
		label.setLocation(50, 30);
		label.setVisible(true);
		painel.add(label);

		label = new JLabel("Número de acompanhantes:");
		label.setFont(new Font("Dialog", Font.HANGING_BASELINE, 15));
		label.setSize(200, 300);
		label.setLocation(50, 60);
		label.setVisible(true);
		painel.add(label);

		label = new JLabel("add:");
		label.setFont(new Font("Dialog", Font.ITALIC, 15));
		label.setSize(100, 300);
		label.setLocation(610, 30);
		label.setVisible(true);
		painel.add(label);

		texto = new JTextField();
		texto.setBackground(Color.lightGray);
		texto.setFont(new Font("Dialog", Font.HANGING_BASELINE, 15));
		texto.setSize(490, 20);
		texto.setLocation(110, 168);
		texto.setVisible(true);
		painel.add(texto);

		acompanhantes = new JTextField();
		acompanhantes.setBackground(Color.lightGray);
		acompanhantes.setFont(new Font("Dialog", Font.HANGING_BASELINE, 15));
		acompanhantes.setSize(30, 20);
		acompanhantes.setLocation(240, 200);
		acompanhantes.setVisible(true);
		painel.add(acompanhantes);

		botao = new JButton("+");
		botao.setFont(new Font("Dialog", Font.ITALIC, 20));
		botao.setBackground(Color.LIGHT_GRAY);
		botao.setSize(50, 20);
		botao.setLocation(640, 168);
		botao.setVisible(true);
		painel.add(botao);

		saiu = new JButton("Atendido");
		saiu.setFont(new Font("Dialog", Font.ITALIC, 20));
		saiu.setBackground(Color.LIGHT_GRAY);
		saiu.setSize(200, 20);
		saiu.setLocation(110, 500);
		saiu.setVisible(true);
		painel.add(saiu);

		desistiu = new JButton("Desistiu");
		desistiu.setFont(new Font("Dialog", Font.ITALIC, 20));
		desistiu.setBackground(Color.LIGHT_GRAY);
		desistiu.setSize(200, 20);
		desistiu.setLocation(330, 500);
		desistiu.setVisible(true);
		painel.add(desistiu);

		frame.dispose();
		frame.setVisible(true);

		tabela2 = new DefaultTableModel(campos, 0);

		table = new JTable();
		table.setBounds(10, 10, 130, 500);
		table.setModel(tabela2);
		table.setVisible(true);
		painel.add(table);

		roll = new JScrollPane(table);
		roll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		roll.setBounds(110, 230, 580, 260);
		roll.setViewportView(table);
		painel.add(roll);

		adicionar();

	}

	public static lista reservar(Integer posicao) {
		cliente = new lista();
		cliente.indice = posicao;
		cliente.nome = texto.getText();
		cliente.acompanhantes = acompanhantes.getText();

		return cliente;
	}

	public static void proximo(lista cliente, lista novo) {

		if (cliente.proximo == null) {
			cliente.proximo = novo;
			cliente.anterior = cliente;
		} else {
			proximo(cliente.proximo, novo);
			cliente.anterior = cliente;
		}

	}

	static void adicionar() {

		botao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = texto.getText().trim();
				String acompanhan = acompanhantes.getText().trim();

				tabela2.addRow(new String[] { "" + (senha + 1), nome, acompanhan });
				reservar(senha);

				lista novaReserva = reservar(senha + 1);

				proximo(cliente, novaReserva);

				senha++;

				texto.setText("");
				acompanhantes.setText("");

				texto.requestFocus();

			}
		});

		saiu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int quantidade = table.getRowCount();
				int linha = table.getSelectedRow();

				if (quantidade == 0) {

					JOptionPane.showMessageDialog(null, "Não existe reservas cadastradas!");

				} else {
					if (linha < 0) {

						JOptionPane.showMessageDialog(null, "Por favor, selecione a linha!");

					} else {

						tabela2.removeRow(linha);

					}
				}

			}
		});

		desistiu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int quantidade = table.getRowCount();
				int linha = table.getSelectedRow();

				if (quantidade == 0) {

					JOptionPane.showMessageDialog(null, "Não existe reservas cadastradas!");

				} else {
					if (linha < 0) {

						JOptionPane.showMessageDialog(null, "Por favor, selecione a linha!");

					} else {

						tabela2.removeRow(linha);

					}
				}

			}
		});

	}
}
