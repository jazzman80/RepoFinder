package ru.craftapps.repofinder.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.compose.koinViewModel
import ru.craftapps.repofinder.theme.AppTheme

class SearchFragment : Fragment() {

    private val thisFragment = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Отключение кнопки "Назад" - это стартовый фрагмент
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
    }

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
                    val viewModel = koinViewModel<SearchViewModel>()

                    // Состояние экрана
                    val state = viewModel.viewState.value

                    // События
                    val setEvent: (SearchContract.Event) -> Unit =
                        { viewModel.setEvent(event = it) }

                    SearchScreen(
                        state = state,
                        setEvent = setEvent,
                        navigateToDownload = {
                            val action =
                                SearchFragmentDirections.actionSearchFragmentToDownloadFragment()
                            navigation.navigate(action)
                        }
                    )
                }
            }
        }
    }
}