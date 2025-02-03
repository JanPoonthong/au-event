package com.example.auevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.auevent.pages.CreatePage
import com.example.auevent.pages.EventPage
import com.example.auevent.pages.HomePage
import com.example.auevent.ui.theme.AUEventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AUEventTheme {
                val items = listOf(
                    BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
                    BottomNavigationItem("", Icons.Filled.Add, Icons.Outlined.Add),
                    BottomNavigationItem("Event", Icons.Filled.DateRange, Icons.Outlined.DateRange)
                )
                var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = { selectedItemIndex = index },
                                    label = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    ContentScreen(
                        modifier = Modifier.padding(innerPadding),
                        selectedItemIndex
                    )
                }
            }
        }
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedItemIndex: Int) {
    when (selectedItemIndex) {
        0 -> HomePage()
        1 -> CreatePage()
        2 -> EventPage()
    }
}