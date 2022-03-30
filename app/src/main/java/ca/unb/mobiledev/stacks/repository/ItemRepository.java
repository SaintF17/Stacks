package ca.unb.mobiledev.stacks.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ca.unb.mobiledev.stacks.dao.ItemDao;
import ca.unb.mobiledev.stacks.db.AppDatabase;
import ca.unb.mobiledev.stacks.entity.Item;

public class ItemRepository {
    private final ItemDao itemDao;

    public ItemRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        itemDao = db.itemDao();
    }


    public LiveData<List<Item>> listAllItems() {
        return itemDao.listAllItems();
    }

    public void insertRecord(String name, float price) {
        Item newItem = new Item();
        newItem.setName(name);
        newItem.setPrice(price);

        insertRecord(newItem);
    }

    private void insertRecord(final Item item) {
        AppDatabase.databaseWriterExecutor.execute(() -> itemDao.insert(item));
    }

    public List<Item> searchRecords(String name) {
        Future <List<Item>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Item>>() {
            public List<Item> call() {
                return itemDao.searchItems(name);
            }
        });
        List<Item> items = null;
        try {
            items = future.get();
        } catch(ExecutionException e) {
            System.out.println("Something went wrong");
        } catch(InterruptedException e) {
            System.out.println("Something went wrong");
        }
        return items;
    }

}
