package com.example.do_khac_hung.cuahangbanhtrungthu.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_khac_hung.cuahangbanhtrungthu.R;
import com.example.do_khac_hung.cuahangbanhtrungthu.adapter.LoaiSanPhamAdapter;
import com.example.do_khac_hung.cuahangbanhtrungthu.adapter.SanPhamAdapter;
import com.example.do_khac_hung.cuahangbanhtrungthu.model.LoaiSanPham;
import com.example.do_khac_hung.cuahangbanhtrungthu.model.SanPham;
import com.example.do_khac_hung.cuahangbanhtrungthu.utils.CheckNetworkConnection;
import com.example.do_khac_hung.cuahangbanhtrungthu.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolBarMainTrangChinh;
    ViewFlipper viewFlipperMain;
    RecyclerView recyclerViewMain;
    NavigationView navigationViewMain;
    ListView listViewMain;
    DrawerLayout drawerLayoutMain;
    ArrayList<LoaiSanPham> arrayListLoaiSanPham;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    int id = 0;
    String tenloaisanpham = "";
    String hinhanhloaisanpham = "";
    ArrayList<SanPham> arrayListSanPham;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        if (CheckNetworkConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            getDuLieuLoaiSanPham();
            getDuLieuSanPhamMoi();
        } else {
            CheckNetworkConnection.ShowToast(getApplicationContext(), "Có vẻ bạn chưa kết nối Internet");
            finish();
        }

    }

    private void getDuLieuSanPhamMoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.linkSanPhamMoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String tenSanPham = "";
                    Integer giaSanPham = 0;
                    String hinhAnhSanPham = "";
                    String moTaSanPham = "";
                    int idSanPham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenSanPham = jsonObject.getString("tensanpham");
                            giaSanPham = jsonObject.getInt("giasanpham");
                            hinhAnhSanPham = jsonObject.getString("hinhanhsanpham");
                            moTaSanPham = jsonObject.getString("motasanpham");
                            idSanPham = jsonObject.getInt("idsanpham");
                            arrayListSanPham.add(new SanPham(id, tenSanPham, giaSanPham, hinhAnhSanPham, moTaSanPham, idSanPham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDuLieuLoaiSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.linkLoaiSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisanpham = jsonObject.getString("tenloaisanpham");
                            hinhanhloaisanpham = jsonObject.getString("hinhanhloaisanpham");
                            arrayListLoaiSanPham.add(new LoaiSanPham(id, tenloaisanpham, hinhanhloaisanpham));
                            loaiSanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arrayListLoaiSanPham.add(3, new LoaiSanPham(0, "Liên Hệ", "http://thoitranggoicam.com/wp-content/uploads/2016/04/Call-icon-blue.png"));
                    arrayListLoaiSanPham.add(4, new LoaiSanPham(0, "Thông Tin", "http://support.casio.com/global/vi/wat/manual/5413_vi/fig/App_icon_02_VPCVILcirwnbhj.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckNetworkConnection.ShowToast(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> viewFlipper = new ArrayList<>();
        viewFlipper.add("http://trungthuhuunghi.com.vn/Portals/2/Small_335553543.png");
        viewFlipper.add("http://trungthuhuunghi.com.vn/Portals/2/Small_88978373.png");
        viewFlipper.add("http://trungthuhuunghi.com.vn/Portals/2/Small_387667433.png");
        viewFlipper.add("http://trungthuhuunghi.com.vn/Portals/2/Small_953284816.png");
        for (int i = 0; i < viewFlipper.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(viewFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }
        viewFlipperMain.setFlipInterval(5000);
        viewFlipperMain.setAutoStart(true);
        Animation animationFlipperIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_flipper_in_main);
        Animation animationFlipperOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_flipper_out_main);
        viewFlipperMain.setInAnimation(animationFlipperIn);
        viewFlipperMain.setOutAnimation(animationFlipperOut);
    }

    private void ActionBar() {
        setSupportActionBar(toolBarMainTrangChinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarMainTrangChinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolBarMainTrangChinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutMain.openDrawer(GravityCompat.START);
            }
        });
    }

    private void addControls() {
        toolBarMainTrangChinh = (Toolbar) findViewById(R.id.tool_bar_main_trang_chinh);
        viewFlipperMain = (ViewFlipper) findViewById(R.id.view_flipper_main);
        recyclerViewMain = (RecyclerView) findViewById(R.id.recycler_view_main);
        navigationViewMain = (NavigationView) findViewById(R.id.navigation_view_main);
        listViewMain = (ListView) findViewById(R.id.list_view_main);
        drawerLayoutMain = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        arrayListLoaiSanPham = new ArrayList<>();
        arrayListLoaiSanPham.add(0, new LoaiSanPham(0, "Trang chính", "http://files.hostgator.co.in/hostgator210558/image/home-icon.png"));
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(arrayListLoaiSanPham, getApplicationContext());
        listViewMain.setAdapter(loaiSanPhamAdapter);
        arrayListSanPham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), arrayListSanPham);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewMain.setAdapter(sanPhamAdapter);
    }
}
