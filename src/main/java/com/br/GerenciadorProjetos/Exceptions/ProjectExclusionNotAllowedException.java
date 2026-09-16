package com.br.GerenciadorProjetos.Exceptions;

public class ProjectExclusionNotAllowedException extends RuntimeException {
    public ProjectExclusionNotAllowedException() {
        super("Projetos com status iniciado ou superior não podem ser excluidos");
  }
}
