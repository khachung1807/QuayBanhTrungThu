package com.example.do_khac_hung.cuahangbanhtrungthu.model;

/**
 * Created by DO_KHAC_HUNG on 8/31/2017.
 */

public class LoaiSanPham {
    public int id;
    public String tenLoaiSanPham;
    public String hinhAnhLoaiSanPham;

    public LoaiSanPham(int id, String tenLoaiSanPham, String hinhAnhLoaiSanPham) {
        this.id = id;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.hinhAnhLoaiSanPham = hinhAnhLoaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getHinhAnhLoaiSanPham() {
        return hinhAnhLoaiSanPham;
    }

    public void setHinhAnhLoaiSanPham(String hinhAnhLoaiSanPham) {
        this.hinhAnhLoaiSanPham = hinhAnhLoaiSanPham;
    }
}
