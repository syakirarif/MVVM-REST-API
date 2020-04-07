package com.arifudesu.mvvmrestapi.util.navigation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arifudesu.mvvmrestapi.R;
import com.bumptech.glide.Glide;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


/**
 * Created by miki on 7/7/17.
 */

public class TitleViewHolder extends GroupViewHolder{
    private TextView titleView;
    private String titleString;
    private ImageView arrow;
    private ImageView icon;
    NavMenuAdapter adapter;

    public TitleViewHolder(View itemView, NavMenuAdapter adapter) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.nav_menu_item_icon);
        titleView = (TextView) itemView.findViewById(R.id.nav_menu_item_title);
        arrow = (ImageView) itemView.findViewById(R.id.nav_menu_item_arrow);
        this.adapter = adapter;
    }

    public void setGenreTitle(Context context, ExpandableGroup title) {
        if (title instanceof TitleMenu) {
            if (((TitleMenu) title).getImageResource() != 0){
                Glide.with(context)
                        .load(((TitleMenu) title).getImageResource())
                        .into(icon);
            }

            titleString = title.getTitle();
            titleView.setText(title.getTitle());

            if (title.getItems().size() > 0){
                arrow.setVisibility(View.VISIBLE);

                boolean isExpand = false;
                for (int i = 0; i < adapter.isExpandList.size(); i++) {
                    if (titleString.equals(adapter.isExpandList.get(i))){
                        isExpand = true;
                        break;
                    }
                }
                if (isExpand) {
                    arrow.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    arrow.setImageResource(R.drawable.ic_arrow_down);
                }
            }else{
                arrow.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void expand() {
        adapter.isExpandList.add(titleString);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void collapse() {
        adapter.isExpandList.remove(titleString);
        adapter.notifyDataSetChanged();
    }
}
