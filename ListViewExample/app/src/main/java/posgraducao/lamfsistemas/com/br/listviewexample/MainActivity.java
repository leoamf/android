package posgraducao.lamfsistemas.com.br.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView  listView = (ListView)findViewById(R.id.lstFilmes);

        List<Filme> filmeList = new ArrayList<>();

        filmeList.add(new Filme("Teste 01","2012","http://i.imgur.com/DvpvklR.png"));
        filmeList.add(new Filme("Teste 02","2002","http://i.imgur.com/DvpvklR.png"));
        filmeList.add(new Filme("Teste 03","2015","http://i.imgur.com/DvpvklR.png"));

        FilmeAdpter filmeAdpter = new FilmeAdpter( getApplicationContext(),R.layout.item_filme,filmeList);

        listView.setAdapter(filmeAdpter);

    }
}
