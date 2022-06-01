function httpReq(theUrl){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return JSON.parse(xmlHttp.responseText);
}

window.onload = function() {

        if(window.location.search == '?sucess=true'){
                    alert('Sucesso!. Transação concluída')
                    alert("Esta é sua Key: DCLAJNDAISOASSALNCA")
        }
        if(window.location.search == '?error=saldo%20insuficiente'){
                     alert('Saldo insuficiente. Transação cancelada')
        }


        var btnComprar = document.querySelectorAll("#btnComprar")


        for (let i = 0; i < btnComprar.length; i++) {
                    btnComprar[i].addEventListener("click", function(event){
                       var destinatarioList = document.querySelectorAll("#fornecedor")
                       var idVantagemList = document.querySelectorAll("#vantagemNome")
                       var href = btnComprar[i].getAttribute("href")
                       var destinatario_code = destinatarioList[i].getAttribute("value")
                       var idVantagem_code = idVantagemList[i].getAttribute("value")

//                        var destinatario = document.getElementById("fornecedor").getAttribute("value")
//                        var idVantagem = document.getElementById("vantagemNome").getAttribute("value")

                       href = href + "/" + destinatario_code + "/" + idVantagem_code
                       btnComprar[i].setAttribute("href", href)
//                       window.location.replace(httpReq(href))
                    })
        }
}