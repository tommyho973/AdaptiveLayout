package com.example.adaptivelayout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.adaptivelayout.ui.theme.AdaptiveLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveLayoutTheme {
                    AdaptiveLayoutParent()
            }
        }
    }
}

@Composable
fun AdaptiveLayoutParent(modifier: Modifier = Modifier){
    val windowInfo = calculateCurrentWindowInfo()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isNotCompactHeight = windowSizeClass.windowHeightSizeClass != WindowHeightSizeClass.COMPACT
    val isNotCompactWidth = windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT
    if (windowInfo.orientation == Orientation.PORTRAIT){
        PortraitMode(isNotCompactHeight)
    }
    else{
        LandscapeMode(isNotCompactHeight)
    }
}
@Composable
fun PortraitMode(isNotCompact: Boolean){
    if (isNotCompact) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(.1f))
            Text(text = "Portrait Mode", modifier = Modifier.weight(1.5f))
        }
    }
    else {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(.1f))
            Text(text = "Portrait Mode", modifier = Modifier.weight(1.5f))
        }
    }

}
@Composable
fun LandscapeMode(isNotCompact: Boolean) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Landscape Mode")
    }
}

@Composable
fun calculateCurrentWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val orientation = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
        Orientation.PORTRAIT
    } else {
        Orientation.LANDSCAPE
    }
    return WindowInfo(
        widthDp = screenWidth,
        heightDp = screenHeight,
        orientation = orientation
    )
}
enum class Orientation {
    PORTRAIT,
    LANDSCAPE
}
data class WindowInfo(
    val widthDp: Int,
    val heightDp: Int,
    val orientation: Orientation // Add this line
)
