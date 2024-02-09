package fr.efrei.jo.lieu;

import fr.efrei.jo.Epreuve.AjoutEpreuves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieux")
public class LieuController {
    @Autowired
    private LieuService lieuService;

    @GetMapping
    public ResponseEntity<List<Lieu>>getLieux() {

        return new ResponseEntity<>(lieuService.getLieux(),HttpStatus.OK);
    }
    @PostMapping
    public void ajoutLieu(@RequestBody Lieu lieu){
        lieuService.ajoutLieu(lieu);
    }

    @GetMapping("/{idLieu}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable Integer idLieu){

        Lieu lieu = lieuService.getLieuByID(idLieu);
        if(lieu == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(lieu, HttpStatus.OK);
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
    public ResponseEntity<?> ajoutEpreuve (@PathVariable Integer idLieu, @RequestBody AjoutEpreuves idsEpreuve){
        lieuService.ajoutEpreuve(idLieu, idsEpreuve);
        return ResponseEntity.noContent().build();
    }
}
