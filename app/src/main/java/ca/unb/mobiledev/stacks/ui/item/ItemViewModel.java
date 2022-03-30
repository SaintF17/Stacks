package ca.unb.mobiledev.stacks.ui.item;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.unb.mobiledev.stacks.entity.Item;
import ca.unb.mobiledev.stacks.repository.ItemRepository;

public class ItemViewModel extends AndroidViewModel {
    private final ItemRepository itemRepository;
    private LiveData<List<Item>> items;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        items = updateItemsList();
    }

    public LiveData<List<Item>> updateItemsList() {
        return itemRepository.listAllItems();
    }

    public void insert(String name, int num) {
        itemRepository.insertRecord(name, num);
    }

    public List<Item> searchItemsList(String name) {
        return itemRepository.searchRecords(name);
    }

}