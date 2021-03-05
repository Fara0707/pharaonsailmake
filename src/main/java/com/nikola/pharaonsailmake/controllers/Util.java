package com.nikola.pharaonsailmake.controllers;

import com.nikola.pharaonsailmake.domain.DeliverieCloth;
import com.nikola.pharaonsailmake.domain.DeliverieHardware;
import com.nikola.pharaonsailmake.repos.DeliverieClothRepo;
import com.nikola.pharaonsailmake.repos.DeliverieHardwareRepo;

public class Util {

    public static boolean findUsagesDeliverieHardware(Integer id, DeliverieHardwareRepo deliverieHardwareRepo) {
        Iterable<DeliverieHardware> deliverieHardwares = deliverieHardwareRepo.findAll();
        for (DeliverieHardware deliverieHardware : deliverieHardwares) {
            if (deliverieHardware.getHardware_id().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findUsagesDeliverieCloth(Integer id, DeliverieClothRepo deliverieClothRepo) {
        Iterable<DeliverieCloth> deliverieCloths = deliverieClothRepo.findAll();
        for (DeliverieCloth deliverieCloth : deliverieCloths) {
            if (deliverieCloth.getCloth_id().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
