window.onload = function() {
    if(window.location.search == '?error=produto%20ja%20existe'){
         alert('Saldo insuficiente. Transação cancelada')
     }

     var botaotransacao = document.getElementById("botaotransacao")
     var btnTransacao = document.getElementById("btnModalTransacao")
     btnTransacao.addEventListener("click", function(event){

                 var codeRementente = botaotransacao.getAttribute("value")
                 var codeDestinatario = document.querySelector("#inputGroupSelect04").value
                 var campoValor = document.querySelector("#valor").value

                 var formModalTransacao = document.getElementById("formTransacao")
                 var btnHref = "/transacao/criar/" + codeRementente + "/" + codeDestinatario + "/" + campoValor

                 formModalTransacao.setAttribute("action", btnHref)
             })
}