package com.example.genji.am101_db;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by genji on 3/26/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    // my data
    private List<Product> productList;
    // Define listener member variable
    private static OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductAdapter(List<Product> products) {
        productList = products;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public Product getProduct(int position){
        return productList.get(position);
    }

    public void add(Product product, int position) {
        position = (position == -1 ? getItemCount()  : position);
        productList.add(position, product);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void update(int position, String description){
        if (position < getItemCount()  ) {
            Product updated = productList.get(position);
            updated.setDescription(description);
            updated.setUpdated(1);
            notifyItemChanged(position);
        }
    }

    @Override
    public void onBindViewHolder(ProductViewHolder pvh, int i) {
        Product p = productList.get(i);
        pvh.vName.setText(p.getName());
        pvh.vDescription.setText(p.getDescription());
        pvh.vUpdated.setChecked(p.getUpdated() == 1 ? true : false);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_product, viewGroup, false);

        return new ProductViewHolder(itemView);
    }


    // you provide access to all the views for a data item in a view holder
    public static class ProductViewHolder extends RecyclerView.ViewHolder
          //  implements View.OnClickListener
    {
        protected TextView vName;
        protected TextView vDescription;
        protected CheckBox vUpdated;

        public ProductViewHolder(final View itemView) {
            super(itemView);
            vName = (TextView) itemView.findViewById(R.id.name);
            vDescription = (TextView) itemView.findViewById(R.id.description);
            vUpdated = (CheckBox) itemView.findViewById(R.id.updated);
            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}


