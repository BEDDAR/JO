package fr.efrei.jo.Epreuve;

import fr.efrei.jo.billet.AjoutBillet;
import fr.efrei.jo.billet.Billet;
import fr.efrei.jo.billet.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EpreuveService {

    private EpreuveRepository epreuveRepository;
    private BilletService billetService;

    public EpreuveService(EpreuveRepository epreuveRepository, BilletService billetService) {
        this.epreuveRepository = epreuveRepository;
        this.billetService = billetService;
    }

    public List<Epreuve> getEpreuves() {
        return epreuveRepository.findAll();
    }

    public void ajoutEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public Epreuve getEpreuveByID(Integer id) {
        return epreuveRepository.findById(id).orElse(null);
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

    public List<Epreuve> getAllById(List<Integer> ids) {
        return epreuveRepository.findAllByIdIn(ids);
    }

    public void save(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public void AjoutBillets(Integer idEpreuve, AjoutBillet idsbillet) {
        Epreuve epreuve = getEpreuveByID(idEpreuve);
        List<Billet> billets = billetService.getAllById(idsbillet.getIds());
        billets.forEach(billet -> {
            billet.setEpreuve(epreuve);
            billetService.save(billet);
        });
    }

}
