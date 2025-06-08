package es.etg.dam.pmdm19.repositorio.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.etg.dam.pmdm19.repositorio.R
import es.etg.dam.pmdm19.repositorio.databinding.ActivityMainBinding
import es.etg.dam.pmdm19.repositorio.database.ReminderDatabase
import es.etg.dam.pmdm19.repositorio.repository.ReminderRepository
import es.etg.dam.pmdm19.repositorio.viewmodel.ReminderViewModel
import es.etg.dam.pmdm19.repositorio.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ReminderViewModel
    private lateinit var adapter: ReminderAdapter

    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var viewModel: ReminderViewModel
        private lateinit var adapter: ReminderAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupRecyclerView()

            initViewModel()

            binding.fabAdd.setOnClickListener {
                val intent = Intent(this, EditReminderActivity::class.java)
                startActivityForResult(intent, ADD_REMINDER_REQUEST)
            }
        }

        private fun setupRecyclerView() {
            adapter = ReminderAdapter { reminder ->
                val intent = Intent(this, EditReminderActivity::class.java)
                intent.putExtra(EditReminderActivity.EXTRA_REMINDER_ID, reminder.id)
                startActivityForResult(intent, EDIT_REMINDER_REQUEST)
            }
            binding.rvReminders.adapter = adapter
            binding.rvReminders.layoutManager = LinearLayoutManager(this)
        }

        private fun initViewModel() {
            val reminderDao = ReminderDatabase.getDatabase(this).reminderDao()
            val repository = ReminderRepository(reminderDao)
            viewModel = ViewModelProvider(
                this,
                ViewModelFactory(repository)
            ).get(ReminderViewModel::class.java)

            viewModel.allReminders.observe(this) { reminders ->
                reminders?.let {
                    adapter.submitList(it)
                }
            }
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_settings -> {
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }

        companion object {
            const val ADD_REMINDER_REQUEST = 1
            const val EDIT_REMINDER_REQUEST = 2
        }
    }
}
