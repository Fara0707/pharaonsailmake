package com.nikola.pharaonsailmake.domain;

public class Stat {
    Double allCostSail;
    Double allBuyHardware;
    Double allBuyCloth;
    Double howClothUsed;

    public Stat(Double allCostSail, Double allBuyHardware, Double allBuyCloth, Double howClothUsed) {
        this.allCostSail = allCostSail;
        this.allBuyHardware = allBuyHardware;
        this.allBuyCloth = allBuyCloth;
        this.howClothUsed = howClothUsed;
    }
    public Stat() {
        this.allCostSail = 0.;
        this.allBuyHardware = 0.;
        this.allBuyCloth = 0.;
        this.howClothUsed = 0.;
    }

    public Double getAllCostSail() {
        return allCostSail;
    }

    public Stat setAllCostSail(Double allCostSail) {
        this.allCostSail = allCostSail;
        return this;
    }

    public Double getAllBuyHardware() {
        return allBuyHardware;
    }

    public Stat setAllBuyHardware(Double allBuyHardware) {
        this.allBuyHardware = allBuyHardware;
        return this;
    }

    public Double getAllBuyCloth() {
        return allBuyCloth;
    }

    public Stat setAllBuyCloth(Double allBuyCloth) {
        this.allBuyCloth = allBuyCloth;
        return this;
    }

    public Double getHowClothUsed() {
        return howClothUsed;
    }

    public Stat setHowClothUsed(Double howClothUsed) {
        this.howClothUsed = howClothUsed;
        return this;
    }
}
