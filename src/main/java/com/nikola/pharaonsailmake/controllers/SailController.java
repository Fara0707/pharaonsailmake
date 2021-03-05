package com.nikola.pharaonsailmake.controllers;

import com.nikola.pharaonsailmake.domain.*;
import com.nikola.pharaonsailmake.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SailController {


    private final ClientRepo clientRepo;
    private final ClothRepo clothRepo;
    private final DeliverieClothRepo deliverieClothRepo;
    private final SailRepo sailRepo;
    private final WorkerRepo workerRepo;
    private final ClothTypeRepo clothTypeRepo;
    private final SailTypeRepo sailTypeRepo;
    private final HardwareOnSailRepo hardwareOnSailRepo;
    private final SailCutRepo sailCutRepo;

    @Autowired
    public SailController(ClientRepo clientRepo, ClothRepo clothRepo, DeliverieClothRepo deliverieClothRepo,
                          SailRepo sailRepo, WorkerRepo workerRepo, ClothTypeRepo clothTypeRepo, SailTypeRepo sailTypeRepo,
                          HardwareOnSailRepo hardwareOnSailRepo, SailCutRepo sailCutRepo) {
        this.clientRepo = clientRepo;
        this.clothRepo = clothRepo;
        this.deliverieClothRepo = deliverieClothRepo;
        this.sailRepo = sailRepo;
        this.workerRepo = workerRepo;
        this.clothTypeRepo = clothTypeRepo;
        this.sailTypeRepo = sailTypeRepo;
        this.hardwareOnSailRepo = hardwareOnSailRepo;
        this.sailCutRepo = sailCutRepo;
    }


    @GetMapping("cloth")
    public String cloth(Map<String, Object> model) {
        initClothType();
        Iterable<ClothType> clothTypes = clothTypeRepo.findAll();
        model.put("ClothTypes", clothTypes);

        Iterable<Cloth> cloths = clothRepo.findAll();
        model.put("Cloths", cloths);

        return "cloth";
    }

    private void initClothType() {

        if (!clothTypeRepo.findAll().iterator().hasNext()) {
            clothTypeRepo.save(new ClothType("Спинакерная"));
            clothTypeRepo.save(new ClothType("Лавировочная"));
        }
    }

    @PostMapping("addCloth")
    public String addCloth(@RequestParam Integer Type_id, @RequestParam String Name, @RequestParam Integer Width,
                           @RequestParam Double Weight, @RequestParam Double Price, Map<String, Object> model) {

        if (Type_id != null && clothTypeRepo.existsById(Type_id) && Name.length() != 0 && Width != null && Weight != null && Price != null) {
            Cloth cloth = new Cloth(clothTypeRepo.findById(Type_id).orElse(new ClothType()), Name, Width, Weight, Price);
            clothRepo.save(cloth);
        }
        return cloth(model);
    }

    @PostMapping("deleteCloth")
    public String deleteCloth(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            if (Util.findUsagesDeliverieCloth(id, deliverieClothRepo)) return cloth(model);
            if (clothRepo.existsById(id))
                clothRepo.deleteById(id);
        }
        return cloth(model);
    }


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

    private void initSailType() {

        if (!sailTypeRepo.findAll().iterator().hasNext()) {
            sailTypeRepo.save(new SailType("Грот"));
            sailTypeRepo.save(new SailType("Стаксель"));
            sailTypeRepo.save(new SailType("Спинакер"));
            sailTypeRepo.save(new SailType("Генакер"));
        }
    }


    private void initCutType() {
        if (!sailCutRepo.findAll().iterator().hasNext()) {
            sailCutRepo.save(new SailCut("Радиальный"));
            sailCutRepo.save(new SailCut("Параллельный"));
            sailCutRepo.save(new SailCut("Комбинированный"));
        }
    }


    @PostMapping("addSail")
    public String addSail(@RequestParam Integer SailType_id, @RequestParam Integer Cut_id, @RequestParam Integer LengthLeech,
                          @RequestParam Integer LengthLuff, @RequestParam Integer LengthFoot, @RequestParam Integer SickleLeech,
                          @RequestParam Integer SickleLuff, @RequestParam Integer SickleFoot,
                          @RequestParam Integer Seam, @RequestParam Double ElapsedLength,
                          @RequestParam Integer Client_id, @RequestParam Integer Worker_id, @RequestParam Integer DeliverieCloth_id,
                          Map<String, Object> model) {

        if (Cut_id != null && sailCutRepo.existsById(Cut_id) && LengthLeech != null && LengthFoot != null && LengthLuff != null && SickleLeech != null && SickleLuff != null && SickleFoot != null
                && Seam != null && ElapsedLength != null && Client_id != null && clientRepo.existsById(Client_id) && Worker_id != null &&
                workerRepo.existsById(Worker_id) && DeliverieCloth_id != null && deliverieClothRepo.existsById(DeliverieCloth_id)
                && SailType_id != null && sailTypeRepo.existsById(SailType_id)) {


            Double PriceCloth = (workerRepo.findById(Worker_id).orElse(new Worker()).getWorkMeter() +
                    deliverieClothRepo.findById(DeliverieCloth_id).orElse(new DeliverieCloth()).getCloth_id().getPrice()) * ElapsedLength;


            Sail sail = new Sail(sailCutRepo.findById(Cut_id).orElse(new SailCut()), LengthLeech, LengthLuff, LengthFoot, SickleLeech, SickleLuff, SickleFoot, Seam, ElapsedLength,
                    PriceCloth, sailTypeRepo.findById(SailType_id).orElse(new SailType()), clientRepo.findById(Client_id).orElse(new Client()), workerRepo.findById(Worker_id).orElse(new Worker()),
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


}
