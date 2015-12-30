package com.raizlabs.folio.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.raizlabs.folio.LineFolio;
import com.raizlabs.folio.SimpleTextFolio;
import com.raizlabs.folio.SlidingTabFolio;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_SIZE = 12;

    private String[] urls;

    private ViewPager viewPager;
    private ViewFlipper folioFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        folioFlipper = (ViewFlipper) findViewById(R.id.folioFlipper);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        LineFolio lineFolio = (LineFolio) findViewById(R.id.lineFolio);
        SimpleTextFolio textFolio = (SimpleTextFolio) findViewById(R.id.textFolio);
        SlidingTabFolio slidingTabFolio = (SlidingTabFolio) findViewById(R.id.slidingTabFolio);

        populateViewPager();

        lineFolio.setViewPager(viewPager);
        textFolio.setViewPager(viewPager);
        slidingTabFolio.setViewPager(viewPager);

        final List<FolioType> folioTypes = new ArrayList<>();
        folioTypes.add(new FolioType(folioFlipper.indexOfChild(lineFolio), "LineFolio"));
        folioTypes.add(new FolioType(folioFlipper.indexOfChild(textFolio), "SimpleTextFolio"));
        folioTypes.add(new FolioType(folioFlipper.indexOfChild(slidingTabFolio), "SlidingTabFolio"));

        populateSpinner(folioTypes);
    }

    private void populateViewPager() {
        urls = new String[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            urls[i] = "http://thecatapi.com/api/images/get?format=src&cacheBust=" + i;
        }
        viewPager.setAdapter(new ImageAdapter(getSupportFragmentManager(), urls));
    }

    private void populateSpinner(final List<FolioType> folioTypes) {
        final Spinner folioSpinner = (Spinner) findViewById(R.id.folioSpinner);
        final FolioTypeAdapter folioAdapter = new FolioTypeAdapter(folioTypes);
        folioSpinner.setAdapter(folioAdapter);
        folioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectFolioType(folioTypes.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void selectFolioType(FolioType type) {
        folioFlipper.setDisplayedChild(type.flipperIndex);
    }

    private static class FolioType {
        int flipperIndex;
        String title;

        public FolioType(int flipperIndex, String title) {
            this.flipperIndex = flipperIndex;
            this.title = title;
        }
    }

    private class FolioTypeAdapter extends BaseAdapter {

        private List<FolioType> folioTypes;

        public FolioTypeAdapter(List<FolioType> folioTypes) {
            this.folioTypes = folioTypes;
        }

        @Override
        public int getCount() {
            return folioTypes.size();
        }

        @Override
        public Object getItem(int position) {
            return folioTypes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return folioTypes.get(position).flipperIndex;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text, parent, false);
            }

            FolioType type = folioTypes.get(position);
            ((TextView) convertView.findViewById(R.id.list_item_text_text)).setText(type.title);

            return convertView;
        }
    }
}
