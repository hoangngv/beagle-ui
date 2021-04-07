package com.vt.beagle_ui.beagle_components.custom_widget_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.layout_pie_chart.view.*

class PieChart(context: Context) : LinearLayout(context){

    init {
        View.inflate(context, R.layout.layout_pie_chart, this)
        invalidate()
        requestLayout()
    }

    fun setupPieChart(dataset: ArrayList<Array<String>>) {
        pieChart.apply {
            isRotationEnabled = true
            description = Description()
            holeRadius = 35f
            setTransparentCircleAlpha(0)
            centerText = "PieChart"
            setCenterTextSize(10F)
            setDrawEntryLabels(true)
        }

        addDataSet(pieChart)
    }

    private fun addDataSet(pieChart: PieChart) {
        val yEntrys: ArrayList<PieEntry> = ArrayList()
        val xEntrys: ArrayList<String> = ArrayList()
        val yData = floatArrayOf(25f, 40f, 70f)
        val xData = arrayOf("January", "February", "January")
        for (i in yData.indices) {
            yEntrys.add(PieEntry(yData[i], i))
        }
        for (i in xData.indices) {
            xEntrys.add(xData[i])
        }
        val pieDataSet = PieDataSet(yEntrys, "Employee Sales")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 12f
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.GRAY)
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        pieDataSet.colors = colors
        val legend: Legend = pieChart.legend
        legend.form = Legend.LegendForm.CIRCLE
        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.invalidate()
    }
}