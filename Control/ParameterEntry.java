package IPNS.Control;


//import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

public class ParameterEntry extends JPanel implements ActionListener, FocusListener {
    Parameter param = new Parameter();
    JLabel paramLabel = new JLabel();
    JTextField paramValEntry = new JTextField();
    JComboBox paramChoiceEntry = new JComboBox();
    JTextField paramDbEntry = new JTextField();
    boolean dbshown = false;

    public ParameterEntry() {
	
    }

    public ParameterEntry( Parameter param ) {
	this.param = param;
	GridLayout lo = new GridLayout(1,3);
	setLayout ( lo );
        setMinimumSize(new Dimension(400, 20));
        setMaximumSize(new Dimension(400, 20));
        setSize( getMinimumSize());
        setPreferredSize( getMinimumSize());
	
	paramLabel = new JLabel(param.Name());
	add ( paramLabel );
	if (param.OptionsAvailable() ) {
	    paramChoiceEntry = new JComboBox( param.Options() );
	    paramChoiceEntry.setSelectedIndex((int)param.Value());
	    paramChoiceEntry.setActionCommand(new String("ChoiceEntry"));
	    paramChoiceEntry.addActionListener(this);
	    paramChoiceEntry.addFocusListener(this);
	    add ( paramChoiceEntry );
	}
	else {
	    paramValEntry = 
		new JTextField( (new Float(param.Value())).toString(), 20 );
	    paramValEntry.setActionCommand( "ValEntry" );
	    paramValEntry.addActionListener( this );
	    paramValEntry.addFocusListener( this );
	    add ( paramValEntry );
	}
	paramDbEntry = new JTextField(param.DbSignal());
	paramDbEntry.setActionCommand( "DbEntry" );
	paramDbEntry.addFocusListener( this );
				      
    }

    public void showDbEntry( boolean on ) {
	if (on) {
	    if ( dbshown != true ) {
		add( paramDbEntry );
		dbshown = true;
	    }
	}
	else {
	    if ( dbshown == true ) {
		remove( paramDbEntry );
		dbshown = false;
	    }
      	}
    }

    public void actionPerformed ( ActionEvent e ) {
        String command = e.getActionCommand();
	if (command.equals("ChoiceEntry")) {
	    param.value = 
		paramChoiceEntry.getSelectedIndex();
	    System.out.println( "Setting " + param.name + " to " + 
				param.options[(int)param.value]);
	}
	else if (command.equals("ValEntry") ){
	    try{
		float tValue= 
		    ( new Float(paramValEntry.getText())).floatValue();
		param.value = tValue;
	    }
	    catch (NumberFormatException ex) {
		JOptionPane.showMessageDialog(this, 
			    "All Parameters must be entered as a number");
		paramValEntry.
		    setText(Float.toString(param.value));
	    }
	    System.out.println("Field " + param.name + " set to " + 
			       param.value );
	    
	}
	else if (command.startsWith("dbEntry")) {
	    param.dbsignal= paramDbEntry.getText();
	}
    }

    public void focusLost( FocusEvent e ) {
        String command = e.paramString();
	Component comp = e.getComponent();

	if (comp == paramChoiceEntry) {
	    param.value = 
		paramChoiceEntry.getSelectedIndex();
	    System.out.println( "Setting " + param.name + " to " + 
				param.options[(int)param.value]);
	}
	else if (comp == paramValEntry ){
	    try{
		float tValue= 
		    ( new Float(paramValEntry.getText())).floatValue();
		param.value = tValue;
	    }
	    catch (NumberFormatException ex) {
		JOptionPane.showMessageDialog(this, 
			    "All Parameters must be entered as a number");
		paramValEntry.
		    setText(Float.toString(param.value));
	    }
	    System.out.println("Field " + param.name + " set to " + 
			       param.value );
	    
	}
	
    }
    public void focusGained( FocusEvent e ) {
	
    }
    
    

}
