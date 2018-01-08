package com.chepizhko.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;


    // Для обращения к активам используется класс AssetManager
    // конструктор, который получает Context, извлекает AssetManager и сохраняет на будущее
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        // Этот конструктор считается устаревшим,
        // но он нужен для обеспечения совместимости
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }
    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void release() {
        mSoundPool.release();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            // Метод AssetManager.list(String) возвращает список имен файлов, содержащихся в заданной папке.
            // Передав ему путь к папке со звуками, вы получите информацию обо всех файлах .wav в этой папке.
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found ======  " + soundNames[0]);
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
        // из списка имен файлов, содержащихся в заданной папке, строится список объектов Sound
        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }

        }
    }

    // метод load(Sound) для загрузки Sound в SoundPool
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        // записываем ID объекта sound
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
