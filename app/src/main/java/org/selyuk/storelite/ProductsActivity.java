package org.selyuk.storelite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.selyuk.storelite.models.Product;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    List<Product> products = new ArrayList<>();
    ListView productList;
    ProductsAdapter productsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        setTitle(R.string.title_products);

        productList = findViewById(R.id.listProducts);

        products = Product.listAll(Product.class);

        productsAdapter = new ProductsAdapter(this, R.layout.list_product_item, products);
        productList.setAdapter(productsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.products_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.mProductImport:
                showFileChooser();
                return true;
            case R.id.mProductExport:
                return true;
            case R.id.mProductDelete:
                Product.deleteAll(Product.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String id = DocumentsContract.getDocumentId(uri).split(":")[1];
                String FileName = Environment.getExternalStorageDirectory()+"/"+id;
                Exchange.UploadProducts(FileName, productsAdapter);
                productsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("file/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"application/*", "text/*", "application/octet-stream"});
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }
}