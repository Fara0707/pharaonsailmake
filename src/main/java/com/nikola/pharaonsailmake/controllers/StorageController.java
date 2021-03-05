package com.nikola.pharaonsailmake.controllers;

import com.nikola.pharaonsailmake.domain.StorageUnit;
import com.nikola.pharaonsailmake.repos.DeliverieClothRepo;
import com.nikola.pharaonsailmake.repos.DeliverieHardwareRepo;
import com.nikola.pharaonsailmake.repos.StorageUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class StorageController {


    private final DeliverieClothRepo deliverieClothRepo;
    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final StorageUnitRepo storageUnitRepo;

    @Autowired
    public StorageController(DeliverieClothRepo deliverieClothRepo, DeliverieHardwareRepo deliverieHardwareRepo, StorageUnitRepo storageUnitRepo) {
        this.deliverieClothRepo = deliverieClothRepo;
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.storageUnitRepo = storageUnitRepo;
    }

    @GetMapping("storageUnit")
    public String storageUnit(Map<String, Object> model) {
        Iterable<StorageUnit> storageUnits = storageUnitRepo.findAll();
        model.put("StorageUnits", storageUnits);

        return "storageUnit";
    }

    @PostMapping("addStorageUnit")
    public String addStorageUnit(@RequestParam String Town, @RequestParam String Address, Map<String, Object> model) {
        if (Town.length() != 0 && Address.length() != 0) {
            StorageUnit storageUnit = new StorageUnit(Town, Address);
            storageUnitRepo.save(storageUnit);
        }
        return storageUnit(model);
    }

    @PostMapping("deleteStorageUnit")
    public String deleteStorageUnit(@RequestParam Integer id, Map<String, Object> model) {

        if (id != null) {
            if (Util.findUsagesDeliverieCloth(id, deliverieClothRepo)) return storageUnit(model);
            if (Util.findUsagesDeliverieHardware(id, deliverieHardwareRepo)) return storageUnit(model);
            if (storageUnitRepo.existsById(id)) storageUnitRepo.deleteById(id);
        }
        return storageUnit(model);
    }


}
