package fpoly.thanhndph45160.duanmau.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;


    public MyDbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Start Create Table
        String createTableThuThu = "CREATE TABLE ThuThu (\n" +
                "    maTT    TEXT PRIMARY KEY\n" +
                "                 NOT NULL,\n" +
                "    hoTen   TEXT NOT NULL,\n" +
                "    matKhau TEXT NOT NULL\n" +
                ");";
        db.execSQL(createTableThuThu);

        String createTableThanhVien = "CREATE TABLE ThanhVien (\n" +
                "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    hoTen   TEXT    NOT NULL,\n" +
                "    namSinh TEXT    NOT NULL\n" +
                ");";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach = "CREATE TABLE LoaiSach (\n" +
                "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    tenLoai TEXT    NOT NULL\n" +
                ");";
        db.execSQL(createTableLoaiSach);

        String createTableSach = "CREATE TABLE Sach (\n" +
                "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    tenSach TEXT    NOT NULL,\n" +
                "    giaThue INTEGER NOT NULL,\n" +
                "    maLoai  INTEGER REFERENCES LoaiSach (maLoai) \n" +
                "                    NOT NULL\n" +
                ");";
        db.execSQL(createTableSach);

        String createTablePhieuMuon = "CREATE TABLE PhieuMuon (\n" +
                "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     NOT NULL,\n" +
                "    maTT     TEXT    REFERENCES ThuThu (maTT) \n" +
                "                     NOT NULL,\n" +
                "    maTV     INTEGER REFERENCES ThanhVien (maTV) \n" +
                "                     NOT NULL,\n" +
                "    maSach   INTEGER REFERENCES Sach (maSach),\n" +
                "    tienThue INTEGER NOT NULL,\n" +
                "    ngay     Date    NOT NULL,\n" +
                "    traSach  INTEGER NOT NULL\n" +
                ");";
        db.execSQL(createTablePhieuMuon);
        //        End Create Table

        // Insert data
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuTHu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuTHu);

        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);

        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);

        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);

        String dropTablePhieuMuon= "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);

    }
}
