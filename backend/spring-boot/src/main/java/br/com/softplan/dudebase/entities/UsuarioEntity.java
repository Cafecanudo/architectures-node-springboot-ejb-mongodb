package br.com.softplan.dudebase.entities;

import br.com.softplan.dudebase.entities.enums.TipoRedeSocialEnum;
import br.com.softplan.dudebase.entities.enums.UsuarioStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "senha")
    private String senha;

    @Basic
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioStatusEnum status;

    @Basic
    @Column(name = "id_social", unique = true)
    private String idSocial;

    @Basic
    @Column(name = "tipo_rede_social")
    @Enumerated(EnumType.STRING)
    private TipoRedeSocialEnum tipoRedeSocial;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_perfis_usuario",
            joinColumns = {
                    @JoinColumn(name = "id_usuario")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_perfil")
            }
    )
    private Collection<PerfilEntity> PerfisUsuario;
}
