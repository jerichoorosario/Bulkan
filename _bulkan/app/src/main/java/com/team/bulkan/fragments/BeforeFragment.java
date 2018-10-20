package com.team.bulkan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.bulkan.MainActivity;
import com.team.bulkan.R;
import com.team.bulkan.adapters.ItemKitAdapter;
import com.team.bulkan.helpers.OnItemKitSelectedListener;
import com.team.bulkan.model.ItemKit;

import java.util.ArrayList;
import java.util.List;

public class BeforeFragment extends Fragment implements OnItemKitSelectedListener {

    private static final String ARG_POSITION = "position";

    private int position;
    private LinearLayout layout;
    private LinearLayout llOnboarding;
    private TextView icon;

    private TextView tvOnboardText;

    private ItemKitAdapter itemKitAdapter, itemKitAdapterB, itemKitAdapterC, itemKitAdapterHouse;
    private RecyclerView rvItemKits,rvItemKitsB,rvItemKitsC, rvHouseItems;
    List<ItemKit> itemKitList;

    public static BeforeFragment newInstance(int position) {
        BeforeFragment f = new BeforeFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_before,
                container, false);
        layout = (LinearLayout) rootView.findViewById(R.id.fragment_wizard_before_layout);
        ViewCompat.setElevation(rootView, 50);

        rvItemKits = (RecyclerView) rootView.findViewById(R.id.rv_item_kits);
        rvItemKitsB = (RecyclerView) rootView.findViewById(R.id.rv_item_kits_b);
        rvItemKitsC = (RecyclerView) rootView.findViewById(R.id.rv_item_kits_c);
        rvHouseItems = (RecyclerView) rootView.findViewById(R.id.rv_house_items);

        rvItemKits.setLayoutManager(new LinearLayoutManager(MainActivity.getInstance()));
        rvItemKits.setHasFixedSize(true);
        rvItemKits.setNestedScrollingEnabled(false);

        rvItemKitsB.setLayoutManager(new LinearLayoutManager(MainActivity.getInstance()));
        rvItemKitsB.setHasFixedSize(true);
        rvItemKitsB.setNestedScrollingEnabled(false);

        rvItemKitsC.setLayoutManager(new LinearLayoutManager(MainActivity.getInstance()));
        rvItemKitsC.setHasFixedSize(true);
        rvItemKitsC.setNestedScrollingEnabled(false);

        rvHouseItems.setLayoutManager(new LinearLayoutManager(MainActivity.getInstance()));
        rvHouseItems.setHasFixedSize(true);
        rvHouseItems.setNestedScrollingEnabled(false);

        itemKitList = new ArrayList<>();
        String[] items = MainActivity.getInstance().getResources().getStringArray(R.array.item_kits_a);
        String[] itemsB = MainActivity.getInstance().getResources().getStringArray(R.array.item_kits_b);
        String[] itemsC = MainActivity.getInstance().getResources().getStringArray(R.array.item_kits_c);
        String[] itemsHouse = MainActivity.getInstance().getResources().getStringArray(R.array.household_items);

        for(int x=0;x < items.length;x++){
            ItemKit ls = new ItemKit(items[x].toString(), false);
            itemKitList.add(ls);
        }
        itemKitAdapter = new ItemKitAdapter(itemKitList, this);
        rvItemKits.setAdapter(itemKitAdapter);

        itemKitList = new ArrayList<>();
        for(int x=0;x < itemsB.length;x++){
            ItemKit ls = new ItemKit(itemsB[x].toString(), false);
            itemKitList.add(ls);
        }

        itemKitAdapterB = new ItemKitAdapter(itemKitList, this);
        rvItemKitsB.setAdapter(itemKitAdapterB);

        itemKitList = new ArrayList<>();
        for(int x=0;x < itemsC.length;x++){
            ItemKit ls = new ItemKit(itemsC[x].toString(), false);
            itemKitList.add(ls);
        }
        itemKitAdapterC = new ItemKitAdapter(itemKitList, this);
        rvItemKitsC.setAdapter(itemKitAdapterC);

        itemKitList = new ArrayList<>();
        for(int x=0;x < itemsHouse.length;x++){
            ItemKit ls = new ItemKit(itemsHouse[x].toString(), false);
            itemKitList.add(ls);
        }
        itemKitAdapterHouse = new ItemKitAdapter(itemKitList, this);
        rvHouseItems.setAdapter(itemKitAdapterHouse);


        return rootView;
    }

    @Override
    public void onItemSelected(ItemKit itemKit) {

    }
}
