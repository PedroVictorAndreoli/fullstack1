package br.com.jtech.tasklist.application.ports.output;

import br.com.jtech.tasklist.application.core.domains.Tasklist;

import java.util.List;

public interface GetTasklistOutputGateway {
    List<Tasklist> findAll();
    Tasklist findById(String id);
}