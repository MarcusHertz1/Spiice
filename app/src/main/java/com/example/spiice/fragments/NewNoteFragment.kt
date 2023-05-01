package com.example.spiice.fragments

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.DBHelper
import com.example.spiice.databinding.NewNoteLayoutBinding


class NewNoteFragment : Fragment() {
    private lateinit var binding: NewNoteLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewNoteLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBt.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        val dbHelper = DBHelper(requireContext())
        val database = dbHelper.writableDatabase
        val contentValues = ContentValues()
        binding.addBt.setOnClickListener {
            contentValues.put(DBHelper.KEY_MAIL, "change me!!!!!!!!!!!!!!!!!!!!!!")
            contentValues.put(DBHelper.KEY_TITLE, binding.titleTIET.text.toString())
            contentValues.put(DBHelper.KEY_MESSAGE, binding.messageTIET.text.toString())
            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues)
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }
}