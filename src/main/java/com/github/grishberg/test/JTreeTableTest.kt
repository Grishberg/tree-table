package com.github.grishberg.test

import com.github.grishberg.expandabletree.JTreeTable
import com.github.grishberg.expandabletree.model.GroupedTableModel
import com.github.grishberg.expandabletree.model.RowInfo
import com.github.grishberg.expandabletree.model.TableRowInfo
import java.awt.Color
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.*
import java.util.*
import javax.swing.*
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import kotlin.system.exitProcess

/**
 * A test usage of the tree table component.
 */
class JTreeTableTest {
    val table: JTreeTable

    init {
        val groups = createSampleData()
        val tableModel = GroupedTableModel(emptyList())
        table = JTreeTable(tableModel)
        val tableRenderer = table.treeTableCellRenderer
        tableRenderer.isRootVisible = false
        table.setShowGrid(true)
        table.showHorizontalLines = true
        table.showVerticalLines = true

        table.setModel(GroupedTableModel(groups))

        val copyStroke = KeyStroke.getKeyStroke(
            KeyEvent.VK_C,
            Toolkit.getDefaultToolkit().menuShortcutKeyMask,
            false
        )
        table.registerKeyboardAction(CopyAction(), "Copy", copyStroke, JComponent.WHEN_FOCUSED)
        table.selectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION
        table.setSize(200, 300)

        table.addTreeExpansionListener(object : TreeExpansionListener {
            override fun treeExpanded(event: TreeExpansionEvent) {
                val expandedGroupName = event.path
                println("expanded group: ${expandedGroupName.lastPathComponent}")
            }

            override fun treeCollapsed(event: TreeExpansionEvent) {
                val collapsedGroupName = event.path
                println("collapsed group: ${collapsedGroupName.lastPathComponent}")
            }
        })

        val treeView = JScrollPane(table)
        val sp = JScrollPane(treeView)
        sp.viewport.background = Color.white
        val frame = JFrame("JTreeTableTest")
        frame.contentPane.add(sp)
        frame.setSize(400, 300)
        frame.isVisible = true
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(we: WindowEvent) {
                exitProcess(0)
            }
        })
    }

    private fun createSampleData(): List<TableRowInfoImpl> {
        val groupsList = ArrayList<TableRowInfoImpl>()
        for (g in 0..19) {
            val rows = ArrayList<RowInfoImpl>()
            for (i in 0..19) {
                rows.add(RowInfoImpl("Very long name $g:$i", "value $g:$i"))
            }
            groupsList.add(TableRowInfoImpl("Group $g", rows))
        }
        return groupsList
    }

    /**
     * Table model
     */
    private class TableRowInfoImpl(private val name: String, private val rows: ArrayList<RowInfoImpl>) :
        TableRowInfo {
        override fun getRowCount(): Int {
            return rows.size
        }

        override fun getRowAt(row: Int): RowInfo {
            return rows[row]
        }

        override fun toString(): String {
            return name
        }
    }

    /**
     * Table row value model.
     */
    private class RowInfoImpl(private val name: String, private val value: String) : RowInfo {
        override fun value(): String {
            return value
        }

        override fun toString(): String {
            return name
        }
    }

    private inner class CopyAction : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            if (e.actionCommand.compareTo("Copy") != 0) {
                return
            }
            val sbf = StringBuffer()
            val numcols: Int = table.columnModel.columnCount
            val numrows: Int = table.selectedRowCount

            if (numrows < 1) {
                JOptionPane.showMessageDialog(
                    null, "Invalid Copy Selection",
                    "Invalid Copy Selection", JOptionPane.ERROR_MESSAGE
                )
                return
            }
            val rowsselected = table.selectedRows.first()

            for (j in 0 until numcols) {
                sbf.append(table.getValueAt(rowsselected, j))
                if (j < numcols - 1) sbf.append("\t")
            }

            val stringSelection = StringSelection(sbf.toString())
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(stringSelection, null)

        }
    }

    companion object {
        /*
         *  Main method for testing.
        */
        @JvmStatic
        fun main(args: Array<String>) {
            JTreeTableTest()
        }
    }
}
