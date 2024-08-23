package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super (msg);
    }
    
}
