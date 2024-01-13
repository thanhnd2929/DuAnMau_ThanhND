package fpoly.thanhndph45160.duanmau.DbHelper;

public class Data_SQLite {
    public static final String INSERT_THU_THU = "Insert into ThuThu(maTT, hoTen, matKhau)" +
            "Values ('admin', 'Nguyen admin', 'admin')," +
            "('thanh', 'Nguyen Thanh', '123456')," +
            "('namnv', 'Nguyen Nam', '123456')," +
            "('minhnv', 'Nguyen Minh', '123456')";

    public static final String INSERT_THANH_VIEN ="INSERT INTO ThanhVien(hoTen, namSinh)\n" +
            "VALUES \n" +
            "    ('Nguyen Long', '2001'),\n" +
            "    ('Nguyen Anh', '1995'),\n" +
            "    ('Tran Minh', '2003'),\n" +
            "    ('Le Van', '1998'),\n" +
            "    ('Pham Thanh', '2005');\n";

    public static final String INSERT_LOAI_SACH = "\n" +
            "INSERT INTO LoaiSach(tenLoai)\n" +
            "VALUES \n" +
            "    ('Tiểu thuyết'),\n" +
            "    ('Kinh doanh'),\n" +
            "    ('Khoa học'),\n" +
            "    ('Công nghệ'),\n" +
            "    ('Tâm lý học'),\n" +
            "    ('Lịch sử');";
    public static final String INSERT_SACH = "INSERT INTO Sach(tenSach, giaThue, maLoai)\n" +
            "VALUES\n" +
            "    ('Đắc Nhân Tâm', 2000,1),\n" +
            "    ('Startup Thành Công', 2000, 2),\n" +
            "    ('Bí Mật Số 9', 2000, 3),\n" +
            "    ('Lập Trình Java', 2000, 4),\n" +
            "    ('Tâm Lý Tội Phạm', 2000, 5),\n" +
            "    ('Lịch Sử Thế Giới', 2000, 6);";
    public static final String INSERT_PHIEU_MUON = "INSERT INTO PhieuMuon(maTT, maTV, maSach, tienThue, ngay, traSach)\n" +
            "VALUES\n" +
            "    ('admin', 1, 1, 2000,'2024/01/09', 0),\n" +
            "    ('admin', 2, 2, 2000,'2024/01/10', 1),\n" +
            "    ('admin', 3, 3, 2000,'2024/01/11', 0),\n" +
            "    ('admin', 4, 4, 2000,'2024/01/12', 1),\n" +
            "    ('admin', 5, 5, 2000,'2024/01/13', 0);";

}
