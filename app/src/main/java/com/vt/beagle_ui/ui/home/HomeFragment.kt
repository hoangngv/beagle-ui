package com.vt.beagle_ui.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var destinationUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            destinationUrl = it.getString("destination").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderServerDrivenUI()
    }

    private fun renderServerDrivenUI() {
        banner.loadView(this, ScreenRequest(destinationUrl), object : OnServerStateChanged {
            override fun invoke(serverState: ServerDrivenState) {
                Log.d("dLog", serverState.toString())
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(destination: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString("destination", destination)
                }
            }
    }
}