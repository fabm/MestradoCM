package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.xmodel.Localidade;
import pt.ipg.mcm.xmodel.ResGetAllLocalidades;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

@Stateless
public class LocalidadeDao {

    @EJB
    private MappedSql mappedSql;

    public ResGetAllLocalidades getAll() {

        SqlSession session = mappedSql.getSqlSession();
        try {
            return new ResGetAllLocalidades(session.<Localidade>selectList("getAllLocalidades"));
        } finally {
            session.close();
        }
    }

    public ResGetAllLocalidades getFiltered(final int pagina, final String filter) {

        SqlSession session = mappedSql.getSqlSession();
        try {
            List<Localidade> localidades = session.selectList("getLocalidadesFiltradasPaginada",
                    new HashMap<String, Object>() {{
                        put("pagina", pagina);
                        put("quantidade", 50);
                        put("filtro", "%" + filter + "%");
                    }}
            );
            return new ResGetAllLocalidades(localidades);
        } finally {
            session.close();
        }

    }

    public ResGetAllLocalidades getLocalidadesPaginada(final int page) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return new ResGetAllLocalidades(session.<Localidade>selectList("getLocalidadesPaginadas",
                    new HashMap<String, Object>() {{
                        put("pagina",page);
                        put("quantidade",50);
                    }}
            ));
        } finally {
            session.close();
        }
    }


}
