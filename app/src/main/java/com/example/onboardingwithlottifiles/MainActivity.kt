package com.example.onboardingwithlottifiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.onboardingwithlottifiles.ui.theme.OnboardingWithLottiFilesTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingWithLottiFilesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Start()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Start() {
    val listOfLotti =
        listOf(DataModel.First, DataModel.Second, DataModel.Third, DataModel.Forth, DataModel.Fifth)
    val pagerState = rememberPagerState()
    Column(Modifier.fillMaxSize()) {
        HorizontalPager(
            count = listOfLotti.size,
            state = pagerState,
            modifier = Modifier.weight(0.8f)
        ) { index ->
            DrawTheShape(dataModel = listOfLotti[index])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .weight(0.1f)
                .align(
                    CenterHorizontally
                ),
            spacing = 15.dp, indicatorHeight = 10.dp, indicatorWidth = 10.dp,
            activeColor = Color(0xFF00b0ff),
            inactiveColor = Color(0xFF0B72A2)
        )
        Row(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                enter = fadeIn(animationSpec = tween(1000)) +
                        expandVertically(
                            animationSpec = tween(1500)
                        ),
                exit = fadeOut(animationSpec = tween(1000)) +
                        shrinkVertically(
                            animationSpec = tween(1500)
                        ),
                visible = pagerState.currentPage == 4,
                modifier = Modifier
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .padding(bottom = 10.dp)
                        .width(64.dp.times(2))
                        .height(TextFieldDefaults.MinHeight),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 15.dp,
                        pressedElevation = 30.dp,
                        hoveredElevation = 10.dp,
                        focusedElevation = 10.dp
                    ),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF007bb2),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Go",
                        fontWeight = FontWeight.Medium,
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
            }
        }
    }

}


@Composable
@Preview(showBackground = true)
fun DrawTheShape(dataModel: DataModel = DataModel.Fifth) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        /** Lotti file location specified */
        val composition =
            rememberLottieComposition(LottieCompositionSpec.RawRes(dataModel.lottiID))

        /** Add extra properties to lottie file object*/
        val progress =
            animateLottieCompositionAsState(composition.value, iterations = Int.MAX_VALUE)

        /** Lotti File Animation*/
        LottieAnimation(
            composition.value,
            progress.value, modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = dataModel.title,
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF01579B)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = dataModel.subTitle,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color(0xFF01579B)
        )
    }
}
