![simpleanimation](https://user-images.githubusercontent.com/1386930/92987048-fb65ac80-f4f9-11ea-8f15-c368421901cf.gif)

```kotlin
@Composable
fun SimpleAnimation() {
    var isRightState by remember { mutableStateOf(false) }
    val leftMarginSize = animate(if (isRightState) 200.dp else 50.dp)
    Row(
        Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.width(leftMarginSize))
        Surface(
            color = Color.Green,
            modifier = Modifier.size(100.dp)
                .clickable(onClick = {
                    isRightState = !isRightState
                }),
        ) {}
    }
}
```

![transition](https://user-images.githubusercontent.com/1386930/92987062-10dad680-f4fa-11ea-97fa-2e2faade98e3.gif)

```kotlin
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
```
