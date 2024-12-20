package space.prainy.day01

import space.prainy.println
import space.prainy.readInput
import kotlin.math.abs

fun main() {
    fun List<String>.splitIntegers(): Pair<List<Int>, List<Int>> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        for (line in this) {
            val splitted = line.split("\\s+".toRegex())

            list1.add(splitted[0].toInt())
            list2.add(splitted[1].toInt())
        }

        return Pair(list1.toList(), list2.toList())
    }

    fun part1(input: List<String>): Int {
        val (list1, list2) = input.splitIntegers()

        val sorted1 = list1.sorted()
        val sorted2 = list2.sorted()

        var sum = 0;
        for (i in 0..< input.size) {
            val distance = abs(sorted1[i] - sorted2[i])
            sum += distance
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val (list1, list2) = input.splitIntegers()

        val countMap = hashMapOf<Int, Int>()
        for (element in list2) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1)
        }

        var score = 0;
        for (num in list1) {
            score += num * countMap.getOrDefault(num, 0)
        }

        return score
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
   val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
