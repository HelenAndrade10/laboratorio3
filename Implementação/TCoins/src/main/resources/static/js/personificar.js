window.onload = function() {
    var url_atual = window.location.pathname

    var formPesquisar = document.getElementById("formPesquisar")
        var returnHome = "returnBackHome"

        let text = "Hello world, welcome to the universe.";
        let result = text.includes("world");

        if(url_atual.includes("/admin/personificar/alunos")){
               var path = "/admin/personificar/alunos/pesquisar/"
            }else{
              var path = "/admin/personificar/professores/pesquisar/"
            }

        formPesquisar.setAttribute("action", path + returnHome)

    var inptPesq = document.getElementById("inputPesquisar")
        inptPesq.addEventListener("change", function(){
                var formPesquisar = document.getElementById("formPesquisar")
                var pattern = document.getElementById("inputPesquisar").value
                var inptHref = path + pattern
                formPesquisar.setAttribute("action", inptHref)
        })
}
