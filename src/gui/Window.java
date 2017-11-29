package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Window extends JFrame{
	


		private JPanel contentPane;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JButton btnHam_1;
		private JButton btnSpam;
		private JLabel lblRulesPath;
		private JLabel lblHamPath;
		private JLabel lblSpamPath;
		private JComboBox comboBox;
		private JTextField textField_3;
		private JTextField textField_4;
		private JTextField textField_5;
		private JLabel lblFn;
		private JLabel lblFp;
		private JButton btnNewButton;
		private JButton btnNewButton_1;
		private JComboBox comboBox_1;
		private JLabel label;
		private JLabel label_1;
		private JTextField textField_6;
		private JTextField textField_7;
		private JButton btnNewButton_2;
		private JButton btnNewButton_3;
		private JLabel lblGeradorManual;
		private JLabel lblGeradorAutomtico;


		public Window(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 785, 458);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);
			
			lblRulesPath = new JLabel("Rules Path");
			GridBagConstraints gbc_lblRulesPath = new GridBagConstraints();
			gbc_lblRulesPath.insets = new Insets(0, 0, 5, 5);
			gbc_lblRulesPath.gridx = 1;
			gbc_lblRulesPath.gridy = 1;
			contentPane.add(lblRulesPath, gbc_lblRulesPath);
			
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.anchor = GridBagConstraints.SOUTH;
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 1;
			contentPane.add(textField, gbc_textField);
			textField.setColumns(10);
			
			JButton btnHam = new JButton("Rules");
			btnHam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			GridBagConstraints gbc_btnHam = new GridBagConstraints();
			gbc_btnHam.insets = new Insets(0, 0, 5, 0);
			gbc_btnHam.gridx = 3;
			gbc_btnHam.gridy = 1;
			contentPane.add(btnHam, gbc_btnHam);
			
			lblHamPath = new JLabel("Ham Path");
			GridBagConstraints gbc_lblHamPath = new GridBagConstraints();
			gbc_lblHamPath.insets = new Insets(0, 0, 5, 5);
			gbc_lblHamPath.gridx = 1;
			gbc_lblHamPath.gridy = 2;
			contentPane.add(lblHamPath, gbc_lblHamPath);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 2;
			contentPane.add(textField_1, gbc_textField_1);
			
			btnHam_1 = new JButton("Ham");
			GridBagConstraints gbc_btnHam_1 = new GridBagConstraints();
			gbc_btnHam_1.insets = new Insets(0, 0, 5, 0);
			gbc_btnHam_1.gridx = 3;
			gbc_btnHam_1.gridy = 2;
			contentPane.add(btnHam_1, gbc_btnHam_1);
			
			lblSpamPath = new JLabel("Spam Path");
			GridBagConstraints gbc_lblSpamPath = new GridBagConstraints();
			gbc_lblSpamPath.insets = new Insets(0, 0, 5, 5);
			gbc_lblSpamPath.gridx = 1;
			gbc_lblSpamPath.gridy = 3;
			contentPane.add(lblSpamPath, gbc_lblSpamPath);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 2;
			gbc_textField_2.gridy = 3;
			contentPane.add(textField_2, gbc_textField_2);
			
			btnSpam = new JButton("Spam");
			GridBagConstraints gbc_btnSpam = new GridBagConstraints();
			gbc_btnSpam.insets = new Insets(0, 0, 5, 0);
			gbc_btnSpam.gridx = 3;
			gbc_btnSpam.gridy = 3;
			contentPane.add(btnSpam, gbc_btnSpam);
			this.setVisible(true);
		}
		
		public static void main(String[] args) {
			new Window();
		}
		
}
