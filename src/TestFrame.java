import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.lang.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class TestFrame extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private String filename = "Number_base.txt";

    File[] roots =  File.listRoots();
    JList list = new JList(roots);
    File root = (File) list.getSelectedValue();
    File[] children = root.listFiles();
    File file = new File("C:/test.txt");



    public TestFrame() {
        super("Test frame");

        createGUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JPanel rootContent = new JPanel();
        rootContent.setLayout(new BoxLayout(rootContent, BoxLayout.Y_AXIS));
        final JScrollPane rootContentScroll = new JScrollPane(rootContent);
        File[] roots = File.listRoots();
        final JList list = new JList(roots);
        list.setVisibleRowCount(5);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                rootContent.removeAll();
                File root = (File) list.getSelectedValue();
                File[] children = root.listFiles();
                if (children != null) {
                    for (int i = 0; i < children.length; i++) {
                        JLabel label = new JLabel(children[i].getName());
                        rootContent.add(label);
                    }
                }
                rootContent.repaint();
                rootContentScroll.revalidate();
            }
        });

        mainPanel.add(new JScrollPane(list), BorderLayout.NORTH);
        mainPanel.add(rootContentScroll, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }




    public void createGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
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
                new TestFrame().update(number + " " + holder + " " + balance + "\n");

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

    public String read(String newText) {
        StringBuilder sb = new StringBuilder();
        File file = new File(filename);

        try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String s;
            while ((s = in.readLine()) != null) {
                if(s.length()!= 0){
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) {

        File file = new File(fileName);

        try {

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {

                out.println(text);
            } finally {

                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String newText) {
        File file = new File(new TestFrame().filename);
        file.exists();
        StringBuilder sb = new StringBuilder();
        String oldFile = read(new TestFrame().filename);

        sb.append(oldFile);

        sb.append(newText);

        write(new TestFrame().filename, sb.toString());
        System.out.println(file.length());
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