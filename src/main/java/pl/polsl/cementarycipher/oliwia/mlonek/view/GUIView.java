
package pl.polsl.cementarycipher.oliwia.mlonek.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.swing.*; 
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/** 
 * View class represents the visualization of the data as a form of SWING GUI. 
 * Provides interaction with the user.
 * 
 * @author Oliwia Mlonek
 * @version 3.0
 */
public class GUIView extends JFrame implements ActionListener { 
    
     /** Variable stores menu bar */
    private JMenuBar menuBar;
      /** Variable stores operation menu */
    private JMenu operationsMenu;
     /** Variable stores menu item for encoding */
    private JMenuItem EnocdeMenuItem;
     /** Variable stores menu item for decoding */
    private JMenuItem DecodeMenuItem;
     /** Variable stores text area for user input*/
    private JTextArea userInput; 
     /** Variable stores text area for user output*/
    private JTextArea outputToUser; 
    /** Variable stores main frame of the program*/
    private JFrame frame = new JFrame("Cemetry Cipher"); 
    /** Variable stores button to perform operations, encode lub decode*/
    private JButton codingButton; 
    /** Variable stores button to add user input to list*/
    private JButton addButton; 
    /** Variable stores label*/
    private JLabel label; 
    /** Variable stores table for decoding alphabet for user*/
    private JTable tableDecodeNumbers = new JTable();
    /** Variable stores table for history of encoding*/
    private JTable historyEncodeTable = new JTable();
    /** Variable stores table for history of decoding*/
    private JTable historyDecodeTable = new JTable();
    /** Variable stores encoding panel */
    private JPanel panelToEncode = new JPanel();
    /** Variable stores decoding panel */
    private JPanel panelToDecode = new JPanel(); 
     /** Variable stores encoding history panel */
    private JPanel panelEncodeHistory = new JPanel(); 
     /** Variable stores decoding history panel */
    private  JPanel panelDecodeHistory = new JPanel(); 
     /** Variable stores list with user input numbers from decoding */
    private List<String> listWithNumbers = new ArrayList<>();
    /** Variable stores list with user input numbers translated to pictograms from decoding */
    private  List<String> listWithCipher = new ArrayList<>();
      /** Variable stores map of encoding history  */
    private HashMap<Integer, String> encodingHistoryMap = new HashMap<Integer, String>();
     /** Variable stores map of decoding history  */
    private HashMap<Integer, String> decodingHistoryMap = new HashMap<Integer, String>();
    /** Variable stores scroll for HistoryEncode panel  */
    private JScrollPane scrollHistoryEncode;
     /** Variable stores scroll for Encode panel  */
    private JScrollPane scrollEncode;
    /** Variable stores scroll for PanelToDecode  */
    private JScrollPane scrollPanelToDecode;
     /** Variable stores scroll for HistoryDecode panel  */
    private JScrollPane scrollHistoryDecode;
    /** Variable stores split panel for encoding */
    private JSplitPane splitPaneEncode;
    /** Variable stores split panel for decoding */
    private JSplitPane splitPaneDecode;
   /** model used to perform actions */
    private CementaryCipherModel model;
    /** Model of decoding alphabet to decode message */
    private DecodeAlphabetModel modelDecode = new DecodeAlphabetModel();
    /** Table model used when encoding */
    private DefaultTableModel tableModelEncode;
     /** Table model used when decoding */
    private DefaultTableModel tableModelDecode;

  
    /**
     * Costructor of the class
     * 
     * @param model used model
     */
    public GUIView(CementaryCipherModel model) 
    { 
        this.model = model;
    } 
  
    /**
     * 
     * Displayes main menu of the program
     */
    public void start() 
    { 
        menuBar = new JMenuBar();
        operationsMenu = new JMenu("Operations");
        operationsMenu.setPreferredSize(new Dimension(150, 50));
        operationsMenu.setMnemonic(KeyEvent.VK_F);
     

        EnocdeMenuItem = new JMenuItem("Encode text");
        EnocdeMenuItem.setPreferredSize(new Dimension(200, EnocdeMenuItem.getPreferredSize().height));
        EnocdeMenuItem.setMnemonic(KeyEvent.VK_E);
        EnocdeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encodeView();
            }
        });
        
        DecodeMenuItem = new JMenuItem("Decode text");
        DecodeMenuItem.setPreferredSize(new Dimension(200, DecodeMenuItem.getPreferredSize().height));
        DecodeMenuItem.setMnemonic(KeyEvent.VK_D);
        DecodeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decodeView();
            }
        });
       
        operationsMenu.add(EnocdeMenuItem);
        operationsMenu.add(DecodeMenuItem);
        menuBar.add(operationsMenu);

        codingButton = new JButton("Encode"); 
        outputToUser = new JTextArea();
        frame.add(new JLabel(new ImageIcon("src/logo.png")));
        frame.setSize(1200, 800); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * Displayes encoding view.
     */
    public void encodeView() 
    { 
        
        panelToEncode.removeAll();
        panelEncodeHistory.removeAll();
        frame.getContentPane().removeAll();

        
        label = new JLabel("Result: "); 
        
        codingButton = new JButton("Encode"); 
        
         codingButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                    model.encodeMessage(userInput.getText());
                    outputToUser.setText(model.getEncodedValue() );
                    int count = tableModelEncode.getRowCount()+1;
                    encodingHistoryMap.put(count, userInput.getText());
                    tableModelEncode.addRow(new Object[]{count, userInput.getText()});
                    resizeColumnWidth(historyEncodeTable);
                    userInput.setText("");
                    model.resetEncodedValue();
                 } catch (WrongInputException error) {
                      JOptionPane.showMessageDialog(frame, error.getMessage());
                      userInput.setText("");
                      outputToUser.setText("");
                      model.resetEncodedValue();
                 }
             }
         });
         

         
        
        userInput = new JTextArea(1,10); 
        outputToUser = new JTextArea();  
        
        panelToEncode.add(userInput); 
        panelToEncode.add(codingButton); 
        panelToEncode.add(label); 
        panelToEncode.add(outputToUser);
        tableModelEncode = new DefaultTableModel(new Object[]{"column1","column2"},0);
        TreeMap<Integer, String> sorted = new TreeMap<>(encodingHistoryMap);   
        sorted.entrySet().forEach(entry -> {
            tableModelEncode.addRow(new Object[] {entry.getKey(), entry.getValue() });
        }); 
        scrollEncode =  new JScrollPane(panelToEncode);
        historyEncodeTable.setModel(tableModelEncode);
        resizeColumnWidth(historyEncodeTable);
        panelEncodeHistory.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                            "History of encoding",
                                                            TitledBorder.CENTER,
                                                            TitledBorder.TOP));
        panelEncodeHistory.add(historyEncodeTable);
        
        scrollHistoryEncode = new JScrollPane(panelEncodeHistory); 

       
        splitPaneEncode = new JSplitPane(SwingConstants.VERTICAL, scrollEncode, scrollHistoryEncode); 


        
        splitPaneEncode.setOrientation(SwingConstants.VERTICAL); 
        splitPaneEncode.setOneTouchExpandable(true);
        splitPaneEncode.setDividerLocation(800);      
        frame.setJMenuBar(menuBar);     
        scrollEncode = new JScrollPane(splitPaneEncode); 
        frame.getContentPane().add(scrollEncode);
        frame.pack ();
        frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
        frame.revalidate(); 
        frame.setSize(1200, 800); 
        frame.setVisible(true);
        
      
    } 
   
    /**
     * Displayes decoding view.
     */
    public void decodeView()
    {
        panelDecodeHistory.removeAll();
        panelToDecode.removeAll();
        frame.getContentPane().removeAll();
        TreeMap<String, String> sorted = new TreeMap<>(modelDecode.getMap());    
        tableModelDecode = new DefaultTableModel(
            new String[] { "Key", "Value" }, 0
        );
       
        sorted.entrySet().forEach(entry -> {
            tableModelDecode.addRow(new String[] {entry.getKey(), entry.getValue() });
        }); 
        
        tableDecodeNumbers = new JTable(tableModelDecode); 
        tableDecodeNumbers.getColumnModel().getColumn(0).setCellRenderer(new JTextAreaColumn());
        tableDecodeNumbers.getColumnModel().getColumn(0).setCellEditor(new JTextAreaColumn());
        tableDecodeNumbers.setRowHeight(40);
        
        userInput = new JTextArea(1,2); 
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     
                     listWithCipher.add(model.checkInput(userInput.getText()));
                     listWithNumbers.add(userInput.getText());
                     outputToUser.setText(listWithNumbers.toString());
                     userInput.setText("");
                     model.resetDecodedValue();
                 } catch (WrongInputException ex) {
                     JOptionPane.showMessageDialog(frame, ex.getMessage());
                     userInput.setText("");
                     model.resetDecodedValue();
                 }
             }
         });
        
        label = new JLabel("Result: "); 
        
        codingButton = new JButton("Decode"); 
        codingButton.addActionListener(this);
        codingButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                    model.decodeMessage(listWithCipher);
                    outputToUser.setText(model.getEncodedValue());
                    int count = tableModelDecode.getRowCount()+1;
                    decodingHistoryMap.put(count, listWithNumbers.toString());
                    tableModelDecode.addRow(new Object[]{count, listWithNumbers.toString()});
                    resizeColumnWidth(historyDecodeTable);
                 } catch (WrongInputException error) {
                      JOptionPane.showMessageDialog(frame, error.getMessage());
                      userInput.setText("");
                 }
                 
                 outputToUser.setText(model.getDecodedValue());
                 listWithCipher.clear();
                 listWithNumbers.clear();
             }
         });
        outputToUser = new JTextArea(1,10); 
        panelToDecode.add(tableDecodeNumbers);
        panelToDecode.add(userInput); 
        panelToDecode.add(addButton);
        panelToDecode.add(codingButton); 
        panelToDecode.add(label); 
        panelToDecode.add(outputToUser);
        tableModelDecode = new DefaultTableModel(new Object[]{"column1","column2"},0);
        TreeMap<Integer, String> sortedHistory = new TreeMap<>(decodingHistoryMap);   
        sortedHistory.entrySet().forEach(entry -> {
            tableModelDecode.addRow(new Object[] {entry.getKey(), entry.getValue() });
        }); 
        scrollPanelToDecode = new JScrollPane(panelToDecode); 
        historyDecodeTable.setModel(tableModelDecode);
        resizeColumnWidth(historyDecodeTable);
        
        panelDecodeHistory.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                            "History of decoding",
                                                            TitledBorder.CENTER,
                                                            TitledBorder.TOP));
        panelDecodeHistory.add(historyDecodeTable);
        
        scrollHistoryDecode = new JScrollPane(panelDecodeHistory); 
       
        splitPaneDecode = new JSplitPane(SwingConstants.VERTICAL, scrollPanelToDecode, scrollHistoryDecode); 
        splitPaneDecode.setOrientation(SwingConstants.VERTICAL); 
        splitPaneDecode.setOneTouchExpandable(true);
        splitPaneDecode.setDividerLocation(800);       
        frame.setJMenuBar(menuBar);   
        frame.getContentPane().add(splitPaneDecode);
        frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
        frame.revalidate(); 
        frame.setSize(1200, 800); 
        frame.setVisible(true);
//       
    }
    
    

    /**
     * Basic method for performing actions with GUI components.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Inside class to resize particular parts of table, depending on size of input inside of table's cells.
     */
     private class JTextAreaColumn extends AbstractCellEditor implements TableCellRenderer,TableCellEditor {

       /** Variable stores text area  */
        private JTextArea area = new JTextArea();
        /** Variable stores scroll panel  */
        private JScrollPane pane = new JScrollPane(area);

        /** Gets cell value*/
        @Override
        public Object getCellEditorValue() {
            return area.getText();
        }

         /** Gets cell value end modify it */
        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            area.setText(value == null ? "" : value.toString());        
            return pane;
        }

        /** 
        * Gets cell value put right background color 
        * @param isSelected check if component was selected
        * @param table table to be modified
        */
        private void setColor(boolean isSelected, JTable table) {
            area.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            area.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }

        /** Gets cell, modify it and render */
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            area.setText(value == null ? "" : value.toString());
            setColor(isSelected,table);
            return pane;
        }

    }

     /**
     * Resize column of a given table.
     * @param table table to be modified
     */
    private void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 15; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width +1 , width);
        }
        if(width > 300)
            width=300;
        columnModel.getColumn(column).setPreferredWidth(width);
    }
}

} 

