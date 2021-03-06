/*
 * Copyright 2016 Anteros Tecnologia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.anteros.vendas.gui;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.anteros.validation.api.ConstraintViolation;
import br.com.anteros.vendas.R;

/**
 * Dialog usada para apresentar os erros de validação.
 *
 * @author Eduardo Greco (eduardogreco93@gmail.com)
 *         Eduardo Albertini (albertinieduardo@hotmail.com)
 *         Edson Martins (edsonmartins2005@gmail.com)
 *         Data: 12/05/16.
 */
public class MensagemErrorDialog<T> extends Dialog implements View.OnClickListener {

    private Button btnOK;
    private Button next;
    private Button previous;
    private TextView edErro;
    private int listPosition;
    private List<ConstraintViolation<T>> messageViolations;

    public MensagemErrorDialog(Context context, String title, Set<ConstraintViolation<T>> violations) {
        super(context);
        setContentView(R.layout.dialog_error_alert);
        setTitle(title);

        listPosition = 0;

        btnOK = (Button) findViewById(R.id.error_alert_dialog_botaoOk);
        next = (Button) findViewById(R.id.error_alert_dialog_botaoProximo);
        previous = (Button) findViewById(R.id.error_alert_dialog_botaoAnterior);
        edErro = (TextView) findViewById(R.id.error_alert_dialog_textDialog);

        messageViolations = new ArrayList<ConstraintViolation<T>>(violations);
        edErro.setText(messageViolations.get(listPosition).getMessage());
        templateControl();

        btnOK.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            dismiss();
        } else if (v == next) {
            if (listPosition < (messageViolations.size() - 1)) {
                listPosition = listPosition + 1;
                edErro.setText(messageViolations.get(listPosition).getMessage());
                templateControl();
            }
        } else if (v == previous) {
            if (listPosition > 0) {
                listPosition = listPosition - 1;
                edErro.setText(messageViolations.get(listPosition).getMessage());
                templateControl();
            }
        }

    }

    private void templateControl() {
        if (listPosition < (messageViolations.size() - 1))
            next.setEnabled(true);
        else
            next.setEnabled(false);

        if (listPosition > 0)
            previous.setEnabled(true);
        else
            previous.setEnabled(false);

    }

}
