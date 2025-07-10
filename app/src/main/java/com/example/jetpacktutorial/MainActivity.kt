package com.example.jetpacktutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktutorial.ui.theme.JetpackTutorialTheme
object AppDestinations {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "detail"
    const val HOME_SCREEN = "home_screen"

    // Add other routes here
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun MyAppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // Creates and remembers the NavController

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME_ROUTE, // The first screen to show
        modifier = modifier
    ) {
        composable(route = AppDestinations.HOME_ROUTE) {
            // Content for the Home screen - using Greeting as the home screen
            Greeting(name = "Android", navController = navController)
        }
        composable(route = AppDestinations.DETAIL_ROUTE) {
            // Content for the Detail screen
            DetailScreen(navController = navController)
        }
        composable(route = AppDestinations.HOME_SCREEN) {
            // Content for the Detail screen
            HomeScreen(navController = navController)
        }
        // Add more composable(route = ...) { ... } for other screens
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {

        val backgroundImage = painterResource(id = R.drawable.bg_compose_background)
        Image(
            painter = backgroundImage,
            contentDescription = "A descriptive text",
            modifier = Modifier.fillMaxWidth() // Fills the maximum available width
        )
        Text(
            text = "Jetpack Compose tutorial\n" +
                    "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs",
            modifier = modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                fontSize = 24.sp,
            ),
            textAlign= TextAlign.Justify
        )

        Text(
            text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = modifier.padding(horizontal = 16.dp),
            textAlign= TextAlign.Justify,
            fontSize = 24.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(

                onClick = {
                    // Navigate to DetailScreen
                    navController.navigate(AppDestinations.DETAIL_ROUTE)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Detail Screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackTutorialTheme {
        Greeting("Android", navController = rememberNavController())
    }
}