package com.example.musicapp.ui.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.databinding.FragmentSongsBinding

class SongsFragment : Fragment() {

private var _binding: FragmentSongsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val songViewModel =
            ViewModelProvider(this).get(SongsViewModel::class.java)

    _binding = FragmentSongsBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textSongs
      songViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}