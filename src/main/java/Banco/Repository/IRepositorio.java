package Banco.Repository;

import Banco.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorio extends JpaRepository<Usuario, Long>{
    void deleteAllById(Long id);
}
