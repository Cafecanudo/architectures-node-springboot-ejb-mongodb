//package br.com.softplan.dudebase.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "tb_perfis_usuario")
//public class PerfisUsuarioEntity {
//    private UsuarioEntity Usuario;
//    private PerfilEntity Perfil;
//
//    @ManyToOne
//    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
//    public UsuarioEntity getUsuario() {
//        return Usuario;
//    }
//
//    public void setUsuario(UsuarioEntity usuario) {
//        Usuario = usuario;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", nullable = false)
//    public PerfilEntity getPerfil() {
//        return Perfil;
//    }
//
//    public void setPerfil(PerfilEntity perfil) {
//        Perfil = perfil;
//    }
//}
