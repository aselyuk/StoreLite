package org.selyuk.storelite;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.telephony.emergency.EmergencyNumber;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.selyuk.storelite.models.Product;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String FileName = "";

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
                File file = new File(uri.getPath());//create path from uri
                String path = file.getAbsolutePath();
                final String[] split = file.getPath().split(":");//split the path.
                FileName = Environment.getExternalStorageDirectory() + "/" +
                        Environment.DIRECTORY_DOCUMENTS + "/" +
                        split[1];
                requestMultiplePermissions();
            }
        }
    }

    public void requestMultiplePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // проверка по запрашиваемому коду
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // разрешение успешно получено
                Exchange.UploadProducts(FileName, productsAdapter);
                productsAdapter.notifyDataSetChanged();
            } else {
                // разрешение не получено
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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