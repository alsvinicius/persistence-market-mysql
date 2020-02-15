function PedidoViewModel() {
    var self = this;
    this.idPedido = ko.observable("");
    this.idCliente = ko.observable("");
    this.data = ko.observable("");
    this.valor = ko.observable(0);
    this.pedidoSalvo = ko.observable(false);
    this.pedidos = ko.observableArray();
    this.clientes = ko.observableArray();

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/clientes/",
        crossDomain: true,
        dataType: "json",
        success: function(data) {
            self.clientes(data);
        }
    })

    this.salvar = function() {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/pedidos/",
            crossDomain: true,
            contentType: "application/json",
            dataType: "json",
            data: ko.toJSON(self),
            success: function() {
                self.pedidoSalvo(true);
            }
        })
    }

    this.loadPedidos = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/pedidos/",
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                self.pedidos(data);
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