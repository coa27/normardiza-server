package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.normadiza.normadiza.model.Rol;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.IRolRepo;
import xyz.normadiza.normadiza.service.IRolService;

public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {


    @Autowired
    private IRolRepo repo;

    @Override
    protected IGenericRepo<Rol, Integer> getRepo() {
        return repo;
    }

}
