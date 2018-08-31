package br.com.softplan.dudebase.entities;

import br.com.softplan.dudebase.entities.enums.HistoricoStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_historico")
public class HistoricoEntity {

    @Id
    @Column(name = "id_historico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorico;

    @Basic
    @Column(name = "observacao")
    private String observacao;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HistoricoStatusEnum status;

    @Basic
    @Column(name = "data_insert")
    private LocalDateTime dataInsert;

    @Basic
    @Column(name = "data_finalizado")
    private LocalDateTime dataFinalizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato", nullable = false)
    private CandidatoEntity candidato;

}
