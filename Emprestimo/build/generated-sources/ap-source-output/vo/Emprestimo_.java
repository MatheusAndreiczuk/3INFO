package vo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-11T16:00:42")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ { 

    public static volatile SingularAttribute<Emprestimo, Date> data_emprestimo;
    public static volatile SingularAttribute<Emprestimo, Date> data_prevista;
    public static volatile SingularAttribute<Emprestimo, Integer> id_emprestimo;
    public static volatile SingularAttribute<Emprestimo, Integer> id_item;
    public static volatile SingularAttribute<Emprestimo, String> cpf;
    public static volatile SingularAttribute<Emprestimo, Date> data_devolucao;
    public static volatile SingularAttribute<Emprestimo, Integer> emprestado;
    public static volatile SingularAttribute<Emprestimo, String> nome_item;

}