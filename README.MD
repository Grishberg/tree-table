# Java Tree Table

See `com.github.grishberg.test.JTreeTableTest` for example.

## Usage as grouped named values:
1) Create instance of `GroupedTableModel` with `List` of `TableRowInfo`
2) Create instance of `JTreeTable` with created `GroupedTableModel`

If you want to update table: call `treeTable.setModel(GroupedTableModel(groups))`

For different usage create your own implementation of `AbstractTreeTableModel`
