-- Author: Wellton S. Barros

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE TABLE IF NOT EXISTS `tb_usuario` (
    `id_usuario`       INT(11)                                           NOT NULL AUTO_INCREMENT,
    `nome`             VARCHAR(45)                                       NOT NULL,
    `email`            VARCHAR(45)                                       NOT NULL,
    `senha`            VARCHAR(200)                                      NULL     DEFAULT NULL,
    `status`           ENUM ('LIBERADO', 'BLOQUEADO')                    NOT NULL,
    `id_social`        VARCHAR(60)                                       NULL     DEFAULT NULL,
    `tipo_rede_social` ENUM ('FACEBOOK', 'GOOGLE', 'LINKEDIN', 'GITHUB') NULL     DEFAULT NULL,
    PRIMARY KEY (`id_usuario`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC),
    UNIQUE INDEX `id_usuario_UNIQUE` (`id_usuario` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tb_perfil` (
    `id_perfil` INT(11)                                               NOT NULL AUTO_INCREMENT,
    `name`      ENUM ('CANDIDATO', 'ADMIN', 'TRIAGEM', 'FINALIZADOR') NOT NULL,
    `descricao` VARCHAR(45)                                           NOT NULL,
    PRIMARY KEY (`id_perfil`),
    UNIQUE INDEX `id_perfil_UNIQUE` (`id_perfil` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tb_perfis_usuario` (
    `id_usuario` INT(11) NOT NULL,
    `id_perfil`  INT(11) NOT NULL,
    PRIMARY KEY (`id_usuario`, `id_perfil`),
    INDEX `fk_tb_usuario_has_tb_perfil_tb_perfil1_idx` (`id_perfil` ASC),
    INDEX `fk_tb_usuario_has_tb_perfil_tb_usuario_idx` (`id_usuario` ASC),
    UNIQUE INDEX `id_usuario_UNIQUE` (`id_usuario` ASC, `id_perfil` ASC),
    UNIQUE INDEX `id_perfil_UNIQUE` (`id_perfil` ASC, `id_usuario` ASC),
    CONSTRAINT `fk_tb_usuario_has_tb_perfil_tb_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `tb_usuario` (`id_usuario`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_tb_usuario_has_tb_perfil_tb_perfil1`
    FOREIGN KEY (`id_perfil`)
    REFERENCES `tb_perfil` (`id_perfil`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tb_candidato` (
    `id_candidato` INT(11)     NOT NULL AUTO_INCREMENT,
    `telefone`     VARCHAR(45) NULL     DEFAULT NULL,
    `cidade`       VARCHAR(45) NOT NULL,
    `uf`           VARCHAR(2)  NOT NULL,
    PRIMARY KEY (`id_candidato`),
    UNIQUE INDEX `telefone_UNIQUE` (`telefone` ASC),
    UNIQUE INDEX `id_candidato_UNIQUE` (`id_candidato` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tb_historico` (
    `id_historico`    INT(11)                                                                                                                                  NOT NULL AUTO_INCREMENT,
    `observacao`      VARCHAR(500)                                                                                                                             NULL     DEFAULT NULL,
    `status`          ENUM ('CADASTRO', 'ENTREVISTA', 'TESTE', 'ENTREVISTA_TECNICA', 'APROVADO', 'REPROVADO', 'AGUARDANDO', 'SEM_ACORDO', 'ACORDO_AGUARDANDO') NOT NULL,
    `id_usuario`      INT(11)                                                                                                                                  NOT NULL,
    `id_candidato`    INT(11)                                                                                                                                  NOT NULL,
    `data_insert`     DATETIME                                                                                                                                 NOT NULL DEFAULT NOW(),
    `data_finalizado` DATETIME                                                                                                                                 NULL     DEFAULT NULL,
    PRIMARY KEY (`id_historico`, `id_usuario`, `id_candidato`),
    INDEX `fk_tb_historico_tb_usuario1_idx` (`id_usuario` ASC),
    INDEX `fk_tb_historico_tb_candidato1_idx` (`id_candidato` ASC),
    UNIQUE INDEX `id_historico_UNIQUE` (`id_historico` ASC),
    CONSTRAINT `fk_tb_historico_tb_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `tb_usuario` (`id_usuario`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_tb_historico_tb_candidato1`
    FOREIGN KEY (`id_candidato`)
    REFERENCES `tb_candidato` (`id_candidato`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tb_habilidade` (
    `id_habilidade` INT(11)     NOT NULL,
    `tipo`          VARCHAR(45) NOT NULL,
    `descricao`     VARCHAR(45) NULL     DEFAULT NULL,
    `data_insert`   DATETIME    NOT NULL DEFAULT NOW(),
    `id_candidato`  INT(11)     NOT NULL,
    PRIMARY KEY (`id_habilidade`, `id_candidato`),
    UNIQUE INDEX `tipo_UNIQUE` (`tipo` ASC),
    INDEX `fk_tb_habilidade_tb_candidato1_idx` (`id_candidato` ASC),
    UNIQUE INDEX `id_habilidade_UNIQUE` (`id_habilidade` ASC),
    CONSTRAINT `fk_tb_habilidade_tb_candidato1`
    FOREIGN KEY (`id_candidato`)
    REFERENCES `tb_candidato` (`id_candidato`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
