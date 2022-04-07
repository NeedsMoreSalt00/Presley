package com.company;



import java.awt.BorderLayout;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class SearchInJtable extends JPanel {


    String[] columns = new String[]{
            "Stock",
            "Name",
            "Price",
            "Pack",
            "UPC"
    };




    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);
    String source;

    JTextField textField = new JTextField();



    public SearchInJtable() throws FileNotFoundException {
        //set the width of the 3rd column to 200 pixels

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(200);

        source = "C:\\Users\\kyleh\\IdeaProjects\\Cash Register V 1.00-20220325T162004Z-001\\Cash Register V 1.00\\src\\com\\company\\Inventory.csv";
        InputStream is;
        try {
            if (source.indexOf("http") == 0) {
                URL facultyURL = new URL(source);
                is = facultyURL.openStream();
            } else { //local file?
                File f = new File(source);
                is = new FileInputStream(f);
            }
            insertData(is);


        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, ioe, "Error reading data", JOptionPane.ERROR_MESSAGE);
        }



        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sort);
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search for an Item:"), BorderLayout.WEST);
        p.add(textField, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(p, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    //(?i) means case insensitive search
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    void insertData(InputStream is) {
        Scanner scan = new Scanner(is);
        String[] array;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.indexOf(",") > -1)
                array = line.split(",");
            else
                array = line.split("\t");
            Object[] data = new Object[array.length];
            for (int i = 0; i < array.length; i++)
                data[i] = array[i];

            model.addRow(data);
        }
        table.setModel(model);
    }

    public static void main(String[] args) {


    }
}