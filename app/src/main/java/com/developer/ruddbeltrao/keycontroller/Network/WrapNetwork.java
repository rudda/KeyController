package com.developer.ruddbeltrao.keycontroller.Network;

import com.developer.ruddbeltrao.keycontroller.domain.Adm;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Check;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;

import java.util.List;

/**
 * Created by Dev Aux on 10/01/2016.
 */
public class WrapNetwork {

    private String      method;
    private Aluno       aluno;
    private Professor   professor;
    private Servidor    servidor;
    private Guarda      guarda;
    private Adm         adm;
    private Check       check;
    private Lab         lab;

    private List <Aluno>  Laluno;
    private List <Professor>  Lprofessor;
    private List <Servidor>  Lservidor;
    private List <Guarda>  Lguarda;
    private List <Adm>  Ladm;
    private List <Check>  Lcheck;
    private List <Lab>  Llab;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Guarda getGuarda() {
        return guarda;
    }

    public void setGuarda(Guarda guarda) {
        this.guarda = guarda;
    }

    public Adm getAdm() {
        return adm;
    }

    public void setAdm(Adm adm) {
        this.adm = adm;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public List<Aluno> getLaluno() {
        return Laluno;
    }

    public void setLaluno(List<Aluno> laluno) {
        Laluno = laluno;
    }

    public List<Professor> getLprofessor() {
        return Lprofessor;
    }

    public void setLprofessor(List<Professor> lprofessor) {
        Lprofessor = lprofessor;
    }

    public List<Servidor> getLservidor() {
        return Lservidor;
    }

    public void setLservidor(List<Servidor> lservidor) {
        Lservidor = lservidor;
    }

    public List<Guarda> getLguarda() {
        return Lguarda;
    }

    public void setLguarda(List<Guarda> lguarda) {
        Lguarda = lguarda;
    }

    public List<Adm> getLadm() {
        return Ladm;
    }

    public void setLadm(List<Adm> ladm) {
        Ladm = ladm;
    }

    public List<Check> getLcheck() {
        return Lcheck;
    }

    public void setLcheck(List<Check> lcheck) {
        Lcheck = lcheck;
    }

    public List<Lab> getLlab() {
        return Llab;
    }

    public void setLlab(List<Lab> llab) {
        Llab = llab;
    }
}
