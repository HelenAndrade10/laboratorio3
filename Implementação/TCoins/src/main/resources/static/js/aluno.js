function httpReq(theUrl){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return JSON.parse(xmlHttp.responseText);
}
window.onload = function() {

    var btnAlterar = document.querySelectorAll(".btnAlterarAluno")
    for (let i = 0; i < btnAlterar.length; i++) {
            btnAlterar[i].addEventListener("click", function(event){
                var codeAluno = btnAlterar[i].getAttribute("value")

                var campoNome = document.querySelector("#AlterarAlunoNome")
                var campoCPF = document.querySelector("#AlterarAlunoCPF")
                var campoEmail = document.querySelector("#AlterarAlunoEmail")
                var campoEndereco = document.querySelector("#AlterarAlunoEndereco")
                var campoInstituicao = document.querySelector("#AlterarAlunoInstituicao")

                var formModalAluno = document.getElementById("formAlterarAluno")

                var btnHref = "/aluno/atualizar/" + codeAluno

                formModalAluno.setAttribute("action", btnHref)

                var origin = window.location.origin

                var searchURL = origin + "/alunos/buscar/" + codeAluno

                var produtoObj = httpReq(searchURL)

                campoNome.setAttribute("value", produtoObj.nome)
                campoCPF.setAttribute("value", produtoObj.email)
                campoEmail.setAttribute("value", produtoObj.cpf)
                campoEndereco.setAttribute("value", produtoObj.endereco)
                campoInstituicao.setAttribute("value", produtoObj.Instituicao.nome)

            })
        }

}





var btnAlterar = document.querySelectorAll(".btnAlterarParceiro")
    for (let i = 0; i < btnAlterar.length; i++) {
        btnAlterar[i].addEventListener("click", function(event){
            document.querySelector("#exampleModalLabel").innerText = "Alterar parceiro"
            document.querySelector("#btnModalCriar").innerText = "Alterar"

            var codeParceiro = btnAlterar[i].getAttribute("value")
            var campoNome = document.querySelector("#editarParceiroNome")
            var campoCNPJ = document.querySelector("#editarParceiroCnpj")

            var formModalParceiro = document.getElementById("formNovoParceiro")

            var btnHref = "/parceiro/atualizar/" + codeParceiro

            formModalParceiro.setAttribute("action", btnHref)

            var origin = window.location.origin

            var searchURL = origin + "/parceiros/buscar/" + codeParceiro

            var produtoObj = httpReq(searchURL)

            campoNome.setAttribute("value", produtoObj.nome)
            campoCNPJ.setAttribute("value", produtoObj.cnpj)
        })
    }