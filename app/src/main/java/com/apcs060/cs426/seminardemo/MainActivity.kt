package com.apcs060.cs426.seminardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apcs060.cs426.seminardemo.ui.theme.MyApplicationTheme
import java.util.UUID

data class Item(
    val id: UUID,
    val title: String,
    val image: String,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: "optimized"

            MyApplicationTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == "optimized",
                                onClick = {
                                    if (currentRoute != "optimized")
                                        navController.navigate("optimized")
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Optimized",
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "laggy",
                                onClick = {
                                    if (currentRoute != "laggy")
                                        navController.navigate("laggy")
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Laggy",
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "optimized",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        val itemSize = 200
                        composable("optimized") { OptimizedScreen(itemSize) }
                        composable("laggy") { LaggyScreen(itemSize) }
                    }
                }
            }
        }
    }
}
