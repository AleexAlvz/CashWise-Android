package com.aleexalvz.cashwise.components

//TODO review this copied code before implement

//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Canvas
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import java.lang.Math.PI
//import kotlin.math.atan2
//
//data class PieChartItem(
//    val name: String,
//    val value: Double,
//    val color: Color
//)
//
//typealias PieChartData = List<PieChartItem>
//
//@Composable
//fun PieChart() {
//
//}
//
//fun getMockedPieChartData(): PieChartData = listOf(
//    PieChartItem("Type 1", 2014.24, Color.Green),
//    PieChartItem("Type 2", 1574.44, Color.Red),
//    PieChartItem("Type 3", 500.00, Color.Yellow)
//)
//
//@Composable
//fun PieChart(
//    modifier: Modifier = Modifier,
//    radius:Float = 500f,
//    innerRadius:Float = 250f,
//    transparentWidth:Float = 70f,
//    input:List<PieChartInput>,
//    centerText:String = ""
//) {
//    var circleCenter by remember {
//        mutableStateOf(Offset.Zero)
//    }
//
//    var inputList by remember {
//        mutableStateOf(input)
//    }
//    var isCenterTapped by remember {
//        mutableStateOf(false)
//    }
//
//    Box(
//        modifier = modifier,
//        contentAlignment = Alignment.Center
//    ){
//        Canvas(
//            modifier = Modifier
//                .fillMaxSize()
//                .pointerInput(true) {
//                    detectTapGestures(
//                        onTap = { offset ->
//                            val tapAngleInDegrees = (-atan2(
//                                x = circleCenter.y - offset.y,
//                                y = circleCenter.x - offset.x
//                            ) * (180f / PI).toFloat() - 90f).mod(360f)
//                            val centerClicked = if (tapAngleInDegrees < 90) {
//                                offset.x < circleCenter.x + innerRadius && offset.y < circleCenter.y + innerRadius
//                            } else if (tapAngleInDegrees < 180) {
//                                offset.x > circleCenter.x - innerRadius && offset.y < circleCenter.y + innerRadius
//                            } else if (tapAngleInDegrees < 270) {
//                                offset.x > circleCenter.x - innerRadius && offset.y > circleCenter.y - innerRadius
//                            } else {
//                                offset.x < circleCenter.x + innerRadius && offset.y > circleCenter.y - innerRadius
//                            }
//
//                            if (centerClicked) {
//                                inputList = inputList.map {
//                                    it.copy(isTapped = !isCenterTapped)
//                                }
//                                isCenterTapped = !isCenterTapped
//                            } else {
//                                val anglePerValue = 360f / input.sumOf {
//                                    it.value
//                                }
//                                var currAngle = 0f
//                                inputList.forEach { pieChartInput ->
//
//                                    currAngle += pieChartInput.value * anglePerValue
//                                    if (tapAngleInDegrees < currAngle) {
//                                        val description = pieChartInput.description
//                                        inputList = inputList.map {
//                                            if (description == it.description) {
//                                                it.copy(isTapped = !it.isTapped)
//                                            } else {
//                                                it.copy(isTapped = false)
//                                            }
//                                        }
//                                        return@detectTapGestures
//                                    }
//                                }
//                            }
//                        }
//                    )
//                }
//        ){
//            val width = size.width
//            val height = size.height
//            circleCenter = Offset(x= width/2f,y= height/2f)
//
//            val totalValue = input.sumOf {
//                it.value
//            }
//            val anglePerValue = 360f/totalValue
//            var currentStartAngle = 0f
//
//            inputList.forEach { pieChartInput ->
//                val scale = if(pieChartInput.isTapped) 1.1f else 1.0f
//                val angleToDraw = pieChartInput.value * anglePerValue
//                scale(scale){
//                    drawArc(
//                        color = pieChartInput.color,
//                        startAngle = currentStartAngle,
//                        sweepAngle = angleToDraw,
//                        useCenter = true,
//                        size = Size(
//                            width = radius*2f,
//                            height = radius*2f
//                        ),
//                        topLeft = Offset(
//                            (width-radius*2f)/2f,
//                            (height - radius*2f)/2f
//                        )
//                    )
//                    currentStartAngle += angleToDraw
//                }
//                var rotateAngle = currentStartAngle-angleToDraw/2f-90f
//                var factor = 1f
//                if(rotateAngle>90f){
//                    rotateAngle = (rotateAngle+180).mod(360f)
//                    factor = -0.92f
//                }
//
//                val percentage = (pieChartInput.value/totalValue.toFloat()*100).toInt()
//
//                drawContext.canvas.nativeCanvas.apply {
//                    if(percentage>3){
//                        rotate(rotateAngle){
//                            drawText(
//                                "$percentage %",
//                                circleCenter.x,
//                                circleCenter.y+(radius-(radius-innerRadius)/2f)*factor,
//                                Paint().apply {
//                                    textSize = 13.sp.toPx()
//                                    textAlign = Paint.Align.CENTER
//                                    color = white.toArgb()
//                                }
//                            )
//                        }
//                    }
//                }
//                if(pieChartInput.isTapped){
//                    val tabRotation = currentStartAngle - angleToDraw - 90f
//                    rotate(tabRotation){
//                        drawRoundRect(
//                            topLeft = circleCenter,
//                            size = Size(12f,radius*1.2f),
//                            color = gray,
//                            cornerRadius = CornerRadius(15f,15f)
//                        )
//                    }
//                    rotate(tabRotation+angleToDraw){
//                        drawRoundRect(
//                            topLeft = circleCenter,
//                            size = Size(12f,radius*1.2f),
//                            color = gray,
//                            cornerRadius = CornerRadius(15f,15f)
//                        )
//                    }
//                    rotate(rotateAngle){
//                        drawContext.canvas.nativeCanvas.apply {
//                            drawText(
//                                "${pieChartInput.description}: ${pieChartInput.value}",
//                                circleCenter.x,
//                                circleCenter.y + radius*1.3f*factor,
//                                Paint().apply {
//                                    textSize = 22.sp.toPx()
//                                    textAlign = Paint.Align.CENTER
//                                    color = white.toArgb()
//                                    isFakeBoldText = true
//                                }
//                            )
//                        }
//                    }
//                }
//            }
//
//            if(inputList.first().isTapped){
//                rotate(-90f){
//                    drawRoundRect(
//                        topLeft = circleCenter,
//                        size = Size(12f,radius*1.2f),
//                        color = gray,
//                        cornerRadius = CornerRadius(15f,15f)
//                    )
//                }
//            }
//            drawContext.canvas.nativeCanvas.apply {
//                drawCircle(
//                    circleCenter.x,
//                    circleCenter.y,
//                    innerRadius,
//                    Paint().apply {
//                        color = white.copy(alpha = 0.6f).toArgb()
//                        setShadowLayer(10f,0f,0f, gray.toArgb())
//                    }
//                )
//            }
//
//            drawCircle(
//                color = white.copy(0.2f),
//                radius = innerRadius+transparentWidth/2f
//            )
//
//        }
//        Text(
//            centerText,
//            modifier = Modifier
//                .width(Dp(innerRadius/1.5f))
//                .padding(25.dp),
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 17.sp,
//            textAlign = TextAlign.Center
//        )
//
//    }
//}
//
//data class PieChartInput(
//    val color:Color,
//    val value:Int,
//    val description:String,
//    val isTapped:Boolean = false
//)
