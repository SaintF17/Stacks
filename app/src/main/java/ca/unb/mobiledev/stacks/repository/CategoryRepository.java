package ca.unb.mobiledev.stacks.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ca.unb.mobiledev.stacks.dao.CategoryDao;
import ca.unb.mobiledev.stacks.db.AppDatabase;
import ca.unb.mobiledev.stacks.entity.Category;

public class CategoryRepository {
    private final CategoryDao categoryDao;

    public CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
    }


    public LiveData<List<Category>> listAllCategories() {
        return categoryDao.listAllCategories();
    }

    public void insertRecord(String name, float budgetAmount) {
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setBudgetAmount(budgetAmount);

        insertRecord(newCategory);
    }

    private void insertRecord(final Category category) {
        AppDatabase.databaseWriterExecutor.execute(() -> categoryDao.insertRecord(category));
    }

    public List<Category> searchRecords(String name) {
        Future <List<Category>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Category>>() {
            public List<Category> call() {
                return categoryDao.searchCategories(name);
            }
        });
        List<Category> categories = null;
        try {
            categories = future.get();
        } catch(ExecutionException e) {
            System.out.println("Something went wrong");
        } catch(InterruptedException e) {
            System.out.println("Something went wrong");
        }
        return categories;
    }
}
