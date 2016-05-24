/** Runs the desired number of simulations of Monopoly and outputs either the number of
 * rolls it takes to land in jail, the outliers of a simulation, or the maximum number of rolls
 * needed to land in jail. 
 * 
 */ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MonopolyRunner extends JPanel
                             implements ActionListener {
    /**
     * oneRunString: String that guides the user to select "One Run"
     */
    private String oneRunString = "One Run";
    /**
     * oneRunString: String that guides the user to select "Multiple Runs (Max Value Outputted)"
     */
    private String multRunMaxString = "Multiple Runs (Max Value Outputted)";
    /**
     * multRunOutliersString: String that guides the user to select "Multiple Runs (Outliers Outputted)"
     */
    private String multRunOutliersString = "Multiple Runs (Outliers Outputted)";
    /**
     * runs: holds how many turns the user wants to run the simulation
     */
    private int runs;
    
    
        
        

    /**
     * Constructs a MonopolyRunner object and creates the radio buttons, their listeners, and GUI layout
     */
    public MonopolyRunner() {
        super(new BorderLayout());
        JRadioButton oneRunButton = new JRadioButton(oneRunString);
        oneRunButton.setMnemonic(KeyEvent.VK_C);
        oneRunButton.setActionCommand(oneRunString);
        JRadioButton multRunMaxButton = new JRadioButton(multRunMaxString);
        multRunMaxButton.setMnemonic(KeyEvent.VK_C);
        multRunMaxButton.setActionCommand(multRunMaxString);
        JRadioButton multRunOutliersButton = new JRadioButton(multRunOutliersString);
        multRunOutliersButton.setMnemonic(KeyEvent.VK_C);
        multRunOutliersButton.setActionCommand(multRunOutliersString);
        ButtonGroup group = new ButtonGroup();
        group.add(oneRunButton);
        group.add(multRunMaxButton);
        group.add(multRunOutliersButton);
        oneRunButton.addActionListener(this);
        multRunMaxButton.addActionListener(this);
        multRunOutliersButton.addActionListener(this);
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(oneRunButton);
        radioPanel.add(multRunMaxButton);
        radioPanel.add(multRunOutliersButton);
        add(radioPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
    }

    /**
     * actionPerformed: determines what each radio buttons controls.
     * Then calls the Monopoly class, runs the simulation(s), and uses a GUI for output.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "One Run"){
            Monopoly game = new Monopoly();
            game.run();
            JOptionPane.showMessageDialog(this,game);  
        }
        else if(e.getActionCommand() == "Multiple Runs (Max Value Outputted)"){
            MonopolyStatistics game = new MonopolyStatistics();
            runs = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of runs"));
            game.numRuns(runs);
            game.output(2);
            game.run();
            JOptionPane.showMessageDialog(this,game);
            
        }
        else{
            MonopolyStatistics game = new MonopolyStatistics();
            runs = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of runs"));
            game.numRuns(runs);
            game.output(1);
            game.run();
            JTextArea area = new JTextArea(game.toString());
            area.setRows(10);
            area.setColumns(50);
            area.setLineWrap(true);
            JScrollPane pane = new JScrollPane(area);
            JOptionPane.showConfirmDialog(null, pane, "Statistical Output",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
            
        }
    }


    /**
     * Creates GUI and adds the radio buttons into it.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("How many runs to land in jail?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new MonopolyRunner();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}