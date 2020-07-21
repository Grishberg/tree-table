package com.github.grishberg.expandabletree.model;

import com.github.grishberg.expandabletree.AbstractTreeTableModel;
import com.github.grishberg.expandabletree.TreeTableModel;

import java.util.List;

public class GroupedTableModel extends AbstractTreeTableModel implements TreeTableModel {

    // Names of the columns.
    static protected String[] cNames = {"Title", "Value"};

    // Types of the columns.
    static protected Class[] cTypes = {TreeTableModel.class, String.class};

    public GroupedTableModel(List<TableRowInfo> groups) {
        super(new GroupedTableDataModel(groups));
    }

    public void setValues(List<TableRowInfo> groups) {
        root = groups;
    }

    //
    // The TreeModel interface
    //
    @Override
    public int getChildCount(Object node) {
        if (node instanceof GroupedTableDataModel) {
            return ((GroupedTableDataModel) node).getGroupsCount();
        }
        if (node instanceof TableRowInfo) {
            return ((TableRowInfo) node).getRowCount();
        }
        return 0;
    }

    @Override
    public Object getChild(Object node, int i) {
        if (node instanceof GroupedTableDataModel) {
            return ((GroupedTableDataModel) node).getTableRowInfo(i);
        }

        if (node instanceof TableRowInfo) {
            return ((TableRowInfo) node).getRowAt(i);
        }
        return null;
    }

    // The superclass's implementation would work, but this is more efficient.
    @Override
    public boolean isLeaf(Object node) {
        //return getContribution(node).isFile();
        return super.isLeaf(node);
    }

    //
    //  The TreeTableNode interface.
    //
    @Override
    public int getColumnCount() {
        return cNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return cNames[column];
    }

    @Override
    public Class getColumnClass(int column) {
        return cTypes[column];
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof TableRowInfo) {
            if (column == 0) {
                return node;
            }
            return null;
        }
        RowInfo rowInfo = (RowInfo) node;

        switch (column) {
            case 0:
                return rowInfo.toString();
            case 1:
                return rowInfo.value();
        }

        return null;
    }
}
