package ac.uk.shef.cc19grp10.demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DemoEntity {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public String getSomeField() {
        return someField;
    }

    public void setSomeField(String someField) {
        this.someField = someField;
    }

    private String someField;

    public DemoEntity() {}

    public DemoEntity(String someField, String lastName) {
        this.someField = someField;
    }

    @Override
    public String toString() {
        return String.format(
                "DemoEntity[id=%d, someField='%s', lastName='%s']",
                id, someField);
    }

}