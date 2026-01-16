package com.example.musium.presentation.ui.home.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CollapsableHeader(scrollBehavior: TopAppBarScrollBehavior) {
//    val collapsedFraction = scrollBehavior.state.collapsedFraction
//    val contentAlpha = 1f - collapsedFraction
//    TopAppBar(
//        navigationIcon = {
//            Icon(
//                modifier = Modifier.height(AppDimensions.searchIconHeight),
//                imageVector = Icons.Filled.Menu,
//                contentDescription = AppStrings.searchHintText,
//                tint = Color.White
//            )
//        },
//        title = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    AppStrings.appName,
//                    style = TextStyle(color = Color.White, fontSize = 32.sp),
//                    modifier = Modifier
//                        .offset((10).dp)
//                        .alpha(contentAlpha)
//                )
//            }
//        }, scrollBehavior = scrollBehavior
//    )
//
//}