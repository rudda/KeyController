package com.developer.ruddbeltrao.keycontroller.mRecycleView;

import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;

/**
 * Criado por Rudda Beltrao em 22/07/2015 as 15:35.
 */
public interface ClickToItem {

    public void OnClickView(Aluno aluno);
    public void OnClickView(Professor aluno);
    public void OnClickView(Servidor servidor);

}
