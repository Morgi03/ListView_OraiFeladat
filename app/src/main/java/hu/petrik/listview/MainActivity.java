package hu.petrik.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView emberLista;
    private class EmberlistaAdapter extends ArrayAdapter<Ember> {
        private List<Ember> emberek;

        public EmberlistaAdapter(List<Ember> objects) {
            super(MainActivity.this, R.layout.ember_list_item, objects);
            this.emberek = objects;
        }

        @SuppressLint("DefaultLocale")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View lista_elem = inflater.inflate(R.layout.ember_list_item, null);
            TextView nev = lista_elem.findViewById(R.id.nev);
            TextView kor = lista_elem.findViewById(R.id.kor);
            TextView torles = findViewById(R.id.torles);
            nev.setText(this.emberek.get(position).getNev());
            kor.setText(String.format("%3d év", this.emberek.get(position).getEletkor()));
            torles.setOnClickListener(view -> {
                this.emberek.remove(position);
                this.notifyDataSetChanged();
            });
            return lista_elem;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        List<Ember> emberek = new ArrayList<>();
        emberek.add(new Ember("Gipsz Jakab", 25));
        emberek.add(new Ember("Márton Áron", 50));
        emberek.add(new Ember("Morgován Nimród", 20));

        emberLista.setAdapter(new EmberlistaAdapter(emberek));
    }
    private void init(){
       this.emberLista =findViewById(R.id.emberLista);
    }
}