package dao;

import db.ConexionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;

public class SerieTresDAO {

    public void guardarResultado(int limite, int resultado) throws Exception {
        String sql = "{CALL SP_GUARDAR_SERIE_TRES(?, ?)}";

        try (Connection con = ConexionOracle.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, limite);
            cs.setInt(2, resultado);
            cs.execute();
        }
    }
}
