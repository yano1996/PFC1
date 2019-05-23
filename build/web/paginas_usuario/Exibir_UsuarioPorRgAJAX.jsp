<%-- 
    Document   : Exibir_UsuarioPorRgAJAX
    Created on : 20/05/2019, 00:44:59
    Author     : Victor_Aguiar
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <table class="responsive-table">
            <tr>
                <td class="center-align letra">Nome</td>  
                <td class="center-align letra">Idade</td>
                <td class="center-align letra">CPF</td>
                <td class="center-align letra">RG</td>
                <td class="center-align letra">Celular</td>
                <td class="center-align letra">Telefone</td>
                <td class="center-align letra">Tiposague</td>
                <td class="center-align letra">Peso</td>
                <td class="center-align letra">Altura</td>
                <td class="center-align letra">Nascimento</td>
                <td class="center-align letra">Opção</td>
            </tr>
            <c:forEach var="usuario" items="${lista}">
                <tr>

                <td class="center-align"><c:out value="${usuario.nome}"/></td>
                <td class="center-align"><c:out value="${usuario.idade}"/></td>
                <td class="center-align"><c:out value="${usuario.cpf}"/></td>
                <td class="center-align"><c:out value="${usuario.rg}"/></td>
                <td class="center-align"><c:out value="${usuario.telefone}"/></td>
                <td class="center-align"><c:out value="${usuario.celular}"/></td>
                <td class="center-align"><c:out value="${usuario.tiposague}"/></td>
                <td class="center-align"><c:out value="${usuario.peso}"/></td>
                <td class="center-align"><c:out value="${usuario.altura}"/></td>
                <td class="center-align"><c:out value="${usuario.nascimento}"/></td>


                <td class="center-align">
                    <form action="../ControleUsuario" method="POST">
                        <button class="btn waves-effect waves-teal red" type="submit" name="acao" value="Deletar"><i class="material-icons">delete</i></button>
                        <button class="btn waves-effect waves-teal blue" type="submit" name="acao" value="Editar"><i class="material-icons">edit</i></button>
                        <input type="text" name="id" value="${usuario.usuario_id}" hidden/>
                    </form>
                </td>

                </tr>
            </c:forEach>
        </table>

    </body>
</html>
