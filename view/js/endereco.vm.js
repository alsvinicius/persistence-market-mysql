function EnderecoViewModel() {
    var self = this;
    this.idCliente = ko.observable("");
    this.logradouro = ko.observable("");
    this.numero = ko.observable("");
    this.complemento = ko.observable("");
    this.cidade = ko.observable("");
    this.uf = ko.observable("");
    this.enderecoSalvo = ko.observable(false);
    this.enderecos = ko.observableArray();
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
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/enderecos/",
            crossDomain: true,
            contentType: "application/json",
            dataType: "json",
            data: ko.toJSON(self),
            success: function() {
                self.enderecoSalvo(true);
            }
        })
    }

    this.loadEnderecos = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/clientes/" + self.idCliente() + "/enderecos/",
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                self.enderecos(data);
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