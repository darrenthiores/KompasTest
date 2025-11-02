package com.darrenthiores.kompastest.core_ui.audio

import kotlinx.coroutines.flow.StateFlow

interface AudioPlayer {
    fun play(url: String)
    fun pause()
    fun resume()
    fun stop()
    fun release()
    val isPlaying: StateFlow<Boolean>
    val isLoading: StateFlow<Boolean>
}