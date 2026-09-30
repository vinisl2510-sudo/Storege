package com.storege.backend.repository;

import com.storege.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("""
            SELECT p FROM Produto p
            WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%'))
               OR LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))
            """)
    List<Produto> pesquisar(@Param("termo") String termo);


}
