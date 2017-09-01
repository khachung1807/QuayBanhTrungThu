package com.example.do_khac_hung.cuahangbanhtrungthu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.do_khac_hung.cuahangbanhtrungthu.R;
import com.example.do_khac_hung.cuahangbanhtrungthu.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DO_KHAC_HUNG on 8/31/2017.
 */

public class LoaiSanPhamAdapter extends BaseAdapter {
    ArrayList<LoaiSanPham> arrayListLoaiSanPham;
    Context context;

    public LoaiSanPhamAdapter(ArrayList<LoaiSanPham> arrayListLoaiSanPham, Context context) {
        this.arrayListLoaiSanPham = arrayListLoaiSanPham;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSanPham.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListLoaiSanPham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        TextView textTenLoaiSanPham;
        ImageView imageViewLoaiSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_loai_san_pham, null);
            viewHolder.textTenLoaiSanPham = view.findViewById(R.id.text_item_loai_san_pham);
            viewHolder.imageViewLoaiSanPham = view.findViewById(R.id.image_view_item_loai_san_pham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiSanPham loaiSanPham = (LoaiSanPham) getItem(i);
        viewHolder.textTenLoaiSanPham.setText(loaiSanPham.getTenLoaiSanPham());
        Picasso.with(context).load(loaiSanPham.getHinhAnhLoaiSanPham())
                .placeholder(R.drawable.image_camera)
                .error(R.drawable.error)
                .into(viewHolder.imageViewLoaiSanPham);
        return view;
    }
}
