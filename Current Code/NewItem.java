package com.company;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewItem implements ActionListener {
    FileWriter writer = new FileWriter("C:\\Users\\kyleh\\IdeaProjects\\Cash Register V 1.00-20220325T162004Z-001\\Cash Register V 1.00\\src\\com\\company\\Inventory.csv", true);
    //string to temporarily hold data
    String UPC = null;
    String itemName = null;
    String itemPrice = null;
    String stock = null;
    String pack = null;
    JFrame frame;
    JButton Add = new JButton("Add Item");
    JButton abort = new JButton("Abort");
    JPanel panel = new JPanel();


    //declaring all textfields to accept item data from user
    JTextField UPCfield = new JTextField();
    JTextField itemField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField stockField = new JTextField();
    JTextField packField = new JTextField();
    //labels for textfields so user knows what data they are inputting
    JLabel UPCLabel = new JLabel(" UPC: ");
    JLabel itemLabel = new JLabel(" Item Name: ");
    JLabel pricelabel = new JLabel(" Price :");
    JLabel stocklabel = new JLabel(" Stock: ");
    JLabel packlabel = new JLabel(" Pack: ");

    public NewItem() throws IOException {
        frame = new JFrame();
        frame.setTitle("Add Item");
        frame.setLocation(900,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(UPCLabel);
        frame.add(UPCfield);
        frame.add(itemLabel);
        frame.add(itemField);
        frame.add(pricelabel);
        frame.add(priceField);
        frame.add(stocklabel);
        frame.add(stockField);
        frame.add(packlabel);
        frame.add(packField);

        Add.addActionListener(this);
        abort.addActionListener(this);

        frame.add(Add);
        frame.add(abort);






        frame.setLayout(new GridLayout(6,2,15,25));
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new NewItem();

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==Add) {
            //Setting Strings equal to the text entered by the user
            UPC = UPCfield.getText();
            itemName = itemField.getText();
            itemPrice = priceField.getText();
            stock = stockField.getText();
            pack = packField.getText();
            //Trimming white space off of strings, could cause problems in CSV reading
            //UPC.trim(); itemName.trim(); itemPrice.trim(); stock.trim(); pack.trim();
            //If any fields are not filled out program will give an error and not write to the file
            if ((UPC.length() == 0) || (itemName.length() == 0) || (itemPrice.length() == 0) ||
                    (stock.length() == 0) || (pack.length() == 0)) { //long if statement be careful
                infoBox("Error: Fields cannot be empty.", "ERROR", 190 , 100);
                try {
                    writer.flush(); //flushes writer
                    writer.close(); //closeswriter
                    frame.dispose();    //destroys add item window and returns memory to OS,
                                        //  ensures that data will be up to date every time button is pressed and used
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
            else if(getSpecialCharacterCount(UPC)==1 || getSpecialCharacterCount(itemName)==1 ||
                    getSpecialCharacterCount(itemPrice)==1 || getSpecialCharacterCount(stock)==1
                    || getSpecialCharacterCount(pack)==1){

                infoBox("Error: Fields cannot contain special characters.", "ERROR", 290, 100);
                try {
                    writer.flush(); //flushes writer
                    writer.close(); //closeswriter
                    frame.dispose();    //destroys add item window and returns memory to OS,
                    //  ensures that data will be up to date every time button is pressed and used
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{

                try {
                    //after confirming no fields are empty the writer writes the item to file leaving a newline
                    //for next item input
                    writer.append(stock.trim() + ",");
                    writer.append(itemName.trim() + ",");
                    writer.append(itemPrice.trim() + ",");
                    writer.append(pack.trim() + ",");
                    writer.append(UPC.trim() + "\n");

                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        }
        else if (e.getSource() == abort){ // if Abort button is pressed the window is closed and nothing is written
            try {
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.dispose();

        }





    }


    public static void infoBox(String infoMessage, String titleBar, int width, int height)
    {
        JFrame error = new JFrame(titleBar);
        JLabel errorLabel = new JLabel(infoMessage);
        error.setSize(width,height);
        error.setLocation(900,300);
        error.add(errorLabel);
        error.setVisible(true);

    }


        public int getSpecialCharacterCount(String s) {
            if (s == null || s.trim().isEmpty()) {
                System.out.println("Incorrect format of string");
                return 0;
            }
            Pattern p = Pattern.compile("[^A-Za-z0-9.]");
            Matcher m = p.matcher(s);
            // boolean b = m.matches();
            boolean b = m.find();
            if (b)
                return 1;
            else

            return 2;
        }






}