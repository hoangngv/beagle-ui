package com.vt.beagle_ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.fragment_drawer.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.layout_dialog.*
import kotlinx.android.synthetic.main.layout_dialog.dialogContainer
import kotlinx.android.synthetic.main.layout_dialog.layout_loading

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationDrawer.loadView(
            this,
            ScreenRequest(destinationUrl),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                }
            }
        )
    }
}