var body = document.querySelector("body");
var menu = document.createElement("div")
menu.id = "menu"
menu.innerHTML = `
    <h1>Menu</h1>
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mongo/view/cliente/lista.html">Clientes</a> |
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mongo/view/endereco/lista.html">Endere\u00e7os</a> | 
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mongo/view/pedido/lista.html">Pedidos</a> | 
    <a href="file:///C:/Users/lowvi/IdeaProjects/mba/persistence/produtos-mongo/view/produto/lista.html">Produtos</a>
`
body.insertBefore(menu, body.firstChild)