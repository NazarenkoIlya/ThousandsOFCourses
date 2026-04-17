package com.example.thousandsofcourses.presentation.favoritescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thousandsofcourses.databinding.FragmentFavoriteCoursesBinding
import com.example.thousandsofcourses.presentation.favoritescreen.model.UIState
import com.example.thousandsofcourses.utils.autoCleared
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class FavoriteCoursesFragment : Fragment() {


    private var binding: FragmentFavoriteCoursesBinding by autoCleared()
    private val viewModel by viewModel<FavoriteCoursesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val adapter = FavoriteCoursesAdapter {
            viewModel.onEvent(it)
        }

        binding.recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { items ->
                    when (items.uiState) {
                        is UIState.Error -> {
                            with(binding) {
                                progressBar.visibility = View.GONE
                                errorText.visibility = View.VISIBLE
                                recyclerView.visibility = View.GONE
                            }
                        }

                        UIState.Loading -> {
                            with(binding) {
                                progressBar.visibility = View.VISIBLE
                                errorText.visibility = View.GONE
                                recyclerView.visibility = View.GONE

                            }
                        }

                        UIState.Success -> {
                            with(binding) {
                                progressBar.visibility = View.GONE
                                errorText.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                            }
                            adapter.submitList(items.courses)
                        }
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }
}