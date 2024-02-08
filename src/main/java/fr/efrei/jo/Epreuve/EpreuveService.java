package fr.efrei.jo.Epreuve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    public List<Epreuve> getEpruves() {
        return epreuveRepository.findAll();
    }

    public void ajoutEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public ResponseEntity<Epreuve> getEpreuveByID(Integer id) {
        Epreuve epreuve = epreuveRepository.findById(id).orElse(null);
        if (epreuve != null) {
            return new ResponseEntity<>(epreuve, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteEpreuve(Integer id) {
        if (!epreuveRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        epreuveRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateEpreuve(Integer id, Epreuve epreuve) {
        Epreuve epreuveAModifier = epreuveRepository.findById(id).orElse(null);
        if (epreuveAModifier != null) {
            epreuveAModifier.setNom(epreuve.getNom());
            epreuveAModifier.setDateEpreuve(epreuve.getDateEpreuve());
            epreuveRepository.save(epreuveAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
