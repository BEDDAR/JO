package fr.efrei.jo.lieu;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LieuRepository extends CrudRepository<Lieu,Integer> {
    public List<Lieu> findAll();
}
