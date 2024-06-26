package ru.craftapps.repofinder.features.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.compose.koinViewModel
import ru.craftapps.repofinder.theme.AppTheme

class DownloadFragment : Fragment() {

    private val thisFragment = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navigation = NavHostFragment.findNavController(thisFragment)

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {

                    // Подключение вьюмодели
                    val viewModel = koinViewModel<DownloadViewModel>()

                    // Состояние экрана
                    val state = viewModel.viewState.value

                    DownloadScreen(
                        state = state,
                        navigateBack = {
                            navigation.popBackStack()
                        }
                    )
                }
            }
        }
    }
}