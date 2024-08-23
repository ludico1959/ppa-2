package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class VagaException extends RuntimeException{
    public VagaException(String msg) {
      super (msg);
  }

}
