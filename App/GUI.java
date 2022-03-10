import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.*;


public class GUI implements ActionListener {

    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JButton inventory;
    private JPanel panel;
    private JTable ItemList;
    private JScrollPane jps;


	
    public GUI() {
      frame = new JFrame();
      inventory = new JButton("Inventory");
      inventory.addActionListener(this);
      label = new JLabel("Number of clicks: 0");
      String[] column = {"Qty.", "Item", "Price"};
      String[][] data = {{"1", "brewski", "1.08"}, {"1", "water", "10.73"}, {"2", "doritos", "6.75"}, {"1", "Guitar", "1000"}, {"2", "hat", "40"}, {"1", "lighter", "1.75"}};	
			Vector<String> data2 = new Vector<String>(0);
      ItemList = new JTable(data, column);
      ItemList.setPreferredScrollableViewportSize(new Dimension(200, 70));
      ItemList.setFillsViewportHeight(false);

     jps = new JScrollPane(ItemList);
      
      
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(175, 250, 200,250));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(inventory);
        panel.add(label); 
        panel.add(jps);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Presley Register v1.0");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    new GUI();
    }

    public void actionPerformed(ActionEvent e) { //performs action when inventory                                                       button is clicked
      count++;
      label.setText("Number of clicks: " + count);
			
    }
}