package start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionExample {

	public static void retrieveProperties(Object object) {

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				System.out.println(field.getName() + "=" + value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @param lista lista de elemente care va fi data pentru crearea tabelului cu elementele acesteia
	 */
	public static void creareTabel(ArrayList<Object> lista) {
		JFrame reflectionFrame = new JFrame("Tabel Reflection");
		ArrayList<String> columns = new ArrayList<>();
		DefaultTableModel model = new DefaultTableModel();
		for(Field field:lista.get(0).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			model.addColumn(field.getName());
			columns.add(field.getName());
			//System.out.println(field.getName());
		}
		model.addRow(columns.toArray());
		for(int i=0; i<lista.size();i++) {
			ArrayList<Object> obiecte = new ArrayList<>();
			for(Field field:lista.get(i).getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try{
					//model.addRow(new Object[] {field.get(lista.get(i))});
					obiecte.add(field.get(lista.get(i)));
				}catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			model.addRow(obiecte.toArray());
		}
		JTable table = new JTable(model);

		reflectionFrame.add(table);
		reflectionFrame.pack();
		reflectionFrame.setVisible(true);

	}
}
