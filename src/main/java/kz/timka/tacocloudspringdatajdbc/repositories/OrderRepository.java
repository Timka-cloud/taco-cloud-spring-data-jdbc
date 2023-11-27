package kz.timka.tacocloudspringdatajdbc.repositories;

import kz.timka.tacocloudspringdatajdbc.data.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
