[![](https://jitpack.io/v/Grishberg/tree-table.svg)](https://jitpack.io/#Grishberg/tree-table)

# Java Tree Table

See `com.github.grishberg.test.JTreeTableTest` for example.

## Usage as grouped named values:
1) Create instance of `GroupedTableModel` with `List` of `TableRowInfo`
2) Create instance of `JTreeTable` with created `GroupedTableModel`

If you want to update table: call `treeTable.setModel(GroupedTableModel(groups))`

For different usage create your own implementation of `AbstractTreeTableModel`


## How to get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

**Step 2.** Add the dependency
```
dependencies {
	    implementation 'com.github.Grishberg:tree-table:Tag'
}
```
