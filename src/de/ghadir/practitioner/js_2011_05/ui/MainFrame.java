package de.ghadir.practitioner.js_2011_05.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ghadir
 * @since 7/20/11 9:52 PM
 */
public class MainFrame extends JFrame {

    private CustomerTable customerTable = new CustomerTable();

    public MainFrame() throws HeadlessException {
        super( "Innovative UIs - A primitive Experiment" );

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JScrollPane(customerTable));

        setPreferredSize( new Dimension( 800, 600 ));
        setSize( new Dimension( 800, 600 ));
    }

    public static void main(String[] args) {

        new MainFrame().setVisible(true);
    }
}
