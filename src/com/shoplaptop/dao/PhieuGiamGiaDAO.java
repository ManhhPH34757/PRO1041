/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoplaptop.dao;
import java.sql.*;

import com.shoplaptop.entity.PhieuGiamGia;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuGiamGiaDAO implements ShopLaptop365DAO<PhieuGiamGia, String> {
    PhieuGiamGia pgg;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public ArrayList<PhieuGiamGia> getALLDAO(){
        ArrayList<PhieuGiamGia> dspg = new ArrayList<>();
        String sql = "select * from PhieuGiamGia";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
              dspg.add(new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));
            }
            System.out.println(dspg.get(0).toString());
            System.out.println(dspg.get(1).toString());
            System.out.println(dspg.get(2).toString());
            System.out.println(dspg.get(3).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return dspg;
    }
    public void ADDAO(PhieuGiamGia pgg){
        String sql = "insert into PhieuGiamGia values(?,?,?,?,?,?)";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, pgg.getMaPG());
            ps.setString(2, pgg.getTenPhieu());
            ps.setString(3, XDate.toString(pgg.getHan(), "yyyy-MM-dd"));
            ps.setInt(4, pgg.getSoLuong());
            ps.setDouble(5, pgg.getGiaGiam());
            ps.setDouble(6, pgg.getDieuKienGiam());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void XOADAO(String MaPG){
        String sql = "delete PhieuGiamGia where MaPG = ?";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, MaPG);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void UPDATEDAO(PhieuGiamGia pgg){
        String sql = "Update PhieuGiamGia \n"
                + "set TenPhieu = ?,\n"
                + "    Han = ?,\n"
                + "     SoLuong = ?,\n"
                + "	GiaGiam =?,\n"
                + "	DieuKienHoaDon = ? \n"
                + "	where MaPG = ?";
        try {
//            con = new XJdbc().Connect();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, pgg.getTenPhieu());
//            ps.setString(2, new XDate().toString(pgg.getHan(), "yyyy-MM-dd"));
//            ps.setInt(3, pgg.getSoLuong());
//            ps.setDouble(4, pgg.getGiaGiam());
//            ps.setDouble(5, pgg.getDieuKienGiam());
//            ps.setString(6, pgg.getMaPG());
//            ps.executeUpdate();
//            con.close();
        	XJdbc.update(sql, pgg.getTenPhieu(),pgg.getHan(),pgg.getSoLuong(),pgg.getGiaGiam(),pgg.getDieuKienGiam(),pgg.getMaPG());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List<PhieuGiamGia> selectAllPhieu(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia)\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOC(){
        String sql="select * from PhieuGiamGia";
        return getALLSQL(sql);
    }
    
    public List<PhieuGiamGia> selectPhieuConHan(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia where Han>(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate))\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOCCONHAN(){
        String sql="select * from PhieuGiamGia where Han>(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate)";
        return getALLSQL(sql);
    }
    
    public List<PhieuGiamGia> selectPhieuHetHan(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia where Han<(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate))\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOCHETHAN(){
        String sql="select * from PhieuGiamGia where Han<(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate)";
        return getALLSQL(sql);
    }
    public ArrayList<PhieuGiamGia> getALLSQL(String sql){
        ArrayList<PhieuGiamGia> dspg = new ArrayList<>();
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                dspg.add(new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));
            }
//            System.out.println(dspg.get(0).toString());
//            System.out.println(dspg.get(1).toString());
//            System.out.println(dspg.get(2).toString());
//            System.out.println(dspg.get(3).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return dspg;
    }
    
    public static void main(String[] args) {
        PhieuGiamGiaDAO pggdao = new PhieuGiamGiaDAO();
        pggdao.getALLDAO();
    }
	@Override
	public String insert(PhieuGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String update(PhieuGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PhieuGiamGia selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PhieuGiamGia> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PhieuGiamGia> selectBySQL(String sql, Object... args) {
		
	List<PhieuGiamGia> list = new ArrayList<PhieuGiamGia>();
			try {
				ResultSet rs = XJdbc.query(sql, args);
				while (rs.next()) {
					PhieuGiamGia phieuGiamGia = new PhieuGiamGia(rs.getInt("ID"), rs.getString("MaPG"), rs.getString("TenPhieu"), rs.getDate("Han"), rs.getInt("SoLuong"), rs.getDouble("GiaGiam"), rs.getDouble("DieuKienHoaDon"));
					list.add(phieuGiamGia);
				}
				rs.getStatement().getConnection().close();
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	
	}
}
