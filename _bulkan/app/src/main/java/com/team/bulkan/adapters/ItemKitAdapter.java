package com.team.bulkan.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.bulkan.R;
import com.team.bulkan.helpers.OnItemKitSelectedListener;
import com.team.bulkan.model.ItemKit;

import java.util.List;

public class ItemKitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ItemKit> stateList;
    private boolean settings;

    private OnItemKitSelectedListener onStateSelectedListener;

    public ItemKitAdapter(List<ItemKit> itemList, OnItemKitSelectedListener onStateSelectedListener) {
        this.stateList = itemList;
        this.onStateSelectedListener = onStateSelectedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemKitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        final ItemKitViewHolder svh = (ItemKitViewHolder) holder;
        final ItemKit itemKit = stateList.get(position);
        svh.cbState.clearFocus();
        svh.tvItemTitle.setText(itemKit.getItemKitTitle());
        svh.cbState.setOnCheckedChangeListener(null);
        svh.cbState.setChecked(itemKit.getChecked());
        svh.cbState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    itemKit.setChecked(true);
                }else{
                    itemKit.setChecked(false);
                }
                onStateSelectedListener.onItemSelected(itemKit);
            }
        });
        svh.cbState.setChecked(itemKit.getChecked());


    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }


    public static class ItemKitViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llItemHolder;
        private CheckBox cbState;
        private TextView tvItemTitle;

        public ItemKitViewHolder(View v) {
            super(v);
            llItemHolder = (LinearLayout) v.findViewById(R.id.ll_item_holder);
            cbState = (CheckBox) v.findViewById(R.id.chck_item);
            tvItemTitle = (TextView) v.findViewById(R.id.tv_item_title);
            tvItemTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cbState.isChecked()){
                        cbState.setChecked(true);
                    }else{
                        cbState.setChecked(false);
                    }

                }
            });
        }
    }
}
