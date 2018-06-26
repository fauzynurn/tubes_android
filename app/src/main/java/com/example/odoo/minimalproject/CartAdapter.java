package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/29/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartItemHolder> {
    private List<Menu> cartList;
    public List<Menu> menuListForFirebase = new ArrayList<>();

    public CartAdapter(List<Menu> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);

        return new CartItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartItemHolder holder, int position) {
        Menu item = cartList.get(position);
        holder.id.setText(item.id);
        holder.cartMenu.setText(item.meal);
        holder.totprice.setText(item.concatedPrice);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void addMenu(Menu menu, int index){
        cartList.add(menu);
        menuListForFirebase.add(menu);
        notifyItemInserted(index);
        if(findFood()){
            int indexMenu = findMenu("Teh Manis");
            if(indexMenu != -1){
                cartList.get(indexMenu).price = 1;
                cartList.get(indexMenu).concatedPrice = "1k";
            }
        }
    }

    public List<Menu> getCartList(){
        return cartList;
    }

    public int countTotalPrice(CartAdapter ca){
        int total = 0;
        for(Menu menu : ca.getCartList()){
            total = total + menu.price;
        }
        return total;
    }

    public boolean findFood(){
        for(Menu menu : cartList){
            if(menu.jenis.equals("f")){
                return true;
            }
        }
        return false;
    }

    public int findMenu(String x){
        for(int i = 0; i < cartList.size(); i++){
            if(cartList.get(i).meal.equals(x)){
                return i;
            }
        }
        return -1;
    }
}
