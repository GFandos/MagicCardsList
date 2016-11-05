package com.example.a47989768s.magiccardslist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_cards_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.refresh) {

            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        RefreshAsyncTask refreshAsyncTask = new RefreshAsyncTask();

        refreshAsyncTask.execute();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView cardsList = (ListView) view.findViewById(R.id.cardsList);

        String [] data = {"Card example 1", "Card example 2", "Card example 3", "Card example 4", "Card example 5", "Card example 6", "Card example 7"};
        items = new ArrayList<>(Arrays.asList(data));


        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.cards_rows,
                R.id.cardName,
                items
        );

        cardsList.setAdapter(adapter);

        return view;


    }

    private class RefreshAsyncTask extends AsyncTask<Void, Void, ArrayList<Card>> {

        @Override
        protected ArrayList<Card> doInBackground(Void... voids) {
            MagicTGGetAllCardsApi api = new MagicTGGetAllCardsApi();

            ArrayList<Card> cards = api.getCards();

            for(int i = 0; i < cards.size(); ++i) {
                Log.d("DEBUG", cards.get(i).toString());
            }

            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {

            adapter.clear();

            for(int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i).getName());
            }
        }
    }

}
