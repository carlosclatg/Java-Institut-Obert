package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Cambrer;
import principal.Element;

/**
 *
 * @author fta
 */
public class CambrerTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Codi", "Nom", "Telefon", "Torn", "Actiu"};

    String[][] data = new String[ControladorPrincipal.getRestaurantActual().getElements().length][5];

    public CambrerTableModel() {
        int i = 0;
        for (Element element : ControladorPrincipal.getRestaurantActual().getElements()) {
            if (element instanceof Cambrer) {

                data[i][0] = String.valueOf(((Cambrer) element).getCodi());
                data[i][1] = ((Cambrer) element).getNom();
                data[i][2] = ((Cambrer) element).getTelefon();
                data[i][3] = ((Cambrer) element).getTorn();

                if (((Cambrer) element).getActiu()) {
                    data[i][4] = "si";
                } else {
                    data[i][4] = "no";
                }

                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

}
