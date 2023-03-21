package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.normadiza.normadiza.model.Tablero;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.ITableroRepo;
import xyz.normadiza.normadiza.service.ITableroService;

public class TableroServiceImpl extends CRUDImpl<Tablero, Long> implements ITableroService {


    @Autowired
    private ITableroRepo repo;

    @Override
    protected IGenericRepo<Tablero, Long> getRepo() {
        return repo;
    }

}
