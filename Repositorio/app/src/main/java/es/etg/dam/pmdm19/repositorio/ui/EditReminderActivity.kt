package es.etg.dam.pmdm19.repositorio.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.etg.dam.pmdm19.repositorio.R
import es.etg.dam.pmdm19.repositorio.database.ReminderDatabase
import es.etg.dam.pmdm19.repositorio.databinding.ActivityEditReminderBinding
import es.etg.dam.pmdm19.repositorio.models.Reminder
import es.etg.dam.pmdm19.repositorio.repository.ReminderRepository
import es.etg.dam.pmdm19.repositorio.viewmodel.ReminderViewModel
import es.etg.dam.pmdm19.repositorio.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class EditReminderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditReminderBinding
    private lateinit var viewModel: ReminderViewModel
    private var reminderId: Long = 0
    private var isEditing = false

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private var selectedDate = Calendar.getInstance()
    private var selectedTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        checkForEditingMode()

        setupDateTimePickers()

        binding.btnSave.setOnClickListener {
            saveReminder()
        }

        if (isEditing) {
            binding.btnDelete.visibility = android.view.View.VISIBLE
            binding.btnDelete.setOnClickListener {
                deleteReminder()
            }
        }
    }

    private fun initViewModel() {
        val reminderDao = ReminderDatabase.getDatabase(this).reminderDao()
        val repository = ReminderRepository(reminderDao)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(ReminderViewModel::class.java)
    }

    private fun checkForEditingMode() {
        reminderId = intent.getLongExtra(EXTRA_REMINDER_ID, 0)
        isEditing = reminderId.toInt() != 0

        if (isEditing) {
            title = "编辑提醒"
            viewModel.allReminders.observe(this) { reminders ->
                reminders.find { it.id == reminderId }?.let { reminder ->
                    populateUI(reminder)
                }
            }
        } else {
            title = "新建提醒"
        }
    }

    private fun populateUI(reminder: Reminder) {
        binding.etTitle.setText(reminder.title)
        binding.etDescription.setText(reminder.description)
        selectedDate.time = reminder.date
        selectedTime.time = reminder.time
        binding.tvDate.text = dateFormat.format(selectedDate.time)
        binding.tvTime.text = timeFormat.format(selectedTime.time)
        binding.cbRecurring.isChecked = reminder.isRecurring
    }

    private fun setupDateTimePickers() {
        binding.tvDate.setOnClickListener {
            showDatePicker()
        }

        binding.tvTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.tvDate.text = dateFormat.format(selectedDate.time)
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                binding.tvTime.text = timeFormat.format(selectedTime.time)
            },
            selectedTime.get(Calendar.HOUR_OF_DAY),
            selectedTime.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun saveReminder() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val isRecurring = binding.cbRecurring.isChecked

        if (title.isEmpty()) {
            Toast.makeText(this, "Introduce Titulo", Toast.LENGTH_SHORT).show()
            return
        }

        val reminder = if (isEditing) {
            Reminder(
                id = reminderId,
                title = title,
                description = description,
                date = selectedDate.time,
                time = selectedTime.time,
                isRecurring = isRecurring
            )
        } else {
            Reminder(
                title = title,
                description = description,
                date = selectedDate.time,
                time = selectedTime.time,
                isRecurring = isRecurring
            )
        }

        if (isEditing) {
            viewModel.update(reminder)
        } else {
            viewModel.insert(reminder)
        }

        setResult(RESULT_OK)
        finish()
    }

    private fun deleteReminder() {
        viewModel.allReminders.observe(this) { reminders ->
            reminders.find { it.id == reminderId }?.let { reminder ->
                viewModel.delete(reminder)
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REMINDER_ID = "extra_reminder_id"
    }
}
