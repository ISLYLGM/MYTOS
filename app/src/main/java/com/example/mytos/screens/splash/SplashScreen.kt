package com.example.mytos.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mytos.ui.theme.MytosPurple
import com.example.mytos.ui.theme.MytosYellow
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onContinuar: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(1500)
        onContinuar()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MytosPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "MYTOS",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Café, sabor e lendas.",
            color = MytosYellow,
            fontSize = 18.sp
        )
    }
}