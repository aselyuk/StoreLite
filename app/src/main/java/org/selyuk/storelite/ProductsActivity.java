package org.selyuk.storelite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.selyuk.storelite.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    List<Product> products = new ArrayList<>();
    ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        setTitle(R.string.title_products);

        productList = findViewById(R.id.listProducts);

        for (int i = 0; i < 1666; i++){
            products.add(new Product("Яблоко" + i, "Артикул " + i));
            products.add(new Product("Мяско", "666" + i, "32132134694894631"));
            products.add(new Product("Shit"));
        }

        ProductsAdapter productsAdapter = new ProductsAdapter(this, R.layout.list_product_item, products);
        productList.setAdapter(productsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.products_menu, menu);
        return true;
    }
}