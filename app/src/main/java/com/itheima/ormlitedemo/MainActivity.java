package com.itheima.ormlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_query)
    Button btnQuery;
    @Bind(R.id.btn_update)
    Button btnUpdate;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.tv_show)
    TextView tvShow;

    private Dao<Product, Integer> mProductDao;
    private List<Product> mProducts;
    private OrmDBOpenHelper mOrmDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mOrmDBOpenHelper = OrmDBOpenHelper.getInstance(this);
        try {
            mProductDao = mOrmDBOpenHelper.getProductDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_add, R.id.btn_query, R.id.btn_update, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_delete:
                delete();
                break;
        }
    }

    private void add() {
        for (int i = 0; i < 5; i++) {
            try {
                mProductDao.create(new Product(100 + i));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    private void query() {
        try {
            mProducts = mProductDao.queryForAll();
            showResult(mProducts.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void update() {
        if (mProducts != null && mProducts.size() > 0) {
            Product product = mProducts.get(0);
            product.name = "我被修改了 (:";
            try {
                mProductDao.update(product);
                query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void delete() {
        if (mProducts != null && mProducts.size() > 0) {
            Product product = mProducts.get(0);
            try {
                mProductDao.delete(product);
                query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showResult(String s) {
        tvShow.setText(s);
    }

    @Override
    protected void onDestroy() {
        mOrmDBOpenHelper.close();
        super.onDestroy();
    }
}
