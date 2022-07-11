package stage3

fun main() {
    println("Connect Four")

    println("First player's name:")
    val firstPlayer = readLine()!!

    println("Second player's name:")
    val secondPlayer = readLine()!!

    val (rows, cols) = getValidDimensions()

    println("$firstPlayer VS $secondPlayer")
    println("$rows X $cols board")

    // first dim is column (x), second dim is row/height (y)
    val board = List(cols) { mutableListOf<Char>() }

    printBoard(rows, cols, board)

    // TODO or make own small data class?
    // alternative: just count turns and go by odd / even
    var activePlayer = Pair(firstPlayer, 'o')

    while (true) {
        println("${activePlayer.first}'s turn:")

        val move = readLine()!!
        if (move == "end") {
            println("Game over!")
            return
        } else if (!isNumber(move)) {
            println("Incorrect column number")
            continue
        } else {
            val colChoice = move.toInt()
            if (colChoice !in 1..cols) {
                println("The column number is out of range (1 - $cols)")
                continue
            }
            if (board[colChoice - 1].size == cols) {
                println("Column $colChoice is full")
                continue
            }

            board[colChoice - 1].add(activePlayer.second)

            activePlayer = toggleActivePlayer(activePlayer.first, firstPlayer, secondPlayer)

            printBoard(rows, cols, board)
        }
    }
}

fun isNumber(s: String): Boolean {
    return Regex("\\d+").matches(s)
}

// TODO make nicer
fun toggleActivePlayer(player: String, firstPlayer: String, secondPlayer: String): Pair<String, Char> {
    return when (player) {
        firstPlayer -> Pair(secondPlayer, '*')
        else -> Pair(firstPlayer, 'o')
    }
}

fun getValidDimensions(): Pair<Int, Int> {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")

    // e.g. "  6  x    3  ", "5X5" or "  2X  4"
    val regex = """\s*(\d)+\s*[xX]\s*(\d)+\s*""".toRegex()
    val userEntryForDimensions = readLine()!!

    val (row, col) = when {
        regex.matches(userEntryForDimensions) -> regex.find(userEntryForDimensions)!!.destructured.toList().map { s -> s.toInt() }
        userEntryForDimensions.isEmpty() -> listOf(6, 7)
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

fun printBoard(rows: Int, cols: Int, board: List<List<Char>>) {
    // header with numbers
    println(" " + (1..cols).joinToString(" "))

    // columns
    repeat (rows) { row ->
        print("║")
        repeat (cols) {col ->
            print("" + board[col].getOrElse(rows - row - 1) {" "} + "║")
        }
        println()
    }

    val bottomRow = CharArray(cols) { '═' }.joinToString("╩")
    println("╚$bottomRow╝")
}