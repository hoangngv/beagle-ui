package com.vt.beagle_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.vt.beagle_ui.R

class BaseNavigationDrawerFragment : Fragment() {
    private var destinationUrl: String = ""

    companion object {
        fun newInstance(destinationUrl : String) : BaseNavigationDrawerFragment {
            val bundle = bundleOf("destination" to destinationUrl)
            return BaseNavigationDrawerFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            destinationUrl = it.getString("destination").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drawer, container, false)
    }
}