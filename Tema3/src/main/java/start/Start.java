package start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;

import model.Client;

import presentation.Controller;
import presentation.View;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		/*Client client = new Client(2);
		*/
		ClientBLL clientBll = new ClientBLL();
		clientBll.view();
		/*if (id > 0) {
			clientBll.findClientById(id);
		}
		
		
		// Generate error
		try {
			clientBll.findClientById(0);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}*/
		//obtain field-value pairs for object through reflection
		View view = new View();
		Controller controller = new Controller(view);

		Client client1= new Client(1,"Popescu","pop@email.com",20);
		Client client2= new Client(2,"Popovici","pa@gemail.com",32);
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(client1);
		lista.add(client2);

		ReflectionExample.creareTabel(lista);

		//view.setVisible(true);
	}
	
	

}
