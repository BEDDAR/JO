package fr.efrei.jo.lieu;

import fr.efrei.jo.Epreuve.AjoutEpreuve;
import fr.efrei.jo.billet.AjoutBillet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieux")
public class LieuController {
    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<Lieu> getLieux() {
        return lieuService.getLieux();
    }
    @PostMapping
    public void ajoutLieu(@RequestBody Lieu lieu){
        lieuService.ajoutLieu(lieu);
    }

    @GetMapping("/{idLieu}")
    public Lieu getLieuById(@PathVariable Integer idLieu){
        return lieuService.getLieuByID(idLieu);
    }
    @DeleteMapping("/{idLieu}")
    public ResponseEntity<?>deleteLieu(@PathVariable Integer idLieu){
        return lieuService.deleteLieu(idLieu);
    }

    @PutMapping("/{idLieu}")
    public ResponseEntity<?>updateClient(@PathVariable Integer idLieu,@RequestBody Lieu lieu){
        return lieuService.updateLieu(idLieu,lieu);
    }

    @PatchMapping("/{idLieu}/epreuves")
    public ResponseEntity<?> ajoutEpreuve (@PathVariable Integer idLieu, @RequestBody AjoutEpreuve idsEpreuve){
        lieuService.ajoutEpreuve(idLieu, idsEpreuve);
        return ResponseEntity.noContent().build();
    }
}
