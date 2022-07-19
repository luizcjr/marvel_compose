package com.example.marvelcompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.presentation.components.LoadingItem
import com.example.marvelcompose.presentation.components.LoadingView
import com.example.marvelcompose.presentation.theme.cardTitle
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterial3Api
@Composable
fun CharacterDetailsLayout(
    characterState: State<List<Character>?>,
    loadingState: State<Boolean?>,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    if (loadingState.value == true) {
        LoadingView(modifier = Modifier.fillMaxSize())
    }

    characterState.value?.let { characterDetail ->
        characterDetail.firstNotNullOf {
            LazyColumn(contentPadding = paddingValues) {
                item {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                            val (image, text) = createRefs()

                            GlideImage(
                                imageModel = it.imageUrl,
                                modifier = modifier
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                        start.linkTo(parent.start)
                                    }
                                    .height(250.dp),
                                loading = {
                                    LoadingItem()
                                },
                                failure = {
                                    Text(text = "image request failed.")
                                }
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black)
                                    .alpha(.7f)
                                    .constrainAs(text) {
                                        bottom.linkTo(image.bottom)
                                        end.linkTo(image.end)
                                        start.linkTo(image.start)
                                    },
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(all = 8.dp),
                                    text = it.name,
                                    color = Color.White,
                                    style = cardTitle
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
