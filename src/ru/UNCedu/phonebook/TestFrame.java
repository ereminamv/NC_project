package ru.UNCedu.phonebook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TestFrame extends JFrame {

    public JTextField textField1;
    public JTextField textField2;
    public JTextField textField3;
    public String filename = "Number_base.txt";

//    File[] roots =  File.listRoots();
//    JList list = new JList(roots);
//    File root = (File) list.getSelectedValue();
//    /*File[] children = root.listFiles();*/
//    File file = new File("C:\\User\1345\\Desktop\\test.txt");



    public TestFrame() {
        super("Test frame");

        createGUI();

    }


    public void createGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel label = new JLabel("choosed file");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton button = new JButton("Show JFileChooser");
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    label.setText(file.getName());
                }
            }
        });

        panel.add(button);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



        panel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Add");
        button1.setActionCommand("Button 1 was pressed!");
        panel.add(button1);


        JButton button3 = new JButton("Save");
        button3.setActionCommand("Button 3 was pressed!");
        panel.add(button3);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField1.setColumns(23);
        textField2.setColumns(23);
        textField3.setColumns(23);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);

        Object rowData[][] = { { "89506085263", "Ivanov", "45" },
                { "89104567896", "Petrov", "100" } };
        Object columnNames[] = { "Number", "Holder", "Balance" };
        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.AFTER_LAST_LINE);
        panel.setVisible(true);

        ActionListener actionListener = new TestActionListener();

        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String number = textField1.getText();
                String holder = textField2.getText();
                Integer balance = new Integer(textField3.getText());
               /* new TestFrame().update(number + " " + holder + " " + balance + "\n");*/

            }
        });



        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField2.setText(e.getActionCommand());
            }
        });

        getContentPane().add(panel);
        setPreferredSize(new Dimension(500, 500));
    }



    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField1.setText(e.getActionCommand());
            textField2.setText(e.getActionCommand());
            textField3.setText(e.getActionCommand());

        }
    }




    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                TestFrame frame = new TestFrame();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}