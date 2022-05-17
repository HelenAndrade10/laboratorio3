function httpReq(theUrl){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return JSON.parse(xmlHttp.responseText);
}
window.onload = function() {

    var btnAlterarProf = document.querySelectorAll(".btnAlterarProf")
    for (let i = 0; i < btnAlterarProf.length; i++) {
            btnAlterarProf[i].addEventListener("click", function(event){
                var codeProf = btnAlterarProf[i].getAttribute("value")

                var campoNome = document.querySelector("#AlterarProfNome")
                var campoCPF = document.querySelector("#AlterarProfCpf")
                var campoDepartamento = document.querySelector("#AlterarProfDpt")
                var campoInstituicao = document.querySelector("#AlterarProfInstituicao")

                var formModalProf = document.getElementById("formAlterarProf")

                var btnHref = "/professor/atualizar/" + codeProf

                formModalProf.setAttribute("action", btnHref)

                var origin = window.location.origin

                var searchURL = origin + "/professores/buscar/" + codeProf

                var produtoObj = httpReq(searchURL)

                campoNome.setAttribute("value", produtoObj.nome)
                campoCPF.setAttribute("value", produtoObj.cpf)
                campoDepartamento.setAttribute("value", produtoObj.departamento)
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