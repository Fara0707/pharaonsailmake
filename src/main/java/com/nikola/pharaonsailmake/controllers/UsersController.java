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
public class UsersController {

    private final ClientRepo clientRepo;
    private final DealerRepo dealerRepo;
    private final DeliverieClothRepo deliverieClothRepo;
    private final DeliverieHardwareRepo deliverieHardwareRepo;
    private final SailRepo sailRepo;
    private final WorkerRepo workerRepo;

    @Autowired
    public UsersController(ClientRepo clientRepo, DealerRepo dealerRepo, DeliverieClothRepo deliverieClothRepo,
                           DeliverieHardwareRepo deliverieHardwareRepo, SailRepo sailRepo, WorkerRepo workerRepo) {
        this.clientRepo = clientRepo;
        this.dealerRepo = dealerRepo;
        this.deliverieClothRepo = deliverieClothRepo;
        this.deliverieHardwareRepo = deliverieHardwareRepo;
        this.sailRepo = sailRepo;
        this.workerRepo = workerRepo;
    }


    @GetMapping("worker")
    public String worker(Map<String, Object> model) {

        Iterable<Worker> workers = workerRepo.findAll();
        model.put("Workers", workers);

        return "worker";
    }

    @PostMapping("addWorker")
    public String addWorker(@RequestParam String Name, @RequestParam Double workMeter, Map<String, Object> model) {
        if (Name.length() != 0 && workMeter != null) {
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

    @GetMapping("client")
    public String client(Map<String, Object> model) {

        Iterable<Client> clients = clientRepo.findAll();
        model.put("Clients", clients);

        return "client";
    }

    @PostMapping("addClient")
    public String addClient(@RequestParam String SurName, @RequestParam String FirstName, @RequestParam String Town,
                            @RequestParam String MobileTel, @RequestParam Integer NewPostNumber, Map<String, Object> model) {
        if (SurName.length() != 0 && FirstName.length() != 0 && Town.length() != 0 && MobileTel != null && NewPostNumber != null) {
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

    @GetMapping("dealer")
    public String dealer(Map<String, Object> model) {

        Iterable<Dealer> clients = dealerRepo.findAll();
        model.put("Dealers", clients);

        return "dealer";
    }

    @PostMapping("addDealer")
    public String addDealer(@RequestParam String Name, Map<String, Object> model) {
        if (Name.length() != 0) {
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

}
