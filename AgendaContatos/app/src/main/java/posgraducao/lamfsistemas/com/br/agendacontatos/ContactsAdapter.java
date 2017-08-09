package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leonardo on 05/08/2017.
 */

public class ContactsAdapter extends ArrayAdapter<Contact>{


    public ContactsAdapter(Context context, List<Contact> contacts) {
        super(context,0,contacts);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact obj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.adp_contacts, parent, false);
        }

        TextView txtName = (TextView)convertView.findViewById(R.id.txtName );
        TextView txtFone = (TextView)convertView.findViewById(R.id.txtFone);
        TextView txtId = (TextView)convertView.findViewById(R.id.txtId);

        if(obj!=null) {
            txtId.setText(String.valueOf(  obj.id));
            txtName.setText(obj.name);
            txtFone.setText(obj.fone);
        }



        return convertView;
    }
}
