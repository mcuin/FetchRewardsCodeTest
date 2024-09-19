package com.fetchrewards.fetchrewardscodetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fetchrewards.fetchrewardscodetest.utils.Item

/**
 * The main composable screen of the app.
 * @param modifier Modifier to apply to this composable.
 * @param fetchRewardsCodeTestViewModel ViewModel to use for this screen injected by Hilt.
 */
@Composable
fun FetchRewardsCodeTestScreen(modifier: Modifier = Modifier, fetchRewardsCodeTestViewModel: FetchRewardsCodeTestViewModel = hiltViewModel()) {

    //Scaffold to provide window insets and padding for the entire screen size
    Scaffold(modifier = modifier.navigationBarsPadding()) {

        //Colum to stack the items on top of each other, filling the entire screen
        Column(modifier = modifier
            .fillMaxSize()
            .padding(it)
            .background(MaterialTheme.colorScheme.primary)) {

            //Collect the items from the ViewModel and the loading state from the ViewModel
            val items by fetchRewardsCodeTestViewModel.items.collectAsStateWithLifecycle()
            val loading by fetchRewardsCodeTestViewModel.loading.collectAsStateWithLifecycle()

            //If the loading state is true, show the circular loader, otherwise show the loaded items list
            if (loading) {
                CircularLoader(modifier = modifier)
            } else {
                GroupList(modifier = modifier, groupItems = items)
            }
        }
    }
}

/**
 * The circular loader composable to show while the items are being loaded.
 * @param modifier Modifier to apply to this composable.
 */
@Composable
fun CircularLoader(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        androidx.compose.material3.CircularProgressIndicator(modifier = modifier.size(dimensionResource(id = R.dimen.circular_loader_size)),
            color = MaterialTheme.colorScheme.secondary,
            strokeWidth = dimensionResource(id = R.dimen.circular_loader_stroke))
    }
}

/**
 * The group title text composable to show the group id above the list of items.
 * @param modifier Modifier to apply to this composable.
 * @param groupId The id of the group to show the title for.
 */
@Composable
fun GroupTitleText(modifier: Modifier, groupId: Int) {
    Text(modifier = modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.standard_padding)), text = stringResource(id = R.string.group, groupId))
}

/**
 * The group list composable to show the list of items grouped by the group id. Using experimental sticky headers to show a floating header for each group as the list is scrolled.
 * @param modifier Modifier to apply to this composable.
 * @param groupItems The items to show grouped by the group id.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupList(modifier: Modifier, groupItems: Map<Int, List<Item>>) {
    if (groupItems.isEmpty()) {
        Text(modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.standard_padding)), text = stringResource(id = R.string.empty_state))
        return
    } else {
        LazyColumn(modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.standard_padding))) {
            groupItems.forEach { group ->
                stickyHeader {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(id = R.dimen.small_padding))
                            .background(MaterialTheme.colorScheme.secondary)
                    ) {
                        GroupTitleText(modifier = modifier, groupId = group.key)
                    }
                }
                items(group.value) { item ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen.small_padding))
                    ) {
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(id = R.dimen.small_padding)),
                            text = item.name ?: ""
                        )
                    }
                }
            }
        }
    }
}