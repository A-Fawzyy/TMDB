package com.fawry.tmdb.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

/**
 * BaseFragment class which has common fragment functionality
 *
 * initializes viewModels and  binding for the application fragments
 */
abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> (
	private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
	private val viewModelClass: (Class<VM>),
) : Fragment() {

	@Inject
	lateinit var viewModel: VM

	private var _binding: VB? = null

	val binding: VB get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		viewModel = ViewModelProvider(this)[viewModelClass]
		_binding = bindingInflater.invoke(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		val view = binding.root
		return view
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
