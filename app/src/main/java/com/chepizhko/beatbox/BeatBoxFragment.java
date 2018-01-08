package com.chepizhko.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chepizhko.beatbox.databinding.FragmentBeatBoxBinding;
import com.chepizhko.beatbox.databinding.ListItemSoundBinding;

import java.util.List;


public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Вызов setRetainInstance(true) удерживает фрагмент.
        // Такой фрагмент не уничтожается вместе с активностью, а сохраняется и передается новой активности
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            // В конструкторе создается и присоединяется объект ViewModel
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }
        // в методе bind обновляются данные, с которыми работает ViewModel
        public void bind(Sound sound) {
            mBinding.getViewModel().setSound(sound);
            // Вызывая этот метод, вы приказываете макету обновить себя немедленно вместо того,
            // чтобы ожидать одну-две миллисекунды. Таким образом обеспечивается быстрота реакции RecyclerView
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }
        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }
        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
