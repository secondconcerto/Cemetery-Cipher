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
import java.util.List;
import java.util.TreeMap;
import javax.swing.*; 
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author roza
 */
public class GUIView extends JFrame implements ActionListener { 

    static JTextArea userInput; 
    static JTextArea outputToUser; 
    static JFrame frame = new JFrame("Cemetry Cipher"); ; 
    static JButton enocdeButton; 
    static JButton addButton; 
    static JLabel label; 
    JPanel panelToEncode = new JPanel(); 
    JPanel panelToDecode = new JPanel(); 
    List<String> listWithNumbers = new ArrayList<>();
    List<String> listWithCipher = new ArrayList<>();
    
    CementaryCipherModel model;
    DecodeAlphabetModel modelDecode = new DecodeAlphabetModel();
  
    public GUIView(CementaryCipherModel model) 
    { 
        this.model = model;
    } 
  
    public void start() 
    { 
        var menuBar = new JMenuBar();
        var operationsMenu = new JMenu("Operations");
        operationsMenu.setPreferredSize(new Dimension(150, 50));
        operationsMenu.setMnemonic(KeyEvent.VK_F);
     

        var EnocdeMenuItem = new JMenuItem("Encode text");
        EnocdeMenuItem.setPreferredSize(new Dimension(200, EnocdeMenuItem.getPreferredSize().height));
        EnocdeMenuItem.setMnemonic(KeyEvent.VK_E);
        EnocdeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encodeView(menuBar);
            }
        });
        
        var DecodeMenuItem = new JMenuItem("Decode text");
        DecodeMenuItem.setPreferredSize(new Dimension(200, DecodeMenuItem.getPreferredSize().height));
        DecodeMenuItem.setMnemonic(KeyEvent.VK_D);
        DecodeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decodeView(menuBar);
            }
        });
       
        operationsMenu.add(EnocdeMenuItem);
        operationsMenu.add(DecodeMenuItem);
        menuBar.add(operationsMenu);

        enocdeButton = new JButton("Encode"); 
        outputToUser = new JTextArea();
        //frame.add(enocdeButton);
        frame.add(new JLabel(new ImageIcon("src/logo.png")));
        frame.setSize(1000, 1000); 
        

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);
    }

    public void encodeView(JMenuBar menu) 
    { 
        
        panelToEncode.removeAll();
        frame.getContentPane().removeAll();
        
        label = new JLabel("Result: "); 
        
        enocdeButton = new JButton("Encode"); 
        
         enocdeButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                    model.encodeMessage(userInput.getText());
                    outputToUser.setText(model.getEncodedValue() );
                    userInput.setText("");
                    model.resetValue();
                     
                 } catch (WrongInputException error) {
                      JOptionPane.showMessageDialog(frame, error.getMessage());
                      userInput.setText("");
                      outputToUser.setText("");
                      model.resetValue();
                 }
             }
         });
        
        userInput = new JTextArea(1,10); 
        outputToUser = new JTextArea(16,58); 
        outputToUser = new JTextArea(); 
        panelToEncode.add(userInput); 
        panelToEncode.add(enocdeButton); 
        panelToEncode.add(label); 
        panelToEncode.add(outputToUser);
        
       
        frame.setJMenuBar(menu);     
        JScrollPane scroll = new JScrollPane(panelToEncode); 
       
         frame.getContentPane().add(scroll);
        //frame.add(scroll); //panel = panel you want to change too. 
        frame.pack ();
        frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
        frame.revalidate(); 
        frame.setSize(1000, 1000); 
        frame.setVisible(true);
    } 
    
    public void decodeView(JMenuBar menu)
    {
       panelToDecode.removeAll();
       frame.getContentPane().removeAll();
       TreeMap<String, String> sorted = new TreeMap<>(modelDecode.getMap());    
        DefaultTableModel jtableModel = new DefaultTableModel(
            new String[] { "Key", "Value" }, 0
        );
       
        sorted.entrySet().forEach(entry -> {
            jtableModel.addRow(new String[] {entry.getValue(), entry.getKey() });
        }); 
        
        JTable table = new JTable(jtableModel); 
        table.getColumnModel().getColumn(0).setCellRenderer(new JTextAreaColumn());
        table.getColumnModel().getColumn(0).setCellEditor(new JTextAreaColumn());
        table.setRowHeight(40);
        
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
        panelToDecode.add(table);
        panelToDecode.add(userInput); 
        panelToDecode.add(addButton);
        panelToDecode.add(enocdeButton); 
        panelToDecode.add(label); 
        panelToDecode.add(outputToUser);
        JScrollPane scroll2 = new JScrollPane(panelToDecode); 
        frame.setJMenuBar(menu);   
        frame.getContentPane().add(scroll2);
        //frame.add(scroll2); //panel = panel you want to change too.
        frame.repaint();             //Ensures that the frame swaps to the next panel and doesn't get stuck.
        frame.revalidate(); 
        frame.setSize(1000, 1000); 
        frame.setVisible(true);
//       
    }
    
    
  

//    @Override
//    public void actionPerformed(ActionEvent e) 
//    { 
//        String s = e.getActionCommand(); 
//        if (s.equals("Encode")) { 
//            decodeView();
// 
//        } 
//    } 

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


} 

