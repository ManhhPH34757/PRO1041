package com.shoplaptop.entity;

public class DotGiamGia {
    private String MaDG;
   private String tenDG;
   private String Han;
   private String giagiam;
   private String DKgiam;

   public DotGiamGia() {
   }

   public DotGiamGia(String MaDG, String tenDG, String Han, String giagiam, String DKgiam) {
       this.MaDG = MaDG;
       this.tenDG = tenDG;
       this.Han = Han;
       this.giagiam = giagiam;
       this.DKgiam = DKgiam;
   }

   public String getMaDG() {
       return MaDG;
   }

   public void setMaDG(String MaDG) {
       this.MaDG = MaDG;
   }

   public String getTenDG() {
       return tenDG;
   }

   public void setTenDG(String tenDG) {
       this.tenDG = tenDG;
   }

   public String getHan() {
       return Han;
   }

   public void setHan(String Han) {
       this.Han = Han;
   }

   public String getGiagiam() {
       return giagiam;
   }

   public void setGiagiam(String giagiam) {
       this.giagiam = giagiam;
   }

   public String getDKgiam() {
       return DKgiam;
   }

   public void setDKgiam(String DKgiam) {
       this.DKgiam = DKgiam;
   }

   @Override
   public String toString() {
       return "DotGiamGia{" + "MaDG=" + MaDG + ", tenDG=" + tenDG + ", Han=" + Han + ", giagiam=" + giagiam + ", DKgiam=" + DKgiam + '}';
   }

   
}
