package com.github.takahirom.compose_animation_samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animate
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.takahirom.compose_animation_samples.ui.ComposeanimationsamplesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeanimationsamplesTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }

}

@Composable
fun App() {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Text(text = "simple animation")
            SimpleAnimation()
            Text(text = "transition animation")
            BoxTransitionAnimation()
        }
    }
}

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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeanimationsamplesTheme {
        App()
    }
}