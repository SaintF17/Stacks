package ca.unb.mobiledev.stacks.ui.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.unb.mobiledev.stacks.entity.Category;
import ca.unb.mobiledev.stacks.entity.Item;
import ca.unb.mobiledev.stacks.repository.CategoryRepository;

public class CategoryViewModel extends AndroidViewModel {
    private final CategoryRepository categoryRepository;
    private LiveData<List<Category>> categories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        categories = updateItemsList();
    }

    public LiveData<List<Category>> updateItemsList() {
        return categoryRepository.listAllCategories();
    }

    public void insert(String name, int num) {
        categoryRepository.insertRecord(name, num);
    }

    public List<Category> searchItemsList(String name) {
        return categoryRepository.searchRecords(name);
    }

}
