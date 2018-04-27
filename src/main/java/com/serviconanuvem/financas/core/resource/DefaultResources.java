package com.serviconanuvem.financas.core.resource;

import com.serviconanuvem.financas.core.exceptions.FinancasException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultResources {

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<List<Map<String, Object>>> handleBadRequests(ConstraintViolationException response) {
        List<Map<String, Object>> erros = new ArrayList<>();
        for (ConstraintViolation c : response.getConstraintViolations()) {
            Map<String, Object> mapErros = new LinkedHashMap<>();
            mapErros.put("status", 500);
            mapErros.put("id", c.getPropertyPath().toString() + " da entidade " + c.getRootBeanClass().getSimpleName());
            mapErros.put("descricao", c.getMessage());
            erros.add(mapErros);
        }
        return new ResponseEntity<>(erros, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FinancasException.class, java.sql.SQLException.class})
    public ResponseEntity<List<Map<String, Object>>> handleTransporteException(FinancasException response) {
        List<Map<String, Object>> erros = new ArrayList<>();
        Map<String, Object> mapErros = new LinkedHashMap<>();
        mapErros.put("status", 500);
        mapErros.put("id", "0000");
        mapErros.put("descricao", response.getMessage());
        erros.add(mapErros);
        return new ResponseEntity<>(erros, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
