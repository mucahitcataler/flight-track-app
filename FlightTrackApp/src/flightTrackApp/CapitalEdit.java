package flightTrackApp;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CapitalEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapitalEdit frame = new CapitalEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	@SuppressWarnings("unchecked")
	public CapitalEdit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		LinkedList<Capital> capitals = new LinkedList<Capital>();
		try {
			ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Capitals.txt" ) );
			capitals = (LinkedList<Capital>)reader.readObject();
			reader.close();
		} 
		catch( IOException e ) {
			System.out.println("An exception has occured during file reading.");
			e.printStackTrace();
		} 
		catch( ClassNotFoundException e ) {
			System.out.println("An exception has occured while processing read records.");
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 31, 454, 166);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "X", "Y"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(116);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(108, 304, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 342, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(108, 382, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(65, 307, 33, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(65, 345, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Y");
		lblNewLabel_2.setBounds(65, 385, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = Integer.parseInt(textField_1.getText());
				int y = Integer.parseInt(textField_2.getText());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { textField.getText() , x , y });
				Capital capital = new Capital(textField.getText() , x , y);
				LinkedList<Capital> capitals2 = new LinkedList<Capital>();
				try {
					ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Capitals.txt" ) );
					capitals2 = (LinkedList<Capital>)reader.readObject();
					reader.close();
				} 
				catch( IOException e ) {
					System.out.println("An exception has occured during file reading.");
					e.printStackTrace();
				} 
				catch( ClassNotFoundException e ) {
					System.out.println("An exception has occured while processing read records.");
					e.printStackTrace();
				}
				capitals2.add(capital);
				try {
		        	ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Capitals.txt"));
		        	writer.writeObject(capitals2);
		        	writer.close();
		        	System.out.println("The information you have entered has "
							+ "been successfully saved in file " + "Capitals.txt");
		        }
		        catch(IOException e) {
		        	e.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(246, 350, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					LinkedList<Capital> capitals2 = new LinkedList<Capital>();
					try {
						ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Capitals.txt" ) );
						capitals2 = (LinkedList<Capital>)reader.readObject();
						reader.close();
					} 
					catch( IOException e2 ) {
						System.out.println("An exception has occured during file reading.");
						e2.printStackTrace();
					} 
					catch( ClassNotFoundException e2 ) {
						System.out.println("An exception has occured while processing read records.");
						e2.printStackTrace();
					}
					
					int r = table.getSelectedRow();
					for (Capital capital2 : capitals2) {					
						if (capital2.getName().equals(model.getValueAt(r, 0)) == true) {
							capital2.setName(textField.getText());
							capital2.setX(Integer.parseInt(textField_1.getText()));
							capital2.setY(Integer.parseInt(textField_2.getText()));
						}
					}
								
					try {
			        	ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Capitals.txt"));
			        	writer.writeObject(capitals2);
			        	writer.close();
			        	System.out.println("The information you have entered has "
								+ "been successfully saved in file " + "Capitals.txt");
			        }
			        catch(IOException e2) {
			        	e2.printStackTrace();
			        }
					
					model.setValueAt(textField.getText(), table.getSelectedRow(), 0);
					model.setValueAt(Integer.parseInt(textField_1.getText()), table.getSelectedRow(), 1);
					model.setValueAt(Integer.parseInt(textField_2.getText()), table.getSelectedRow(), 2);
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(rootPane, "Please select a row from table.", "Selection "
							+ "Error", JOptionPane.INFORMATION_MESSAGE);
				}							
			}
		});
		btnNewButton_1.setBounds(246, 381, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					LinkedList<Capital> capitals2 = new LinkedList<Capital>();
					try {
						ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Capitals.txt" ) );
						capitals2 = (LinkedList<Capital>)reader.readObject();
						reader.close();
					} 
					catch( IOException e2 ) {
						System.out.println("An exception has occured during file reading.");
						e2.printStackTrace();
					} 
					catch( ClassNotFoundException e2 ) {
						System.out.println("An exception has occured while processing read records.");
						e2.printStackTrace();
					}
					
					int r = table.getSelectedRow();
					int i = 0;
					for (Capital capital2 : capitals2) {					
						if (capital2.getName().equals(model.getValueAt(r, 0)) == true) {
							i = capitals2.indexOf(capital2);
						}
					}
					capitals2.remove(i);
								
					try {
			        	ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Capitals.txt"));
			        	writer.writeObject(capitals2);
			        	writer.close();
			        	System.out.println("The information you have entered has "
								+ "been successfully saved in file " + "Capitals.txt");
			        }
			        catch(IOException e2) {
			        	e2.printStackTrace();
			        }
					model.removeRow(table.getSelectedRow());
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(rootPane, "Please select a row from table.", "Selection "
							+ "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_2.setBounds(470, 219, 85, 21);
		contentPane.add(btnNewButton_2);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (Capital aCapital : capitals) {
			model.addRow(new Object[] { aCapital.getName() , aCapital.getX() , aCapital.getY() });
		}
		
	}
}
