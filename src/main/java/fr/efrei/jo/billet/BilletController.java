package fr.efrei.jo.billet;

import fr.efrei.jo.Epreuve.AjoutEpreuve;
import fr.efrei.jo.Epreuve.AjoutEpreuves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billets")
public class BilletController {
    @Autowired
    private BilletService billetService;

    @GetMapping
    public List<Billet> getBillets() {
        return billetService.getBillets();
    }
    @PostMapping
    public void ajoutBillet(@RequestBody Billet billet){
        billetService.ajoutBillets(billet);
    }

    @GetMapping("/{idBillet}")
    public ResponseEntity<Billet> getBilletById(@PathVariable Integer idBillet){
        Billet billet = billetService.getBilletByID(idBillet);
        if(billet == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(billet, HttpStatus.OK);
    }
    @DeleteMapping("/{idBillet}")
    public ResponseEntity<?>deleteBillet(@PathVariable Integer idBillet){
        return billetService.deleteBillet(idBillet);
    }

    @PutMapping("/{idBillet}")
    public ResponseEntity<?>updateBillet(@PathVariable Integer idBillet,@RequestBody Billet billet){
        return billetService.updateBillet(idBillet,billet);
    }

}
