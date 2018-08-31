package br.com.softplan.dudebase.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "tb_candidato")
public class CandidatoEntity {

    @Id
    @Column(name = "id_candidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCandidato;

    @Basic
    @Column(name = "telefone")
    private String telefone;

    @Basic
    @ToString.Exclude
    @Column(name = "cidade")
    private String cidade;

    @Basic
    @ToString.Exclude
    @Column(name = "uf")
    private String uf;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private Collection<HabilidadeEntity> Habilidades;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private Collection<HistoricoEntity> Historicos;

}
