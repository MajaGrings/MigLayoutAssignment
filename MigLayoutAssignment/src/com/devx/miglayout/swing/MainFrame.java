/**
 * Made a comment on the commit
 */
package com.devx.miglayout.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import com.devx.miglayout.swing.samples.ComplexSample;

public class MainFrame extends JFrame {

	private JDesktopPane samplesFrame = new JDesktopPane();
	private JList list = new JList();
	
	/**
	 * Constructor
	 */
	public MainFrame(String title) {
		super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		MigLayout layout = new MigLayout();
		setLayout(layout);
		
		//list of samples
		
		list.setBorder(BorderFactory.createLoweredBevelBorder());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel model = new DefaultListModel();
		model.add(0, new ComplexSample());
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JInternalFrame sample = (JInternalFrame)list.getSelectedValue();
				sample.setVisible(true);
				try {
					sample.setSelected(true);
				} catch (PropertyVetoException e1) {}
			}			
		});
		
		list.setModel(model);
		
		add(list,"top, width 150, height max");
		
		//internal frame used for showing the samples
		samplesFrame.setVisible(true);
		add(samplesFrame,"width max, height max");
		
		//add all the sample frames to the desktop frame
		for(int i = 0; i < model.getSize(); i++) {
			JInternalFrame sample = (JInternalFrame)model.get(i);
			sample.setVisible(false);
			samplesFrame.add(sample);
		}
	}
	


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
        			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            		//UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
        		} catch (Exception e) {
        			// TODO Auto-generated catch block			
        			e.printStackTrace();
        		}
            	
            	MainFrame frame = new MainFrame("Donkey Sanctuary Database");
                frame.setVisible(true);
            }
        });
    }
}
