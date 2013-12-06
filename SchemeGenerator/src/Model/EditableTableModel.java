package Model;

import javax.swing.table.AbstractTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/21/13
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditableTableModel extends AbstractTableModel
{
    String[] columnTitles;
    Object[][] dataEntries;
    int rowCount;
    public EditableTableModel(String[] columnTitles, Object[][] dataEntries)
    {
        this.columnTitles = columnTitles;
        this.dataEntries = dataEntries;
    }
    public int getRowCount()
    {
        return dataEntries.length;
    }
    public int getColumnCount()
    {
        return columnTitles.length;
    }
    public Object getValueAt(int row, int column)
    {
        return dataEntries[row][column];
    }
    public String getColumnName(int column)
    {
        return columnTitles[column];
    }
    public Class getColumnClass(int column)
    {
        return getValueAt(0, column).getClass();
    }
    public boolean isCellEditable(int row, int column)
    {
        return true;
    }
    public void setValueAt(Object value, int row, int column)
    {
        dataEntries[row][column] = value;
    }
}
