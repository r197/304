package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends Application {
	protected Connection con;
	private String tags[] = { "Reserve Room", "Check In", "Modify Reservation", "Check Out", "Query Room Amenities",
			"Query Room Above/Below Price", "Query Late Check Outs", "Query Customer Information",
			"Query Customer Who Reserved All Rooms In Branch", "Query Most/Least Expensive Room In Branch",
			"Query Aggregated Prices In Branch" };

	public MainMenu(Connection con) {
		this.con = con;
	}

	public void showMenu() {
		try {
			con.setAutoCommit(false);

			int numTags = tags.length;
			JButton buttons[] = new JButton[numTags];

			JPanel contentPane = new JPanel();
			mainFrame.setContentPane(contentPane);
			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			contentPane.setLayout(gb);
			contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.insets = new Insets(5, 10, 10, 10);
			c.anchor = GridBagConstraints.CENTER;

			for (int i = 0; i < numTags; i++) {
				buttons[i] = new JButton(tags[i]);
				gb.setConstraints(buttons[i], c);
				contentPane.add(buttons[i]);
				buttons[i].setActionCommand(tags[i]);
				buttons[i].addActionListener(this);
			}

			mainFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			mainFrame.pack();

			Dimension d = mainFrame.getToolkit().getScreenSize();
			Rectangle r = mainFrame.getBounds();
			mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

			mainFrame.setVisible(true);

		} catch (SQLException e) {
			// TODO handle appropriately in UI (error dialog?)
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		CollectInput c = new CollectInput(con);
		if (cmd.equals(tags[0])) {
			mainFrame.dispose();
			c.ReserveRoom();
		} else if (cmd.equals(tags[1])) {
			mainFrame.dispose();
			c.CheckIn();
		} else if (cmd.equals(tags[2])) {
			mainFrame.dispose();
			c.ModifyReservation();
		} else if (cmd.equals(tags[3])) {
			mainFrame.dispose();
			c.CheckOut();
		} else if (cmd.equals(tags[4])) {
			mainFrame.dispose();
			c.RoomAmenities();
		} else if (cmd.equals(tags[5])) {
			mainFrame.dispose();
			c.RoomPrice();
		} else if (cmd.equals(tags[6])) {
			mainFrame.dispose();
			c.LateCheckOut();
		} else if (cmd.equals(tags[7])) {
			mainFrame.dispose();
			c.CustInfo();
		} else if (cmd.equals(tags[8])) {
			mainFrame.dispose();
			c.CustAllRoom();
		} else if (cmd.equals(tags[9])) {
			mainFrame.dispose();
			c.ML();
		} else if (cmd.equals(tags[10])) {
			mainFrame.dispose();
			c.AggrPrice();
		}
	}

}