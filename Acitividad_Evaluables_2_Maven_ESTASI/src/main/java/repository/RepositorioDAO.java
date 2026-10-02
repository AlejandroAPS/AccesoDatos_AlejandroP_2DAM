package repository;

import java.util.List;

public interface RepositorioDAO<TipoRegistro, TipoId> {
    public boolean add(TipoRegistro data);
    public boolean remove(TipoId id);
    public TipoRegistro findById(TipoId id);
    public boolean update(TipoRegistro data);
    public List<TipoRegistro> getList();
}
