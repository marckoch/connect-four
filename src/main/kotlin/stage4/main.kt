package stage4

const val WINNING_LENGTH = 4

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
    val board = List(cols) { MutableList(rows) { ' ' } }

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
            if (indexOfFreeRowInColumn(board, colChoice - 1)  < 0) {
                println("Column $colChoice is full")
                continue
            }

            board[colChoice - 1][board[colChoice - 1].indexOfFirst { it == ' ' }] = activePlayer.second

            printBoard(rows, cols, board)

            if (checkBoardForWinningPlayer(board, activePlayer.second)) {
                println("Player ${activePlayer.first} won")
                println("Game over!")
                return
            }

            // check for draw
            val boardHasNoEmptyCell = board.all { chars -> chars.all { c -> c != ' ' } }
            if (boardHasNoEmptyCell) {
                println("It is a draw")
                println("Game over!")
                return
            }

            // else: continue game with other player
            activePlayer = toggleActivePlayer(activePlayer.first, firstPlayer, secondPlayer)
        }
    }
}

fun indexOfFreeRowInColumn(board: List<List<Char>>, col: Int): Int {
    return board[col].indexOfFirst { it == ' ' }
}

fun checkBoardForWinningPlayer(board: List<List<Char>>, token: Char): Boolean {
    if (checkColumnsForWinningPlayer(board, token)) {
        return true
    }

    if (checkRowsForWinningPlayer(board, token)) {
        return true
    }

    // diagonals /
    if (checkDiagonalsForWinningPlayer(board, token)) {
        return true
    }

    // check other diagonals \
    if (checkOtherDiagonalsForWinningPlayer(board, token)) {
        return true
    }

    // no winner or draw found, continue with next player...
    return false
}

fun checkColumnsForWinningPlayer(board: List<List<Char>>, token: Char): Boolean {
    for (c in board.indices) {
        val column = board[c]
        for (r in 0..column.size - WINNING_LENGTH) {
            val cells = listOf(
                getCell(board, c, r),
                getCell(board, c, r + 1),
                getCell(board, c, r + 2),
                getCell(board, c, r + 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkRowsForWinningPlayer(board: List<List<Char>>, token: Char): Boolean {
    // check rows
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in column.indices) {
            val cells = listOf(
                getCell(board, c, r),
                getCell(board, c + 1, r),
                getCell(board, c + 2, r),
                getCell(board, c + 3, r))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkDiagonalsForWinningPlayer(board: List<List<Char>>, token: Char): Boolean {
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in 0 .. column.size - WINNING_LENGTH) {
            val cells = listOf(
                getCell(board, c, r),
                getCell(board, c + 1, r + 1),
                getCell(board, c + 2, r + 2),
                getCell(board, c + 3, r + 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkOtherDiagonalsForWinningPlayer(board: List<List<Char>>, token: Char): Boolean {
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in WINNING_LENGTH - 1 until column.size) {
            val cells = listOf(
                getCell(board, c, r),
                getCell(board, c + 1, r - 1),
                getCell(board, c + 2, r - 2),
                getCell(board, c + 3, r - 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun getCell(board: List<List<Char>>, col: Int, row: Int): Char {
    return board[col][row]
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

fun printBoard(noOfRows: Int, noOfCols: Int, board: List<List<Char>>) {
    // header with numbers
    println(" " + (1..noOfCols).joinToString(" "))

    // columns
    repeat (noOfRows) { row ->
        print("║")
        repeat (noOfCols) { col ->
            print("" + getCell(board, col, noOfRows - row - 1) + "║")
        }
        println()
    }

    val bottomRow = CharArray(noOfCols) { '═' }.joinToString("╩")
    println("╚$bottomRow╝")
}