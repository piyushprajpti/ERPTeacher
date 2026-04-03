package com.piyushprajpti.feature.syllabus.coverage.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.syllabus.coverage.TimelinePoint

@Composable
fun TimelineChart(
    data: List<TimelinePoint>,
    modifier: Modifier = Modifier
) {
    val actualColor = CustomTheme.colors.primary
    val plannedColor = CustomTheme.colors.secondaryText
    val secondaryBackground = CustomTheme.colors.secondaryBackground

    Canvas(modifier = modifier.fillMaxSize()) {
        if (data.isEmpty()) return@Canvas

        val width = size.width
        val height = size.height
        val spacing = width / (data.size - 1)

        val plannedPath = Path()
        val actualPath = Path()

        data.forEachIndexed { index, point ->
            val x = index * spacing
            val plannedY = height - (point.planned * height)
            val actualY = height - (point.actual * height)

            if (index == 0) {
                plannedPath.moveTo(x, plannedY)
                actualPath.moveTo(x, actualY)
            } else {
                plannedPath.lineTo(x, plannedY)
                actualPath.lineTo(x, actualY)
            }
        }

        // Draw Planned Path (Dashed)
        drawPath(
            path = plannedPath,
            color = plannedColor,
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        )

        // Draw Actual Path (Solid)
        drawPath(
            path = actualPath,
            color = actualColor,
            style = Stroke(width = 3.dp.toPx())
        )
        
        // Draw Points for Actual
        data.forEachIndexed { index, point ->
            val x = index * spacing
            val actualY = height - (point.actual * height)
            drawCircle(
                color = actualColor,
                radius = 4.dp.toPx(),
                center = Offset(x, actualY)
            )
            drawCircle(
                color = secondaryBackground,
                radius = 2.dp.toPx(),
                center = Offset(x, actualY)
            )
        }
    }
}
