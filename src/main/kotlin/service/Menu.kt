package service

import java.util.Scanner

class Menu<T>(
    private val title: String,
    private val onUpdate: (Menu<T>) -> Unit
) {
    private val items = mutableListOf<Pair<String, () -> Unit>>()
    private var isRunning = true
    private val scanner = Scanner(System.`in`)

    fun addItem(label: String, action: () -> Unit) {
        items.add(Pair(label, action))
    }

    fun addItem(label: String, data: T, action: (T) -> Unit) {
        items.add(Pair(label) { action(data) })
    }

    fun run() {
        while (isRunning) {
            items.clear()
            onUpdate(this)

            println("\n$title")
            items.forEachIndexed { index, item ->
                println("$index. ${item.first}")
            }
            print("Введите цифру: ")

            val input = scanner.nextLine().trim()
            val choice = input.toIntOrNull()

            if (choice == null || choice !in items.indices) {
                println("Ошибка: Введите корректную цифру из меню.")
                continue
            }

            items[choice].second()
        }
    }

    fun stop() {
        isRunning = false
    }
}