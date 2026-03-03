package service;

import dao.SerieTresDAO;

public class SerieTresService {

    private final SerieTresDAO dao = new SerieTresDAO();

    public int calcularSuma(int limite) {
        int suma = 0;
        for (int i = 3; i <= limite; i += 3) {
            suma += i;
        }
        return suma;
    }

    public void procesarSerie(int limite) throws Exception {
        int resultado = calcularSuma(limite);
        dao.guardarResultado(limite, resultado);
    }
}
