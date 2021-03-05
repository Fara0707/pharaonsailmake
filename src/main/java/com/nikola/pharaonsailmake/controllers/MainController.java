package com.nikola.pharaonsailmake.controllers;

import com.nikola.pharaonsailmake.domain.Stat;
import com.nikola.pharaonsailmake.repos.DeliverieClothRepo;
import com.nikola.pharaonsailmake.repos.DeliverieHardwareRepo;
import com.nikola.pharaonsailmake.repos.SailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    private final DeliverieClothRepo deliverieClothRepo;
    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final SailRepo sailRepo;

    @Autowired
    public MainController(DeliverieClothRepo deliverieClothRepo, DeliverieHardwareRepo deliverieHardwareRepo,
                          SailRepo sailRepo) {
        this.deliverieClothRepo = deliverieClothRepo;
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.sailRepo = sailRepo;
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

    @GetMapping("stat")
    public String stat(Map<String, Object> model) {

        Stat stat = new Stat(sailRepo.allCostSail(), deliverieHardwareRepo.allBuyHardware(), deliverieClothRepo.allBuyCloth(),
                sailRepo.howClothUsed());
        model.put("Stat", stat);

        return "stat";
    }

}
