package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;

import java.lang.reflect.Member;
import java.util.List;

public interface PembelianService {
    void addPembelian(PembelianModel pembelian);
    List<PembelianModel> getListPembelian();
    PembelianModel getPembelianByIdPembelian(Long idPembelian);
    Integer getJumlahBarangPembelian(PembelianModel pembelian);
    List<BarangModel> getListBarangPembelian(PembelianModel pembelian);
    void hapusPembelian(PembelianModel pembelian);
    List<PembelianModel> cariPembelianBerdasarkanMemberDanPembayaran(MemberModel member, Integer isCash);
    List<PembelianModel> cariPembelianBerdasarkanMember(MemberModel member);
    List<PembelianModel> cariPembelianBerdasarkanIsCash(Integer isCash);
    void generateNomorInvoicePembelian(PembelianModel pembelian);
}
