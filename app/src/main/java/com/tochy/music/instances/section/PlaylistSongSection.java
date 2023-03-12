package com.tochy.music.instances.section;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tochy.music.R;
import com.tochy.music.instances.Library;
import com.tochy.music.instances.Playlist;
import com.tochy.music.instances.Song;
import com.tochy.music.instances.viewholder.DragDropSongViewHolder;
import com.tochy.music.instances.viewholder.PlaylistSongViewHolder;
import com.tochy.music.view.EnhancedAdapters.EnhancedViewHolder;
import com.tochy.music.view.EnhancedAdapters.HeterogeneousAdapter;

import java.util.List;

public class PlaylistSongSection extends EditableSongSection {

    public static final int ID = 720;

    private Context mContext;
    private DragDropSongViewHolder.OnRemovedListener mRemovedListener;
    private Playlist mReference;

    public PlaylistSongSection(List<Song> data, Context context,
                               DragDropSongViewHolder.OnRemovedListener onRemovedListener,
                               Playlist reference) {
        super(ID, data);
        mContext = context;
        mRemovedListener = onRemovedListener;
        mReference = reference;
    }

    @Override
    protected void onDrop(int from, int to) {
        if (from == to) return;

        Library.editPlaylist(mContext, mReference, mData);
    }

    @Override
    public EnhancedViewHolder<Song> createViewHolder(final HeterogeneousAdapter adapter,
                                                     ViewGroup parent) {
        return new PlaylistSongViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.instance_song_drag, parent, false),
                mData,
                mReference,
                mRemovedListener);
    }
}
