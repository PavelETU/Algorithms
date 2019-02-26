package arraysandstring

import org.junit.Test
import kotlin.test.assertEquals

// Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0
class ZeroMatrix {

    private fun transformMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val rowsToMakeZero = ArrayList<Int>()
        val colsToMakeZero = ArrayList<Int>()
        matrix.forEachIndexed { row, rowArray -> rowArray.forEachIndexed { col, element ->
            if (element == 0) {
                rowsToMakeZero.add(row)
                colsToMakeZero.add(col)
            }
        } }
        return Array(matrix.size) {row -> IntArray(matrix[0].size) { col ->
            if (rowsToMakeZero.contains(row) || colsToMakeZero.contains(col)) {
                0
            } else {
                matrix[row][col]
            }
        }}
    }

    @Test
    fun `Matrix 3x3 and center element turns 1 row and 1 columns into 0`() {
        val array = Array(3) {row -> IntArray(3) {col ->
            if (row == 1 && col == 1) {
                0
            } else {
                99
            }
        }}

        val transformedMatrix = transformMatrix(array)

        // Check column transformation
        assertEquals(0, transformedMatrix[0][1])
        assertEquals(0, transformedMatrix[2][1])

        // Check row transformation
        assertEquals(0, transformedMatrix[1][1])
        assertEquals(0, transformedMatrix[1][2])
    }

}