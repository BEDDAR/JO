package fr.efrei.jo.client;

import fr.efrei.jo.Epreuve.Epreuve;
import fr.efrei.jo.billet.AjoutBillet;
import fr.efrei.jo.billet.Billet;
import fr.efrei.jo.billet.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private BilletService billetService;

    public ClientService(ClientRepository clientRepository,BilletService billetService){
        this.clientRepository=clientRepository;
        this.billetService=billetService;
    }
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void ajoutClient(Client client) {
        clientRepository.save(client);
    }

    public Client getClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);

    }

    public ResponseEntity<?> deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateClient(Integer id,Client client) {
        Client clientAModifier = clientRepository.findById(id).orElse(null);
        if (clientAModifier != null) {
            clientAModifier.setNom(client.getNom());
            clientAModifier.setPrenom(client.getPrenom());
            clientRepository.save(clientAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean acheterBilletPossible(Client client,Billet billet) {
        for(Billet reservation :client.getReservations()){
            if(reservation.getEpreuve().getDateEpreuve().equals(billet.getEpreuve().getDateEpreuve())) {
                return false;
            }
        }
        return true;
    }
    public void ajoutBillets(Integer idClient, AjoutBillet idsBillets) {
        Client client = getClientById(idClient);
        List<Billet> billets = billetService.getAllById(idsBillets.getIds());
        for(Billet b1:billets){
            for(Billet b2:billets){
                if (b1.getEpreuve().getDateEpreuve().equals(b2.getEpreuve().getDateEpreuve()) &&!b2.equals(b1)){
                    billets.remove(b2) ;
                }
            }
        }
        for(Billet billet:billets){
            if(acheterBilletPossible(client,billet)){
                billet.setClient(client);
                billetService.save(billet);
            }
        }
    }
}
