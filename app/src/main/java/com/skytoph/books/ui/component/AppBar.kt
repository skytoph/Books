package com.skytoph.books.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.skytoph.books.R
import com.skytoph.books.ui.appbar.AppBarState

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    state: AppBarState,
    navigateUp: () -> Unit,
) {
    Crossfade(targetState = state, label = stringResource(R.string.label_animation_app_bar_crossfade)) { state ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.canNavigateUp)
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.navigate_up),
                        contentDescription = stringResource(R.string.description_navigate_up),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            else
                Spacer(modifier = Modifier.size(48.dp))
            Text(
                text = state.title,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}