package fr.efrei.jo.client;

import fr.efrei.jo.billet.Billet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    public List<Client> findAll();
}
