package com.chepizhko.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    // Для обращения к активам используется класс AssetManager
    // конструктор, который получает Context, извлекает AssetManager и сохраняет на будущее
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
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
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);

            Log.i(TAG, "List<Sound> mSounds ======  " + sound.getAssetPath() );

        }
    }
    public List<Sound> getSounds() {
        return mSounds;
    }
}
