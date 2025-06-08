package es.etg.dam.pmdm19.repositorio.viewmodel

import androidx.lifecycle.*
import es.etg.dam.pmdm19.repositorio.models.Reminder
import es.etg.dam.pmdm19.repositorio.repository.ReminderRepository
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {
    val allReminders: LiveData<List<Reminder>> = repository.allReminders

    fun insert(reminder: Reminder) = viewModelScope.launch {
        repository.insert(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch {
        repository.delete(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch {
        repository.update(reminder)
    }
}
