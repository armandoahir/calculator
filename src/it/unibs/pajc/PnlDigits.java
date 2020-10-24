package it.unibs.pajc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlDigits extends PnlOpBase {

	
	public PnlDigits() {
		super();
		
		for(int i=0; i<= 9; i++) {
			addButton(Integer.toString(i));
		}
	}
	

}


