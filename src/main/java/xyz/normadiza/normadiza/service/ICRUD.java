package xyz.normadiza.normadiza.service;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, Id> {

    T registrar(T t);
    T modificar(T t);
    List<T> listar();
    T listarPorId(Id id);
    void eliminar(Id id);

}
