package com.dimonkiv.taskmanagment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dimonkiv.taskmanagment.ui.theme.Secondary
import com.dimonkiv.taskmanagment.ui.theme.TaskManagmentTheme
import com.dimonkiv.taskmanagment.ui.theme.main.BottomNavigationBar
import com.dimonkiv.taskmanagment.ui.theme.main.MainScreen
import com.dimonkiv.taskmanagment.ui.theme.main.Navigation

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagmentTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.background(Secondary),
                    bottomBar = {
                        BottomNavigationBar(navController = navController) {
                            navController.navigate(it)
                        }
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}