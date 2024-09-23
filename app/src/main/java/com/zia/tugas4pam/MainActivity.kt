package com.zia.tugas4pam

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var nimEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var rvStudent: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
        setupRecyclerView()

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val nim = nimEditText.text.toString()

            if (name.isEmpty()){
                nameEditText.error = "Name is required"
                return@setOnClickListener
            }
            if (nim.isEmpty()){
                nimEditText.error = "Nim is required"
                return@setOnClickListener
            }
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            if (selectedGenderId == -1) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val gender: String
            val avatarResource: Int
            if (selectedGenderId == radioMale.id) {
                gender = "Laki-laki"
            } else {
                gender = "Perempuan"
            }

            val student = Student(name, nim, gender)
            adapter.addItem(student)

            nimEditText.text?.clear()
            nameEditText.text?.clear()
            radioGroupGender.clearCheck()
        }

    }
    private fun init() {
        saveButton = findViewById(R.id.saveButton)
        nameEditText = findViewById(R.id.nameTextInputEditText)
        nimEditText = findViewById(R.id.nimTextInputEditText)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        rvStudent = findViewById(R.id.rvMahasiswa)
    }

    private fun setupRecyclerView() {
        rvStudent.layoutManager = LinearLayoutManager(this)
        rvStudent.setHasFixedSize(true)
        adapter = ItemAdapter(ArrayList())
        rvStudent.adapter = adapter
    }
}