package vo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-13T16:37:49")
@StaticMetamodel(Mensagem.class)
public class Mensagem_ { 

    public static volatile SingularAttribute<Mensagem, String> remetente;
    public static volatile SingularAttribute<Mensagem, String> assunto;
    public static volatile SingularAttribute<Mensagem, Calendar> data;
    public static volatile SingularAttribute<Mensagem, String> mensagem;
    public static volatile SingularAttribute<Mensagem, Integer> id;
    public static volatile SingularAttribute<Mensagem, String> destinatario;

}