package kz.timka.tacocloudspringdatajdbc.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient implements Persistable<String> {
    //The Persistable interface provides a simple option to customize the state detection algorithm used for a specific entity class. It defines the isNew() method, which Spring Data JPA calls to determine the state of an entity object. By implementing that method, you can adjust the detection algorithm to the specific needs of your domain model.

    @Id
    private final String id;
    private final String name;
    private final Type type;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
    //The call of the isNew(S entity) method returns the detected state. Spring Data JPA supports 3 different strategies to detect the state of an entity, which I will show you in the following sections.
    // 1) Property-based state detection – the default
    //The default state detection strategy relies on your entity’s properties. If it includes a version property, Spring Data JPA uses it to detect the state. Otherwise, it uses the primary key attribute.
    //
    // 2) Version-based state detection
    //If you’re using optimistic locking to prevent concurrent modifications, you annotate one of your entity’s properties with @Version. Your persistence provider, which in most cases is Hibernate, then uses that property to track the version of that object and its mapped database record. The JPA specification defines different ways and data types to track the version of an entity. The easiest and most efficient one is a simple counter that your persistence provider increments during each write operation.
    //Spring Data JPA also uses the version property to detect new entity objects it needs to persist. The persistence provider manages the property’s value and sets it for the first time when persisting the entity. Due to that, the version property of a new entity object is null. And if it contains any value, the entity object maps an existing database record, which Spring needs to update.
    //
    // 3)Primary key-based state detection
    //The primary key-based state detection is very similar to the version-based approach. The only difference is that Spring Data JPA checks if the primary key attribute is null instead of checking the version attribute. If it’s null, Spring treats the entity as a new object and persists it. Otherwise, it expects the entity to be an existing one and updates the corresponding database record.
    //If you ever used an entity class with a programmatically assigned primary key, you might have already recognized a downside of this approach: It only works for automatically assigned primary keys, e.g., when using a database sequence or autoincrement column.
    //The reason for that is that if you assign the primary key value of a new entity object programmatically, you need to do that before calling the save method on your repository. Otherwise, the primary key value will not be set when your persistence provider persists entity object. But if you set it before calling the save method, Spring Data JPA can’t detect that you want to persist a new entity object.

//    @PostLoad
//    @PrePersist
//    void trackNotNew() {
//        this.isNew = false;
//    }


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
