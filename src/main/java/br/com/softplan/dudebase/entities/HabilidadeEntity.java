package br.com.softplan.dudebase.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_habilidade")
public class HabilidadeEntity {

    @Id
    @Column(name = "id_habilidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabilidade;

    @Basic
    @Column(name = "tipo")
    private String tipo;

    @Basic
    @ToString.Exclude
    @Column(name = "descricao")
    private String descricao;

    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    @Column(name = "data_insert")
    private LocalDateTime dataInsert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato", nullable = false)
    private CandidatoEntity candidato;
}
