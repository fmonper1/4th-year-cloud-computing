package ac.uk.shef.cc19grp10.demo.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DemoRepository extends CrudRepository<DemoEntity, Long> {

    List<DemoEntity> findBySomeField(String someField);
}
