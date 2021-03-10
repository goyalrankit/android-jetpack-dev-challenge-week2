/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Countdown()
    }
}

@Composable
fun Countdown()
{
    var defaultSeconds:Long =30
    var displaySeconds by remember{ mutableStateOf(defaultSeconds)}

    val  timer = object : CountDownTimer((defaultSeconds*1000),1000)
    {
        override fun onTick(millisUntilFinished: Long) {
            if(defaultSeconds>=0) {
                Log.d("TAG", "$defaultSeconds")
                defaultSeconds--
                displaySeconds = defaultSeconds
                if(displaySeconds <= 0) {
                    displaySeconds = 0L
                }
            }
        }
        override fun onFinish() {
            Log.d("TAG", "Finished")
        }
    }

    Surface(color = Color(0xF011062E), modifier = Modifier.fillMaxSize()) {
       Row( modifier = Modifier.fillMaxWidth(),
           Arrangement.Center
       ){
            Text(
                text = "COUNTDOWN TIMER",
                modifier = Modifier
                    .padding(15.dp)
                    .requiredHeight(200.dp)
                    .fillMaxWidth(),
                color = Color.White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .requiredHeight(300.dp)
        ){
            Card(modifier = Modifier
                .clip(shape = RoundedCornerShape(100.dp))
                .border(width = 2.dp, Color.Green)
                .padding(15.dp)
                .fillMaxWidth()
                .fillMaxHeight())
            { Text(text =  "00:$displaySeconds s",
                    modifier = Modifier
                        .padding(15.dp)
                        .requiredHeight(80.dp)
                        .requiredWidth(160.dp),
                        fontSize = 50.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif)
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = Color(0xFFC8E6C9))
                    .padding(20.dp),
            ) {
                Button(modifier = Modifier
                    .padding(5.dp)
                    .requiredHeight(80.dp)
                    .requiredWidth(80.dp),
                    border = BorderStroke(2.dp, Color(0xF011062E)),
                    shape = RoundedCornerShape(50), //50% percent
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    onClick = { timer.start()}
                ){
                    Text( text = "Start" )
                }
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(5.dp)
                            .requiredHeight(80.dp)
                            .requiredWidth(80.dp),
                        border = BorderStroke(2.dp, Color(0xF011062E)),
                        shape = RoundedCornerShape(50), //50% percent
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        onClick = { timer.cancel() }
                    )
                    {
                        Text( text = "Pause" )
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
