package com.company;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.table.*;
import javax.swing.table.TableRowSorter;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;


public class GUI implements ActionListener {

    private int count = 0;

    private JFrame frame;
    private JButton inventory;
    private JButton additem;
    private JPanel panel;
    private JTable ItemList;
    private JScrollPane jps;

    public GUI() {

        frame = new JFrame();
        inventory = new JButton("Inventory");
        additem = new JButton("Add Item");



        inventory.setPreferredSize(new Dimension(100,100));
        additem.setPreferredSize(new Dimension(100,100));
        inventory.setAlignmentX(50);
        inventory.setAlignmentY(50);
        inventory.addActionListener(this);
        additem.addActionListener(this);

        String[] column = {"Qty.", "Item", "Price"};
        String[][] data = {{"1", "brewski", "1.08"}, {"1", "water", "10.73"}, {"2", "doritos", "6.75"}, {"1", "Guitar", "1000"}, {"2", "hat", "40"}, {"1", "lighter", "1.75"}};
        ItemList = new JTable(data, column);

        ItemList.setPreferredScrollableViewportSize(new Dimension(1680, 500));
        ItemList.setFillsViewportHeight(false);

        jps = new JScrollPane(ItemList);


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(175, 250, 200,250));

        panel.add(inventory);
        panel.add(additem);
        panel.add(jps);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Presley Register v1.0");
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) {


    }

    public void actionPerformed(ActionEvent e) { //performs action when inventory button is clicked
        if(e.getSource()==inventory) {
            JFrame f = new JFrame("Inventory");

            try {
                f.add(new SearchInJtable());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            f.setSize(800, 200);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
        else if(e.getSource()==additem){
            try {
                new NewItem();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }




    }
}
