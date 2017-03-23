package main;

import java.sql.Connection;

public class PerformQuery extends CollectInput{

	public PerformQuery(Connection con) {
		super(con);
	}

	public void ReserveRoomExecute() {
		String StartDate = toDateString(Integer.valueOf(RRTextFields[0].getText()), Integer.valueOf(RRTextFields[1].getText()),
				Integer.valueOf(RRTextFields[2].getText()));
		String EndDate = toDateString(Integer.valueOf(RRTextFields[3].getText()), Integer.valueOf(RRTextFields[4].getText()),
				Integer.valueOf(RRTextFields[5].getText()));
		int RoomNumber = Integer.valueOf(RRTextFields[6].getText());
		String Street = RRTextFields[7].getText();
		String HouseNumber = RRTextFields[8].getText();
		String PostalCode = RRTextFields[9].getText();
		int CustomerID = Integer.valueOf(RRTextFields[10].getText());
	}
	
	private String toDateString(int day, int month, int year) {
		return String.format("%d%d%d", year, month, day);
	}

	public void CheckInExecute() {
		// TODO Auto-generated method stub
		
	}

	public void ModifyReservationExecute() {
		// TODO Auto-generated method stub
		
	}

	public void CheckOutExecute() {
		// TODO Auto-generated method stub
		
	}

	public void RoomAmenitiesExecute() {
		// TODO Auto-generated method stub
		
	}

	public void RoomPriceExecute() {
		// TODO Auto-generated method stub
		
	}

	public void LateCheckOutExecute() {
		// TODO Auto-generated method stub
		
	}

	public void CustInfoExecute() {
		// TODO Auto-generated method stub
		
	}

	public void CustAllRoomExecute() {
		// TODO Auto-generated method stub
		
	}

	public void MLExecute() {
		// TODO Auto-generated method stub
		
	}

	public void AggrPriceExecute() {
		// TODO Auto-generated method stub
		
	}
}