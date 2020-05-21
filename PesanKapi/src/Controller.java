/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Windows10
 */
import java.awt.event.*;
import javax.swing.*;

public class Controller {
    Model model;
    View view;
    String idTerpilih;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        readKereta();
        readTiket();

        view.bDaftarTiket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getContentPane().removeAll();
                view.refresh();
                readComboKereta();
                view.tiketView().setVisible(true);
            }
        });
        view.bDaftarKereta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getContentPane().removeAll();
                view.refresh();
                view.keretaView().setVisible(true);
            }
        });
        view.bEditKereta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getContentPane().removeAll();
                view.refresh();
                setInsideEditValue(idTerpilih);
                view.editView().setVisible(true);
            }
        });
        view.bTambahTiket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = view.getNamaPemesan();
                String jk = view.getJk();
                String stasiun = view.getStasiun();
                String id = model.cariId(view.getKereta());
                model.addTiket(nama, jk, stasiun, id);
                readTiket();
            }
        });
        view.bTambahKereta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.getId();
                String nama = view.getNamaKereta();
                String kelas = view.getKelas();
                model.addKereta(id, nama, kelas);
                readKereta();
            }
        });
        view.bHapusKereta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Benar Menghapus Kereta?", "Pilih Opsi : ", JOptionPane.YES_NO_OPTION);
                if (input==0) {
                    model.hapusKereta(idTerpilih);
                    readKereta();
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
        view.bBatal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearTextField();
            }
        });
        view.bKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getContentPane().removeAll();
                view.refresh();
                view.mainView().setVisible(true);
            }
        });
        view.bBatalEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getContentPane().removeAll();
                clearTextField();
                view.refresh();
                view.keretaView().setVisible(true);
            }
        });
        view.tabelKereta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = view.tabelKereta.getSelectedRow();
                String dataTerpilih = view.tabelKereta.getValueAt(baris, 0).toString();
                idTerpilih = dataTerpilih;
                } 
        }); 
        view.bEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Yakin Mengubah Data Kereta?", "Pilih Opsi : ", JOptionPane.YES_NO_OPTION);
                if (input==0) {
                    String id = view.getId();
                    String nama = view.getNamaKereta();
                    String kelas = view.getKelas();
                    model.editKereta(id, nama, kelas);
                    readKereta();
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dirubah");
                }
            }
        });
    }
    void readKereta() {
        try {
            String data[][] = new String[model.getBanyakDataKereta()][3];
            data = model.readKereta();
            view.tabelKereta.setModel((new JTable(data, view.kolomKereta)).getModel());
        } catch (IllegalArgumentException i) {
            System.err.println(i); 
        }
    }
    void readTiket() {
        try {
            String data[][] = new String[model.getBanyakDataTiket()][4];
            data = model.readTiket();
            view.tabelTiket.setModel((new JTable(data, view.kolomTiket)).getModel());
        } catch (IllegalArgumentException i) {
            System.err.println(i); 
        }
    }
    void readComboKereta() {
        try {
            String data[] = new String[model.getBanyakDataKereta()];
            data = model.readComboKereta();
            view.cmbKereta.removeAllItems();
            for (String item : data) {
                view.cmbKereta.addItem(item);
            }
        } catch (IllegalArgumentException i) {
            System.err.println(i); 
        }
    }
    void setInsideEditValue(String dataTerpilih) {
        String data[] = new String[3];
        data = model.getEditValue(dataTerpilih);
        view.tfIdKereta.setText(data[0]);
        view.tfNamaKereta.setText(data[1]);
        view.tfKelas.setText(data[2]);
        }
    void clearTextField() {
        view.tfIdKereta.setText("");
        view.tfNamaKereta.setText("");
        view.tfKelas.setText("");
        view.tfNamaPemesan.setText("");
    }
}