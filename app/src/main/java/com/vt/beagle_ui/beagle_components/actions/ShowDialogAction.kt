package com.vt.beagle_ui.beagle_components.actions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.R
import com.wang.avi.AVLoadingIndicatorView

@RegisterAction
data class ShowDialogAction(
    val endpoint: String
) : Action {
    override fun execute(rootView: RootView, origin: View) {

        val inflater = LayoutInflater.from(rootView.getContext())
        val view = inflater.inflate(R.layout.layout_dialog, null)
        val dialogContainer = view.findViewById<FrameLayout>(R.id.dialogContainer)
        val loading = view.findViewById<AVLoadingIndicatorView>(R.id.layout_loading)

        dialogContainer.loadView(
            rootView.getContext() as AppCompatActivity,
            ScreenRequest(endpoint),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                    when (serverState) {
                        is ServerDrivenState.Started -> {
                            loading.visibility = View.VISIBLE
                        }
                        is ServerDrivenState.Finished -> {
                            loading.visibility = View.GONE
                        }
                        is ServerDrivenState.Error -> {
                            serverState.throwable.printStackTrace()
                        }
                        ServerDrivenState.Canceled -> {
                        }
                        is ServerDrivenState.Loading -> {

                        }
                        ServerDrivenState.Success -> {

                        }
                    }
                }
            }
        )

        val builder: AlertDialog.Builder = AlertDialog.Builder(rootView.getContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }
}