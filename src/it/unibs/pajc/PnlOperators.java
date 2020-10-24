package it.unibs.pajc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlOperators extends PnlOpBase {

	
	public PnlOperators() {
		super();
	}
	
	public PnlOperators(Set<String> operators) {
		this();
		for(String op: operators) {
			addButton(op);
		}
	}
	
	
	

}


