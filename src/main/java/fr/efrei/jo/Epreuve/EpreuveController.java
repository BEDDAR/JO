package fr.efrei.jo.Epreuve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epreuves")
public class EpreuveController {
    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public List<Epreuve> getEpreuves() {
        return epreuveService.getEpreuves();
    }
    @PostMapping
    public void ajoutEpreuve(@RequestBody Epreuve epreuve){
        epreuveService.ajoutEpreuve(epreuve);
    }

    @GetMapping("/{idEpreuve}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable Integer idEpreuve){
        return epreuveService.getEpreuveByID(idEpreuve);
    }
    @DeleteMapping("/{idEpreuve}")
    public ResponseEntity<?>deleteEpreuve(@PathVariable Integer idEpreuve){
        return epreuveService.deleteEpreuve(idEpreuve);
    }

    @PutMapping("/{idEpreuve}")
    public ResponseEntity<?>updateClient(@PathVariable Integer idEpreuve,@RequestBody Epreuve epreuve){
        return epreuveService.updateEpreuve(idEpreuve,epreuve);
    }
}
