package fpoly.thanhndph45160.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Adapter.TopAdapter;
import fpoly.thanhndph45160.duanmau.DAO.PhieuMuonDAO;
import fpoly.thanhndph45160.duanmau.Model.PhieuMuon;
import fpoly.thanhndph45160.duanmau.Model.Top;
import fpoly.thanhndph45160.duanmau.R;


public class TopFragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    TopAdapter topAdapter;
    PhieuMuonDAO phieuMuonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lvTop);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top>) phieuMuonDAO.getTop();
        topAdapter = new TopAdapter(getActivity(), this, list);
        lv.setAdapter(topAdapter);
        return v;
    }
}