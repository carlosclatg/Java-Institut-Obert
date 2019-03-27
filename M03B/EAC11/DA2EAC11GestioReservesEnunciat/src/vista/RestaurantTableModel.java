package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Restaurant;

/**
 *
 * @author fta
 */
public class RestaurantTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Codi", "Nom", "Adreça"};

    String[][] data = new String[ControladorPrincipal.getMAXRESTAURANTS()][3];

    public RestaurantTableModel() {
        int i = 0;
        for (Restaurant restaurant : ControladorPrincipal.getRestaurants()) {
            if (restaurant != null) {
                data[i][0] = String.valueOf(restaurant.getCodi());
                data[i][1] = restaurant.getNom();
                data[i][2] = restaurant.getAdreca();
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
