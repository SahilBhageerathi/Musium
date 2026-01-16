package com.example.musium.presentation.ui.onboarding.widgets

import android.text.style.StyleSpan
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.musium.R

@Composable
fun OnboardingText(
    textResourceId: Int,
    spanStyle: SpanStyle,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    textAlign: TextAlign? = null
) {
    val rawText = stringResource(textResourceId)

    val annotatedString = buildAnnotatedString {
        val regex = "<highlight>(.*?)</highlight>".toRegex()

        var lastIdx = 0

        for (match in regex.findAll(rawText)) {
            val startIdx = match.range.first
            val endIdx = match.range.last
            val beforeText = rawText.substring(lastIdx, startIdx)
            val highlightedText = match.groupValues[1]
            append(beforeText)
            withStyle(style = spanStyle) {
                append(highlightedText)
            }
            lastIdx = endIdx + 1
        }

        if(lastIdx<rawText.length){
            append(rawText.substring(lastIdx))
        }

    }

    Text(
        annotatedString,
        modifier = modifier,
        style = textStyle,
        textAlign = textAlign
    )
}