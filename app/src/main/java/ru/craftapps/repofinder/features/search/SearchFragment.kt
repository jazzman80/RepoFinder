package ru.craftapps.repofinder.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
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
                    SearchScreen(
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