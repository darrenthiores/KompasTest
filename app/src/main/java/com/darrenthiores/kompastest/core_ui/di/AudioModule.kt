package com.darrenthiores.kompastest.core_ui.di

import com.darrenthiores.kompastest.core_ui.audio.AudioPlayer
import com.darrenthiores.kompastest.core_ui.audio.ExoAudioPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AudioModule {

    @Binds
    @ViewModelScoped
    abstract fun provideAudioPlayer(
        player: ExoAudioPlayer
    ): AudioPlayer
}