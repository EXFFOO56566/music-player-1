package com.tochy.music.instances.section;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tochy.music.R;
import com.tochy.music.utils.Themes;
import com.tochy.music.view.EnhancedAdapters.EnhancedViewHolder;
import com.tochy.music.view.EnhancedAdapters.HeterogeneousAdapter;
import com.tochy.music.view.MaterialProgressDrawable;

public class LoadingSingleton extends HeterogeneousAdapter.SingletonSection<Void> {

    public static final int ID = 79;

    public LoadingSingleton(Void data) {
        super(ID, data);
    }

    @Override
    public EnhancedViewHolder<Void> createViewHolder(HeterogeneousAdapter adapter,
                                                     ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instance_loading, parent, false));
    }

    public static class ViewHolder extends EnhancedViewHolder<Void> {

        private MaterialProgressDrawable spinner;

        public ViewHolder(View itemView) {
            super(itemView);
            ImageView spinnerView = (ImageView) itemView.findViewById(R.id.loadingDrawable);
            spinner = new MaterialProgressDrawable(itemView.getContext(), spinnerView);
            spinner.setColorSchemeColors(Themes.getAccent(), Themes.getPrimary());
            spinner.updateSizes(MaterialProgressDrawable.LARGE);
            spinner.setAlpha(255);
            spinnerView.setImageDrawable(spinner);
            spinner.start();
        }

        @Override
        public void update(Void item, int sectionPosition) {
            spinner.stop();
            spinner.start();
        }
    }
}
