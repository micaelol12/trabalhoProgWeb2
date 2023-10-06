package com.mlconti.demo.domain;

public class DeleteError {
    
    public DeleteError() {
    }
    
    public DeleteError(String type, String mensagem) {
        this.type = type;
        this.mensagem = mensagem;
    }

    private String type;
    private String mensagem;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    

}
