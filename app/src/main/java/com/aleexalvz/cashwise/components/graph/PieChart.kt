package com.aleexalvz.cashwise.components.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import com.aleexalvz.cashwise.ui.theme.GreenPieGraph
import com.aleexalvz.cashwise.ui.theme.OceanBluePieGraph
import com.aleexalvz.cashwise.ui.theme.PurplePieGraph
import com.aleexalvz.cashwise.ui.theme.RedPieGraph

typealias PieChartItem = Pair<Float, Color>

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<PieChartItem>,
) {
    Canvas(
        modifier = modifier
            .background(Color.White)
    ) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = minOf(centerX, centerY)

        var startAngle = 0f
        val total = data.map { it.first }.sum()

        val clippedCircleRadius = radius / 3
        val path = Path().apply {
            addOval(
                Rect(
                    center = Offset(centerX, centerY),
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
                    topLeft = Offset(centerX - radius, centerY - radius),
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
        PieChartItem(90f, GreenPieGraph),
        PieChartItem(80f, OceanBluePieGraph),
        PieChartItem(120f, RedPieGraph),
        PieChartItem(70f, PurplePieGraph)
    )

    PieChart(
        modifier = Modifier
            .fillMaxSize(),
        data = data
    )
}
