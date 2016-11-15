package com.example.a47989768s.magiccardslist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.databinding.DataBindingUtil;

import com.example.a47989768s.magiccardslist.databinding.FragmentDetailBinding;
import com.example.a47989768s.magiccardslist.databinding.FragmentMainBinding;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Card> items;
    private CardsAdapter adapter;
    private FragmentMainBinding binding;

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

    @Override
    public void onStart() {
        super.onStart();
        refresh();
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

        binding = DataBindingUtil.inflate(inflater,  R.layout.fragment_main, container, false);
        View view = binding.getRoot();

        items = new ArrayList<>();

        adapter = new CardsAdapter(
                getContext(),
                R.layout.cards_rows,
                items
        );

        binding.cardsList.setAdapter(adapter);

        binding.cardsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Card card = (Card) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("card", card);

                startActivity(intent);
            }

        });

        return view;


    }

    private class RefreshAsyncTask extends AsyncTask<Void, Void, ArrayList<Card>> {

        @Override
        protected ArrayList<Card> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String rarity = preferences.getString("rarity", "");
            String color = preferences.getString("color", "");

            MagicTGGetAllCardsApi api = new MagicTGGetAllCardsApi();

            ArrayList<Card> cards = api.getCards(rarity, color);

            for(int i = 0; i < cards.size(); ++i) {
                Log.d("DEBUG", cards.get(i).toString());
            }

            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {

            adapter.clear();

            for(int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i));
            }
        }
    }

}
