/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Windows10
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Model {
    static final String jdbcDriver = "com.mysql.jdbc.Driver";
    static final String DBurl = "jdbc:mysql://localhost/respon_kereta";
    static final String DBusername = "root";
    static final String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    
    public Model() {
        try {
            Class.forName(jdbcDriver);
            koneksi = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            System.out.println("Koneksi Berhasil");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }
    public void addKereta (String id, String nama, String kelas) {
        try {
            String query = "INSERT INTO kereta VALUE('"+id+"','"+nama+"','"+kelas+"') ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil Ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    public void editKereta (String id, String nama, String kelas) {
        try {
            String query = "UPDATE `kereta` SET `NamaKereta`='"+nama+"', `Kelas`='"+kelas+"' WHERE `IdKereta`='"+id+"' ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dirubah");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void hapusKereta (String id) {
        try {
            String query = "DELETE FROM `kereta` WHERE `IdKereta` = '"+id+"' ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addTiket (String nama, String jk, String stasiun, String kereta) {
        try {
            String query = "INSERT INTO pemesanan VALUE('"+nama+"','"+jk+"','"+stasiun+"','"+kereta+"') ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    public String[][] readKereta() {
        String data[][] = new String[getBanyakDataKereta()][3];
        try {
            int jml=0;
            statement = koneksi.createStatement();
            String query = "SELECT * FROM kereta ";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                data[jml][0] = setResult.getString("IdKereta");
                data[jml][1] = setResult.getString("NamaKereta");
                data[jml][2] = setResult.getString("Kelas");
                jml++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } 
        return data;
    }
    public String[] readComboKereta() {
        String data[] = new String[getBanyakDataKereta()];
        try {
            int jml=0;
            statement = koneksi.createStatement();
            String query = "SELECT * FROM kereta ";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                String nama = setResult.getString("NamaKereta");
                //String kelas = setResult.getString("kelas");
                data[jml] = nama;
                jml++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } 
        return data;
    }
    public String[][] readTiket() {
        String data[][] = new String[getBanyakDataTiket()][4];
        try {
            int jml=0;
            statement = koneksi.createStatement();
            String query = "SELECT * FROM pemesanan JOIN kereta ON kereta.IdKereta=pemesanan.IdKereta";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                data[jml][0] = setResult.getString("NamaPemesan");
                data[jml][1] = setResult.getString("JenisKelamin");
                data[jml][2] = setResult.getString("Stasiun");
                data[jml][3] = setResult.getString("NamaKereta");
                jml++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } 
        return data;
    }
    public int getBanyakDataKereta() {
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM `kereta`";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                jmlData++;
            }
            return jmlData;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            JOptionPane.showMessageDialog(null, "error diperhitungan jumlah data");
            return 0;
        } 
    }
    public int getBanyakDataTiket() {
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM pemesanan JOIN kereta ON kereta.IdKereta=pemesanan.IdKereta";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                jmlData++;
            }
            return jmlData;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            JOptionPane.showMessageDialog(null, "error diperhitungan jumlah data");
            return 0;
        } 
    }
    public String cariId(String kereta) {
        String data = new String();
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM kereta WHERE NamaKereta LIKE '"+kereta+"' ";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                data = setResult.getString("IdKereta");
            }
            //JOptionPane.showMessageDialog(null, data);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } 
        return data;
    }
    public String[] getEditValue(String id) {
        String data[] = new String[3];
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM kereta WHERE IdKereta='"+id+"'";
            ResultSet setResult = statement.executeQuery(query);
            while (setResult.next()) {
                data[0] = id;
                data[1] = setResult.getString("NamaKereta");
                data[2] = setResult.getString("Kelas");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Errorr");
        }
        return data;
    }
}

