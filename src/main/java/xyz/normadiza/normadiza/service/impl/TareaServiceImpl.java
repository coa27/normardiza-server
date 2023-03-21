package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.ITareaRepo;
import xyz.normadiza.normadiza.service.ITareaService;

public class TareaServiceImpl extends CRUDImpl<Tarea, Long> implements ITareaService {


    @Autowired
    private ITareaRepo repo;

    @Override
    protected IGenericRepo<Tarea, Long> getRepo() {
        return repo;
    }

}
