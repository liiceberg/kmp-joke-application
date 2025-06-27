package com.itis.joke.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.itis.joke.android.R
import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory

@Composable
fun JokeCategoriesList(
    selectedCategory: JokeCategory,
    onCategoryChange: (value: JokeCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {

        TitleLargeText(
            text = stringResource(R.string.joke_category),
        )

        JokeCategory.entries.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategoryChange(category) }
            ) {
                RadioItem(category.name, selectedCategory == category) { onCategoryChange(category) }
            }
        }
    }
}

@Composable
fun BlacklistList(
    blackList: List<JokeBlackListItem>,
    onBlackListChange: (item: JokeBlackListItem, checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {

        TitleLargeText(
            text = stringResource(R.string.blacklist),
        )

        JokeBlackListItem.entries.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CheckboxItem(
                    item.name,
                    blackList.contains(item)
                ) { onBlackListChange(item, it) }
            }
        }
    }
}