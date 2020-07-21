package com.github.grishberg.expandabletree.model

/**
 * Values of group collection
 */
interface TableRowInfo {
    fun getRowCount(): Int
    fun getRowAt(row: Int): RowInfo
}
