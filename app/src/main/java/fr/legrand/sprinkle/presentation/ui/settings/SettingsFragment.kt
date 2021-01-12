package fr.legrand.sprinkle.presentation.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.BuildConfig
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentSettingsBinding
import fr.legrand.viewbinding.extensions.BindingFragment
import java.text.SimpleDateFormat
import java.util.*

private const val VERSION_FORMAT = "%s_%d_%s"

private const val BUILD_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss"

@AndroidEntryPoint
class SettingsFragment : BindingFragment<FragmentSettingsBinding>() {

    private val viewModel: SettingsFragmentViewModel by viewModels()

    override fun getBinding(view: View) = FragmentSettingsBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buildDate =
            SimpleDateFormat(BUILD_DATE_FORMAT, Locale.getDefault()).format(Date(BuildConfig.BUILD_TIMESTAMP))

        binding {
            fragmentSettingsVersion.text =
                VERSION_FORMAT.format(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, buildDate)
        }
    }
}