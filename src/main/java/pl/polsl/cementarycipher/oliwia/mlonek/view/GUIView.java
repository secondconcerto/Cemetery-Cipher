/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author roza
 */
public class GUIView extends JFrame implements ActionListener { 
    
    private JMenuBar menuBar;
    private JMenu operationsMenu;
    private JMenuItem EnocdeMenuItem;
    private JMenuItem DecodeMenuItem;
    private JTextArea userInput; 
    private JTextArea outputToUser; 
    private JTextArea historyArea; 
    private JFrame frame = new JFrame("Cemetry Cipher"); ; 
    private JButton enocdeButton; 
    private JButton addButton; 
    private JLabel label; 
    private JTable tableDecodeNumbers = new JTable();
    private JTable historyEncodeTable = new JTable();
    private JTable historyDecodeTable = new JTable();
    private JPanel panelToEncode = new JPanel(); 
    private JPanel panelToDecode = new JPanel(); 
    private JPanel panelEncodeHistory = new JPanel(); 
    private  JPanel panelDecodeHistory = new JPanel(); 
    private List<String> listWithNumbers = new ArrayList<>();
    private  List<String> listWithCipher = new ArrayList<>();
    private HashMap<Integer, String> encodingHistoryMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> decodingHistoryMap = new HashMap<Integer, String>();
    private JScrollPane scrollHistoryEncode;
    private JScrollPane scrollEncode;
    private JScrollPane scrollPanelToDecode;
    private JScrollPane scrollHistoryDecode;
    private JSplitPane splitPaneEncode;
    private JSplitPane splitPaneDecode;
    //private JScrollPane spli
    private CementaryCipherModel model;
    private DecodeAlphabetModel modelDecode = new DecodeAlphabetModel();
    private DefaultTableModel tableModelEncode;
    private DefaultTableModel tableModelDecode;

  
    public GUIView(CementaryCipherModel model) 
    { 
        this.model = model;
    } 
  
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

        enocdeButton = new JButton("Encode"); 
        outputToUser = new JTextArea();
        frame.add(new JLabel(new ImageIcon("src/logo.png")));
        frame.setSize(1200, 800); 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public void encodeView() 
    { 
        
        panelToEncode.removeAll();
        panelEncodeHistory.removeAll();
        frame.getContentPane().removeAll();

        
        label = new JLabel("Result: "); 
        
        enocdeButton = new JButton("Encode"); 
        
         enocdeButton.addActionListener(new ActionListener() {
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
        panelToEncode.add(enocdeButton); 
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
        
        enocdeButton = new JButton("Decode"); 
        enocdeButton.addActionListener(this);
        enocdeButton.addActionListener(new ActionListener() {
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
        panelToDecode.add(enocdeButton); 
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
    
    


    @Override
    public void actionPerformed(ActionEvent e) {
    }

     private class JTextAreaColumn extends AbstractCellEditor implements TableCellRenderer,TableCellEditor {

        private JTextArea area = new JTextArea();
        private JScrollPane pane = new JScrollPane(area);

        @Override
        public Object getCellEditorValue() {
            return area.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            area.setText(value == null ? "" : value.toString());        
            return pane;
        }

        private void setColor(boolean isSelected, JTable table) {
            area.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            area.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            area.setText(value == null ? "" : value.toString());
            setColor(isSelected,table);
            return pane;
        }

    }

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

