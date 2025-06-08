package es.etg.dam.pmdm19.repositorio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.etg.dam.pmdm19.repositorio.repository.ReminderRepository

class ViewModelFactory(private val repository: ReminderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}