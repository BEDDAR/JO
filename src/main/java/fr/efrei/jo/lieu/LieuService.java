package fr.efrei.jo.lieu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuService {
    @Autowired
    private LieuRepository lieuRepository;

    public List<Lieu> getLieux() {
        return lieuRepository.findAll();
    }

    public void ajoutLieu(Lieu lieu) {
        lieuRepository.save(lieu);
    }

    public ResponseEntity<Lieu> getLieuByID(Integer id) {
        Lieu lieu = lieuRepository.findById(id).orElse(null);
        if (lieu != null) {
            return new ResponseEntity<>(lieu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteLieu(Integer id) {
        if (!lieuRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lieuRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateLieu(Integer id, Lieu lieu) {
        Lieu lieuAModifier = lieuRepository.findById(id).orElse(null);
        if (lieuAModifier != null) {
            lieuAModifier.setNom(lieu.getNom());
            lieuAModifier.setAdresse(lieu.getAdresse());
            lieuRepository.save(lieuAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
