package br.imd.ufrn.checklist;

public class Veiculo {
    private int id;
    private String placa;
    private String frota;
    private String modelo;

    public Veiculo(String placa, String frota, String modelo) {
        this.placa = placa;
        this.frota = frota;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFrota() {
        return frota;
    }

    public void setFrota(String frota) {
        this.frota = frota;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}

