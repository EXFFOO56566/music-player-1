package com.tochy.music.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tochy.music.R;
import com.tochy.music.instances.Library;
import com.tochy.music.instances.section.LibraryEmptyState;
import com.tochy.music.instances.section.SongSection;
import com.tochy.music.utils.Themes;
import com.tochy.music.view.BackgroundDecoration;
import com.tochy.music.view.DividerDecoration;
import com.tochy.music.view.EnhancedAdapters.HeterogeneousAdapter;

public class SongFragment extends Fragment implements Library.LibraryRefreshListener {

    private HeterogeneousAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);

        int paddingH = (int) getActivity().getResources().getDimension(R.dimen.global_padding);
        view.setPadding(paddingH, 0, paddingH, 0);

        adapter = new HeterogeneousAdapter();
        adapter.addSection(new SongSection(Library.getSongs()));
        adapter.setEmptyState(new LibraryEmptyState(getActivity()));

        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        list.addItemDecoration(new BackgroundDecoration(Themes.getBackgroundElevated()));
        list.addItemDecoration(new DividerDecoration(getActivity(), R.id.empty_layout));
        list.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Library.addRefreshListener(this);
        // Assume this fragment's data has gone stale since it was last in the foreground
        onLibraryRefreshed();
    }

    @Override
    public void onPause() {
        super.onPause();
        Library.removeRefreshListener(this);
    }

    @Override
    public void onLibraryRefreshed() {
        adapter.notifyDataSetChanged();
    }
}
