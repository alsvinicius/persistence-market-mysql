function ProdutoViewModel() {
    var self = this;
    this.nome = ko.observable("");
    this.quantidade = ko.observable(0);
    this.valor = ko.observable(0.00);
    this.produtoSalvo = ko.observable(false);
    this.produtos = ko.observableArray();

    this.salvar = function() {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/produtos/",
            crossDomain: true,
            contentType: "application/json",
            dataType: "json",
            data: ko.toJSON(self),
            success: function() {
                self.produtoSalvo(true);
            }
        })
    }

    this.loadProdutos = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/produtos/",
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                self.produtos(data);
            }
        })
    }

    this.add = function() {
        window.location.href="adicionar.html";
    }

    this.list = function() {
        window.location.href="lista.html";
    }
}