package com.example.androidcomponents

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.androidcomponents.databinding.FragmentInfoElementBinding


private const val ARG_PARAM1 = "info"


class InfoElementFragment : Fragment() {
    private var info: Array<String>? = null
    private var binding: FragmentInfoElementBinding? = null
    private var prefs: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            info = it.getStringArray(ARG_PARAM1)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefs = context?.getSharedPreferences("savedId", Context.MODE_PRIVATE)
        binding = FragmentInfoElementBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs?.edit {
            putString("id", info?.get(0))
        }
        binding?.apply {
            idEditText.setText(info?.get(0))
            nameEditText.setText(info?.get(1))
            descriptionEditText.setText(info?.get(2))
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(id: String, content: String, details: String) =
            InfoElementFragment().apply {
                val info = arrayOf(id, content, details)
                arguments = Bundle().apply {
                    putStringArray(ARG_PARAM1, info)
                }
            }
    }
}