package com.example.ui.student

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color

@Composable
fun StudentBottomNavBar(
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToLessons: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToQuizzes: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val bgColor = if (isDark) Color(0xFF1E1E22) else Color.White
    val indicatorColor = if (isDark) Color(0xFF3C3F73) else Color(0xFFDCE1FF)
    val selectedIcon = if (isDark) Color(0xFFBFC2FE) else Color(0xFF54578C)
    val selectedText = if (isDark) Color(0xFFBFC2FE) else Color(0xFF54578C)
    val unselected = if (isDark) Color(0xFF777680) else Color.Gray

    val navColors = NavigationBarItemDefaults.colors(
        indicatorColor = indicatorColor,
        selectedIconColor = selectedIcon,
        selectedTextColor = selectedText,
        unselectedIconColor = unselected,
        unselectedTextColor = unselected
    )

    NavigationBar(
        containerColor = bgColor
    ) {
        NavigationBarItem(
            selected = currentRoute == "Home",
            onClick = onNavigateToHome,
            icon = { Icon(if (currentRoute == "Home") Icons.Filled.Home else Icons.Outlined.Home, null) },
            label = { Text("Home") },
            colors = navColors
        )
        NavigationBarItem(
            selected = currentRoute == "Lessons",
            onClick = onNavigateToLessons,
            icon = { Icon(if (currentRoute == "Lessons") Icons.Filled.MenuBook else Icons.Outlined.MenuBook, null) },
            label = { Text("Lessons") },
            colors = navColors
        )
        NavigationBarItem(
            selected = currentRoute == "Progress",
            onClick = onNavigateToProgress,
            icon = { Icon(if (currentRoute == "Progress") Icons.Filled.Timeline else Icons.Outlined.Timeline, null) },
            label = { Text("Progress") },
            colors = navColors
        )
        NavigationBarItem(
            selected = currentRoute == "Quizzes",
            onClick = onNavigateToQuizzes,
            icon = { Icon(if (currentRoute == "Quizzes") Icons.Filled.Quiz else Icons.Outlined.Quiz, null) },
            label = { Text("Quizzes") },
            colors = navColors
        )
    }
}
