package fr.efrei.jo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }
    @PostMapping
    public void ajoutClient(@RequestBody Client client){
        clientService.ajoutClient(client);
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer idClient){
        return clientService.getClientByID(idClient);
    }
    @DeleteMapping("/{idBillet}")
    public ResponseEntity<?>deleteClient(@PathVariable Integer idClient){
        return clientService.deleteClient(idClient);
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<?>updateClient(@PathVariable Integer idClient,@RequestBody Client client){
        return clientService.updateClient(idClient,client);
    }
}
