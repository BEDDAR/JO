package fr.efrei.jo.lieu;

import fr.efrei.jo.Epreuve.AjoutEpreuves;
import fr.efrei.jo.Epreuve.Epreuve;
import fr.efrei.jo.Epreuve.EpreuveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuService {

    private LieuRepository lieuRepository;
    private EpreuveService epreuveService;

    public LieuService(LieuRepository lieuRepository, EpreuveService epreuveService) {
        this.lieuRepository = lieuRepository;
        this.epreuveService = epreuveService;
    }

    public List<Lieu> getLieux() {
        return lieuRepository.findAll();
    }

    public void ajoutLieu(Lieu lieu) {
        lieuRepository.save(lieu);
    }

    public Lieu getLieuByID(Integer id) {
        return lieuRepository.findById(id).orElse(null);
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

    public Boolean lieuOccupe(Epreuve epreuve, Lieu lieu) {
        for (Epreuve E : lieu.getEpreuves()) {
            if (epreuve.getDateEpreuve().equals(E.getDateEpreuve())) {
                return true;
            }
        }
        return false;
    }


    public void ajoutEpreuve(Integer idLieu, AjoutEpreuves idsEpreuves) {
        Lieu lieu = getLieuByID(idLieu);
        List<Epreuve> epreuves = epreuveService.getAllById(idsEpreuves.getIds());
        for (Epreuve E : epreuves) {
            for (Epreuve E2 : epreuves) {
                if (E.getDateEpreuve().equals(E2.getDateEpreuve()) && !E.equals(E2)) {
                    epreuves.remove(E2);
                }
            }
        }
        for (Epreuve epreuve : epreuves) {
            if (!lieuOccupe(epreuve, lieu)) {
                epreuve.setLieu(lieu);
                epreuveService.save(epreuve);
            }
        }
    }
}
