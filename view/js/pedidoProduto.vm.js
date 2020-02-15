function PedidoProdutoViewModel() {
    var self = this;
    this.idProduto = ko.observable("");
    this.produtos = ko.observableArray();
    this.produtosPedido = ko.observableArray();
    this.produtoSalvo = ko.observable(false);

    var hash = window.location.hash.replace("#", "").split(",");

    this.idPedido = ko.observable(hash[0]);
    this.idCliente = ko.observable(hash[1]);

    this.salvar = function() {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/pedidos/" + self.idPedido() + "/produtos/" + self.idProduto(),
            crossDomain: true,
            contentType: "application/json",
            dataType: "json",
            data: ko.toJSON(self),
            success: function() {
                self.produtoSalvo(true);
            }
        })
    }

    this.loadProdutosPedido = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/pedidos/" + self.idPedido(),
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                self.produtosPedido(data["produtos"]);
            }
        })
    }

    this.loadProdutos = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/produtos",
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
        window.location.href="lista.html#" + self.idPedido() + "," + self.idCliente();
    }
}