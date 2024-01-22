package fpoly.thanhndph45160.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Adapter.PhieuMuonAdapter;
import fpoly.thanhndph45160.duanmau.DAO.PhieuMuonDAO;
import fpoly.thanhndph45160.duanmau.Model.PhieuMuon;
import fpoly.thanhndph45160.duanmau.R;


public class PhieuMuonFragment extends Fragment {

    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    PhieuMuon item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        capNhatLv();
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(phieuMuonAdapter);
    }
}