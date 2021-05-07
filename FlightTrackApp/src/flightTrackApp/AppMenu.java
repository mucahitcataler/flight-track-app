package flightTrackApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("unused")
public class AppMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	static int min = 50;
	static int hh = 23;
	static int dd = 13;
	static int mm = 5;
	static int yy = 2020;
	static boolean stateClock = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMenu frame = new AppMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void myTime() {
		Thread clock = new Thread() {
			public void run() {
				while (true) {				
					if (stateClock == true) {
						try {
							min++;
							if(min>59) {
								min=0;
								hh++;
								if(hh>23) {
									hh=0;
									dd++;
									if (dd>30) {
										dd=1;
										mm++;
										if (mm > 12) {
											mm=1;
											yy++;
										}
									}
								}
							}
							lblNewLabel.setText(dd+"-"+mm+"-"+yy);
							lblNewLabel_1.setText(hh+":"+min);
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}					
				}
				
			}
		};
		clock.start();
	}
	
	public void flightState() {
		Thread updateState = new Thread() {
			@SuppressWarnings("unchecked")
			public void run() {
				while(true) {
					try {
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
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for (Flight aFlight : flights) {
							int control = 0;
							int i = 0;
							while (control == 0 && i < model.getRowCount()) {
								if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
									control = 1;
									if (Integer.parseInt(model.getValueAt(i, 6).toString()) != aFlight.getDelay()) {
										model.setValueAt(aFlight.getDay()+"-"+aFlight.getMonth()+"-"+aFlight.getYear(), i, 3);
										model.setValueAt(aFlight.getHour()+":"+aFlight.getMinute(), i, 4);
										model.setValueAt(aFlight.getDelay(), i, 6);
										model.setValueAt(aFlight.getaDay()+"-"+aFlight.getaMonth()+"-"+aFlight.getaYear(), i, 7);
										model.setValueAt(aFlight.getaHour()+":"+aFlight.getaMinute(), i, 8);
									}
								}
								i++;
							}
							if (control == 0 && aFlight.isLanded() == false) {
								model.addRow(new Object[] { aFlight.getID() , aFlight.getFrom().getName() , aFlight.getTo().getName() 
										, aFlight.getDay()+"-"+aFlight.getMonth()+"-"+aFlight.getYear() , aFlight.getHour()+":"+aFlight.getMinute() 
										, aFlight.getDuration() , aFlight.getDelay()
										, aFlight.getaDay()+"-"+aFlight.getaMonth()+"-"+aFlight.getaYear() 
										, aFlight.getaHour()+":"+aFlight.getaMinute() , "Not Leave Airport"});
							}
						}
						for (int i = 0; i < model.getRowCount(); i++) {
							int control = 0;
							for (Flight aFlight : flights) {
								if (aFlight.getID() == Integer.parseInt(model.getValueAt(i, 0).toString())) {
									control = 1;
								}
							}
							if (control == 0) {
								model.removeRow(i);
							}
						}
						
						for (Flight aFlight : flights) {
							if (aFlight.getYear() < yy) {
								for (int i = 0; i < model.getRowCount(); i++) {
									if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
										model.setValueAt("In the Air", i, 9);
									}
								}
							}
							else if (aFlight.getYear() == yy) {
								if (aFlight.getMonth() < mm) {
									for (int i = 0; i < model.getRowCount(); i++) {
										if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
											model.setValueAt("In the Air", i, 9);
										}
									}
								}
								else if (aFlight.getMonth() == mm) {
									if (aFlight.getDay() < dd) {
										for (int i = 0; i < model.getRowCount(); i++) {
											if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
												model.setValueAt("In the Air", i, 9);
											}
										}
									}
									else if (aFlight.getDay() == dd) {
										if (aFlight.getHour() < hh) {
											for (int i = 0; i < model.getRowCount(); i++) {
												if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
													model.setValueAt("In the Air", i, 9);
												}
											}
										}
										else if (aFlight.getHour() == hh) {
											if (aFlight.getMinute() <= min) {
												for (int i = 0; i < model.getRowCount(); i++) {
													if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
														model.setValueAt("In the Air", i, 9);
													}
												}
											}
											
										}
									}
								}
							}
							
							if (aFlight.getaYear() < yy) {
								for (int i = 0; i < model.getRowCount(); i++) {
									if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
										model.setValueAt("Arrived", i, 9);										
									}
								}
							}
							else if (aFlight.getaYear() == yy) {
								if (aFlight.getaMonth() < mm) {
									for (int i = 0; i < model.getRowCount(); i++) {
										if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
											model.setValueAt("Arrived", i, 9);
										}
									}
								}
								else if (aFlight.getaMonth() == mm) {
									if (aFlight.getaDay() < dd) {
										for (int i = 0; i < model.getRowCount(); i++) {
											if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
												model.setValueAt("Arrived", i, 9);
											}
										}
									}
									else if (aFlight.getaDay() == dd) {
										if (aFlight.getaHour() < hh) {
											for (int i = 0; i < model.getRowCount(); i++) {
												if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
													model.setValueAt("Arrived", i, 9);
												}
											}
										}
										else if (aFlight.getaHour() == hh) {
											if (aFlight.getaMinute() <= min) {
												for (int i = 0; i < model.getRowCount(); i++) {
													if (Integer.parseInt(model.getValueAt(i, 0).toString()) == aFlight.getID()) {
														model.setValueAt("Arrived", i, 9);
													}
												}
											}
											
										}
									}
								}
							}
						}
						
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		updateState.start();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public AppMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Capitals");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnýtmNewMenuItem_3 = new JMenuItem("Edit");
		mnýtmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CapitalEdit().setVisible(true);
			}
		});
		mnNewMenu.add(mnýtmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Flights");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mnýtmNewMenuItem = new JMenuItem("Edit");
		mnýtmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FlightEdit().setVisible(true);
			}
		});
		mnNewMenu_1.add(mnýtmNewMenuItem);
		
		JMenuItem mnýtmNewMenuItem_2 = new JMenuItem("Reports");
		mnýtmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FlightReports().setVisible(true);
			}
		});
		mnNewMenu_1.add(mnýtmNewMenuItem_2);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 966, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "From", "To", "Date", "Hour", "Duration(min)", "Delay(min)", "Arrival Date", "Arrival Hour", "State"
			}
		));
		table.getColumnModel().getColumn(6).setPreferredWidth(83);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("13-5-2020");
		lblNewLabel.setBounds(833, 32, 67, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("04:50");
		lblNewLabel_1.setBounds(916, 32, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Create Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int r = table.getSelectedRow();
					if (model.getValueAt(r, 9).equals("Arrived")) {
						for (Flight aFlight : flights) {
							if (aFlight.getID() == Integer.parseInt(model.getValueAt(r, 0).toString())) {
								aFlight.setLanded(true);
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
								FlightReport aReport = new FlightReport(aFlight.getID(), aFlight.getFrom(), aFlight.getTo(), aFlight.getMinute(), aFlight.getHour(), aFlight.getDay()
										, aFlight.getMonth(), aFlight.getYear(), aFlight.getDuration(), aFlight.getDelay(), aFlight.getaMinute(), aFlight.getaHour(), aFlight.getaDay()
										, aFlight.getaMonth(), aFlight.getaYear());
								LinkedList<FlightReport> flightreports = new LinkedList<FlightReport>();
								try {
									ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "FlightReports.txt" ) );
									flightreports = (LinkedList<FlightReport>)reader.readObject();							
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
								flightreports.add(aReport);
								try {
									ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("FlightReports.txt"));
									writer.writeObject(flightreports);
									writer.close();
									System.out.println("The information you have entered has "
											+ "been successfully saved in file " + "FlightReports.txt");
								}
								catch (IOException e2) {
									e2.printStackTrace();
								}
							}
						}
						model.removeRow(r);
					}
					else if (r == -1) {
						
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "This Flight is not arrived.\nPlease select another "
								+ "flight", "Not Arrived", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(rootPane, "Please select a row from table.", "Selection "
							+ "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(848, 333, 113, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stateClock = true;
			}
		});
		btnNewButton_1.setBounds(686, 59, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Stop");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateClock = false;
			}
		});
		btnNewButton_2.setBounds(781, 59, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reset");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				min = 50;
				hh = 23;
				dd = 13;
				mm = 5;
				yy = 2020;
				lblNewLabel.setText(dd+"-"+mm+"-"+yy);
				lblNewLabel_1.setText(hh+":"+min);
			}
		});
		btnNewButton_3.setBounds(876, 59, 85, 21);
		contentPane.add(btnNewButton_3);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (Flight aFlight : flights) {
			if (aFlight.isLanded() == false) {
				model.addRow(new Object[] { aFlight.getID() , aFlight.getFrom().getName() , aFlight.getTo().getName() 
						, aFlight.getDay()+"-"+aFlight.getMonth()+"-"+aFlight.getYear() , aFlight.getHour()+":"+aFlight.getMinute() 
						, aFlight.getDuration() , aFlight.getDelay()
						, aFlight.getaDay()+"-"+aFlight.getaMonth()+"-"+aFlight.getaYear() 
						, aFlight.getaHour()+":"+aFlight.getaMinute() , "Not Leave Airport"});
			}			
		}
		myTime();
		flightState();
	}

	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
