package org.selyuk.storelite;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selyuk.storelite.models.Product;

import java.io.FileInputStream;
import java.util.List;

public class Exchange {

    private static FileFormats getFileFormat(String fileName) {
        if (fileName.endsWith(FileFormats.JSON.getExtension()))
            return FileFormats.JSON;
        else if (fileName.endsWith(FileFormats.XML.getExtension()))
            return FileFormats.XML;
        else if (fileName.endsWith(FileFormats.TXT.getExtension()))
            return FileFormats.TXT;
        else
            return FileFormats.UNSUPPORTED;
    }

    public static void UploadProducts(String fileName, ProductsAdapter adapter) {
        FileFormats fileFormat = getFileFormat(fileName);
        switch (fileFormat) {
            case TXT:
                //txt
                break;
            case XML:
                // xml
                break;
            case JSON:
                UploadProductsFromJSON(fileName, adapter);
                break;
            default:
                // unsupported file format
        }

    }

    private static void UploadProductsFromJSON(String fileName, ProductsAdapter adapter) {
        String jString = null;

        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            byte[] bytes = new byte[inputStream.available()];
            int n = inputStream.read(bytes);
            if (bytes.length != n) {
                // прочитано не все... что-то пошло не так
                return;
            }
            jString = new String(bytes);
        } catch (Exception ex) {
            Log.d("ERROR", ex.getMessage());
        }

        if (jString == null)
            return;

        try {
            JSONObject jObj = new JSONObject(jString);
            JSONObject jStore = jObj.getJSONObject("Склад");
            String storeName = jStore.getString("Наименование");

            JSONArray jProducts = jObj.getJSONArray("Номенклатура");
            int len = jProducts.length();

            if (len == 0)
                return;

            for (int i = 0; i < len; i++) {
                JSONObject jCurObj = (JSONObject) jProducts.get(i);
                String article = jCurObj.getString("Артикул");
                String name = jCurObj.getString("Наименование");
                String GUID = jCurObj.getString("GUID");

                boolean has = !Product.find(Product.class, "outer_guid = ?", GUID).isEmpty();
                if (has)
                    continue;

                Product product = new Product(name, article, GUID);
                product.save();
                adapter.add(product);
            }

            int i = 0;
        } catch (JSONException ex) {
            Log.d("JSON_ERROR", ex.getMessage());
        }
    }

}
