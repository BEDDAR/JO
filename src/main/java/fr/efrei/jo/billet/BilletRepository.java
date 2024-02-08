package fr.efrei.jo.billet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BilletRepository extends CrudRepository<Billet, Integer> {
    public List<Billet> findAll();
}
