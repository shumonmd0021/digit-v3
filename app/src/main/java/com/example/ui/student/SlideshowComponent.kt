package com.example.ui.student

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyActivitySlideshow(onNavigateToLesson: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 5 })

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            val nextPage = (pagerState.currentPage + 1) % 5
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
            )
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 16.dp
        ) { page ->
            Box(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).coerceIn(-1f, 1f)
                    val scale = 1f - (0.05f * kotlin.math.abs(pageOffset))
                    scaleX = scale
                    scaleY = scale
                    alpha = 1f - (0.2f * kotlin.math.abs(pageOffset))
                }
            ) {
                when (page) {
                    0 -> DidYouKnowCard()
                    1 -> FlashcardOfTheDay()
                    2 -> DailyChallengeCard()
                    3 -> WordOfTheDayCard()
                    4 -> TodaysLessonCard(onNavigateToLesson)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { iteration ->
                val isSelected = pagerState.currentPage == iteration
                val targetColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                val targetWidth = if (isSelected) 24.dp else 8.dp
                
                val color by androidx.compose.animation.animateColorAsState(
                    targetValue = targetColor,
                    animationSpec = tween(400, easing = FastOutSlowInEasing),
                    label = "color"
                )
                val width by androidx.compose.animation.core.animateDpAsState(
                    targetValue = targetWidth,
                    animationSpec = tween(400, easing = FastOutSlowInEasing),
                    label = "width"
                )
                
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(width = width, height = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DidYouKnowCard() {
    SlideshowCardBase {
        Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("আজকের তথ্য / Did You Know?", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f), letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text("শুক্র গ্রহের এক দিন পৃথিবীর এক বছরের চেয়ে বড়!", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, lineHeight = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(12.dp))
        Text("A day on Venus is longer than a year on Earth!", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center)
    }
}

@Composable
fun SlideshowCardBase(content: @Composable ColumnScope.() -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 8.dp,
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF3C3F73), Color(0xFF54578C)) // Primary to PrimaryContainer
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = content
            )
        }
    }
}

@Composable
fun FlashcardOfTheDay() {
    var flipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(600, easing = FastOutSlowInEasing),
        label = "flip"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            },
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 8.dp,
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF3C3F73), Color(0xFF54578C))
                    )
                )
        ) {
            if (rotation <= 90f) {
                // Front
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("FLASHCARD OF THE DAY", fontSize = 10.sp, letterSpacing = 2.sp, color = Color.White.copy(alpha = 0.7f))
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text("মহাকাশ (Mohakash)", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Space / Universe", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Button(
                        onClick = { flipped = !flipped },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x19FFFFFF), // white/10
                            contentColor = Color.White
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Icon(Icons.Default.Autorenew, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("উল্টে দেখুন", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                }
            } else {
                // Back
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f }, // Un-flip the content
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Definition", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("তাত্ত্বিকভাবে পৃথিবীর বায়ুমণ্ডলের বাইরের অনন্ত স্থান;", fontSize = 18.sp, textAlign = TextAlign.Center, lineHeight = 24.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("The boundless three-dimensional extent in which objects and events have relative position and direction.", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { flipped = !flipped },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x19FFFFFF),
                            contentColor = Color.White
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Autorenew, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("উল্টে দেখুন")
                    }
                }
            }
        }
    }
}

@Composable
fun DailyChallengeCard() {
    var answered by remember { mutableStateOf<Boolean?>(null) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    
    val options = listOf("A. ৬টি", "B. ৭টি", "C. ৮টি", "D. ৯টি")
    val correctOption = 2

    SlideshowCardBase {
        Text("DAILY CHALLENGE / আজকের কুইজ", fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f), letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("সৌরজগতে কয়টি গ্রহ আছে?", fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text("How many planets are in the solar system?", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChallengeOption(options[0], Modifier.weight(1f), answered, selectedOption == 0, 0 == correctOption) { if(answered == null) { selectedOption = 0; answered = (0 == correctOption) } }
                ChallengeOption(options[1], Modifier.weight(1f), answered, selectedOption == 1, 1 == correctOption) { if(answered == null) { selectedOption = 1; answered = (1 == correctOption) } }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChallengeOption(options[2], Modifier.weight(1f), answered, selectedOption == 2, 2 == correctOption) { if(answered == null) { selectedOption = 2; answered = (2 == correctOption) } }
                ChallengeOption(options[3], Modifier.weight(1f), answered, selectedOption == 3, 3 == correctOption) { if(answered == null) { selectedOption = 3; answered = (3 == correctOption) } }
            }
        }
        
        androidx.compose.animation.AnimatedVisibility(
            visible = answered != null,
            enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.expandVertically(),
            exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.shrinkVertically()
        ) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(if (answered == true) "সঠিক উত্তর! (Correct!)" else "ভুল উত্তর! (Incorrect!)", color = if (answered == true) Color(0xFF4CAF50) else Color(0xFFFF5252), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ChallengeOption(text: String, modifier: Modifier, answered: Boolean?, isSelected: Boolean, isCorrect: Boolean, onClick: () -> Unit) {
    val targetBgColor = when {
        answered != null && isCorrect -> Color(0xFF4CAF50) // Correct
        answered != null && isSelected && !isCorrect -> Color(0xFFFF5252) // Wrong selected
        else -> Color.White.copy(alpha = 0.2f)
    }
    
    val bgColor by androidx.compose.animation.animateColorAsState(
        targetValue = targetBgColor,
        animationSpec = tween(400, easing = FastOutSlowInEasing),
        label = "bgColor"
    )
    
    Surface(
        modifier = modifier.height(44.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        color = bgColor,
        contentColor = Color.White
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun WordOfTheDayCard() {
    SlideshowCardBase {
        Text("ENGLISH WORD OF THE DAY / ইংরেজি শব্দ", fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f), letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Learn", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text("শেখা • Verb", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            color = Color.Black.copy(alpha = 0.15f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("I want to learn something new every day.", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("আমি প্রতিদিন নতুন কিছু শিখতে চাই।", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
            }
        }
    }
}

@Composable
fun TodaysLessonCard(onNavigateToLesson: () -> Unit) {
    SlideshowCardBase {
        Text("TODAY'S LESSON / আজকের পাঠ", fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f), letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Surface(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.2f)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(16.dp), tint = Color.White)
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        Text("বাংলা ব্যাকরণ", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
        Text("কারক ও বিভক্তি", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text("45 Mins", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
        
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToLesson,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF5C6399)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Start Learning (পড়া শুরু করুন)", fontWeight = FontWeight.Bold)
        }
    }
}
