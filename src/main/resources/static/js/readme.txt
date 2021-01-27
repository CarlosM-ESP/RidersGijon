usamos la librería w3.js para poder importar documentos html dentro de otro documento html para reutilizar cabeceras, tablas, etc...


w3.includeHTML()


<div w3-include-html="content.html"></div>

<script>
w3.includeHTML();
</script>