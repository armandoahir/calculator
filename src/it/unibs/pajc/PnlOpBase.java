package it.unibs.pajc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlOpBase extends JPanel {

	public PnlOpBase() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	}

	private ArrayList<ActionListener> listenerList = new ArrayList<>();
	
	public void addActionListener(ActionListener l) {
		listenerList.add(l);
	}

	public void removeActionListener(ActionListener l) {
		listenerList.remove(l);
	}
	
	public void fireActionListener(ActionEvent e) {
		ActionEvent myEvent = new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				e.getActionCommand(),
				e.getWhen(),
				e.getModifiers());
		
		for(ActionListener l: listenerList) {
			l.actionPerformed(myEvent);
		}
	}
	
	
	public void addButton(String symbol) {
		JButton btn = new JButton(symbol);
		btn.setPreferredSize(new Dimension(40, 40));
		this.add(btn);
		
		btn.addActionListener(e -> fireActionListener(e));
	}
	
	
}
