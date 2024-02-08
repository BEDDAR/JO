package fr.efrei.jo.lieu;

import fr.efrei.jo.Epreuve.AjoutEpreuve;
import fr.efrei.jo.Epreuve.Epreuve;
import fr.efrei.jo.Epreuve.EpreuveService;
import fr.efrei.jo.billet.AjoutBillet;
import fr.efrei.jo.billet.Billet;
import fr.efrei.jo.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LieuService {

    private LieuRepository lieuRepository;
    private EpreuveService epreuveService;
    public LieuService(LieuRepository lieuRepository, EpreuveService epreuveService){
        this.lieuRepository=lieuRepository;
        this.epreuveService=epreuveService;
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
    public Boolean lieuOccupe(Epreuve epreuve, Lieu lieu){
        for(Epreuve E :lieu.getEpreuves()){
            if(epreuve.getDateEpreuve()==E.getDateEpreuve()){
                return true;
            }
        }
        return false;
    }

    public void ajoutEpreuve(Integer idLieu, AjoutEpreuve idsEpreuves) {
        Lieu lieu = getLieuByID(idLieu);
        List<Epreuve> epreuves = epreuveService.getAllById(idsEpreuves.getIds());
        for(Epreuve epreuve:epreuves){
            if(!lieuOccupe(epreuve,lieu)){
                epreuve.setLieu(lieu);
                epreuveService.save(epreuve);
            }
        }
    }
}
