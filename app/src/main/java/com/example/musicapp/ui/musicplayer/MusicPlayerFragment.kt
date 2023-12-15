package com.example.musicapp.ui.musicplayer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentMusicPlayerBinding
import java.util.concurrent.Executors

class MusicPlayerFragment : Fragment() {

    private lateinit var binding: FragmentMusicPlayerBinding
    private lateinit var viewModel: MusicPlayerViewModel
    private lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MusicPlayerViewModel::class.java]
        binding.viewModel = viewModel
        mediaPlayer = MediaPlayer() // Khởi tạo mediaPlayer

        setupUI()
        observeViewModel()

        viewModel.fetchSongInfo("11131abd-1b99-688b-96f6-423b4e874d0f")

        return binding.root
    }

    private fun setupUI() {
        binding.playButton.setOnClickListener {
            viewModel.togglePlayPause()
        }

        binding.nextButton.setOnClickListener {
            viewModel.skipToNext()
        }

        binding.prevButton.setOnClickListener {
            viewModel.skipToPrevious()
        }

        binding.repeatButton.setOnClickListener {
            viewModel.toggleRepeat()
        }

        binding.shuffleButton.setOnClickListener {
            viewModel.toggleShuffle()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Tạm dừng việc cập nhật trạng thái của SeekBar khi người dùng chạm vào nó
                handler.removeCallbacksAndMessages(null)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Tiếp tục cập nhật trạng thái của SeekBar sau khi người dùng đã kết thúc việc di chuyển
                mediaPlayer.start()
                updateSeekBar()
            }
        })
    }

    private fun observeViewModel() {
        viewModel.isRepeatOn.observe(viewLifecycleOwner) { isRepeatOn ->
            if(isRepeatOn == true) {
                binding.repeatButton.setColorFilter(ContextCompat.getColor(requireContext(),R.color.primary))
                mediaPlayer.isLooping = true;
            }
            else {
                binding.repeatButton.setColorFilter(ContextCompat.getColor(requireContext(),R.color.white))
                mediaPlayer.isLooping = false;
            }
        }

        viewModel.isShuffleOn.observe(viewLifecycleOwner) { isShuffleOn ->
            if(isShuffleOn == true) {
                binding.shuffleButton.setColorFilter(ContextCompat.getColor(requireContext(),R.color.primary))
            }
            else {
                binding.shuffleButton.setColorFilter(ContextCompat.getColor(requireContext(),R.color.white))
            }
        }

        viewModel.isPlaying.observe(viewLifecycleOwner) { isPlaying ->
            if (isPlaying == true) {
                mediaPlayer.start()
                binding.playButton.setImageResource(R.drawable.ic_pause)
            } else {
                mediaPlayer.pause()
                binding.playButton.setImageResource(R.drawable.ic_play)
            }
        }

        viewModel.currentSongUrl.observe(viewLifecycleOwner) { songUrl ->
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            // on below line we are running a try
            // and catch block for our media player.
            try {
                mediaPlayer.setDataSource(songUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()

                val duration = mediaPlayer.duration
                binding.textViewDuration.text = changeToMinus(duration)
                binding.seekBar.max = duration

                // Cập nhật trạng thái của SeekBar theo thời gian của video
                updateSeekBar()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModel.imgUrl.observe(viewLifecycleOwner) { imgUrl ->
            Glide.with(requireContext())
                .load(imgUrl)
                .into(binding.imageViewSong)
        }

        viewModel.artist.observe(viewLifecycleOwner) { artist ->
            binding.textViewArtist.text = artist
        }

        viewModel.songName.observe(viewLifecycleOwner) { songName ->
            binding.textViewSongName.text = songName
        }
    }

    private fun updateSeekBar() {
        if (mediaPlayer.isPlaying) {
            val time = mediaPlayer.currentPosition
            binding.seekBar.progress = time
            binding.textViewTime.text = changeToMinus(time)
            handler.postDelayed({ updateSeekBar() }, 100)
        }
    }

    private fun changeToMinus(durationInMillis: Int) : String {
        val minutes = (durationInMillis / 1000) / 60
        val seconds = (durationInMillis / 1000) % 60
        val formattedDuration = String.format("%02d:%02d", minutes, seconds)
        return formattedDuration
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }

}
