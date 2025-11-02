package com.darrenthiores.kompastest.core_ui.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ViewModelScoped
class ExoAudioPlayer @Inject constructor(
    @ApplicationContext context: Context
) : AudioPlayer {
    private val player = ExoPlayer.Builder(context).build()
    private var currentUrl: String? = null

    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> get() = _isPlaying

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        player.addListener(
            object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _isPlaying.value = isPlaying
                }
                override fun onPlaybackStateChanged(playbackState: Int) {
                    _isLoading.value = playbackState == Player.STATE_BUFFERING
                }
            }
        )
    }

    override fun play(url: String) {
        when {
            currentUrl != url -> {
                currentUrl = url
                val item = MediaItem.fromUri(url)
                player.setMediaItem(item)
                player.prepare()
                player.play()
            }
            !player.isPlaying -> {
                player.play()
            }
        }
    }

    override fun pause() = player.pause()
    override fun resume() = player.play()
    override fun stop() = player.stop()
    override fun release() = player.release()
}