package com.nikola.pharaonsailmake.controllers;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.nikola.pharaonsailmake.PDF.PDFDocumentDeliverieCloth;
import com.nikola.pharaonsailmake.domain.*;
import com.nikola.pharaonsailmake.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

@Controller
public class DeliverieController {

    private final ClothRepo clothRepo;
    private final DealerRepo dealerRepo;
    private final DeliverieClothRepo deliverieClothRepo;
    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final HardwareRepo hardwareRepo;
    private final SailRepo sailRepo;
    private final StorageUnitRepo storageUnitRepo;
    private final HardwareOnSailRepo hardwareOnSailRepo;

    @Autowired
    public DeliverieController(ClothRepo clothRepo, DealerRepo dealerRepo, DeliverieClothRepo deliverieClothRepo,
                               DeliverieHardwareRepo deliverieHardwareRepo, HardwareRepo hardwareRepo, SailRepo sailRepo,
                               StorageUnitRepo storageUnitRepo, HardwareOnSailRepo hardwareOnSailRepo) {
        this.clothRepo = clothRepo;
        this.dealerRepo = dealerRepo;
        this.deliverieClothRepo = deliverieClothRepo;
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.hardwareRepo = hardwareRepo;
        this.sailRepo = sailRepo;
        this.storageUnitRepo = storageUnitRepo;
        this.hardwareOnSailRepo = hardwareOnSailRepo;
    }


    @GetMapping("deliverieHardware")
    public String deliverieHardware(Map<String, Object> model) {

        Iterable<DeliverieHardware> deliverieHardwares = deliverieHardwareRepo.findAll();
        model.put("DeliverieHarwares", deliverieHardwares);

        return "deliverieHardware";
    }

    @PostMapping("addDeliverieHardware")
    public String addDeliverieHardware(@RequestParam String Data, @RequestParam Integer Number,
                                       @RequestParam Integer StorageUnit_id, @RequestParam Integer Dealer_id,
                                       @RequestParam Integer Hardware_id, Map<String, Object> model) {

        if (Data != null && Number != null && StorageUnit_id != null && storageUnitRepo.existsById(StorageUnit_id) &&
                Dealer_id != null && dealerRepo.existsById(Dealer_id) && Hardware_id != null && hardwareRepo.existsById(Hardware_id)) {
            DeliverieHardware deliverieHardware /*пасхалка, специально для Натальи Романовны запросы на SQL, а не через встроенный интерпритотор)))*/
                    = new DeliverieHardware(Number * hardwareRepo.findById(Hardware_id).orElse(new Hardware()).getPrice(),
                    Data, Number, storageUnitRepo.findById(StorageUnit_id).orElse(new StorageUnit()),
                    dealerRepo.findById(Dealer_id).orElse(new Dealer()), hardwareRepo.findById(Hardware_id).orElse(new Hardware()));
            deliverieHardwareRepo.save(deliverieHardware);
        }

        return deliverieHardware(model);
    }

    @PostMapping("deleteDeliverieHardware")
    public String deleteDeliverieHardware(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            Iterable<HardwareOnSail> hardwareOnSails = hardwareOnSailRepo.findAll();
            for (HardwareOnSail hardwareOnSail : hardwareOnSails) {
                if (hardwareOnSail.getId().equals(id)) {
                    return deliverieHardware(model);
                }
            }
            if (deliverieHardwareRepo.existsById(id))
                deliverieHardwareRepo.deleteById(id);
        }
        return deliverieHardware(model);
    }

    @GetMapping("deliverieCloth")
    public String deliverieCloth(Map<String, Object> model) {
        Iterable<DeliverieCloth> deliverieCloths = deliverieClothRepo.findAll();
        model.put("DeliverieCloths", deliverieCloths);

        return "deliverieCloth";
    }

    @PostMapping("addDeliverieCloth")
    public String addDeliverieCloth(@RequestParam Integer LotNumber, @RequestParam Double RollLength, @RequestParam String Data,
                                    @RequestParam Integer Cloth_id, @RequestParam Integer Dealer_id,
                                    @RequestParam Integer StorageUnit_id, Map<String, Object> model) {

        if (LotNumber != null && RollLength != null && Data != null && Cloth_id != null && clothRepo.existsById(Cloth_id) && Dealer_id != null
                && dealerRepo.existsById(Dealer_id) && StorageUnit_id != null && storageUnitRepo.existsById(StorageUnit_id)) {
            DeliverieCloth deliverieCloth = new DeliverieCloth(LotNumber, RollLength, clothRepo.findById(Cloth_id).orElse(new Cloth()).getPrice() * RollLength,
                    Data, clothRepo.findById(Cloth_id).orElse(new Cloth()), dealerRepo.findById(Dealer_id).orElse(new Dealer()),
                    storageUnitRepo.findById(StorageUnit_id).orElse(new StorageUnit()));

            deliverieClothRepo.save(deliverieCloth);
        }

        return deliverieCloth(model);
    }

    @PostMapping("uploadFile")
    public void uploadDeliverieCloth(HttpServletResponse response) {
        try {
            File pdfFile = new File("deliverieCloth" + ".pdf");
            writePdf(pdfFile);

            InputStream is = new FileInputStream(pdfFile);

            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writePdf(File pdfFile) throws Exception {
        PDFDocumentDeliverieCloth pdf = new PDFDocumentDeliverieCloth();
        Document document = new Document();
//        File pdfFile = new File(System.getProperty("user.home")+"/Downloads/"+"deliverieCloth"+".pdf");

        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();
        pdf.buildPdfDocument(deliverieClothRepo.findAll(), document);
        document.close();
    }


    @PostMapping("deleteDeliverieCloth")
    public String deleteDeliverieCloth(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            Iterable<Sail> sails = sailRepo.findAll();
            for (Sail sail : sails) {
                if (sail.getDeliverieCloth_id().getId().equals(id)) {
                    return deliverieCloth(model);
                }
            }
            if (deliverieClothRepo.existsById(id))
                deliverieClothRepo.deleteById(id);
        }
        return deliverieCloth(model);
    }

}
