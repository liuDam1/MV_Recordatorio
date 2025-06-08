package es.etg.dam.pmdm19.repositorio

import android.app.Application
import es.etg.dam.pmdm19.repositorio.database.ReminderDatabase

class ReminderApplication : Application() {
    val database: ReminderDatabase by lazy { ReminderDatabase.getDatabase(this) }
}    