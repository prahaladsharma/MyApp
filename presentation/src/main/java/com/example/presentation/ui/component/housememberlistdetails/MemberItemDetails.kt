package com.example.presentation.ui.component.housememberlistdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.models.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberItemDetails(
    member: Member,
    navigation: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Overview") },
                navigationIcon = {
                    IconButton(onClick = { navigation.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize(1f)
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray, //Card background color
                    contentColor = Color.White  //Card content color,e.g.text
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = stringResource(R.string.house_details_screen) + member.name,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colorScheme.surface,
                            fontWeight = FontWeight.Bold,
                            style = typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(R.string.name_details_screen) + member.slug,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colorScheme.surface,
                            style = typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(R.string.member_of_details_screen) + member.slug + " " + stringResource(R.string.house_details_screen),
                            modifier = Modifier.padding(10.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colorScheme.surface,
                            fontWeight = FontWeight.Bold,
                            style = typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        for(item in member.members){
                            Text(
                                text = stringResource(R.string.name_details_screen) + item.name,
                                modifier = Modifier.padding(10.dp, 0.dp, 12.dp, 0.dp),
                                color = MaterialTheme.colorScheme.surface,
                                style = typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                }

            }
        }
    }

}


@Preview
@Composable
private fun MemberItemDetailsPreview() {
    MemberItemDetails(
        Member(
            "Lantier",
            "House of Lantier",
        ),
        rememberNavController()
    )
}
