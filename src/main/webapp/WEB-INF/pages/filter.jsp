<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filtr obrazów</title>
    <script type="text/javascript">

        function PreviewImage() {
            var oFReader = new FileReader();
            oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreview").src = oFREvent.target.result;
            };
        }

        function updateTextInput1(val) {
            document.getElementById("textInput1").value = val;
        }

        function updateTextInput2(val) {
            document.getElementById("textInput2").value = val;
        }
        function updateTextInput3(val) {
            document.getElementById("textInput3").value = val;
        }


    </script>
</head>
<body>

<h2>Filtr obrazów</h2>
<form:form method="POST" action="/addFilter" enctype="multipart/form-data">
    <div style="display: block">

        <p>
        <h3>Filtry</h3>
        <form:checkbox path="brightness" value="brightness"/>Rozjaśnienie/Przyciemnienie
        <form:checkbox path="greyscale" value="greyscale"/>Skala szarości
        <form:checkbox path="threshold" value="threshold"/>Progowanie
        <form:checkbox path="blur" value="blur"/>Rozmycie
        <form:checkbox path="sharpen" value="sharpen"/>Wyostrzenie
        <form:checkbox path="emboss" value="emboss"/>Emboss
        <form:checkbox path="edge" value="edge"/>Wykrycie krawędzi
        <form:checkbox path="invert" value="invert"/>Odwrócenie kolorów
        </p>
        <p>


            Stopień rozjaśnienia/przyciemnienia:
            <form:input type="range" name="rangeInput" min="0" max="10" step="0.1" value="2"
                        onchange="updateTextInput1(this.value);" path="brightLevel"/>
            <input type="text" id="textInput1" value="2" maxlength="4" size="4" disabled></br>


            Poziom progowania:&nbsp;&nbsp;
            <form:input type="range" name="rangeInput" min="0" max="255" step="1" value="115"
                        onchange="updateTextInput3(this.value);" path="thresholdLevel"/>
            <input type="text" id="textInput3" value="115" maxlength="4" size="4" disabled></br>


        </p>
        <div class="image-block" style="max-width: 50%">
            <p>

                <img id="uploadPreview" src="<%=request.getContextPath()%>${image}"
                     style="max-width: 1500px;"/><form:input path="image" id="uploadImage" type="file" name="myPhoto"
                                                             onchange="PreviewImage();"/>
                </form>

            </p>

        </div>


        <input type="submit" value="Zastosuj filtry"/>
    </div>

</form:form>

</body>
</html>