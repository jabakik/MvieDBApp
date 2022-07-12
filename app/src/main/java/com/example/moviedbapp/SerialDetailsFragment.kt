package com.example.moviedbapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.moviedbapp.apis.RetrofitBuilder
import com.example.moviedbapp.databinding.LeyautSerialListFragmentBinding
import com.example.moviedbapp.databinding.LeyoutSerialDetailsFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SerialDetailsFragment: Fragment() {
    private var _binding: LeyoutSerialDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LeyoutSerialDetailsFragmentBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID_PARAM)
        CoroutineScope(IO). launch {
            val serialDetails = RetrofitBuilder.tvApi.getSerialDetail(id, SerialListFragment.API_KEY)
            withContext(Main){
                binding
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
   companion object {
       const val KEY_ID_PARAM = "KEY_ID_PARAM"
       fun newInstance (id: Int):SerialDetailsFragment {
           return SerialDetailsFragment().apply {
               arguments = bundleOf(KEY_ID_PARAM to id)
           }
       }
   }
}