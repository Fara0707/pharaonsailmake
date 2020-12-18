package com.nikola.pharaonsailmake.controllers;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
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
public class MainController {

    private final ClientRepo clientRepo;
    private final ClothRepo clothRepo;
    private final DealerRepo dealerRepo;
    private final DeliverieClothRepo deliverieClothRepo;
    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final HardwareRepo hardwareRepo;
    private final SailRepo sailRepo;
    private final StorageUnitRepo storageUnitRepo;
    private final WorkerRepo workerRepo;
    private final ClothTypeRepo clothTypeRepo;
    private final HardwareTypeRepo hardwareTypeRepo;
    private final SailTypeRepo sailTypeRepo;
    private final HardwareOnSailRepo hardwareOnSailRepo;
    private final SailCutRepo sailCutRepo;

    @Autowired
    public MainController(ClientRepo clientRepo, ClothRepo clothRepo, DealerRepo dealerRepo, DeliverieClothRepo deliverieClothRepo,
                          DeliverieHardwareRepo deliverieHardwareRepo, HardwareRepo hardwareRepo, SailRepo sailRepo,
                          StorageUnitRepo storageUnitRepo, WorkerRepo workerRepo, ClothTypeRepo clothTypeRepo,
                          HardwareTypeRepo hardwareTypeRepo, SailTypeRepo sailTypeRepo, HardwareOnSailRepo hardwareOnSailRepo, SailCutRepo sailCutRepo) {
        this.clientRepo = clientRepo;
        this.clothRepo = clothRepo;
        this.dealerRepo = dealerRepo;
        this.deliverieClothRepo = deliverieClothRepo;
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.hardwareRepo = hardwareRepo;
        this.sailRepo = sailRepo;
        this.storageUnitRepo = storageUnitRepo;
        this.workerRepo = workerRepo;
        this.clothTypeRepo = clothTypeRepo;
        this.hardwareTypeRepo = hardwareTypeRepo;
        this.sailTypeRepo = sailTypeRepo;
        this.hardwareOnSailRepo = hardwareOnSailRepo;
        this.sailCutRepo = sailCutRepo;
    }

    @GetMapping("main")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("menuMain")
    public String menuMain(Map<String, Object> model) {
        return "menuMain";
    }

    @GetMapping("registrations")
    public String menuRegistrations(Map<String, Object> model) {
        return "menuRegistrations";
    }

    @GetMapping("orders")
    public String menuOrders(Map<String, Object> model) {
        return "menuOrders";
    }

    @GetMapping("deliveries")
    public String menuDeliveries(Map<String, Object> model) {
        return "menuDeliveries";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("stat")
    public String stat(Map<String, Object> model) {

        Stat stat = new Stat(sailRepo.allCostSail(), deliverieHardwareRepo.allBuyHardware(), deliverieClothRepo.allBuyCloth(),
                sailRepo.howClothUsed());
        model.put("Stat", stat);

        return "stat";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("worker")
    public String worker(Map<String, Object> model) {

        Iterable<Worker> workers = workerRepo.findAll();
        model.put("Workers", workers);

        return "worker";
    }

    @PostMapping("addWorker")
    public String addWorker(@RequestParam String Name, @RequestParam Double workMeter, Map<String, Object> model) {
        if(Name.length()!=0 && workMeter!=null) {
            Worker worker = new Worker(Name, workMeter);
            workerRepo.save(worker);
        }
        return worker(model);
    }

    @PostMapping("deleteWorker")
    public String deleteWorker(@RequestParam Integer id, Map<String, Object> model) {
        if (id != null) {
            Iterable<Sail> sails = sailRepo.findAll();
            for (Sail sail : sails) {
                if (sail.getWorker_id().getId().equals(id)) {
                    return worker(model);
                }
            }
            if (workerRepo.existsById(id))
                workerRepo.deleteById(id);
        }
        return worker(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("client")
    public String client(Map<String, Object> model) {

        Iterable<Client> clients = clientRepo.findAll();
        model.put("Clients", clients);

        return "client";
    }

    @PostMapping("addClient")
    public String addClient(@RequestParam String SurName,@RequestParam String FirstName, @RequestParam String Town,
                            @RequestParam String MobileTel,@RequestParam Integer NewPostNumber, Map<String, Object> model) {
        if(SurName.length()!=0 && FirstName.length()!=0 && Town.length()!=0 && MobileTel != null && NewPostNumber!=null) {
            Client client = new Client(SurName, FirstName, Town, MobileTel, NewPostNumber);
            clientRepo.save(client);
        }
        return client(model);
    }

    @PostMapping("deleteClient")
    public String deleteClient(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            Iterable<Sail> sails = sailRepo.findAll();
            for (Sail sail : sails) {
                if (sail.getClient_id().getId().equals(id)) {
                    return client(model);
                }
            }

            if (clientRepo.existsById(id))
                clientRepo.deleteById(id);
        }
        return client(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("dealer")
    public String dealer(Map<String, Object> model) {

        Iterable<Dealer> clients = dealerRepo.findAll();
        model.put("Dealers", clients);

        return "dealer";
    }

    @PostMapping("addDealer")
    public String addDealer(@RequestParam String Name, Map<String, Object> model) {
        if(Name.length()!=0) {
            Dealer dealer = new Dealer(Name);
            dealerRepo.save(dealer);
        }
        return dealer(model);
    }

    @PostMapping("deleteDealer")
    public String deleteDealer(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            Iterable<DeliverieHardware> deliverieHardwares = deliverieHardwareRepo.findAll();
            Iterable<DeliverieCloth> deliverieCloths = deliverieClothRepo.findAll();

            for (DeliverieHardware deliverieHardware : deliverieHardwares) {
                if (deliverieHardware.getDealer_id().getId().equals(id)) {
                    return dealer(model);
                }
            }

            for (DeliverieCloth deliverieCloth : deliverieCloths) {
                if (deliverieCloth.getDealer_id().getId().equals(id)) {
                    return dealer(model);
                }
            }

            if (dealerRepo.existsById(id))
                dealerRepo.deleteById(id);
        }
        return dealer(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("hardware")
    public String hardware(Map<String, Object> model) {

        Iterable<HardwareType> hardwareTypes = hardwareTypeRepo.findAll();
        model.put("HardwareTypes", hardwareTypes);

        Iterable<Hardware> hardwares = hardwareRepo.findAll();
        model.put("Hardwares", hardwares);

        return "hardware";
    }

    @PostMapping("addHardware")
    public String addHardware(@RequestParam String Name, @RequestParam Integer Type_id,
                              @RequestParam Double Price, Map<String, Object> model) {

        if(Name.length()!= 0 && Type_id != null && hardwareTypeRepo.existsById(Type_id) && Price != null) {
            Hardware hardware = new Hardware(Name, Price, hardwareTypeRepo.findById(Type_id).orElse(new HardwareType()));
            hardwareRepo.save(hardware);
        }

        return hardware(model);
    }

    @PostMapping("deleteHardware")
    public String deleteHardware(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            if (findUsagesDeliverieHardware(id)) return hardware(model);
            if (hardwareRepo.existsById(id)) hardwareRepo.deleteById(id);
        }
        return hardware(model);
    }

    @PostMapping("addHardwareType")
    public String addHardwareType(@RequestParam String Type, Map<String, Object> model) {
        if(Type!=null) {
            HardwareType hardwareType = new HardwareType(Type);
            hardwareTypeRepo.save(hardwareType);
        }
        return hardware(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("cloth")
    public String cloth(Map<String, Object> model) {
        initClothType();
        Iterable<ClothType> clothTypes = clothTypeRepo.findAll();
        model.put("ClothTypes", clothTypes);

        Iterable<Cloth> cloths = clothRepo.findAll();
        model.put("Cloths", cloths);

        return "cloth";
    }

    @PostMapping("addCloth")
    public String addCloth(@RequestParam Integer Type_id,@RequestParam String Name,@RequestParam Integer Width,
                           @RequestParam Double Weight,@RequestParam Double Price, Map<String, Object> model) {

        if(Type_id!=null && clothTypeRepo.existsById(Type_id) && Name.length()!=0 && Width!=null && Weight != null && Price!=null) {
            Cloth cloth = new Cloth(clothTypeRepo.findById(Type_id).orElse(new ClothType()), Name, Width, Weight, Price);
            clothRepo.save(cloth);
        }
        return cloth(model);
    }

    @PostMapping("deleteCloth")
    public String deleteCloth(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            if (findUsagesDeliverieCloth(id)) return cloth(model);
            if (clothRepo.existsById(id))
                clothRepo.deleteById(id);
        }
        return cloth(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("storageUnit")
    public String storageUnit(Map<String, Object> model) {
        Iterable<StorageUnit> storageUnits = storageUnitRepo.findAll();
        model.put("StorageUnits", storageUnits);

        return "storageUnit";
    }

    @PostMapping("addStorageUnit")
    public String addStorageUnit(@RequestParam String Town,@RequestParam String Address, Map<String, Object> model) {
        if(Town.length()!=0 && Address.length()!=0) {
            StorageUnit storageUnit = new StorageUnit(Town, Address);
            storageUnitRepo.save(storageUnit);
        }
        return storageUnit(model);
    }

    @PostMapping("deleteStorageUnit")
    public String deleteStorageUnit(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            if (findUsagesDeliverieCloth(id)) return storageUnit(model);
            if (findUsagesDeliverieHardware(id)) return storageUnit(model);
            if (storageUnitRepo.existsById(id))  storageUnitRepo.deleteById(id);
        }
        return storageUnit(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("deliverieCloth")
    public String deliverieCloth(Map<String, Object> model) {
        Iterable<DeliverieCloth> deliverieCloths = deliverieClothRepo.findAll();
        model.put("DeliverieCloths", deliverieCloths);

        return "deliverieCloth";
    }

    @PostMapping("addDeliverieCloth")
    public String addDeliverieCloth(@RequestParam Integer LotNumber,@RequestParam Double RollLength,@RequestParam String Data,
                                    @RequestParam Integer Cloth_id,@RequestParam Integer Dealer_id,
                                    @RequestParam Integer StorageUnit_id, Map<String, Object> model) {

        if(LotNumber!=null && RollLength!=null && Data!=null && Cloth_id!=null&& clothRepo.existsById(Cloth_id) && Dealer_id!=null
                &&dealerRepo.existsById(Dealer_id) && StorageUnit_id!=null&&storageUnitRepo.existsById(StorageUnit_id)) {
            DeliverieCloth deliverieCloth = new DeliverieCloth(LotNumber, RollLength, clothRepo.findById(Cloth_id).orElse(new Cloth()).getPrice() * RollLength,
                    Data, clothRepo.findById(Cloth_id).orElse(new Cloth()),dealerRepo.findById(Dealer_id).orElse(new Dealer()),
                    storageUnitRepo.findById(StorageUnit_id).orElse(new StorageUnit()));

            deliverieClothRepo.save(deliverieCloth);
        }

        return deliverieCloth(model);
    }

    @PostMapping("uploadFile")
    public void uploadDeliverieCloth( HttpServletResponse response){
        try {
            File pdfFile = new File("deliverieCloth"+".pdf");
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

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("deliverieHardware")
    public String deliverieHardware(Map<String, Object> model) {

        Iterable<DeliverieHardware> deliverieHardwares = deliverieHardwareRepo.findAll();
        model.put("DeliverieHarwares", deliverieHardwares);

        return "deliverieHardware";
    }

    @PostMapping("addDeliverieHardware")
    public String addDeliverieHardware(@RequestParam String Data,@RequestParam Integer Number,
                                    @RequestParam Integer StorageUnit_id,@RequestParam Integer Dealer_id,
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

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("sail")
    public String sail(Map<String, Object> model) {

        initSailType();
        Iterable<SailType> sailTypes = sailTypeRepo.findAll();
        model.put("SailTypes", sailTypes);

        initCutType();
        Iterable<SailCut> sailsCut = sailCutRepo.findAll();
        model.put("SailCuts", sailsCut);

        Iterable<Sail> sails = sailRepo.findAll();
        model.put("Sails", sails);


        return "sail";
    }

    @PostMapping("addSail")
    public String addSail(@RequestParam Integer SailType_id, @RequestParam Integer Cut_id, @RequestParam Integer LengthLeech,
                          @RequestParam Integer LengthLuff, @RequestParam Integer LengthFoot, @RequestParam Integer SickleLeech,
                          @RequestParam Integer SickleLuff, @RequestParam Integer SickleFoot,
                          @RequestParam Integer Seam, @RequestParam Double ElapsedLength,
                          @RequestParam Integer Client_id, @RequestParam Integer Worker_id, @RequestParam Integer DeliverieCloth_id,
                          Map<String, Object> model) {

        if(Cut_id!=null && sailCutRepo.existsById(Cut_id) && LengthLeech!=null && LengthFoot!=null && LengthLuff!=null && SickleLeech!=null&&SickleLuff!=null&&SickleFoot!=null
            &&Seam!=null && ElapsedLength!=null&&Client_id!=null&&clientRepo.existsById(Client_id) &&Worker_id!=null&&
                workerRepo.existsById(Worker_id) && DeliverieCloth_id!=null&&deliverieClothRepo.existsById(DeliverieCloth_id)
                &&SailType_id!=null&& sailTypeRepo.existsById(SailType_id)) {


            Double PriceCloth = ( workerRepo.findById(Worker_id).orElse(new Worker()).getWorkMeter() +
                    deliverieClothRepo.findById(DeliverieCloth_id).orElse(new DeliverieCloth()).getCloth_id().getPrice() ) * ElapsedLength;



            Sail sail = new Sail(sailCutRepo.findById(Cut_id).orElse(new SailCut()), LengthLeech, LengthLuff, LengthFoot, SickleLeech, SickleLuff, SickleFoot, Seam, ElapsedLength,
                    PriceCloth, sailTypeRepo.findById(SailType_id).orElse(new SailType()),clientRepo.findById(Client_id).orElse(new Client()), workerRepo.findById(Worker_id).orElse(new Worker()),
                    deliverieClothRepo.findById(DeliverieCloth_id).orElse(new DeliverieCloth()));

            sailRepo.save(sail);
        }

        return sail(model);
    }

    @PostMapping("deleteSail")
    public String deleteSail(@RequestParam Integer id, Map<String, Object> model) {
        if (id != null) {
            Iterable<HardwareOnSail> hardwareOnSails = hardwareOnSailRepo.findAll();
            for (HardwareOnSail hardwareOnSail : hardwareOnSails)
                if (hardwareOnSail.getSail_id().getId().equals(id)) {
                    return sail(model);
                }
            if (sailRepo.existsById(id)) sailRepo.deleteById(id);
        }
        return sail(model);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void initClothType() {

        if(!clothTypeRepo.findAll().iterator().hasNext()){
            clothTypeRepo.save(new ClothType("Спинакерная"));
            clothTypeRepo.save(new ClothType("Лавировочная"));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private void initSailType() {

        if(!sailTypeRepo.findAll().iterator().hasNext()){
            sailTypeRepo.save(new SailType("Грот"));
            sailTypeRepo.save(new SailType("Стаксель"));
            sailTypeRepo.save(new SailType("Спинакер"));
            sailTypeRepo.save(new SailType("Генакер"));
        }
    }

    private void initCutType() {
        if(!sailCutRepo.findAll().iterator().hasNext()){
            sailCutRepo.save(new SailCut("Радиальный"));
            sailCutRepo.save(new SailCut("Параллельный"));
            sailCutRepo.save(new SailCut("Комбинированный"));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("hardwareOnSail")
    public String hardwareOnSail(Map<String, Object> model) {
        Iterable<HardwareOnSail> hardwareOnSails = hardwareOnSailRepo.findAll();
        model.put("HardwareOnSail", hardwareOnSails);

        return "hardwareOnSail";
    }

    @PostMapping("addHardwareOnSail")
    public String addHardwareOnSail(@RequestParam Integer DeliverieHardware_id, @RequestParam Integer Sail_id,
                                  @RequestParam Integer NumberOfHardware, Map<String, Object> model) {
        if(DeliverieHardware_id!=null&&deliverieHardwareRepo.existsById(DeliverieHardware_id) &&
            Sail_id!=null && sailRepo.existsById(Sail_id)&&NumberOfHardware!=null) {
            HardwareOnSail hardwareOnSail = new HardwareOnSail(
                    deliverieHardwareRepo.findById(DeliverieHardware_id).orElse(new DeliverieHardware()),
                    sailRepo.findById(Sail_id).orElse(new Sail()), NumberOfHardware,
                    howHardwareCost(DeliverieHardware_id, NumberOfHardware));
            hardwareOnSailRepo.save(hardwareOnSail);
            Sail sail = sailRepo.findById(Sail_id).orElse(new Sail());
            sail.setPrice(sail.getPrice()+hardwareOnSail.getPrice());
            sailRepo.save(sail);
        }
        return hardwareOnSail(model);
    }


    @PostMapping("deleteHardwareOnSail")
    public String deleteHarwareOnSail(@RequestParam Integer id, Map<String, Object> model){
        if(id!=null&&hardwareOnSailRepo.existsById(id)){
            HardwareOnSail hardwareOnSail = hardwareOnSailRepo.findById(id).orElse(new HardwareOnSail());

            reSailPrice(hardwareOnSail.getPrice(),0., hardwareOnSail.getSail_id().getId());
            hardwareOnSailRepo.deleteById(id);
        }
        return hardwareOnSail(model);
    }

    @PostMapping("editHardwareOnSail")
    public String editHarwareOnSail(@RequestParam Integer id, @RequestParam Integer NumberOfHardware,
                                    Map<String, Object> model){
        if(id!=null&&hardwareOnSailRepo.existsById(id) && NumberOfHardware != null){
            HardwareOnSail hardwareOnSail = hardwareOnSailRepo.findById(id).orElse(new HardwareOnSail());
            Integer oldNum = hardwareOnSail.getNumberOfHardware();
            hardwareOnSail.setNumberOfHardware(NumberOfHardware);
            hardwareOnSail.setPrice(howHardwareCost(hardwareOnSail.getDeliverieHardware_id().getId(), NumberOfHardware));
            hardwareOnSailRepo.save(hardwareOnSail);
            reSailPrice(howHardwareCost(hardwareOnSail.getDeliverieHardware_id().getId(), oldNum),
                    hardwareOnSail.getPrice(), hardwareOnSail.getSail_id().getId());
        }
        return hardwareOnSail(model);
    }

    private void reSailPrice(Double oldPrice, Double newPrice, Integer sail_id) {
        Sail sail = sailRepo.findById(sail_id).orElse(new Sail());
        sail.setPrice(sail.getPrice()-oldPrice+newPrice);
        sailRepo.save(sail);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////



    private Double howHardwareCost(Integer deliverieHardware_id, Integer number) {
        return deliverieHardwareRepo.findById(deliverieHardware_id).isPresent() ?
                deliverieHardwareRepo.findById(deliverieHardware_id).orElse(new DeliverieHardware()).getHardware_id().getPrice()
                        * number : 0.0;
    }

    private boolean findUsagesDeliverieHardware(@RequestParam Integer id) {
        Iterable<DeliverieHardware> deliverieHardwares = deliverieHardwareRepo.findAll();
        for (DeliverieHardware deliverieHardware : deliverieHardwares) {
            if (deliverieHardware.getHardware_id().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean findUsagesDeliverieCloth(@RequestParam Integer id) {
        Iterable<DeliverieCloth> deliverieCloths = deliverieClothRepo.findAll();
        for (DeliverieCloth deliverieCloth : deliverieCloths) {
            if (deliverieCloth.getCloth_id().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
