package br.com.TrinketStore.Repository;

import br.com.TrinketStore.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}