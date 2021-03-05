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
public class HardwareController {

    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final HardwareRepo hardwareRepo;
    private final SailRepo sailRepo;
    private final HardwareOnSailRepo hardwareOnSailRepo;
    private final HardwareTypeRepo hardwareTypeRepo;

    @Autowired
    public HardwareController(DeliverieHardwareRepo deliverieHardwareRepo, HardwareRepo hardwareRepo, SailRepo sailRepo,
                              HardwareOnSailRepo hardwareOnSailRepo, HardwareTypeRepo hardwareTypeRepo) {
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.hardwareRepo = hardwareRepo;
        this.sailRepo = sailRepo;
        this.hardwareOnSailRepo = hardwareOnSailRepo;
        this.hardwareTypeRepo = hardwareTypeRepo;
    }

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

        if (Name.length() != 0 && Type_id != null && hardwareTypeRepo.existsById(Type_id) && Price != null) {
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
        if (Type != null) {
            HardwareType hardwareType = new HardwareType(Type);
            hardwareTypeRepo.save(hardwareType);
        }
        return hardware(model);
    }


    @GetMapping("hardwareOnSail")
    public String hardwareOnSail(Map<String, Object> model) {
        Iterable<HardwareOnSail> hardwareOnSails = hardwareOnSailRepo.findAll();
        model.put("HardwareOnSail", hardwareOnSails);

        return "hardwareOnSail";
    }

    @PostMapping("addHardwareOnSail")
    public String addHardwareOnSail(@RequestParam Integer DeliverieHardware_id, @RequestParam Integer Sail_id,
                                    @RequestParam Integer NumberOfHardware, Map<String, Object> model) {
        if (DeliverieHardware_id != null && deliverieHardwareRepo.existsById(DeliverieHardware_id) &&
                Sail_id != null && sailRepo.existsById(Sail_id) && NumberOfHardware != null) {
            HardwareOnSail hardwareOnSail = new HardwareOnSail(
                    deliverieHardwareRepo.findById(DeliverieHardware_id).orElse(new DeliverieHardware()),
                    sailRepo.findById(Sail_id).orElse(new Sail()), NumberOfHardware,
                    howHardwareCost(DeliverieHardware_id, NumberOfHardware));
            hardwareOnSailRepo.save(hardwareOnSail);
            Sail sail = sailRepo.findById(Sail_id).orElse(new Sail());
            sail.setPrice(sail.getPrice() + hardwareOnSail.getPrice());
            sailRepo.save(sail);
        }
        return hardwareOnSail(model);
    }


    @PostMapping("deleteHardwareOnSail")
    public String deleteHarwareOnSail(@RequestParam Integer id, Map<String, Object> model) {
        if (id != null && hardwareOnSailRepo.existsById(id)) {
            HardwareOnSail hardwareOnSail = hardwareOnSailRepo.findById(id).orElse(new HardwareOnSail());

            reSailPrice(hardwareOnSail.getPrice(), 0., hardwareOnSail.getSail_id().getId());
            hardwareOnSailRepo.deleteById(id);
        }
        return hardwareOnSail(model);
    }

    @PostMapping("editHardwareOnSail")
    public String editHarwareOnSail(@RequestParam Integer id, @RequestParam Integer NumberOfHardware,
                                    Map<String, Object> model) {
        if (id != null && hardwareOnSailRepo.existsById(id) && NumberOfHardware != null) {
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
        sail.setPrice(sail.getPrice() - oldPrice + newPrice);
        sailRepo.save(sail);
    }

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


}
