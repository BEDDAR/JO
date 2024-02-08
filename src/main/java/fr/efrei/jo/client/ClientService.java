package fr.efrei.jo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void ajoutClient(Client client) {
        clientRepository.save(client);
    }

    public ResponseEntity<Client> getClientByID(Integer id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
}
