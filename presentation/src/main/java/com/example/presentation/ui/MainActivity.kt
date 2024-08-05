package com.example.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.presentation.databinding.MainActivityBinding
import com.example.presentation.ui.navigation.HouseMemberNavigation
import com.example.presentation.ui.widget.EmptyState
import com.example.presentation.utils.Network
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater).apply {
            composeView.setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!Network.isConnected(this@MainActivity)){
                            EmptyState()
                        } else {
                            HouseMemberNavigation()
                        }
                    }
                }
            }
        }
        setContentView(binding.root)
    }
}