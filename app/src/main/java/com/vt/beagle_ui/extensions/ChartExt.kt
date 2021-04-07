package com.vt.beagle_ui.extensions

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.MPPointF
import com.vt.beagle_ui.R
import java.text.DecimalFormat
import java.util.*

fun PieChart.setupChartTask(): PieChart {
    setUsePercentValues(true)
    description.isEnabled = false
    setExtraOffsets(5f, 10f, 5f, 5f)

    dragDecelerationFrictionCoef = 0.95f

    //setCenterTextTypeface(tfLight)
    //centerText = generateCenterSpannableText()
    setDrawEntryLabels(false)

    isDrawHoleEnabled = true
    setHoleColor(Color.WHITE)

    setTransparentCircleColor(Color.WHITE)
    //setTransparentCircleAlpha(110)

    holeRadius = 55f
    //transparentCircleRadius = 61f

    setDrawCenterText(true)

    rotationAngle = -90f
    // enable rotation of the chart by touch
    isRotationEnabled = false
    isHighlightPerTapEnabled = false

    // chart.setUnit(" €");
    // chart.setDrawUnitsInChart(true);

    animateY(1000, Easing.EaseInOutQuad)
    //chart.spin(2000, 0, 360);

    legend.run {
        legend.isEnabled = false //hide legend
        verticalAlignment = Legend.LegendVerticalAlignment.TOP
        horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        orientation = Legend.LegendOrientation.VERTICAL
        setDrawInside(false)
        xEntrySpace = 7f
        yEntrySpace = 0f
        yOffset = 0f
    }


    // entry label styling
    setEntryLabelColor(Color.BLACK)
    //setEntryLabelTypeface(tfRegular)
    setEntryLabelTextSize(12f)
    context.resources.getDimension(R.dimen._12sdp)

    return this
}

fun PieChart.restartAnimation(){
    animateY(2000, Easing.EaseInOutQuad)
}

fun PieChart.updateData(
    values: List<PieEntry>,
    colors: List<Int>,
    textCenter: CharSequence = ""
) {
    val dataSet = PieDataSet(values, "hihi")

    dataSet.setDrawValues(true) //draw number value
    dataSet.setDrawIcons(false) //draw icon

    dataSet.sliceSpace = 3f
    dataSet.iconsOffset = MPPointF(0f, 40f)
    dataSet.selectionShift = 5f
    dataSet.colors = colors

    val data = PieData(dataSet)
    //data.setValueFormatter(CustomPercentFormatter(this))
    data.setValueTextColor(Color.WHITE)
//    data.setValueTextSize(11f)
    data.setValueTextSize(context.resources.getDimension(R.dimen._10sdp).pxToDp(context).toFloat())

    this.centerText = textCenter
    this.setCenterTextSize(context.resources.getDimension(R.dimen._14sdp).pxToDp(context).toFloat())
    this.data = data
}

fun HorizontalBarChart.setupChart() : HorizontalBarChart{

    requestLayout()

//    setOnChartValueSelectedListener(this)

    description.isEnabled = false

    // if more than 60 entries are displayed in the chart, no values will be
    // drawn
    setMaxVisibleValueCount(40)

    isScaleXEnabled = false

    setTouchEnabled(false)
    setPinchZoom(false)
    isDoubleTapToZoomEnabled = false

    setDrawGridBackground(false)
    setDrawBarShadow(false)

//    setDrawValueAboveBar(true)
//    isHighlightFullBarEnabled = false

    axisLeft.apply {
        textSize = context.resources.getDimension(R.dimen._10sdp).pxToDp(context).toFloat()
        isEnabled = false
        axisMinimum = 0f
//        setDrawAxisLine(false)
//        setDrawGridLines(false)
//        valueFormatter = CustomRightAxisValueFormatter()
//        isInverted = true
    }

     axisRight.apply {
        textSize = context.resources.getDimension(R.dimen._10sdp).pxToDp(context).toFloat()
        isEnabled = true
        axisMinimum = 0f
        setDrawAxisLine(true)
        setDrawGridLines(true)

         valueFormatter = object : ValueFormatter(){
             val mFormat: DecimalFormat = DecimalFormat("###,###,###,###")

             override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return mFormat.format(value).toString()
            }
        }
    }

    // setting data

    legend.apply {
        isEnabled = false
//        verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        orientation = Legend.LegendOrientation.HORIZONTAL
//        setDrawInside(false)
//        formSize = 8f
//        formToTextSpace = 4f
//        xEntrySpace = 6f
    }

    setFitBars(true)
    animateY(1000)

    return this
}

fun HorizontalBarChart.updateData(times : List<String> , values: MutableList<BarEntry>, colors: List<Int>){
    layoutParams.height = 120 * times.size + 100

    xAxis.apply {
        //valueFormatter = CustomTimeDataFormatBarChart(times)
        position = XAxisPosition.BOTTOM
        labelCount = times.size
        setDrawAxisLine(true)
        setDrawGridLines(false)
        textSize = context.resources.getDimension(R.dimen._10sdp).pxToDp(context).toFloat()
        granularity = 1f
    }

    val set1: BarDataSet

    if (data != null && data.dataSetCount > 0) {
        try {
            set1 = data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            data.notifyDataChanged()
            notifyDataSetChanged()
        } catch (throwable : Throwable) {
            throwable.printStackTrace()
        }
    } else {
        try{
            set1 = BarDataSet(values, "").apply {
                setDrawIcons(false)
                setDrawValues(false)
                setColors(*colors.toIntArray())
//            stackLabels = arrayOf("Births", "Divorces", "Marriages")
            }
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets).apply {
                barWidth = 0.65f
//            setValueFormatter(CustomBarChartValueFormatter())
//            setValueTextColor(Color.WHITE)
//            isHighlightEnabled = false
            }
            setData(data)

        } catch (throwable : Throwable) {
            throwable.printStackTrace()
        }
    }

    setFitBars(true)
    invalidate()
}