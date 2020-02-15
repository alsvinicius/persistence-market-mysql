function ClienteViewModel() {
    var self = this;
    this.nome = ko.observable("");
    this.cpf = ko.observable("");
    this.clienteSalvo = ko.observable(false);
    this.clientes = ko.observableArray();

    this.salvar = function() {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/clientes/",
            crossDomain: true,
            contentType: "application/json",
            dataType: "json",
            data: ko.toJSON(self),
            success: function() {
                self.clienteSalvo(true);
            }
        })
    }

    this.loadClientes = function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/clientes/",
            crossDomain: true,
            dataType: "json",
            success: function(data) {
                self.clientes(data);
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