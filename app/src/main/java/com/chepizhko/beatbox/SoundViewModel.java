package com.chepizhko.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;
    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }
    // метод для получения названия, которое должно отображаться на кнопке
    @Bindable
    public String getTitle() {
        return mSound.getName();
    }
    public Sound getSound() {
        return mSound;
    }
    public void setSound(Sound sound) {
        mSound = sound;
        // метод notifyChange() оповещает класс привязки о том, что все Bindable-поля ваших объектов были обновлены.
        // Класс привязки выполняет код внутри { } для повторного заполнения представления.
        // Таким образом, при вызове setSound(Sound) объект ListItemSoundBinding получит уведомление и
        // вызовет Button.setText(String),как указано в файле list_item_sound.xml.
        notifyChange();
    }
}
