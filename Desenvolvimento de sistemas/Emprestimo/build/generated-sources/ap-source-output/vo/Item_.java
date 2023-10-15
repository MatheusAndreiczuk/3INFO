package vo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-11T16:00:42")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> caracteristicas;
    public static volatile SingularAttribute<Item, Integer> id_item;
    public static volatile SingularAttribute<Item, String> num_serie;
    public static volatile SingularAttribute<Item, String> nome;
    public static volatile SingularAttribute<Item, Date> fabricacao;
    public static volatile SingularAttribute<Item, String> descricao;

}