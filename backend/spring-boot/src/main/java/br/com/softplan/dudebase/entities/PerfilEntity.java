package br.com.softplan.dudebase.entities;

import br.com.softplan.dudebase.entities.enums.TipoPerfilEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_perfil")
public class PerfilEntity {

    @Id
    @Column(name = "id_perfil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;

    @Basic
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private TipoPerfilEnum name;

    @Basic
    @ToString.Exclude
    @Column(name = "descricao")
    private String descricao;

}
