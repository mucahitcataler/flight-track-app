package flightTrackApp;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FlightReports extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightReports frame = new FlightReports();
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
	public FlightReports() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 966, 443);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Departure City", "Arrival City", "Scheduled Departure Time", "Scheduled Arrival Time", "Delay", "Take Off Time", "Landing Time"
			}
		));
		scrollPane.setViewportView(table);
		
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
		DefaultTableModel model = (DefaultTableModel) table.getModel();				
		for (FlightReport aflightReport : flightreports) {
			model.addRow(new Object[] { aflightReport.getID() , aflightReport.getFrom().getName() , aflightReport.getTo().getName() 
					, aflightReport.getSdDay()+"-"+aflightReport.getSdMonth()+"-"+aflightReport.getSdYear()+"  "+aflightReport.getSdHour()+":"+aflightReport.getSdMinute() 
					, aflightReport.getSaDay()+"-"+aflightReport.getSaMonth()+"-"+aflightReport.getSaYear()+"  "+aflightReport.getSaHour()+":"+aflightReport.getSaMinute()
					, aflightReport.getDelay()
					, aflightReport.gettDay()+"-"+aflightReport.gettMonth()+"-"+aflightReport.gettYear()+"  "+aflightReport.gettHour()+":"+aflightReport.gettMinute()
					, aflightReport.getlDay()+"-"+aflightReport.getlMonth()+"-"+aflightReport.getlYear()+"  "+aflightReport.getlHour()+":"+aflightReport.getlMinute()});
		}
	}
}
