package xyz.normadiza.normadiza.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.exceptions.customs.EntidadNoEncontradaException;
import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.repo.ITareaRepo;
import xyz.normadiza.normadiza.security.model.DetallesDelUsuario;

@Service
public class AutorizacionService {

    @Autowired
    private ITareaRepo tareaRepo;

    public boolean esSuTablero(Long idTablero){
        DetallesDelUsuario detallesDelUsuario = (DetallesDelUsuario) SecurityContextHolder.getContext().getAuthentication().getDetails();

        return detallesDelUsuario.idsTableros().contains(idTablero);
    }

    public boolean perteneceAlTablero(Long idTarea){
        Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new EntidadNoEncontradaException("No existe esta tarea"));
        DetallesDelUsuario detallesDelUsuario = (DetallesDelUsuario) SecurityContextHolder.getContext().getAuthentication().getDetails();

        return detallesDelUsuario.idsTableros().contains(tarea.getTablero().getIdTablero());
    }

}
