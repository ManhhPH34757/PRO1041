package com.shoplaptop.dao;


import com.shoplaptop.entity.DotGiamGia;
import com.shoplaptop.utils.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DotGiamGiaDao {

    ArrayList<DotGiamGia> dsdg = new ArrayList<>();
    DotGiamGia dg;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<DotGiamGia> getAll() {
        ArrayList<DotGiamGia> list = new ArrayList<>();
        String sql = "select * from DotGiamGia";
        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DotGiamGia(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void add(DotGiamGia dg) {
        String sql = "insert into DotGiamGia values (?,?,?,?,?)";
        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dg.getMaDG());
            ps.setString(2, dg.getTenDG());
            ps.setString(3, dg.getHan());
            ps.setString(4, dg.getGiagiam());
            ps.setString(5, dg.getDKgiam());
            ps.executeUpdate();
        } catch (Exception e) {
            dsdg.add(dg);
        }
    }

    public void delete(DotGiamGia dg) {
        String sql = "Delete DotGiamGia where MaDG = ?";
        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dg.getMaDG());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void update(DotGiamGia dg) {
        String sql = "update DotGiamGia set TenDG = ?, Han = ?, GiaGiam = ?, DieuKienHoaDon = ? where MaDG = ?";

        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);

            ps.setString(1, dg.getTenDG());
            ps.setString(2, dg.getHan());
            ps.setString(3, dg.getGiagiam());
            ps.setString(4, dg.getDKgiam());
            ps.setString(5, dg.getMaDG());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("No rows were updated.");
            } else {
                System.out.println("Update was successful.");
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public List<DotGiamGia> findByTen(String Ten) {
        DotGiamGia dg = null;
        String sql = "SELECT * FROM DotGiamGia WHERE TenDG = ?";
        List<DotGiamGia> list = new ArrayList<DotGiamGia>();
        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dg = new DotGiamGia();
                dg.setMaDG(rs.getString("MaDG"));
                dg.setTenDG(rs.getString("TenDG"));
                dg.setHan(rs.getString("Han"));
                dg.setGiagiam(rs.getString("GiaGiam"));
                dg.setDKgiam(rs.getString("DieuKienHoaDon"));
                list.add(dg);
            }
            System.out.println(list);
            rs.close();
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
       public List<DotGiamGia> findByMaDG(String MaDG) {
        DotGiamGia dg = null;
        String sql = "SELECT * FROM DotGiamGia WHERE MaDG = ?";
        List<DotGiamGia> list = new ArrayList<DotGiamGia>();
        try {
            conn = new XJdbc().Connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1, MaDG);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dg = new DotGiamGia();
                dg.setMaDG(rs.getString("MaDG"));
                dg.setTenDG(rs.getString("TenDG"));
                dg.setHan(rs.getString("Han"));
                dg.setGiagiam(rs.getString("GiaGiam"));
                dg.setDKgiam(rs.getString("DieuKienHoaDon"));
                list.add(dg);
            }
            System.out.println(list);
            rs.close();
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       }

}

