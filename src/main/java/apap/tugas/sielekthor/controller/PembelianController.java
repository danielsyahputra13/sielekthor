package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PembelianController {
    @Qualifier("pembelianServiceImpl")

    @Autowired
    private PembelianService pembelianService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/pembelian/tambah")
    public String tambahPembelianFormPage(Model model) {

        // Dapatin tanggal hari ini.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        PembelianModel pembelian = new PembelianModel();
        pembelian.setListBarang(new ArrayList<>());
        pembelian.getListBarang().add(new KuantitasPembelianModel());

        List<BarangModel> listBarang = barangService.getListBarang();
        List<MemberModel> listMember = memberService.getListMember();

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listMember", listMember);
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "tambahBarang")
    private String tambahBarang(@ModelAttribute PembelianModel pembelian, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        pembelian.getListBarang().add(new KuantitasPembelianModel());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "hapusBarang")
    private String hapusBarang(@ModelAttribute PembelianModel pembelian,
                               Model model, HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);

        Integer indexBarang = Integer.valueOf(request.getParameter("hapusBarang"));
        pembelian.getListBarang().remove(indexBarang.intValue());
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarang", barangService.getListBarang());
        model.addAttribute("listMember", memberService.getListMember());
        model.addAttribute("dateNow", dateNow);
        return "form-add-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = "simpan")
    private String simpanPembelian(@ModelAttribute PembelianModel pembelian, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();
        String dateNowString = formatter.format(dateNow);
        dateNow = LocalDate.parse(dateNowString, formatter);
        pembelian.setTanggalPembelian(dateNow);
        for (KuantitasPembelianModel k : pembelian.getListBarang()) {
            System.out.println(k.getBarang().getIdBarang() + " " + k.getPembelian().getIdPembelian());
        }
        pembelianService.addPembelian(pembelian);
//        pembelianService.setTotalHargaPembelian(pembelian);
        model.addAttribute("pesan", String.format("Pembelian dengan nomor invoice %s berhasil ditambahkan!",
                pembelian.getNomorInvoice()));
        return "info";
    }

    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        return "viewall-pembelian";
    }
}
