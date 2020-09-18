package org.selyuk.storelite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selyuk.storelite.models.Product;

import java.util.List;

public class ProductsAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;

    public ProductsAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);

        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imangeView = (ImageView) view.findViewById(R.id.image);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView articleView = (TextView) view.findViewById(R.id.articleNumber);

        Product product = products.get(position);

        nameView.setText(product.getName());
        articleView.setText(product.getArticleNumber());

        return view;
    }
}
