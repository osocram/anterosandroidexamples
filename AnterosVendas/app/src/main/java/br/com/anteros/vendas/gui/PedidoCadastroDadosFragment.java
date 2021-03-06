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

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import br.com.anteros.core.utils.DateUtil;
import br.com.anteros.vendas.R;
import br.com.anteros.vendas.gui.adapter.CondicaoPagamentoAdapter;
import br.com.anteros.vendas.modelo.CondicaoPagamento;
import br.com.anteros.vendas.modelo.FormaPagamento;


/**
 * @author Eduardo Greco (eduardogreco93@gmail.com)
 *         Eduardo Albertini (albertinieduardo@hotmail.com)
 *         Edson Martins (edsonmartins2005@gmail.com)
 *         Data: 12/05/16.
 */
public class PedidoCadastroDadosFragment extends Fragment implements View.OnClickListener {

    private EditText edNumero;
    private static EditText edValorTotal;
    public static EditText edData;
    private static EditText edCliente;
    public static Spinner spCondicaoPagamento;
    public static Spinner spFormaPagamento;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pedido_cadastro_dados, null);

        edNumero = (EditText) view.findViewById(R.id.pedido_cadastro_dados_nr_pedido);
        edData = (EditText) view.findViewById(R.id.pedido_cadastro_dados_data_pedido);
        edData.setOnClickListener(this);
        spCondicaoPagamento = (Spinner) view.findViewById(R.id.pedido_cadastro_dados_cb_condicao_pagamento);
        spFormaPagamento = (Spinner) view.findViewById(R.id.pedido_cadastro_dados__cb_tipo_formaPagamento);
        edCliente = (EditText) view.findViewById(R.id.pedido_cadastro_dados_cliente);
        edCliente.setOnClickListener(this);
        edValorTotal = (EditText) view.findViewById(R.id.pedido_cadastro_dados_valor);

        spCondicaoPagamento.setAdapter(new CondicaoPagamentoAdapter(getContext(), CondicaoPagamento.values()));
        spFormaPagamento.setAdapter(new ArrayAdapter<FormaPagamento>(getContext(), android.R.layout.simple_list_item_1, FormaPagamento.values()));
        try {
            bindView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void bindView() {
        edNumero.setText(PedidoConsultaActivity.pedido.getNrPedido().toString());
        edData.setText(DateUtil.toStringDateDMA(PedidoConsultaActivity.pedido.getDtPedido()));

        atualizarCliente();

        edValorTotal.setText(PedidoConsultaActivity.pedido.getVlTotalPedidoAsString());

        if (PedidoConsultaActivity.pedido.getCondicaoPagamento() != null) {
            spCondicaoPagamento.setSelection(PedidoConsultaActivity.pedido.getCondicaoPagamento().ordinal());
        }
        if (PedidoConsultaActivity.pedido.getFormaPagamento() != null) {
            spFormaPagamento.setSelection(PedidoConsultaActivity.pedido.getFormaPagamento().ordinal());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == edCliente) {
            ClienteConsultaDialog clienteConsultaDialog = new ClienteConsultaDialog();
            clienteConsultaDialog.show(getFragmentManager(), "clienteConsultaDialog");

        } else if (v == edData) {
            selecionarData();
        }
    }

    public static void atualizarCliente() {
        if (PedidoConsultaActivity.pedido.getCliente() != null) {
            edCliente.setText(PedidoConsultaActivity.pedido.getCliente().getId() + " - " + PedidoConsultaActivity.pedido.getCliente().getRazaoSocial());
        }
    }

    public static void atualizarValorTotal(){
        if (PedidoConsultaActivity.pedido.getVlTotalPedido() != null) {
            edValorTotal.setText(PedidoConsultaActivity.pedido.getVlTotalPedidoAsString());
        }
    }

    private void selecionarData() {
        Calendar cal = Calendar.getInstance();
        if (edData.getText().length() > 0)
            cal.setTime(DateUtil.stringToDate(edData.getText().toString(), DateUtil.DATE));
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edData.setText(dayOfMonth + "/" + (monthOfYear + 1)
                        + "/" + year);
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show();
    }
}
