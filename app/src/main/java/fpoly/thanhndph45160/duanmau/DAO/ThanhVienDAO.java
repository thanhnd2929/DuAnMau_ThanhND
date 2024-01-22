package fpoly.thanhndph45160.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import fpoly.thanhndph45160.duanmau.DbHelper.MyDbHelper;
import fpoly.thanhndph45160.duanmau.Model.ThanhVien;

public class ThanhVienDAO {
     private MyDbHelper dbHelper;
     private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        String[] dk = new String[]{String.valueOf(obj.getMaTV())};
        return db.update("ThanhVien", values, "maTV=?", dk);
    }

    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        if (c.getCount()>0) {
            while (c.moveToNext()) {
                ThanhVien obj = new ThanhVien();
                obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
                obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
                obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));

                list.add(obj);
            }
        }
        return list;
    }

    // get tat ca data
    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    //get data theo id
    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }



}
