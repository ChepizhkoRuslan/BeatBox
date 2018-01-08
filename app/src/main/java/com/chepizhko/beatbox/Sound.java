package com.chepizhko.beatbox;

public class Sound {
    private static final String TAG = "Sound";
    private String mAssetPath;
    private String mName;
    // тип Integer вместо int позволяет представить неопределенное состояние Sound —
    // для этого mSoundId присваивается null.
    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }
    public String getAssetPath() {
        return mAssetPath;
    }
    public String getName() {
        return mName;
    }
    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}