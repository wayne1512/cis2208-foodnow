package com.example.foodnow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodnow.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MutableLiveData<String> searchString = new MutableLiveData<>("");

    MainActivityViewModel vm;


    MenuItem menuFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_discover, R.id.navigation_fav, R.id.navigation_random)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchViewItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String query) {

                //update the search query from the view model
                vm.setSearchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //update the search query from the view model
                vm.setSearchQuery("");
                return false;
            }
        });

        menuFilterButton = menu.findItem(R.id.filterButton);
        updateFilterButton();


        return super.onCreateOptionsMenu(menu);
    }

    private void updateFilterButton() {

        int iconResource;

        if (vm.getFilter() != null && !vm.getFilter().requiredFoodTypes.isEmpty())
            iconResource = R.drawable.ic_filter_icon_activated_24dp;
        else
            iconResource = R.drawable.ic_filter_icon_24dp;

        menuFilterButton.setIcon(iconResource);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.filterButton) {

            //open filters
            Intent intent = new Intent(this, FiltersActivity.class);
            //pass the current filter to the activity, so that the activity can edit the current filter instead of starting a new one.
            intent.putExtra("filter", vm.getFilter());
            filterActivityResultLauncher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    ActivityResultLauncher<Intent> filterActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //get the data from the filters activity and set it on the vm
                        Intent data = result.getData();
                        Filter f = null;
                        if (data != null) {
                            f = (Filter) data.getSerializableExtra("filter");
                        }
                        vm.setFilter(f);
                        //update the icon of the filter button as it may require a change
                        updateFilterButton();
                    }
                }
            });

}