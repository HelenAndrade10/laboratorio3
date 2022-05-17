window.onload = function() {
    if(window.location.search == '?error=produto%20ja%20existe'){
         alert('Saldo insuficiente. Transação cancelada')
     }
     var botaotransacaoProf = document.getElementById("botaotransacaoProf")
     var btnTransacaoProf = document.getElementById("btnModalTransacaoProf")
     btnTransacaoProf.addEventListener("click", function(event){

                      var codeRementente = botaotransacaoProf.getAttribute("value")
                      var codeDestinatario = document.querySelector("#inputGroupSelect04").value
                      var campoValor = document.querySelector("#valor").value

                      var formModalTransacao = document.getElementById("formTransacaoProf")
                      var btnHref = "/transacaoprof/criar/" + codeRementente + "/" + codeDestinatario + "/" + campoValor

                      formModalTransacao.setAttribute("action", btnHref)
                  })
}