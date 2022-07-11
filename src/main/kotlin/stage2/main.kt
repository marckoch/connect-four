package stage2

fun main() {
    println("Connect Four")

    println("First player's name:")
    val firstPlayer = readLine()!!

    println("Second player's name:")
    val secondPlayer = readLine()!!

    val (row, col) = getValidDimensions()

    println("$firstPlayer VS $secondPlayer")
    println("$row X $col board")

    printBoard(row, col)
}

fun getValidDimensions(): Pair<Int, Int> {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")

    // e.g. "  6  x    3  ", "5X5" or "  2X  4"
    val regex = """\s*(\d)+\s*[xX]\s*(\d)+\s*""".toRegex()
    val userEntryForDimensions = readLine()!!

    val (row, col) = when {
        regex.matches(userEntryForDimensions) -> regex.find(userEntryForDimensions)!!.destructured.toList().map { s -> s.toInt() }
        userEntryForDimensions.isEmpty() -> listOf(6, 7) // default
        else -> {
            println("Invalid input")
            return getValidDimensions()
        }
    }

    // validate row
    if (row !in 5..9) {
        println("Board rows should be from 5 to 9")
        return getValidDimensions()
    }

    //validate col
    if (col !in 5..9) {
        println("Board columns should be from 5 to 9")
        return getValidDimensions()
    }

    return Pair(row, col)
}

fun printBoard(row: Int, col: Int) {
    // header with numbers
    println(" " + (1..col).joinToString(" "))

    // columns
    val line = CharArray(col + 1) { '║' }.joinToString(" ")
    repeat (row) {
        println(line)
    }

    val bottomRow = CharArray(col) { '═' }.joinToString("╩")
    println("╚$bottomRow╝")
}