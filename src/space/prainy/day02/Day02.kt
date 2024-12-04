package space.prainy.day02

import space.prainy.println
import space.prainy.readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safeCount = 0

        for (line in input) {
            val levels = line.split(" ").map { it.toInt() }
            val diffs = levels.zipWithNext { a, b -> a - b }

            var safe = 1
            for (i in 0 ..< diffs.size) {
                if (i > 0 && diffs[i] * diffs[i - 1] <= 0 || abs(diffs[i]) > 3) {
                    safe = 0;
                    break;
                }
            }

            safeCount += safe
        }

        return safeCount
    }

    fun part2(input: List<String>): Int {
        var safeCount = 0

        for (line in input) {
            val levels = line.split(" ").map { it.toInt() }

            fun checkSafe(input: List<Int>): Boolean {
                val diffs = input.zipWithNext { a, b -> a - b }

                for (i in 0 ..< diffs.size) {
                    if (i > 0 && diffs[i] * diffs[i - 1] <= 0 || abs(diffs[i]) > 3) {
                        return false
                    }
                }
                return true
            }

            if (levels.size <= 2 || checkSafe(levels)) { safeCount ++ }
            else {
                for (i in 0 .. levels.lastIndex) {
                    val dampened = levels.filterIndexed { index, _ -> index != i }
                    if (checkSafe(dampened)) {
                        safeCount ++
                        break
                    }
                }
            }
        }

        return safeCount
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 5)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
