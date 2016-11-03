package com.itheima.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @创建者 Adminstration
 * @创建时间 2016/11/3 13:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class OrmDBOpenHelper extends OrmLiteSqliteOpenHelper {

    private static OrmDBOpenHelper ormDBOpenHelper;

    public OrmDBOpenHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    public static synchronized OrmDBOpenHelper getInstance(Context context){
        if (ormDBOpenHelper == null){
            synchronized (OrmDBOpenHelper.class){
                if (ormDBOpenHelper == null){
                    ormDBOpenHelper = new OrmDBOpenHelper(context);
                }
            }
        }
        return ormDBOpenHelper;
    }

    /**
     * 创建表,可创建多个表
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新表，可更新多个表
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Product.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取操作数据库的DAO
    public Dao<Product, Integer> producDao;

    public Dao<Product, Integer> getProductDao() throws SQLException {

        if (producDao == null){
            producDao = getDao(Product.class);
        }
        return producDao;
    }

    /**
     * 关闭操作数据库的DAO
     */
    @Override
    public void close() {
        super.close();
        producDao = null;
    }
}
