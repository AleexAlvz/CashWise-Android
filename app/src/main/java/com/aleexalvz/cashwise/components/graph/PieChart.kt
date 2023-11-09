package com.aleexalvz.cashwise.components.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.ui.theme.GreenPieGraphColor
import com.aleexalvz.cashwise.ui.theme.OceanBluePieGraphColor
import com.aleexalvz.cashwise.ui.theme.PurplePieGraphColor
import com.aleexalvz.cashwise.ui.theme.RedPieGraphColor

typealias PieChartItem = Pair<Float, Color>

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<PieChartItem>,
) {
    Canvas(
        modifier = modifier
    ) {
        val center = size.minDimension / 2f
        val radius = minOf(center, center)

        var startAngle = 0f
        val total = data.map { it.first }.sum()

        val clippedCircleRadius = radius / 3
        val path = Path().apply {
            addOval(
                Rect(
                    center = Offset(center, center),
                    radius = clippedCircleRadius
                )
            )
        }

        clipPath(
            path = path,
            clipOp = ClipOp.Difference
        ) {
            data.forEach { item ->
                val (value, color) = item

                val sweepAngle = 360f * (value / total)
                drawArc(
                    color = color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center - radius, center - radius),
                    size = Size(radius * 2, radius * 2)
                )
                startAngle += sweepAngle
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PieChartPreview() {
    val data: List<PieChartItem> = listOf(
        PieChartItem(90f, GreenPieGraphColor),
        PieChartItem(80f, OceanBluePieGraphColor),
        PieChartItem(120f, RedPieGraphColor),
        PieChartItem(70f, PurplePieGraphColor)
    )

    PieChart(
        modifier = Modifier
            .fillMaxSize(),
        data = data
    )
}
