package xyz.normadiza.normadiza.service.impl;

import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, Id> implements ICRUD<T, Id> {

    protected abstract IGenericRepo<T, Id> getRepo();

    @Override
    public T registrar(T t) { return getRepo().save(t); }

    @Override
    public T modificar(T t) {
        return getRepo().save(t);
    }

    @Override
    public List<T> listar() {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(Id id) {
        return getRepo().findById(id).orElseThrow();
    }

    @Override
    public void eliminar(Id id) {
        getRepo().deleteById(id);
    }

}
