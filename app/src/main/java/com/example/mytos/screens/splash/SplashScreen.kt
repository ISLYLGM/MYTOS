package com.example.mytos.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytos.R
import com.example.mytos.ui.theme.MytosTheme
import kotlinx.coroutines.delay

private val PurpleTop = Color(0xFF3A1A63)
private val PurpleMid = Color(0xFF2A1248)
private val PurpleBottom = Color(0xFF1B0B33)

private val Cream = Color(0xFFFBF3E4)
private val ProgressPurple = Color(0xFF9B6DDB)

@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    durationMillis: Int = 2500
) {

    val progress = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {

        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = durationMillis
            )
        )

        delay(100)

        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        PurpleTop,
                        PurpleMid,
                        PurpleBottom
                    )
                )
            )
            .semantics {
                contentDescription =
                    "Tela inicial do aplicativo MYTOS. Carregando o aplicativo."
                liveRegion = LiveRegionMode.Polite
            }
    ) {

        // =========================
        // MONTANHAS DECORATIVAS
        // =========================

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val w = size.width
            val h = size.height

            val backMountain = Path().apply {

                moveTo(0f, h)

                lineTo(0f, h * 0.72f)

                lineTo(w * 0.18f, h * 0.58f)

                lineTo(w * 0.35f, h * 0.72f)

                lineTo(w * 0.52f, h * 0.55f)

                lineTo(w * 0.70f, h * 0.70f)

                lineTo(w * 0.86f, h * 0.58f)

                lineTo(w, h * 0.68f)

                lineTo(w, h)

                close()
            }

            drawPath(
                path = backMountain,
                color = Color.White.copy(alpha = 0.05f)
            )

            val frontMountain = Path().apply {

                moveTo(0f, h)

                lineTo(0f, h * 0.84f)

                lineTo(w * 0.22f, h * 0.72f)

                lineTo(w * 0.42f, h * 0.84f)

                lineTo(w * 0.62f, h * 0.70f)

                lineTo(w * 0.80f, h * 0.82f)

                lineTo(w, h * 0.74f)

                lineTo(w, h)

                close()
            }

            drawPath(
                path = frontMountain,
                color = Color.White.copy(alpha = 0.07f)
            )
        }

        // =========================
        // CONTEÚDO
        // =========================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 32.dp,
                    vertical = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // LOGO MAIOR

            Image(
                painter = painterResource(
                    id = R.drawable.mytos_logo_white
                ),
                contentDescription = "Logo MYTOS",
                modifier = Modifier
                    .size(150.dp)
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // TEXTO CENTRALIZADO

            Text(
                text = "Café, sabor e lendas\nem cada gole.",
                color = Cream,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center
            )

            // MAIS ESPAÇO ANTES DA BARRA

            Spacer(
                modifier = Modifier.height(65.dp)
            )

            // BARRA MENOR

            LinearProgressIndicator(
                progress = {
                    progress.value
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(5.dp)
                    .semantics {
                        contentDescription =
                            "Carregando o aplicativo"
                    },
                color = ProgressPurple,
                trackColor = Color.White.copy(
                    alpha = 0.18f
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SplashScreenPreview() {

    MytosTheme {

        SplashScreen(
            onFinished = {}
        )
    }
}