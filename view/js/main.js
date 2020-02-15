var body = document.querySelector("body");
var menu = document.createElement("div")
menu.id = "menu"
menu.innerHTML = `
    <h1>Menu - MySQL</h1>
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mysql/view/cliente/lista.html">Clientes</a> |
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mysql/view/endereco/lista.html">Endere\u00e7os</a> |
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mysql/view/pedido/lista.html">Pedidos</a> |
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mysql/view/produto/lista.html">Produtos</a>
`
body.insertBefore(menu, body.firstChild)