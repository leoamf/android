package com.example.lmachadodefreitas.architecturesample.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lmachadodefreitas.architecturesample.R;
import com.example.lmachadodefreitas.architecturesample.model.Species;
import java.util.List;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class SpeciesAdapter extends ArrayAdapter<Species.Specie> {

    public SpeciesAdapter(@NonNull Context context, @NonNull List<Species.Specie> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Species.Specie obj = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item, parent, false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        txtTitle.setText(obj.name);

        return convertView;
    }
}
