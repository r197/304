package components;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Customer;
import queries.CustomersReservingAllRoomsInBranch;
import queries.IQuery;
import ui.QueryControl;

public class CustomerAllRoomsComponent extends AbstractQueryComponent<List<Customer>> {

	public CustomerAllRoomsComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("Enter House Number: "), QueryControl.text("Enter Street: "),
				QueryControl.text("Enter Postal Code: ") };
	}

	@Override
	protected IQuery<List<Customer>> createQuery(JFormattedTextField[] textFields) {
		String houseNo = textFields[0].getText();
		String street = textFields[1].getText();
		String postalCode = textFields[2].getText();
		System.out.println("house:" + houseNo);
		System.out.println("Street" + street);
		System.out.println("Post" + postalCode);
		return new CustomersReservingAllRoomsInBranch(street, houseNo, postalCode);
	}

	@Override
	public String getDescription() {
		return "Query Customer Who Reserved All Rooms In Branch";
	}

	protected void executeQuery(JTextField[] textFields) {
		String HouseNumber = textFields[0].getText();
		String Street = textFields[1].getText();
		String PostalCode = textFields[2].getText();
		
		CustomersReservingAllRoomsInBranch c = new CustomersReservingAllRoomsInBranch(Street, HouseNumber, PostalCode);
		try{
			List<Customer> r = c.execute(con);
			mainFrame.dispose();
			displayData(parseData(r));
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected List<List<String>> parseData(List<Customer> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("Customer ID", "Name"));
		for (int i = 0; i < t.size(); i++){
			Customer b = t.get(i);
			data.add(Arrays.asList(Integer.toString(b.getId()), b.getName()));
		}
		return data;
	}
}
