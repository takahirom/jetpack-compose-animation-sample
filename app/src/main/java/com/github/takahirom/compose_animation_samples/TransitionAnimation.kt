package com.github.takahirom.compose_animation_samples

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private enum class BoxSelectedState {
    Selected, Unselected
}

private val LeftMargin = DpPropKey()
private val BoxColor = ColorPropKey()
private val ShapeCornerPropKey = DpPropKey()

private val BoxTransitionDefinition = transitionDefinition<BoxSelectedState> {
    state(BoxSelectedState.Selected) {
        this[LeftMargin] = 50.dp
        this[BoxColor] = Color.Green
        this[ShapeCornerPropKey] = 0.dp
    }
    state(BoxSelectedState.Unselected) {
        this[LeftMargin] = 200.dp
        this[BoxColor] = Color.Red
        this[ShapeCornerPropKey] = 24.dp
    }
    transition {
        LeftMargin using tween(durationMillis = 1000)
        BoxColor using tween(durationMillis = 2000)
        ShapeCornerPropKey using tween(durationMillis = 2000)
    }
}

@Composable
fun BoxTransitionAnimation() {
    var selectedState by remember { mutableStateOf(BoxSelectedState.Selected) }

    val transitionState = transition(
        definition = BoxTransitionDefinition,
        toState = selectedState
    )

    Row(
        Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.width(transitionState[LeftMargin]))
        Surface(
            color = transitionState[BoxColor],
            shape = RoundedCornerShape(transitionState[ShapeCornerPropKey]),
            modifier = Modifier.size(100.dp)
                .clickable(onClick = {
                    selectedState = when (selectedState) {
                        BoxSelectedState.Selected -> BoxSelectedState.Unselected
                        BoxSelectedState.Unselected -> BoxSelectedState.Selected
                    }
                }),
        ) {}
    }
}
