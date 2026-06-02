package com.example.ui.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.isSystemInDarkTheme

val Primary: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFBFC2FE) else Color(0xFF3C3F73)
val PrimaryContainer: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF3C3F73) else Color(0xFF54578C)
val PrimaryFixed: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFE0E0FF) else Color(0xFFE0E0FF)
val SecondaryContainer: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF40436E) else Color(0xFFC9CBFF)
val TertiaryContainer: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF6D5E13) else Color(0xFFBFAB5A)
val AchievementOrange: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFE59A6D) else Color(0xFFD38350)
val SurfaceCard: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF1E1E22) else Color(0xFFFFFFFF)
val SurfaceContainerHighest: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF33353A) else Color(0xFFE1E3E4)
val SurfaceContainerLow: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF282A2F) else Color(0xFFF3F4F5)
val SurfaceBg: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF121216) else Color(0xFFF8F9FA)
val OnSurface: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFE1E3E4) else Color(0xFF191C1D)
val OnSurfaceVariant: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFC4C6D0) else Color(0xFF46464F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToLesson: () -> Unit,
    onNavigateToLessonsTab: () -> Unit,
    onNavigateToQuiz: () -> Unit,
    onNavigateToFlashcards: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToQuizzesTab: () -> Unit
) {
    Scaffold(
        containerColor = SurfaceBg,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceBg.copy(alpha = 0.95f)),
                title = {},
                navigationIcon = {
                    Row(
                        modifier = Modifier.padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            color = PrimaryContainer,
                            border = androidx.compose.foundation.BorderStroke(2.dp, Primary.copy(alpha = 0.2f))
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.padding(8.dp), tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("আসালামু আলাইকুম", fontSize = 10.sp, color = OnSurfaceVariant)
                            Text("রাহিম", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Primary)
                        }
                    }
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 16.dp)) {
                        Row(
                            modifier = Modifier
                                .background(SurfaceContainerLow, CircleShape)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = AchievementOrange, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("৫", fontWeight = FontWeight.Bold, color = AchievementOrange, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Primary)
                        }
                    }
                }
            )
        },
        bottomBar = {
            StudentBottomNavBar(
                currentRoute = "Home",
                onNavigateToHome = onNavigateToHome,
                onNavigateToLessons = onNavigateToLessonsTab,
                onNavigateToProgress = onNavigateToProgress,
                onNavigateToQuizzes = onNavigateToQuizzesTab
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            
            // Activity Slideshow
            item {
                DailyActivitySlideshow(onNavigateToLesson)
            }

            // Daily Goal
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(32.dp),
                    color = SurfaceCard,
                    shadowElevation = 0.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHighest)
                ) {
                    Box {
                        // Background decor
                        Box(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(128.dp)
                            .clip(RoundedCornerShape(bottomStart = 100.dp))
                            .background(AchievementOrange.copy(alpha = 0.2f))
                        )
                        Column(modifier = Modifier.padding(24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(shape = CircleShape, color = AchievementOrange.copy(alpha = 0.1f), modifier = Modifier.size(48.dp)) {
                                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = AchievementOrange, modifier = Modifier.padding(12.dp))
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text("দৈনিক লক্ষ্য", fontWeight = FontWeight.Bold, color = OnSurface, fontSize = 18.sp)
                                    Text("Daily Goal", fontSize = 12.sp, color = OnSurfaceVariant)
                                }
                            }
                            Text("২/৩ পাঠ", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = OnSurfaceVariant)
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        LinearProgressIndicator(
                            progress = { 0.66f },
                            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                            color = AchievementOrange,
                            trackColor = SurfaceContainerHighest
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = onNavigateToLesson, 
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Primary)
                        ) {
                            Text("পড়া চালিয়ে যান", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    }
                    }
                }
            }

            // Quick Quiz
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(32.dp),
                    color = SurfaceCard,
                    shadowElevation = 2.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHighest)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(shape = CircleShape, color = SecondaryContainer.copy(alpha = 0.5f), modifier = Modifier.size(56.dp)) {
                            Icon(Icons.Default.Quiz, contentDescription = null, tint = Primary, modifier = Modifier.padding(14.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("দ্রুত কুইজ", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                            Text("5 Questions • 2 Mins", fontSize = 14.sp, color = OnSurfaceVariant)
                        }
                        Button(
                            onClick = onNavigateToQuizzesTab,
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer, contentColor = PrimaryFixed),
                            shape = CircleShape
                        ) {
                            Text("শুরু করুন", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Recent Lessons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text("সাম্প্রতিক পাঠসমূহ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Primary)
                        Text("Recent Lessons", fontSize = 12.sp, color = OnSurfaceVariant)
                    }
                    TextButton(onClick = { onNavigateToLessonsTab() }) { 
                        Text("সব দেখ", fontWeight = FontWeight.SemiBold, color = Primary) 
                    }
                }
            }

            item {
                // We use a Column of two items or Row (if wide). For mobile, a Column of cards.
                Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    LessonCard(
                        title = "যোগ ও বিয়োগ",
                        category = "গণিত",
                        progress = 0.8f,
                        icon = Icons.Default.Calculate,
                        iconCol = SecondaryContainer,
                        iconTint = Primary,
                        onClick = onNavigateToLesson
                    )
                    LessonCard(
                        title = "সৌরজগৎ",
                        category = "বিজ্ঞান",
                        progress = 0.4f,
                        icon = Icons.Default.WbSunny,
                        iconCol = TertiaryContainer,
                        iconTint = if (isSystemInDarkTheme()) Color(0xFFF9E28B) else Color(0xFF4B3F00),
                        onClick = onNavigateToLesson
                    )
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun LessonCard(title: String, category: String, progress: Float, icon: ImageVector, iconCol: Color, iconTint: Color, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        color = SurfaceCard,
        shadowElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHighest),
        onClick = onClick
    ) {
        Box {
            // Background decor
            Box(modifier = Modifier.align(Alignment.TopEnd).size(128.dp).clip(RoundedCornerShape(bottomStart = 100.dp)).background(iconCol.copy(alpha = 0.3f)))

            Column(modifier = Modifier.padding(32.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(shape = CircleShape, color = iconCol, shadowElevation = 1.dp, modifier = Modifier.size(64.dp)) {
                        Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.padding(16.dp))
                    }
                    Surface(
                        color = if (category == "গণিত") PrimaryContainer else SurfaceContainerHighest,
                        shape = CircleShape
                    ) {
                        Text(
                            text = category,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (category == "গণিত") PrimaryFixed else OnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(title, fontWeight = FontWeight.Bold, color = Primary, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Progress", fontSize = 11.sp, color = OnSurfaceVariant)
                    Text("${(progress * 100).toInt()} %", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Primary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = Primary,
                    trackColor = SurfaceContainerHighest
                )
            }
        }
    }
}
