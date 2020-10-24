package it.unibs.pajc;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.SwingConstants;
import java.awt.event.*;

public class Calculator {

	private JFrame frame;
	private JLabel lblResult;
	private JLabel lblInfo;
	
	private CalcModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculator() {
		model = new CalcModel();
		initialize();
		
		//model.addChangeListener(e -> this.dump());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 487, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlTop = new JPanel();
		frame.getContentPane().add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));
		
		lblResult = new JLabel("--");
		lblResult.setBackground(Color.WHITE);
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		pnlTop.add(lblResult, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
				
		PnlDigits pnlDigits = new PnlDigits();
		pnlDigits.setBounds(159, 6, 173, 183);
		panel.add(pnlDigits);
		
		PnlOperators pnlOperators = new PnlOperators(model.getOperatorsSymbol());
		pnlOperators.setBounds(10, 6, 139, 183);
		panel.add(pnlOperators);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(342, 160, 117, 29);
		panel.add(btnEnter);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(342, 80, 117, 29);
		panel.add(btnClear);
		
		JButton btnEval = new JButton("Eval");
		btnEval.setBounds(342, 120, 117, 29);
		panel.add(btnEval);
		
		lblInfo = new JLabel("");
		frame.getContentPane().add(lblInfo, BorderLayout.SOUTH);
		
		
		pnlDigits.addActionListener(e -> this.addDigit(e));
		pnlOperators.addActionListener(e -> this.addOperator(e));
		btnEnter.addActionListener(e -> this.addOperand());
		btnClear.addActionListener(e -> this.clear());
		btnEval.addActionListener(e -> this.eval());
	}
	
	
	private boolean userIsTyping = false;
	private String currentTypedValueString;
	private Double currentTypedValue;
	
	
	
	void addDigit(ActionEvent e) {
		String dgt = e.getActionCommand();
		
		String typedValue = userIsTyping ? currentTypedValueString + dgt :
			dgt;
		
		try {
			currentTypedValue = Double.parseDouble(typedValue);
			userIsTyping = true;
			currentTypedValueString = typedValue;
			
			lblResult.setText(currentTypedValueString);
			
		} catch(Exception ex) {
			
		}
		
	}
	
	void addOperand() {
		model.pushOperand(currentTypedValue);
		userIsTyping = false;
		dump();
	}
	
	void addOperator(ActionEvent e) {
		model.pushOperator(e.getActionCommand());
		userIsTyping = false;
		dump();
	}
	
	void clear() {
		model.clearAll();
		dump();
	}
	
	void eval() {
		lblResult.setText("" + model.evaluate());
		dump();
	}
	
	void dump() {
		lblInfo.setText(model.dump());
	}
	
	
}