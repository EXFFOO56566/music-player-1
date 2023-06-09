package com.tochy.music.activity.instance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tochy.music.R;
import com.tochy.music.activity.BaseActivity;
import com.tochy.music.instances.Genre;
import com.tochy.music.instances.Library;
import com.tochy.music.instances.Song;
import com.tochy.music.instances.section.LibraryEmptyState;
import com.tochy.music.instances.section.SongSection;
import com.tochy.music.utils.Themes;
import com.tochy.music.view.BackgroundDecoration;
import com.tochy.music.view.DividerDecoration;
import com.tochy.music.view.EnhancedAdapters.HeterogeneousAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends BaseActivity {

    public static final String GENRE_EXTRA = "genre";
    private Genre reference;
    private List<Song> data;
    private HeterogeneousAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);

        reference = getIntent().getParcelableExtra(GENRE_EXTRA);

        if (reference != null) {
            data = Library.getGenreEntries(reference);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(reference.getGenreName());
            }
        } else {
            data = new ArrayList<>();
        }

        adapter = new HeterogeneousAdapter();
        adapter.addSection(new SongSection(data));
        adapter.setEmptyState(new LibraryEmptyState(this) {
            @Override
            public String getEmptyMessage() {
                if (reference == null) {
                    return getString(R.string.empty_error_genre);
                } else {
                    return super.getEmptyMessage();
                }
            }

            @Override
            public String getEmptyMessageDetail() {
                if (reference == null) {
                    return "";
                } else {
                    return super.getEmptyMessageDetail();
                }
            }

            @Override
            public String getEmptyAction1Label() {
                return "";
            }
        });

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.addItemDecoration(new BackgroundDecoration(Themes.getBackgroundElevated()));
        list.addItemDecoration(new DividerDecoration(this, R.id.empty_layout));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(layoutManager);
    }
}
