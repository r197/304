package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import model.Reservation;

public class ModifyReservation extends AbstractQuery<Reservation>{
	
	String checkIn;
	String checkout;
	int confirmationID;
	int custID;

	public ModifyReservation(String checkIn, String checkout, int confirmationID, int custID) {
		this.checkIn = checkIn;
		this.checkout = checkout;
		this.confirmationID = confirmationID;
		this.custID = custID;
	}

	@Override
	public Reservation execute(Connection con) throws SQLException {
		boolean exists = new CheckReservationExists(confirmationID, custID).execute(con);
		if (!exists) {
			throw new SQLException("Failed to find existing non expired reservation for the given customer");
		}
		boolean timeGood = checkDates();
		if(!timeGood) {
			throw new SQLException("Error: Check In time must be before Check Out time and after Current time");
		}
		super.execute(con);
		con.commit();
		Reservation r = new GetCorrespondingReservation(confirmationID).execute(con);
		return r;
	}
	
	@Override
	protected Reservation parseResult(ResultSet rs) throws SQLException {
		return null;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format("UPDATE Reservation SET StartDate = to_date(%s, 'yyyymmdd'), "
				+ "EndDate = to_date(%s, 'yyyymmdd') WHERE ConfirmationID = %d", checkIn, checkout, confirmationID);
	}
	
	
	protected boolean checkDates() {
		GregorianCalendar startDate = new GregorianCalendar(Integer.parseInt(checkIn.substring(0, 4)),
				Integer.parseInt(checkIn.substring(4, 6)),
				Integer.parseInt(checkIn.substring(6, 8)));
		GregorianCalendar endDate = new GregorianCalendar(Integer.parseInt(checkout.substring(0, 4)),
				Integer.parseInt(checkout.substring(4, 6)),
				Integer.parseInt(checkout.substring(6, 8)));
		if(!startDate.before(endDate)) {
			return false;
		}
		if(!startDate.after(new GregorianCalendar())) {
			return false;
		}
		return true;
		
	}
	
	
	private class GetCorrespondingReservation  extends AbstractQuery<Reservation>{

		private int confID;
		
		private GetCorrespondingReservation(int confirmationID) {
			confID = confirmationID;
		}
		
		@Override
		protected Reservation parseResult(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			rs.next();
			Reservation r = new Reservation(rs.getInt("ConfirmationID"), 
					rs.getString("StartDate"), rs.getString("EndDate"), rs.getInt("RoomNumber"), rs.getString("Street"),
					rs.getString("HouseNumber"), rs.getString("PostalCode"), rs.getInt("CustomerID"));
			return r;
		}

		@Override
		protected String getQueryDefinition() {
			return String.format("SELECT * FROM Reservation WHERE ConfirmationID = %d", confID);
		}
		
	}

}
