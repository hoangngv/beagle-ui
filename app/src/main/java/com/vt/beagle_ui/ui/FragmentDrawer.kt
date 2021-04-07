package com.vt.beagle_ui.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.LAYOUT_DIRECTION_LTR
import android.view.View.LAYOUT_DIRECTION_RTL
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.beagle_ui.R
import com.vt.beagle_ui.common.Gravity
import kotlinx.android.synthetic.main.fragment_drawer_navigation.*

class FragmentDrawer : Fragment() {
    companion object {
        const val KEY_URL = "KEY_URL"
        const val KEY_GRAVITY = "KEY_GRAVITY"

        fun newInstance(url : String, gravity: Gravity = Gravity.START) : FragmentDrawer{
            return FragmentDrawer().apply {
                arguments = bundleOf(
                    KEY_URL to url,
                    KEY_GRAVITY to gravity
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drawer_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            addChildView()
        }, 100)
    //        changeGravity()

//        layoutCancel.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }

//        layoutContent.loadView(
//            requireActivity() as AppCompatActivity,
//            ScreenRequest(arguments?.getString(KEY_URL, "") ?: ""),
//            object : OnServerStateChanged {
//                override fun invoke(serverState: ServerDrivenState) {
//                    Log.d("dLog", serverState.toString())
////                    when (serverState) {
////                        is ServerDrivenState.Started -> {
////                            layout_loading.visibility = View.VISIBLE
////                        }
////                        is ServerDrivenState.Finished -> {
////                            layout_loading.visibility = View.GONE
////                        }
////                        is ServerDrivenState.Error -> {
////                            serverState.throwable.printStackTrace()
////                        }
////                        ServerDrivenState.Canceled -> {
////                        }
////                        is ServerDrivenState.Loading -> {
////
////                        }
////                        ServerDrivenState.Success -> {
////
////                        }
////                    }
//                }
//            }
//        )
    }

    private fun addChildView(){
        val viewCancel = layoutInflater.inflate(R.layout.view_cancel_drawer, null)
        val viewContent = layoutInflater.inflate(R.layout.view_content_drawer, null) as FrameLayout

        val parentHeight = view?.height ?: 0
        val parentWidth = view?.width ?: 0

        val cancelChildWidth = parentWidth / 10 * 2
        val contentChildWidth = parentWidth - cancelChildWidth

        if(arguments?.getSerializable(KEY_GRAVITY) == Gravity.END) {
            layoutDrawer.removeAllViews()
            layoutDrawer.addView(viewCancel, cancelChildWidth, parentHeight)
            layoutDrawer.addView(viewContent, contentChildWidth, parentHeight)
        } else {
            layoutDrawer.removeAllViews()
            layoutDrawer.addView(viewContent, contentChildWidth, parentHeight)
            layoutDrawer.addView(viewCancel, cancelChildWidth, parentHeight)
        }

        viewCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewContent.loadView(
            requireActivity() as AppCompatActivity,
            ScreenRequest(arguments?.getString(KEY_URL, "") ?: ""),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                }
            }
        )
    }

    private fun changeGravity() {
        if(arguments?.getSerializable(KEY_GRAVITY) == Gravity.END) {
            layoutDrawer.layoutDirection = LAYOUT_DIRECTION_RTL
            layoutDrawer.requestLayout()
        } else {
            layoutDrawer.layoutDirection = LAYOUT_DIRECTION_LTR
            layoutDrawer.requestLayout()
        }
    }

}