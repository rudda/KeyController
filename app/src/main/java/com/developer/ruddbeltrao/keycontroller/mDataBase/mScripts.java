package com.developer.ruddbeltrao.keycontroller.mDataBase;

/**
 * Created by Rudda Beltrao Rudda - Lab 312 - ICET/UFAM on 17/07/2015 as 14:12
 contact : beltrao.rudah@gmail.com

 */
public class mScripts {

        //nome das tabelas
        public static final String TAB_ALUNO= "aluno";
        public static final String TAB_PROFESSOR = "professor";
        public static final String TAB_ADM= "Adm";
        public static final String TAB_USUARIO= "usuario";
        public static final String TAB_LAB = "lab";
        public static final String TAB_USUARIO_HAS_LAB = "usuario_has_lab";
        public static final String TAB_USUARIO_PERTENCE_LAB= "usuario_pertence_lab";
        public static final String TAB_SERVIDOR= "servidor";
        public static final String TAB_GUARDA= "guarda";
        public static final String TAB_CHECK_IN= "checkin";
        public static final String TAB_CHECK_OUT= "checkout";





        //CREATE SCRIPTS...




    public static String CREATE_TABLE_LAB = "CREATE TABLE lab (" +
            "  lab_numero INTEGER  NOT NULL," +
            "  lab_bloco CHAR NULL," +
            "  lab_pavimento VARCHAR(20) NULL," +
            "  lab_desc VARCHAR(45) NULL," +
            "  lab_on INTEGER NULL," +
            "  PRIMARY KEY(lab_numero)" +
            ");";

    public static String CREATE_TABLE_USUARIO =

            "CREATE TABLE usuario (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  usuario_nome VARCHAR(45) NOT NULL," +
                    "  usuario_senha VARCHAR(20) NOT NULL," +
                    "  usuario_foto VARCHAR(255) NULL," +
                    "  usuario_tipo INTEGER NULL," +
                    "  usuario_email VARCHAR(255) NULL," +
                    "  PRIMARY KEY(usuario_cpf)" +
                    ");";


    public static String CREATE_TABLE_ADM =
            "CREATE TABLE Adm (" +
                    "  idAdm INTEGER  NOT NULL," +
                    "  nomeAdm VARCHAR(45) NULL," +
                    "  cpfAdm VARCHAR(20) NOT NULL," +
                    "  siapeAdm VARCHAR(20) NOT NULL," +
                    "  senha VARCHAR(20) NULL," +
                    "  PRIMARY KEY(idAdm)" +
                    ");";


    public static String CREATE_TABLE_SERVIDOR =

            "CREATE TABLE servidor (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  serv_siape VARCHAR(45) NOT NULL," +
                    "  serv_funcao VARCHAR(45) NULL," +
                    "  serv_pasep VARCHAR(20) NULL," +
                    "  PRIMARY KEY(usuario_cpf)," +
                    "  FOREIGN KEY(usuario_cpf)" +
                    "    REFERENCES usuario(usuario_cpf)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE NO ACTION" +
                    ");";


    public static String CREATE_TABLE_PROFESSOR =

            "CREATE TABLE professor (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  prof_siape VARCHAR(45) NOT NULL," +
                    "  prof_pasep VARCHAR(20) NULL," +
                    "  PRIMARY KEY(usuario_cpf)," +
                    "  FOREIGN KEY(usuario_cpf)" +
                    "    REFERENCES usuario(usuario_cpf)" +
                    "      ON DELETE NO ACTION" +
                    "      ON UPDATE NO ACTION" +
                    ");";


    public static String CREATE_TABLE_USUARIO_PERTENCE_LAB =

            "CREATE TABLE usuario_pertence_lab (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  lab_numero INTEGER UNSIGNED NOT NULL," +
                    "  PRIMARY KEY(usuario_cpf, lab_numero)," +
                    "  FOREIGN KEY(usuario_cpf)" +
                    "    REFERENCES usuario(usuario_cpf)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE NO ACTION," +
                    "  FOREIGN KEY(lab_numero)" +
                    "    REFERENCES lab(lab_numero)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE NO ACTION" +
                    ");";

/*    public static String CREATE_TABLE_USUARIO_HAS_LAB =

            "CREATE TABLE usuario_has_lab (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  lab_numero INTEGER UNSIGNED NOT NULL," +
                    "  data_entrada TIMESTAMP NOT NULL," +
                    "  usuario_saida VARCHAR(20) NULL," +
                    "  data_saida TIMESTAMP NULL," +
                    "  PRIMARY KEY(usuario_cpf, lab_numero, data_entrada)," +
                    "  FOREIGN KEY(usuario_cpf)" +
                    "    REFERENCES usuario(usuario_cpf)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE NO ACTION," +
                    "  FOREIGN KEY(lab_numero)" +
                    "    REFERENCES lab(lab_numero)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE NO ACTION" +
                    ");";*/


    public static String CREATE_TABLE_ALUNO =

            "CREATE TABLE aluno (" +
                    "  usuario_cpf VARCHAR(20) NOT NULL," +
                    "  lab_numero INTEGER   NOT NULL," +
                    "  aluno_matricula INTEGER   NOT NULL," +
                    "  aluno_curso VARCHAR(45) NOT NULL," +
                    "  aluno_ano_curso YEAR NULL," +
                    "  PRIMARY KEY(usuario_cpf)," +
                    "  FOREIGN KEY(usuario_cpf)" +
                    "    REFERENCES usuario(usuario_cpf)" +
                    "      ON DELETE CASCADE" +
                    "      ON UPDATE CASCADE," +
                    "  FOREIGN KEY(lab_numero)" +
                    "    REFERENCES lab(lab_numero)" +
                    "      ON DELETE NO ACTION" +
                    "      ON UPDATE NO ACTION" +
                    ");";



        public static String CREATE_CHECK_IN =
                "CREATE TABLE checkin (" +
                "  usuario_cpf VARCHAR(20) NOT NULL," +
                "  lab_numero INTEGER UNSIGNED NOT NULL," +
                "  data_check TIMESTAMP NOT NULL," +
                "  guarda_g_cpf VARCHAR(20) NOT NULL," +
                "  PRIMARY KEY(usuario_cpf, lab_numero, data_check)," +
                "  FOREIGN KEY(usuario_cpf)" +
                "    REFERENCES usuario(usuario_cpf)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION," +
                "  FOREIGN KEY(lab_numero)" +
                "    REFERENCES lab(lab_numero)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION," +
                "  FOREIGN KEY(guarda_g_cpf)" +
                "    REFERENCES guarda(g_cpf)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION" +
                ");";


        public static String CREATE_CHECK_OUT= "CREATE TABLE checkout (" +
                "  usuario_cpf VARCHAR(20) NOT NULL," +
                "  lab_numero INTEGER UNSIGNED NOT NULL," +
                "  data_check TIMESTAMP NOT NULL," +
                "  guarda_g_cpf VARCHAR(20) NOT NULL," +
                "  PRIMARY KEY(usuario_cpf, lab_numero, data_check)," +
                "  FOREIGN KEY(usuario_cpf)" +
                "    REFERENCES usuario(usuario_cpf)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION," +
                "  FOREIGN KEY(lab_numero)" +
                "    REFERENCES lab(lab_numero)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION," +
                "  FOREIGN KEY(guarda_g_cpf)" +
                "    REFERENCES guarda(g_cpf)" +
                "      ON DELETE NO ACTION" +
                "      ON UPDATE NO ACTION" +
                ");\n";



        public static String CREATE_GUARDA =

                "CREATE TABLE guarda (" +
                "  g_cpf VARCHAR(20) NOT NULL," +
                "  g_pasep VARCHAR(20) NOT NULL," +
                "  g_nome VARCHAR(255) NULL," +
                "  g_senha VARCHAR(45) NULL," +
                "  g_foto VARCHAR(255) NULL," +
                "  g_rg VARCHAR(20) NULL," +
                "  PRIMARY KEY(g_cpf)" +
                ");";


        //DROP SCRIPTS

        public static String DROP_USUARIO = "DROP TABLE IF EXISTS "+TAB_USUARIO+";";
        public static String DROP_ADM= "DROP TABLE IF EXISTS "+TAB_ADM+";";
        public static String DROP_PROFESSOR= "DROP TABLE IF EXISTS "+TAB_PROFESSOR+";";
        public static String DROP_ALUNO= "DROP TABLE IF EXISTS "+TAB_ALUNO+";";
        public static String DROP_SERVIDOR= "DROP TABLE IF EXISTS "+TAB_SERVIDOR+";";
        public static String DROP_USUARIO_HAS_LAB= "DROP TABLE IF EXISTS "+TAB_USUARIO_HAS_LAB+";";
        public static String DROP_USUARIO_PERTENCE_LAB="DROP TABLE IF EXISTS "+TAB_USUARIO_PERTENCE_LAB+";";
        public static String DROP_LAB= "DROP TABLE IF EXISTS "+TAB_LAB+";";
        public static String DROP_CHECK_IN = "DROP TABLE IF EXISTS "+TAB_CHECK_IN;
        public static String DROP_CHECK_OUT = "DROP TABLE IF EXISTS "+TAB_CHECK_OUT;
        public static String DROP_GUARDA = "DROP TABLE IF EXISTS "+TAB_GUARDA;




}
