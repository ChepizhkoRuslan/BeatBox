package com.chepizhko.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class SoundViewModelTest {
    // фиктивный объект (замокать)
    private BeatBox mBeatBox;
    // Sound — простой объект данных, не обладающий поведением, поэтому для него
    // фиктивный объект можно не создавать — это не несет в себе ни малейшего риска
    private Sound mSound;
    // тестируется объект mSubject (а все остальные — нет)
    private SoundViewModel mSubject;
    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }
    // тест, который проверяет существующее поведение в SoundViewModel:
    // свойство getTitle() связывается со свойством Sound getName() объекта Sound
    @Test
    public void exposesSoundNameAsTitle() {
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }
    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();
        // вызов verify(…) означает: «Проверить, что метод play(…) был вызван для mBeatBox с передачей mSound в параметре»
        verify(mBeatBox).play(mSound);
        //  то же самое
//        verify(mBeatBox);
//        mBeatBox.play(mSound);
    }

}