package com.tochy.music.instances.viewholder;

import android.view.MenuItem;
import android.view.View;

import com.tochy.music.PlayerController;
import com.tochy.music.R;
import com.tochy.music.activity.instance.AlbumActivity;
import com.tochy.music.activity.instance.ArtistActivity;
import com.tochy.music.instances.AutoPlaylist;
import com.tochy.music.instances.Library;
import com.tochy.music.instances.Playlist;
import com.tochy.music.instances.PlaylistDialog;
import com.tochy.music.instances.Song;
import com.tochy.music.utils.Navigate;

import java.util.List;

public class PlaylistSongViewHolder extends DragDropSongViewHolder {

    private OnRemovedListener removedListener;
    private boolean isReferenceAuto;

    public PlaylistSongViewHolder(View itemView, List<Song> playlistEntries, Playlist reference,
                                  OnRemovedListener listener) {
        super(itemView, playlistEntries,
                (reference instanceof AutoPlaylist)
                        ? R.array.edit_auto_playlist_options
                        : R.array.edit_playlist_options);
        this.removedListener = listener;
        this.isReferenceAuto = reference instanceof AutoPlaylist;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 0: //Queue this song next
                PlayerController.queueNext(reference);
                return true;
            case 1: //Queue this song last
                PlayerController.queueLast(reference);
                return true;
            case 2: //Go to artist
                Navigate.to(
                        itemView.getContext(),
                        ArtistActivity.class,
                        ArtistActivity.ARTIST_EXTRA,
                        Library.findArtistById(reference.getArtistId()));
                return true;
            case 3: // Go to album
                Navigate.to(
                        itemView.getContext(),
                        AlbumActivity.class,
                        AlbumActivity.ALBUM_EXTRA,
                        Library.findAlbumById(reference.getAlbumId()));
                return true;
            case 4:
                if (isReferenceAuto) { // Add to playlist
                    PlaylistDialog.AddToNormal.alert(itemView, reference,
                            itemView.getResources().getString(
                                    R.string.header_add_song_name_to_playlist, reference));
                } else { // Remove
                    removedListener.onItemRemoved(getAdapterPosition());
                }
                return true;
        }
        return false;
    }

}
