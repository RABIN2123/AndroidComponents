package com.example.androidcomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcomponents.databinding.FragmentInfoElementBinding
import com.example.androidcomponents.models.InfoElementModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.presenters.InfoElementPresenter
import com.example.androidcomponents.presenters.interfaces.InfoElementView


private const val ARG_PARAM1 = "info"


class InfoElementFragment : Fragment(), InfoElementView {
    private var binding: FragmentInfoElementBinding? = null
    private val presenter by lazy {
        InfoElementPresenter(model)
    }
    private val model by lazy {
        InfoElementModel(requireActivity().applicationContext)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoElementBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.viewIsReady()

    }

    override fun showInfo(values: PlaceholderContent.PlaceholderItem) {
        binding?.apply {
            idEditText.setText(values.id)
            nameEditText.setText(values.content)
            descriptionEditText.setText(values.details)
        }
    }

    override fun getIdInfo(): Int? {
        return arguments?.getInt(ARG_PARAM1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            InfoElementFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id.toInt())
                }
            }
    }
}