package fr.efrei.jo.Epreuve;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EpreuveRepository extends CrudRepository<Epreuve,Integer> {
    public List<Epreuve> findAll();
    public List<Epreuve>findAllByIdIn(List<Integer> ids);
}
