package posgraducao.lamfsistemas.com.br.listviewexample;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Leonardo on 22/07/2017.
 */

public class FilmeAdpter extends ArrayAdapter<Filme> {

    public FilmeAdpter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Filme> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Filme  filme = getItem(position);
        if(convertView == null)
        {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_filme,parent,false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView txtYear = (TextView) convertView.findViewById(R.id.txtYear);
        ImageView imageUrl = (ImageView) convertView.findViewById(R.id.imgUrl);


        txtTitle.setText(filme.getTitle());
        txtYear.setText(filme.getYear());

        Picasso.with(getContext()).load(filme.getUrlImg()).into(imageUrl);

        return convertView;
    }
}
