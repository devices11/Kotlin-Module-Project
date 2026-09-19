package service

import model.Archive
import model.Note
import java.util.Scanner

class NoteMenu(private val archive: Archive) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        val menu = Menu("--- Список заметок архива ${archive.name} ---") { menuItem ->
            menuItem.addItem("Создать заметку") { createNote() }

            archive.notes.forEach { note ->
                menuItem.addItem(note.title, note) { selectedNote ->
                    viewNote(selectedNote)
                }
            }

            menuItem.addItem("Назад") { menuItem.stop() }
        }
        menu.run()
    }

    private fun createNote() {
        println("\nВведите название новой заметки:")
        val title = scanner.nextLine().trim()
        if (title.isEmpty()) {
            println("Ошибка: Название заметки не может быть пустым!")
            return
        }

        println("Введите текст заметки:")
        val content = scanner.nextLine().trim()
        if (content.isEmpty()) {
            println("Ошибка: Текст заметки не может быть пустым!")
            return
        }

        archive.notes.add(Note(title, content))
        println("Заметка '$title' успешно создана.")
    }

    private fun viewNote(note: Note) {
        val menu = Menu<Note>("--- Просмотр заметки '${note.title}': ---\n\n${note.content}\n") { m ->
            m.addItem("Назад к списку заметок") {
                m.stop()
            }
        }
        menu.run()
    }

}