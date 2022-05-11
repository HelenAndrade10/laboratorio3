function httpReq(theUrl){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return JSON.parse(xmlHttp.responseText);
}
window.onload = function() {

    var btnAlterar = document.querySelectorAll(".btnAlterarParceiro")
    for (let i = 0; i < btnAlterar.length; i++) {
            btnAlterar[i].addEventListener("click", function(event){
                var codeParceiro = btnAlterar[i].getAttribute("value")

                var campoNome = document.querySelector("#editarParceiroNome")
                var campoCNPJ = document.querySelector("#editarParceiroCnpj")

                var formModalParceiro = document.getElementById("formEditarParceiro")

                var btnHref = "/parceiro/atualizar/" + codeParceiro

                formModalParceiro.setAttribute("action", btnHref)

                var origin = window.location.origin

                var searchURL = origin + "/parceiros/buscar/" + codeParceiro

                var produtoObj = httpReq(searchURL)

                campoNome.setAttribute("value", produtoObj.nome)
                campoCNPJ.setAttribute("value", produtoObj.cnpj)
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