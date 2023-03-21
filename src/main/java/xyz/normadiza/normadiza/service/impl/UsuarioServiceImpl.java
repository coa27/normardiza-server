package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.IUsuarioRepo;
import xyz.normadiza.normadiza.service.IUsuarioService;

public class UsuarioServiceImpl extends CRUDImpl<Usuario, Long> implements IUsuarioService {


    @Autowired
    private IUsuarioRepo repo;

    @Override
    protected IGenericRepo<Usuario, Long> getRepo() {
        return repo;
    }

}
