package flightTrackApp;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class FlightEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightEdit frame = new FlightEdit();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FlightEdit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LinkedList<Flight> flights = new LinkedList<Flight>();
		try {
			ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Flights.txt" ) );
			flights = (LinkedList<Flight>)reader.readObject();
			
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
		scrollPane.setBounds(10, 29, 666, 193);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "From", "To", "Date", "Hour", "Duration", "Delay", "Arrival Date", "Arrival Hour"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Cancel Flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					LinkedList<Flight> flights = new LinkedList<Flight>();
					try {
						ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Flights.txt" ) );
						flights = (LinkedList<Flight>)reader.readObject();
						
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
					Flight deleteFlight = new Flight();
					int r = table.getSelectedRow();
					int i = 0;
					for (Flight aFlight : flights) {
						if (aFlight.getID() == Integer.parseInt(model.getValueAt(r, 0).toString())) {
							i = flights.indexOf(aFlight);
						}
					}
					flights.remove(i);
					try {
						ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("Flights.txt"));
						writer.writeObject(flights);
						writer.close();
						System.out.println("The information you have entered has "
								+ "been successfully saved in file " + "Flights.txt");
					}
					catch (IOException e2) {
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
		btnNewButton.setBounds(542, 232, 110, 21);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Add Delay");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					LinkedList<Flight> flights = new LinkedList<Flight>();
					try {
						ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Flights.txt" ) );
						flights = (LinkedList<Flight>)reader.readObject();
						
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
					Flight updateFlight = new Flight();
					int r = table.getSelectedRow();
					for (Flight aFlight : flights) {
						if (aFlight.getID() == Integer.parseInt(model.getValueAt(r, 0).toString())) {
							aFlight.setDelay(aFlight.getDelay()+Integer.parseInt(textField.getText()));
							model.setValueAt(aFlight.getDay()+"-"+aFlight.getMonth()+"-"+aFlight.getYear(), table.getSelectedRow(), 3);
							model.setValueAt(aFlight.getHour()+":"+aFlight.getMinute(), table.getSelectedRow(), 4);
							model.setValueAt(aFlight.getDelay(), table.getSelectedRow(), 6);
							model.setValueAt(aFlight.getaDay()+"-"+aFlight.getaMonth()+"-"+aFlight.getaYear(), table.getSelectedRow(), 7);
							model.setValueAt(aFlight.getaHour()+":"+aFlight.getaMinute(), table.getSelectedRow(), 8);
							
						}
					}
					try {
						ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("Flights.txt"));
						writer.writeObject(flights);
						writer.close();
						System.out.println("The information you have entered has "
								+ "been successfully saved in file " + "Flights.txt");
					}
					catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(rootPane, "Please select a row from table.", "Selection "
							+ "Error", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btnNewButton_1.setBounds(551, 338, 101, 21);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(445, 339, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 251, 211, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(28, 254, 45, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setBounds(143, 375, 4, 13);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		for (Capital aCapital : capitals) {
			comboBox.addItem(aCapital.getName());
		}
		comboBox.setBounds(83, 280, 211, 21);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		for (Capital aCapital : capitals) {
			comboBox_1.addItem(aCapital.getName());
		}
		comboBox_1.setBounds(83, 312, 211, 21);
		contentPane.add(comboBox_1);
		
		txtDay = new JTextField();
		txtDay.setText("Day");
		txtDay.setBounds(83, 343, 50, 19);
		contentPane.add(txtDay);
		txtDay.setColumns(10);
		
		txtMonth = new JTextField();
		txtMonth.setText("Month");
		txtMonth.setColumns(10);
		txtMonth.setBounds(160, 343, 50, 19);
		contentPane.add(txtMonth);
		
		txtYear = new JTextField();
		txtYear.setText("Year");
		txtYear.setColumns(10);
		txtYear.setBounds(234, 343, 60, 19);
		contentPane.add(txtYear);
		
		textField_5 = new JTextField();
		textField_5.setBounds(83, 372, 50, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(157, 372, 50, 19);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setBounds(143, 346, 4, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("-");
		lblNewLabel_2_1.setBounds(220, 346, 4, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("From");
		lblNewLabel_3.setBounds(28, 284, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("To");
		lblNewLabel_4.setBounds(28, 316, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setBounds(28, 346, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Hour");
		lblNewLabel_6.setBounds(28, 375, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Flight> flights = new LinkedList<Flight>();
				try {
					ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Flights.txt" ) );
					flights = (LinkedList<Flight>)reader.readObject();
					
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
				
				LinkedList<Capital> capitals = new LinkedList<Capital>();
				try {
					ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Capitals.txt" ) );
					capitals = (LinkedList<Capital>)reader.readObject();			
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
				
				int control = 0;
				for (Flight aFlight : flights) {
					if (aFlight.getID() == Integer.parseInt(textField_1.getText())) {
						control=1;
					}
				}
				if (control== 0) {
					Capital newFrom = new Capital();
					Capital newTo = new Capital();
					int newID = Integer.parseInt(textField_1.getText());
					int newMinute = Integer.parseInt(textField_6.getText());
					int newHour = Integer.parseInt(textField_5.getText());
					int newDay = Integer.parseInt(txtDay.getText());
					int newMonth = Integer.parseInt(txtMonth.getText());
					int newYear = Integer.parseInt(txtYear.getText());
					
					for (Capital aCapital : capitals) {
						if (aCapital.getName().equals(comboBox.getSelectedItem().toString() ) == true ) {
							newFrom = aCapital;
						}
						if (aCapital.getName().equals(comboBox_1.getSelectedItem().toString() ) == true ) {
							newTo = aCapital;
						}
					}
					Flight newFlight = new Flight(newID, newFrom, newTo, newMinute, newHour, newDay, newMonth, newYear);
					flights.add(newFlight);
					
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();				
					model.addRow(new Object[] { newFlight.getID() , newFlight.getFrom().getName() , newFlight.getTo().getName() 
							, newFlight.getDay()+"-"+newFlight.getMonth()+"-"+newFlight.getYear() 
							, newFlight.getHour()+":"+newFlight.getMinute() , newFlight.getDuration() 
							, newFlight.getDelay() , newFlight.getaDay()+"-"+newFlight.getaMonth()+"-"+newFlight.getaYear() 
							, newFlight.getaHour()+":"+newFlight.getaMinute() });
					
					try {
						ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("Flights.txt"));
						writer.writeObject(flights);
						writer.close();
						System.out.println("The information you have entered has "
								+ "been successfully saved in file " + "Flights.txt");
					}
					catch (IOException e2) {
						e2.printStackTrace();
					}

				}
				else {
					JOptionPane.showMessageDialog(rootPane, "This ID is same with a past or current flight.\nPlease enter a "
							+ "different ID", "Same ID Error", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btnNewButton_2.setBounds(209, 405, 85, 21);
		contentPane.add(btnNewButton_2);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (Flight aFlight : flights) {
			if (aFlight.isLanded() == false) {
				model.addRow(new Object[] { aFlight.getID() , aFlight.getFrom().getName() , aFlight.getTo().getName() 
						, aFlight.getDay()+"-"+aFlight.getMonth()+"-"+aFlight.getYear() , aFlight.getHour()+":"+aFlight.getMinute() 
						, aFlight.getDuration() , aFlight.getDelay()
						, aFlight.getaDay()+"-"+aFlight.getaMonth()+"-"+aFlight.getaYear() 
						, aFlight.getaHour()+":"+aFlight.getaMinute() });
			}			
		}
	}
}
