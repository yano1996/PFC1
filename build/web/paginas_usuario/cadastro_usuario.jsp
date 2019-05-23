<%-- 
    Document   : cadastro_usuario
    Created on : 01/11/2018, 20:33:51
    Author     : nelson_amaral
--%>

<%@page import="model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="/paginas_utilitarias/cabecalho_02.jsp" />

<%

    //recupera o usuario da sessao
    Funcionario funcionario = (Funcionario) session.getAttribute("usuarioAutenticado");
    if (funcionario != null) {
        System.out.println("id " + funcionario.getId_funcionario());


%>
<%    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
%><font color="red"><%=msg%></font>
<%}%>
<!DOCTYPE html>
<main>
    <div class="container">
        <div class="row">
            <div class="col s2">
            </div>
            <div class="col s8">            
                <div class="card z-depth-2">
                    <div class="card-content">
                        <span class="card-title titulo-tabela letra center-align">Cadastro Paciente</span>
                        <form action="../ControleUsuario" method="POST" ><br/>
                            <input name="id_funcionrio" value="<%=funcionario.getId_funcionario()%>" hidden>
                            <% }%>
                            <div class="row">
                                <div class="col s7 input-field hoverable">
                                    <input type="text" name="txtNome" id="txtNome" class="hoverable validate" placeholder="Máximo 50 caracteres" size="50" maxlength=50 required>
                                    <label class="black-text" for="txtNome">Nome<i class="material-icons left">account_box</i></label>                                   
                                </div>

                                <div class="col s2 input-field hoverable">
                                    <input type="text" name="txtIdade" id="txtIdade" onkeyup="somenteNumeros(this)" onchange="verificarIdade()" pattern="[0-9]+$" class="hoverable validate" placeholder="000" size="3" maxlength=3 required>
                                    <label class="black-text" for="txtIdade">Idade<i class="material-icons left">account_box</i></label>                                   
                                </div>

                                <div id="id_responsavel" class="col s3 input-field hoverable">
                                    <input type="text" name="txtRgResponsavel" id="txtRgResponsavel" class="hoverable validate" placeholder="00.000.000-00" size="10" maxlength=10>
                                    <label class="black-text" for="txtRgResponsavel">Rg Responsavel<i class="material-icons left">account_box</i></label>                                   
                                </div>

                            </div>
                            <div class="row">
                                <div class="col s4 input-field hoverable">
                                    <input type="text" name="txtCpf" id="txtCpf" class="hoverable validate cpfMaskara" placeholder="000.000.000-00" size="14" maxlength=14 required>
                                    <label class="black-text" for="txtCpf">Cpf<i class="material-icons left">account_box</i></label>                                   
                                </div>

                                <div class="col s4 input-field hoverable">
                                    <input type="text" name="txtRg" id="txtRg" class="hoverable validate" placeholder="00.000.000-00" size="10" maxlength=10 required>
                                    <label class="black-text" for="txtRg">RG<i class="material-icons left">account_box</i></label>                                   
                                </div>

                                <div class="col s2 input-field hoverable">
                                    <input type="text" name="txtPeso"  id="txtPeso" class="hoverable validate mascarapeso" placeholder="0000" size="5" maxlength=5>
                                    <label class="black-text" for="txtPeso">Peso<i class="material-icons left">account_box</i></label>                                   
                                </div>    

                                <div class="col s2 input-field hoverable">
                                    <input type="text" name="txtAtura" id="txtAtura" pattern="[-+]?[0-9]*\.?[0-9]*" class="hoverable validate alturamaskara" placeholder="0.00" size="3" maxlength=3>
                                    <label class="black-text" for="txtAtura">Altura<i class="material-icons left">account_box</i></label>                                   
                                </div>                             
                            </div>

                            <div class="row">
                                <div class="col s3 input-field hoverable">
                                    <input type="text" name="txtTelefone" placeholder="(00) 0000-0000" class="hoverable validate telefone" required>
                                    <label class="black-text" for="txtTelefone">Telefone<i class="material-icons left">phone</i></label>                                   
                                </div>
                                <div class="col s3 input-field hoverable">
                                    <input type="text" name="txtCelular" placeholder="(00) 0000-0000" class="celular hoverable validate" required>
                                    <label class="black-text" for="txtCelular">Celular<i class="material-icons left">phone</i></label>                                   
                                </div>

                                <div class='col s3'>
                                    <label class="black-text left-align" for="txtTiposague">Tipo Sanguineo<i class="material-icons left">assignment</i></label>
                                    <select class="browser-default" name="txtTiposague">
                                        <option value="" disabled selected>Selecione...</option>
                                        <option value="AB+">AB+</option>
                                        <option value="AB-">AB-</option>
                                        <option value="A+">A+</option>
                                        <option value="A-">A-</option>
                                        <option value="O+">O+</option>
                                        <option value="O-">O-</option>
                                        <option value="B+">B+</option>
                                        <option value="B-">B-</option>
                                    </select>
                                </div>

                                <div class="col s3">
                                    <label class="black-text left" for="txtDate">Data Nascimento<i class="material-icons left">perm_contact_calendar</i></label>                                                   
                                    <br>
                                    <input type="Date" name="txtDate" id="dataUsuario" class="hoverable validate" required>
                                </div>

                                <div class="col s6 input-field hoverable">
                                    <input type="email" name="txtEmail" id="txtemail" class="hoverable validate" placeholder="Máximo 50 caracteres" size="50" maxlength=50 required>
                                    <label class="black-text" for="txtNome">Email<i class="material-icons left">account_box</i></label>                                   
                                </div>
                                <div class="col s6 input-field hoverable">
                                    <input type="email" id="txtemailconfirma" onchange="confirmaEmail()" class="hoverable validate" placeholder="Máximo 50 caracteres" size="50" maxlength=50 required>
                                    <label class="black-text" for="txtNome">Confirmar Email<i class="material-icons left">account_box</i></label>                                   
                                </div>

                            </div>

                            <div class="row">
                                <h4 class="center-align"><i>Endereço</i></h4>
                            </div>

                            <div class="row">
                                <div class="col s12">
                                    <div class="card z-depth-2">
                                        <div class="card-content">
                                            <div class="row">
                                                <div class="col s10 input-field hoverable">
                                                    <input type="text" name="txtLog" id="txtLog" class="hoverable validate" placeholder="Máximo 50 caracteres" size="50" maxlength=50 required>
                                                    <label class="black-text" for="txtLog">Logradouro<i class="material-icons left">hotel</i></label>                                   
                                                </div>

                                                <div class="col s2 input-field hoverable">
                                                    <input type="text" name="txtNumero" onkeyup="somenteNumeros(this)" class="hoverable validate" placeholder="000" size="8" maxlength=8 required>
                                                    <label class="black-text" for="txtNumero">Numero<i class="material-icons left"></i></label>                                   
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col s4 input-field hoverable">
                                                    <input type="text" name="txtCidade" id="txtCidade" class="hoverable validate" placeholder="Máximo 30 caracteres" size="30" maxlength=30 required>
                                                    <label class="black-text" for="txtCidade">Cidade<i class="material-icons left">assignment</i></label>                                   
                                                </div>


                                                <div class="col s5 input-field hoverable">
                                                    <input type="text" name="txtBairro" id="txtBairro" class="hoverable validate" placeholder="Máximo 30 caracteres" size="30" maxlength=30 required>
                                                    <label class="black-text" for="txtBairro">Bairro<i class="material-icons left">assignment</i></label>                                   
                                                </div>

                                                <div class="col s3 input-field hoverable">
                                                    <input type="text" name="txtCep" onkeyup="somenteNumeros(this)" class="hoverable validate" placeholder="00000-000" size="12" maxlength=12 required>
                                                    <label class="black-text" for="txtCep">Cep<i class="material-icons left">assignment</i></label>                                   
                                                </div>
                                            </div>


                                            <div class="row">

                                                <div class="col s9 input-field hoverable">
                                                    <input type="text" name="txtComplemento" id="txtComplemento" class="hoverable validate" placeholder="Máximo 54 caracteres" size="54" maxlength=54 required>
                                                    <label class="black-text" for="txtComplemento">Complemento<i class="material-icons left">assignment</i></label>                                   
                                                </div>

                                                <div class='col s3'>
                                                    <label class="black-text left-align" for="txtUf">UF<i class="material-icons left">assignment</i></label>
                                                    <select class="browser-default" name="txtUf" required>
                                                        <option value="" disabled selected>Selecione...</option>
                                                        <option value="AC">AC</option>
                                                        <option value="AL">AL</option>
                                                        <option value="AP">AP</option>
                                                        <option value="AM">AM</option>
                                                        <option value="BA">BA</option>
                                                        <option value="CE">CE</option>
                                                        <option value="DF">DF</option>
                                                        <option value="ES">ES</option>
                                                        <option value="GO">GO</option>
                                                        <option value="MA">MA</option>
                                                        <option value="MT">MT</option>
                                                        <option value="MS">MS</option>
                                                        <option value="MG">MG</option>
                                                        <option value="PA">PA</option>
                                                        <option value="PB">PB</option>
                                                        <option value="PR">PR</option>
                                                        <option value="PE">PE</option>
                                                        <option value="PI">PI</option>
                                                        <option value="RJ">RJ</option>
                                                        <option value="RN">RN</option>
                                                        <option value="RO">RO</option>
                                                        <option value="RR">RR</option>
                                                        <option value="SC">SC</option>
                                                        <option value="SP">SP</option>
                                                        <option value="SE">SE</option>
                                                        <option value="TO">TO</option>                                                                                                                       
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s12">
                                    <div class="card z-depth-2">
                                        <div class="card-content">
                                            <div class="col s6 input-field hoverable">
                                                <input type="password" name="txtSenha" id="txtSenha" onchange="verifica()" class="hoverable validate" placeholder="Senha" size="30" maxlength=30 required>
                                                <label class="black-text" for="txtSenha">Digite a senha<i class="material-icons left">vpn_key</i></label>                                   
                                            </div>

                                            <div class="col s6 input-field hoverable">
                                                <input type="password" name="txtSenha" id="txtSenhaNovamente" onchange="confirmaSenha()" class="hoverable validate" placeholder="Senha" size="30" maxlength=30 required>
                                                <label class="black-text" for="txtSenha">Digite novamente a senha<i class="material-icons left">vpn_key</i></label>                                   
                                            </div>
                                            <div class='col s6 left'>
                                                <a id="mostraresultadosenha"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row center-align">
                                <input class="btn waves-effect center-align" type="submit" value="Cadastrar" name="acao" >
                            </div>
                        </form>   
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>

<script>


    jQuery.fn.extend({maskWeight: function (t) {
            window._maskData = {selector: $(this), arr: [], insertCount: 0, numberPressed: !1, options: {}, defOptions: {integerDigits: 3, decimalDigits: 3, decimalMark: ".", initVal: "", roundingZeros: !0, digitsCount: 6, callBack: null, doFocus: !0}, initializeOptions: function (t) {
                    if (this.options = $.extend(!0, this.defOptions), this.arr = [], this.insertCount = 0, this.numberPressed = !1, t)
                        for (var i in t)
                            void 0 !== t[i] && null !== t[i] && (this.options[i] = t[i]);
                    0 == this.options.decimalDigits && (this.options.decimalMark = "");
                    var s = !1;
                    if ("" == this.options.initVal) {
                        if (this.options.roundingZeros)
                            this.options.initVal += "0";
                        else
                            for (var n = 0; n < this.options.integerDigits; n++)
                                this.options.initVal += "0";
                        this.options.initVal += this.options.decimalMark;
                        for (var n = 0; n < this.options.decimalDigits; n++)
                            this.options.initVal += "0"
                    } else
                        s = !0;
                    this.options.digitsCount = this.options.integerDigits + this.options.decimalDigits, this.arr = [];
                    for (var n = 0; n < this.options.digitsCount; n++)
                        this.arr.push("0");
                    s && parseInt(this.options.initVal) > 0 && this.createInitialValueArr()
                }, createInitialValueArr: function () {
                    this.options.initVal = 0 == this.options.decimalDigits ? parseInt(this.options.initVal) : parseFloat(this.options.initVal.toString().replace(",", ".")).toFixed(this.options.decimalDigits).replace(".", this.options.decimalMark);
                    for (var t = this.options.initVal.toString().replace(".", "").replace(",", "").split(""), i = 0; i < t.length; i++)
                        this.insert(t[i])
                }, insert: function (t) {
                    var i = this.mask(t);
                    this.selector.val(i), this.setCartetOnEnd()
                }, mask: function (t) {
                    "backspace" == t ? this.insertCount > 0 && (this.arr.pop(), this.arr.unshift("0"), this.insertCount--) : this.insertCount < this.options.digitsCount && (this.arr.shift(), this.arr.push(t.toString()), this.insertCount++);
                    for (var i = "", s = 0; s < this.arr.length; s++)
                        i += this.arr[s];
                    return i = this.reduce(i)
                }, reduce: function (t) {
                    return 0 == this.options.decimalDigits ? this.options.roundingZeros ? parseInt(t) : t : this.options.roundingZeros ? parseInt(t.substring(0, this.options.integerDigits)) + this.options.decimalMark + t.substring(this.options.integerDigits, this.options.digitsCount) : t.substring(0, this.options.integerDigits) + this.options.decimalMark + t.substring(this.options.integerDigits, this.options.digitsCount)
                }, getNumber: function (t) {
                    return String.fromCharCode(t.keyCode || t.which)
                }, setCartetOnEnd: function () {
                    var t = this;
                    setTimeout(function () {
                        var i = t.selector.val().length;
                        t.options.doFocus && t.selector[0].focus(), t.selector[0].setSelectionRange(i, i)
                    }, 1)
                }, isNumberOrBackspace: function (t) {
                    return"backspace" == t ? !0 : parseInt(t) || 0 == parseInt(t) ? !0 : !1
                }, init: function () {
                    var t = this;
                    this.selector.val(this.options.initVal), this.selector.on("click", function (i) {
                        t.setCartetOnEnd()
                    });
                    var i = navigator.userAgent.toLowerCase(), s = i.indexOf("android") > -1;
                    s ? (window._maskDataLastVal = this.selector.val(), this.selector[0].removeEventListener("input", window._maskDataAndroidMaskHandler, !0), setTimeout(function () {
                        window._maskDataAndroidMaskHandler = function (i) {
                            if (i.preventDefault(), i.stopPropagation(), t.selector.val().length < window._maskDataLastVal.length)
                                t.insert("backspace");
                            else {
                                var s = t.selector.val().charAt(t.selector.val().length - 1);
                                0 == parseFloat(t.selector.val().replace(",", ".")) && 0 == parseInt(s) ? t.insert("backspace") : t.insert(s)
                            }
                            return window._maskDataLastVal = t.selector.val(), t.options.callBack && t.options.callBack(), !1
                        }, t.selector[0].addEventListener("input", window._maskDataAndroidMaskHandler, !0)
                    }, 0)) : (this.selector.on("keydown", function (i) {
                        var s = i.keyCode || i.which;
                        (8 == s || 46 == s) && (i.preventDefault(), i.stopPropagation(), t.insert("backspace")), t.options.callBack && t.options.callBack()
                    }), this.selector.on("keypress", function (i) {
                        i.keyCode || i.which;
                        i.preventDefault(), i.stopPropagation();
                        var s = t.getNumber(i);
                        t.isNumberOrBackspace(s) && (0 == parseFloat(t.selector.val().replace(",", ".")) && 0 == parseInt(s) ? t.insert("backspace") : t.insert(s)), t.options.callBack && t.options.callBack()
                    }))
                }}, window._maskData.initializeOptions(t), window._maskData.init()
        }, removeMask: function () {
            window._maskData && ($(this).unbind(), window._maskData = null)
        }});
    $('.mascarapeso').maskWeight({
        integerDigits: 3,
        decimalDigits: 2,
        decimalMark: '.'
    });
</script>

<script>

    function somenteNumeros(num) {
        var er = /[^0-9.]/;
        er.lastIndex = 0;
        var campo = num;
        if (er.test(campo.value)) {
            campo.value = "";
        }
    }

    //Função para verificar senhas digitas  iguais
    function confirmaEmail() {

        var email = document.getElementById("txtemail").value;
        var emailnovamente = document.getElementById("txtemailconfirma").value;

        //verifica os campos senhas são iguais
        if (email != emailnovamente) {

            alert('Atenção e-mails digitados não são iguais!');

            //limpar inputs de senha para a nova digitação
            document.getElementById("txtemail").value = '';
            document.getElementById("txtemailconfirma").value = '';
        } else {
            //comando para seguir com a ação atual
            event.preventDefault();
        }
        ;
    }
    ;

    //Função para verificar senhas digitas  iguais
    function confirmaSenha() {

        var Senha = document.getElementById("txtSenha").value;
        var Senhanovamente = document.getElementById("txtSenhaNovamente").value;

        //verifica os campos senhas são iguais
        if (Senha != Senhanovamente) {

            alert('Atenção senhas digitas não são iguais!');

            //limpar inputs de senha para a nova digitação
            document.getElementById("txtSenha").value = '';
            document.getElementById("txtSenhaNovamente").value = '';
        } else {
            //comando para seguir com a ação atual
            event.preventDefault();
        }
        ;
    }
    ;

    function verifica() {
        senha = document.getElementById("txtSenha").value;
        forca = 0;
        mostra = document.getElementById("mostraresultadosenha");
        if ((senha.length >= 4) && (senha.length <= 7)) {
            forca += 10;
        } else if (senha.length > 7) {
            forca += 25;
        }
        if (senha.match(/[a-z]+/)) {
            forca += 10;
        }
        if (senha.match(/[A-Z]+/)) {
            forca += 20;
        }
        if (senha.match(/d+/)) {
            forca += 20;
        }
        if (senha.match(/W+/)) {
            forca += 25;
        }
        return mostra_res();
    }
    function mostra_res() {
        if (forca < 30) {
            mostra.innerHTML = '<tr><td class="letra123"><i class="material-icons red-text">lock_open</i> Fraca</td></tr>';
            //limpar inputs de senha para a nova digitação
            document.getElementById("txtSenha").value = '';
        } else if ((forca >= 30) && (forca < 45)) {
            mostra.innerHTML = '<tr><td><i class="material-icons yellow-text">lock_outline</i> Justa</td></td></tr>';
            ;
        } else if ((forca >= 45) && (forca < 85)) {
            mostra.innerHTML = '<tr><td><i class="material-icons blue-text">lock</i>Forte</td></tr>';
        } else {
            mostra.innerHTML = '<tr><td>Excelente </td></tr>';
        }
    }
</script>

<script>

    function verificarIdade() {
        var display = document.getElementById("id_responsavel").style.display;
        var idadeusuario = document.getElementById("txtIdade").value;

        if (idadeusuario < 18) {
            document.getElementById("id_responsavel").style.display = 'block';
        } else {
            document.getElementById("id_responsavel").style.display = 'none';

        }
    }

</script>


<!-- IMPORTANDO RODAPE DA PAGINA -->
<jsp:include page="/paginas_utilitarias/rodape_02.jsp" />

</html>