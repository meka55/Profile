package com.example.profile

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.example.profile.databinding.FragmentCreateTaskDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskDialog : BottomSheetDialogFragment() {

    lateinit var binding: FragmentCreateTaskDialogBinding
    val cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun showRegularDialog(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.regular_dialog)
        dialog.show()
        val inflater = LayoutInflater.from(context)
        val regDialog: View = inflater.inflate(R.layout.regular_dialog, null)
        dialog.setContentView(regDialog)
        val cancel: TextView = regDialog.findViewById(R.id.otmenaBtn)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }


    val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeBackground(view)
        initClicker()
    }

    private fun updateDateInView() {
        val myFormat = "MM.dd.yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dateBtn.text = sdf.format(cal.getTime())
    }

    private fun initClicker() {
        with(binding){
            dateBtn.setOnClickListener{
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
                }
                regularBtn.setOnClickListener{
                showRegularDialog()
                }
            }
        }
    private  fun removeBackground(view: View){
        val bottomSheet = view.parent as View
        bottomSheet.backgroundTintMode = PorterDuff.Mode.CLEAR
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }
}