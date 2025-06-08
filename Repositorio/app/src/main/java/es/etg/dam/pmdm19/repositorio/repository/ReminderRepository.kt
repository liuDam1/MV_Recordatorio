package es.etg.dam.pmdm19.repositorio.repository

import androidx.lifecycle.LiveData
import es.etg.dam.pmdm19.repositorio.database.ReminderDao
import es.etg.dam.pmdm19.repositorio.models.Reminder

class ReminderRepository(private val reminderDao: ReminderDao) {
    val allReminders: LiveData<List<Reminder>> = reminderDao.getAllReminders()

    suspend fun insert(reminder: Reminder) {
        reminderDao.insertReminder(reminder)
    }

    suspend fun delete(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }

    suspend fun update(reminder: Reminder) {
        reminderDao.updateReminder(reminder)
    }
}
