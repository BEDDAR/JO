package fr.efrei.jo.billet;

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
    @Autowired
    private BilletRepository billetRepository;

    public List<Billet> getBillets() {
        return billetRepository.findAll();
    }

    public void ajoutBillets(Billet billet) {
        billetRepository.save(billet);
    }

    public ResponseEntity<Billet> getBilletByID(Integer id) {
        Billet billet = billetRepository.findById(id).orElse(null);
        if (billet != null) {
            return new ResponseEntity<>(billet, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteBillet(Integer id) {
        if (!billetRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        billetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateBillet(Integer id,Billet billet) {
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

    public List<Billet>getAllById(List<Integer> ids){
        return billetRepository.findAllByIdIn(ids);
    }

    public void save(Billet billet) {
        billetRepository.save(billet);
    }
}
