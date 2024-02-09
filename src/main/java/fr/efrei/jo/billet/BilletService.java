package fr.efrei.jo.billet;

import fr.efrei.jo.Epreuve.AjoutEpreuve;
import fr.efrei.jo.Epreuve.AjoutEpreuves;
import fr.efrei.jo.Epreuve.Epreuve;
import fr.efrei.jo.Epreuve.EpreuveService;
import fr.efrei.jo.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BilletService {

    private BilletRepository billetRepository;
    private EpreuveService epreuveService;

    public BilletService(BilletRepository billetRepository, EpreuveService epreuveService) {
        this.billetRepository = billetRepository;
        this.epreuveService = epreuveService;
    }

    public List<Billet> getBillets() {
        return billetRepository.findAll();
    }

    public void ajoutBillets(Billet billet) {
        billetRepository.save(billet);
    }

    public Billet getBilletByID(Integer id) {
        return billetRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> deleteBillet(Integer id) {
        if (!billetRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        billetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateBillet(Integer id, Billet billet) {
        Billet billetAModifier = billetRepository.findById(id).orElse(null);
        if (billetAModifier != null) {
            billetAModifier.setReference(billet.getReference());
            billetAModifier.setDateReservation(billet.getDateReservation());
            billetAModifier.setDateEvenement(billet.getDateEvenement());
            billetRepository.save(billetAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Billet> getAllById(List<Integer> ids) {
        return billetRepository.findAllByIdIn(ids);
    }

    public void save(Billet billet) {
        billetRepository.save(billet);
    }

    public void AjoutEpreuve(Integer idBillet, AjoutEpreuve idEpreuve) {
        Billet billet = getBilletByID(idBillet);
        Epreuve epreuve = epreuveService.getEpreuveByID(idEpreuve.getId());
        billet.setEpreuve(epreuve);
        billetRepository.save(billet);
    }
}
