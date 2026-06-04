package com.example.ui.teacher

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DigitTabHeader
import com.example.ui.student.Primary
import com.example.ui.student.PrimaryContainer
import com.example.ui.student.SurfaceCard
import com.example.ui.student.SurfaceContainerHighest
import com.example.ui.student.SurfaceContainerLow
import com.example.ui.student.SurfaceBg
import com.example.ui.student.OnSurface
import com.example.ui.student.OnSurfaceVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToAlerts: () -> Unit
) {
    Scaffold(
        containerColor = SurfaceBg,
        topBar = {
            DigitTabHeader()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 16.dp
            )
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape,
                        color = PrimaryContainer,
                        border = androidx.compose.foundation.BorderStroke(2.dp, Primary)
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.padding(24.dp), tint = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Mustafizur Rahman", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                    Text("Senior Mathematics Teacher", fontSize = 14.sp, color = OnSurfaceVariant)
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Account Settings", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Primary)
                    
                    ProfileOptionCard("Edit Profile", Icons.Default.Edit)
                    ProfileOptionCard("Class Settings", Icons.Default.SettingsApplications)
                    ProfileOptionCard("Help & Support", Icons.Default.HelpOutline)
                    ProfileOptionCard("Sign Out", Icons.Default.Logout, tint = Color(0xFFD32F2F))
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Composable
fun ProfileOptionCard(title: String, icon: ImageVector, tint: Color = Primary) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = SurfaceCard,
        shadowElevation = 0.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHighest),
        onClick = { }
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = CircleShape, color = tint.copy(alpha = 0.1f), modifier = Modifier.size(44.dp)) {
                Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = if (tint != Primary) tint else OnSurface, modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(20.dp))
        }
    }
}
