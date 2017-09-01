package com.example.do_khac_hung.cuahangbanhtrungthu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.do_khac_hung.cuahangbanhtrungthu.R;
import com.example.do_khac_hung.cuahangbanhtrungthu.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by DO_KHAC_HUNG on 9/1/2017.
 */

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arrayListSanPham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListSanPham) {
        this.context = context;
        this.arrayListSanPham = arrayListSanPham;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham_moi, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        SanPham sanPham = arrayListSanPham.get(position);
        holder.textViewTenSanPham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewGiaSanPham.setText("Giá : " + decimalFormat.format(sanPham.getGiaSanPham()) + "Đ");
        Picasso.with(context).load(sanPham.getHinhAnhSanPham())
                .placeholder(R.drawable.image_camera)
                .error(R.drawable.error)
                .into(holder.imageViewHinhAnhSanPham);
    }

    @Override
    public int getItemCount() {
        return arrayListSanPham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewHinhAnhSanPham;
        public TextView textViewTenSanPham, textViewGiaSanPham;

        public ItemHolder(View itemView) {
            super(itemView);
            imageViewHinhAnhSanPham = itemView.findViewById(R.id.image_view_item_san_pham);
            textViewGiaSanPham = itemView.findViewById(R.id.text_view_item_gia_san_pham);
            textViewTenSanPham = itemView.findViewById(R.id.text_view_item_ten_san_pham);
        }
    }

}
