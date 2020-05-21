/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Windows10
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
//import java.awt.*;
import java.awt.Font;

public class View extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JFrame currentFrame;
    Dimension dMain;
    Dimension dKereta;
    Dimension dTiket;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JTable tabelTiket;
    JTable tabelKereta;
    String[] kolomTiket = {"Nama","Jenis Kelamin","Stasiun","Kereta"};
    String[] kolomKereta = {"ID Kereta","Nama Kereta","Kelas"};
    JLabel lIdKereta = new JLabel("ID Kereta :");
    JLabel lNamaKereta = new JLabel("Nama Kereta :");
    JLabel lKelas = new JLabel("Kelas : ");
    JLabel lNamaPemesan = new JLabel("Nama : ");
    JLabel lJk = new JLabel("Jenis Kelamin : ");
    JLabel lStasiun = new JLabel("Stasiun Tujuan :");
    JLabel lKereta = new JLabel("Kereta");
    JTextField tfIdKereta = new JTextField();
    JTextField tfNamaKereta = new JTextField();
    JTextField tfKelas = new JTextField();
    JTextField tfNamaPemesan = new JTextField();
    String jk[]= {"Laki-Laki","Perempuan"};
    String stasiun[]= {"Tugu Jogja","Lempuyangan","Maguwo"};
    JComboBox<String> cmbJk = new JComboBox<>(jk);
    JComboBox<String> cmbStasiun = new JComboBox<>(stasiun);
    JComboBox<String> cmbKereta = new JComboBox<>();
    JButton bDaftarTiket = new JButton("Daftar Tiket");
    JButton bDaftarKereta = new JButton("Daftar Kereta");
    JButton bTambahTiket = new JButton("Tambah");
    JButton bBatal = new JButton("Batal");
    JButton bTambahKereta = new JButton("Tambah");
    JButton bEditKereta = new JButton("Edit");
    JButton bHapusKereta = new JButton("Hapus");
    JButton bKembali = new JButton("Kembali");
    JButton bEdit = new JButton("Edit");
    JButton bBatalEdit = new JButton("Batal");
    public View() {
        dMain = new Dimension(700,590); //lebar,tinggi
        //dKereta = new Dimension(650,700);
        //dTiket = new Dimension(650,700);
        tableModel = new DefaultTableModel(kolomKereta,0);
        tabelKereta = new JTable(tableModel);
        tableModel = new DefaultTableModel(kolomTiket,0);
        tabelTiket = new JTable(tableModel);
        
        mainView();
    }
    public void refresh () {
        dispose();
    }
    public JFrame mainView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        //setPreferredSize(dMain);
        setSize(dMain);
        add(bDaftarTiket); bDaftarTiket.setBounds(285,40,120,40);
        add(bDaftarKereta); bDaftarKereta.setBounds(285,120,120,40);

       
        return currentFrame;
    }
    public JFrame keretaView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setPreferredSize(dMain);
        //setSize(dMain);
        scrollPane = new JScrollPane(tabelKereta);
        add(scrollPane);
        scrollPane.setBounds(20,190,640,330); //x,y,lebar,tinggi
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(lIdKereta); lIdKereta.setBounds(40,40,200,20); //x,y,lebar,tinggi
        add(tfIdKereta); tfIdKereta.setBounds(140,40,200,20);
        add(lNamaKereta); lNamaKereta.setBounds(40,70,200,20);
        add(tfNamaKereta); tfNamaKereta.setBounds(140,70,200,20);
        add(lKelas); lKelas.setBounds(40,100,200,20);
        add(tfKelas); tfKelas.setBounds(140,100,200,20);
        tfIdKereta.setEditable(true);
        
        add(bTambahKereta); bTambahKereta.setBounds(380,40,100,20);
        add(bEditKereta); bEditKereta.setBounds(380,70,100,20);
        add(bHapusKereta); bHapusKereta.setBounds(380,100,100,20);
        add(bBatal); bBatal.setBounds(500,40,100,20);
        add(bKembali); bKembali.setBounds(500,70,100,20);

        return currentFrame;
    }
    public JFrame tiketView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setPreferredSize(dMain);
        scrollPane = new JScrollPane(tabelTiket);
        add(scrollPane);
        scrollPane.setBounds(20,190,640,330); //x,y,lebar,tinggi
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(lNamaPemesan); lNamaPemesan.setBounds(40,40,200,20); //x,y,lebar,tinggi
        add(tfNamaPemesan); tfNamaPemesan.setBounds(140,40,200,20);
        add(lJk); lJk.setBounds(40,70,200,20);
        add(cmbJk); cmbJk.setBounds(140,70,200,20);
        add(lStasiun); lStasiun.setBounds(40,100,200,20);
        add(cmbStasiun); cmbStasiun.setBounds(140,100,200,20);
        add(lKereta); lKereta.setBounds(40,130,200,20);
        add(cmbKereta); cmbKereta.setBounds(140,130,200,20);

        add(bTambahTiket); bTambahTiket.setBounds(380,40,100,20);
        add(bBatal); bBatal.setBounds(500,40,100,20);
        add(bKembali); bKembali.setBounds(500,70,100,20);

        return currentFrame;
    }
    public JFrame editView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setPreferredSize(dMain);
        add(lIdKereta); lIdKereta.setBounds(40,40,200,20); //x,y,lebar,tinggi
        add(tfIdKereta); tfIdKereta.setBounds(140,40,200,20);
        add(lNamaKereta); lNamaKereta.setBounds(40,70,200,20);
        add(tfNamaKereta); tfNamaKereta.setBounds(140,70,200,20);
        add(lKelas); lKelas.setBounds(40,100,200,20);
        add(tfKelas); tfKelas.setBounds(140,100,200,20);
        tfIdKereta.setEditable(false);

        add(bEdit); bEdit.setBounds(380,40,100,20);
        add(bBatalEdit); bBatalEdit.setBounds(500,40,100,20);

        return currentFrame;
    }
    public String getId() {
        return tfIdKereta.getText();
    }
    public String getNamaKereta() {
        return tfNamaKereta.getText();
    }
    public String getKelas() {
        return tfKelas.getText();
    }
    public String getNamaPemesan() {
        return tfNamaPemesan.getText();
    }
    public String getJk() {
        String select = (String) cmbJk.getSelectedItem();
        return select;
    }
    public String getStasiun() {
        String select = (String) cmbStasiun.getSelectedItem();
        return select;
    }
    public String getKereta() {
        String select = (String) cmbKereta.getSelectedItem();
        return select;
    }
}