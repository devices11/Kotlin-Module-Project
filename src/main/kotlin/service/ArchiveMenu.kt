package service

import model.Archive
import java.util.Scanner

class ArchiveMenu {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun show() {
        val menu = Menu("--- Список архивов ---") { menuItem ->
            menuItem.addItem("Создать архив") { createArchive() }

            archives.forEach { archive ->
                menuItem.addItem(archive.name, archive) { selectedArchive ->
                    NoteMenu(selectedArchive).show()
                }
            }

            menuItem.addItem("Выход") { menuItem.stop() }
        }
        menu.run()
    }

    private fun createArchive() {
        println("\nВведите название нового архива:")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Ошибка: Название архива не может быть пустым!")
            return
        }
        archives.add(Archive(name))
        println("Архив '$name' успешно создан.")
    }
}