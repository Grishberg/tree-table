package com.github.grishberg.expandabletree.model

/**
 * Groups collection.
 */
internal class GroupedTableDataModel(
    private val groups: List<TableRowInfo>
) {
    fun getGroupsCount() = groups.size
    fun getTableRowInfo(groupIndex: Int): TableRowInfo = groups[groupIndex]
}
